package com.mindstormman.musiczones;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class MusicPlayer {

    private static final HashMap<UUID, PositionedSoundInstance> activeSounds = new HashMap<>();

    public static void play(ServerPlayerEntity player, String musicPath) {
        Identifier soundId = Identifier.of("music_zones:" + musicPath);

        PositionedSoundInstance sound = PositionedSoundInstance.ambient(new SoundEvent(soundId, Optional.empty()));


        activeSounds.put(player.getUuid(), sound);
        MinecraftClient.getInstance().getSoundManager().play(sound);
    }

    public static void stop(UUID playerId) {
        if (activeSounds.containsKey(playerId)) {
            MinecraftClient.getInstance().getSoundManager().stop(activeSounds.get(playerId));
            activeSounds.remove(playerId);
        }
    }
}
