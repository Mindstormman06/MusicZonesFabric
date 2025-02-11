package com.example;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.UUID;

public class SelectionHandler {

    private static final HashMap<UUID, BlockPos[]> selections = new HashMap<>();

    public static void setSelection(ServerPlayerEntity player, BlockPos pos, boolean first) {
        selections.computeIfAbsent(player.getUuid(), k -> new BlockPos[2]);
        selections.get(player.getUuid())[first ? 0 : 1] = pos;
    }

    public static BlockPos[] getSelection(UUID playerId) {
        return selections.get(playerId);
    }

    public static void clearSelection(UUID playerId) {
        selections.remove(playerId);
    }

}
