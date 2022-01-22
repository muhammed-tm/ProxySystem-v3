package eu.hypetime.proxy.bots.dc.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;

public class PlayerSendHandler implements AudioSendHandler {

    private final AudioPlayer audioPlayer;
    private AudioFrame lastFrame;

    public PlayerSendHandler(AudioPlayer player) {
        this.audioPlayer = player;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public AudioFrame getLastFrame() {
        return lastFrame;
    }

    @Override
    public boolean canProvide() {
        return false;
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        return null;
    }

    @Override
    public boolean isOpus() {
        return AudioSendHandler.super.isOpus();
    }
}
