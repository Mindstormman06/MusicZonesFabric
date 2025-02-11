package com.example;

import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class MusicZone {

    public final UUID owner;
    public final BlockPos corner1;
    public final BlockPos corner2;
    public final String musicPath;

    public MusicZone(UUID owner, BlockPos corner1, BlockPos corner2, String musicPath) {
        this.owner = owner;
        this.corner1 = corner1;
        this.corner2 = corner2;
        this.musicPath = musicPath;
    }

    public boolean isInside(BlockPos pos) {
        return pos.getX() >= Math.min(corner1.getX(), corner2.getX()) &&
                pos.getX() <= Math.max(corner1.getX(), corner2.getX()) &&
                pos.getY() >= Math.min(corner1.getY(), corner2.getY()) &&
                pos.getY() <= Math.max(corner1.getY(), corner2.getY()) &&
                pos.getZ() >= Math.min(corner1.getZ(), corner2.getZ()) &&
                pos.getZ() <= Math.max(corner1.getZ(), corner2.getZ());
    }

}
