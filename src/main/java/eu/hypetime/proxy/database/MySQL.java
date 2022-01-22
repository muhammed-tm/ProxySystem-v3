/*
 *  Created by quele | Muhammed
 *  Copyright (C) all rights reserved.
 *  Website: http://quele.live
 */

package eu.hypetime.proxy.database;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

     public String host, username, password, database;
     private Connection connection;

     public MySQL(String host, String username, String password, String database) {
          this.host = host;
          this.username = username;
          this.password = password;
          this.database = database;

          try {
               connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?useSSL=false", username, password);
          } catch (SQLException exception) {
               exception.printStackTrace();
          }

          //createTables();
     }

     public void createTables() {
          System.out.println("MySQL CREATE tables");
          update("CREATE TABLE IF NOT EXISTS proxy_BannedPlayers (Spielername VARCHAR(100), UUID VARCHAR(100), IP VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100), Banner VARCHAR(100))");
          update("CREATE TABLE IF NOT EXISTS proxy_MutedPlayers (Spielername VARCHAR(100), UUID VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100), MUTER VARCHAR(100))");
          update("CREATE TABLE IF NOT EXISTS proxy_Report (Spielername VARCHAR(100), UUID VARCHAR(100), Reports INT(100))");
          update("CREATE TABLE IF NOT EXISTS proxy_Reports (Reporter VARCHAR(100), Reported VARCHAR(100), Reason VARCHAR(100))");
          update("CREATE TABLE IF NOT EXISTS proxy_PlayerData (UUID VARCHAR(100), DSGVO VARCHAR(100), Language INT(11) DEFAULT 0)");
          update("CREATE TABLE IF NOT EXISTS proxy_Rewards (UUID VARCHAR(100), NameMC VARCHAR(100))");
          update("CREATE TABLE IF NOT EXISTS proxy_BetaSystem (betakey VARCHAR(100), uuid VARCHAR(100))");
          update("CREATE TABLE IF NOT EXISTS proxy_FriendSystem (uuid VARCHAR(100), name VARCHAR(100), playerFriend VARCHAR(100), requests VARCHAR(100), maxFriends VARCHAR(100))");
          update("CREATE TABLE IF NOT EXISTS clansystem_PLAYERCLAN (PLAYER VARCHAR(64) UNIQUE, UUID VARCHAR(64), CLAN VARCHAR(64), ENTERED VARCHAR(64), REQUESTS VARCHAR(3700));");
          update("CREATE TABLE IF NOT EXISTS clansystem_CLAN (LEADERNAME VARCHAR(64) UNIQUE, CLANTAG VARCHAR(64), CLANNAME VARCHAR(64), MODS VARCHAR(1850), MEMBER VARCHAR(3700));");

     }

     public ResultSet getResult(String qry) {
          try {
               if (connection.isClosed()) {
                    connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?useSSL=false", username, password);
               }
               return connection.prepareStatement(qry).executeQuery();
          } catch (SQLException exception) {
               return null;
          }
     }

     public void update(String qry) {
          try {
               if (connection.isClosed()) {
                    connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?useSSL=false", username, password);
               }
               connection.prepareStatement(qry).executeUpdate();
          } catch (SQLException exception) {
               exception.printStackTrace();
          }
     }

     public Object get(String select, String database, String what) {

          ResultSet rs = getResult("SELECT " + select + " FROM " + database + "");
          try {
               assert rs != null;
               if (rs.next()) {
                    return rs.getObject(what);
               }
          } catch (SQLException e) {
               return "ERROR";
          }

          ProxyServer.getInstance().getConsole().sendMessage(TextComponent.fromLegacyText("§cCould not load Data."));
          return "ERROR";
     }

     public Object get(String select, String database, String where, String whereresult) {

          ResultSet rs = getResult("SELECT " + select + " FROM " + database + " WHERE " + where + "='" + whereresult + "'");
          try {
               assert rs != null;
               if (rs.next()) {
                    return rs.getObject(select);
               }
          } catch (SQLException e) {
               return "ERROR";
          }

          ProxyServer.getInstance().getConsole().sendMessage(TextComponent.fromLegacyText("§cCould not load Data."));
          return "ERROR";
     }

}
