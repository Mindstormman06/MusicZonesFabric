package com.example;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.Items;
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
    }
}
