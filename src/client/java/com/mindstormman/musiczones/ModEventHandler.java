package com.mindstormman.musiczones;

import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class ModEventHandler {
    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitresult) -> {
            if(!world.isClient && player.getMainHandStack().isOf(Items.STICK)) {
                BlockPos pos = hitresult.getBlockPos();
                boolean first = player.isSneaking();

                SelectionHandler.setSelection((ServerPlayerEntity) player, pos, first);
                player.sendMessage(Text.of("Set " + (first ? "first" : "second") + " selection at " + pos), false);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, enviroment) -> {
            dispatcher.register(CommandManager.literal("musicselect")
                    .executes(context -> {
                        Networking.sendOpenGuiPacket();
                        return Command.SINGLE_SUCCESS;
                    })
            );
        });
    }
}
