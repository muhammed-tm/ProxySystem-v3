package eu.hypetime.proxy.bots.dc.listener;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OwnChannel extends ListenerAdapter {

     @Override
     public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
          if (event.getChannelJoined().getId().equalsIgnoreCase("832711758061502524")) {
               Member member = event.getMember();
               event.getGuild().createVoiceChannel(member.getEffectiveName(), event.getGuild().getCategoryById("693213387634638891")).queue();
          }
          if (event.getChannelLeft().getName().equalsIgnoreCase(event.getMember().getEffectiveName())) {
               event.getChannelLeft().delete().queue();
          }
     }

     @Override
     public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
          if (event.getChannelJoined().getId().equalsIgnoreCase("832711758061502524")) {
               Member member = event.getMember();
               event.getGuild().createVoiceChannel(member.getEffectiveName(), event.getGuild().getCategoryById("693213387634638891")).queue();
          }
     }

     @Override
     public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
          if (event.getChannelLeft().getName().equalsIgnoreCase(event.getMember().getEffectiveName())) {
               event.getChannelLeft().delete().queue();
          }
     }

     @Override
     public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {
          Member member = event.getGuild().getMembersByEffectiveName(event.getChannel().getName(), true).get(0);
          event.getGuild().moveVoiceMember(member, event.getChannel()).queue();
          event.getGuild().getMembers().forEach(all -> {
               event.getChannel().upsertPermissionOverride(all).setDeny(Permission.VIEW_CHANNEL).queue();
          });
          event.getChannel().upsertPermissionOverride(member)
               .setAllow(Permission.VIEW_CHANNEL).setAllow(Permission.KICK_MEMBERS).queue();
     }
}
