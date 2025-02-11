package com.mindstormman.musiczones;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Networking {
    // Correctly create the Identifier
    public static final Identifier OPEN_MUSIC_GUI = Identifier.of("music_zones", "open_music_gui");

    public static void registerServerReceivers() {
        // Register the server-side packet receiver
        ServerPlayNetworking.registerGlobalReceiver(OPEN_MUSIC_GUI, (server, player, handler, buf, responseSender) -> {
            // Ensure player is an instance of ServerPlayerEntity
            if (player instanceof ServerPlayerEntity serverPlayer) {
                // Execute on the server thread
                MinecraftServer.execute(() -> {
                    // Send a message to the player
                    serverPlayer.sendMessage(Text.literal("Trying to open GUI..."), false);
                    // Additional logic to open the GUI should be implemented here
                });
            }
        });
    }

    public static void sendOpenGuiPacket() {
        // Create a new PacketByteBuf
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        // Send the packet to the server
        ClientPlayNetworking.send(OPEN_MUSIC_GUI, buf);
    }
}
