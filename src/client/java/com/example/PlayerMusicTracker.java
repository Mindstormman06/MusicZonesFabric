package com.example;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.UUID;

public class PlayerMusicTracker {

    private static final HashMap<UUID, MusicZone> playerZones = new HashMap<>();

    public static void register() {
        ServerTickEvents.END_WORLD_TICK.register(server -> {

            MinecraftServer minecraftServer = server.getServer()
;
            for (ServerPlayerEntity player : minecraftServer.getPlayerManager().getPlayerList()) {
                BlockPos playerPos = player.getBlockPos();
                UUID playerId = player.getUuid();
                boolean isInside = false;

                for (MusicZone zone : MusicZoneManager.getZones()) {
                    if (zone.isInside(playerPos)) {
                        isInside = true;
                        if(!playerZones.containsKey(playerId)) {
                            playerZones.put(playerId, zone);
                            playMusic(player, zone.musicPath);
                        }
                        break;
                    }
                }

                if(!isInside && playerZones.containsKey(playerId)) {
                    stopMusic(player);
                    playerZones.remove(playerId);
                }
            }

        });
    }

    private static void playMusic(ServerPlayerEntity player, String musicPath) {
        player.sendMessage(Text.of("Now Player: " + musicPath), false);
        MusicPlayer.play(player, musicPath);
    }

    private static void stopMusic(ServerPlayerEntity player) {
        player.sendMessage(Text.of("Music Stopped"), false);
        MusicPlayer.stop(player.getUuid());
    }

}
