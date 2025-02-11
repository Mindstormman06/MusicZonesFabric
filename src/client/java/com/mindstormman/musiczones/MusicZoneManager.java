package com.mindstormman.musiczones;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.mojang.text2speech.Narrator.LOGGER;

public class MusicZoneManager {

    public static void openMusicSelectionGUI(ServerPlayerEntity player) {
        player.networkHandler.sendPacket(new OpenScreenS2CPacket(1, ScreenHandlerType.GENERIC_9X1, Text.of("Select Track")));
    }

    private static final String CONFIG_PATH = "config/music_zones.json";
    private static final List<MusicZone> zones = new ArrayList<>();

    public static void addZone(ServerPlayerEntity player, BlockPos pos1, BlockPos pos2, String musicPath) {
        LOGGER.info(new MusicZone(player.getUuid(), pos1, pos2, musicPath).toString());
        zones.add(new MusicZone(player.getUuid(), pos1, pos2, musicPath));

        saveZones();
    }

    public static List<MusicZone> getZones() {
        return zones;
    }

    private static void saveZones() {
        try (Writer writer = new FileWriter(CONFIG_PATH)) {
            new Gson().toJson(zones, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadZones() {
        File file = new File(CONFIG_PATH);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type listType = new TypeToken<ArrayList<MusicZone>>() {}.getType();
                List<MusicZone> loadedZones = new Gson().fromJson(reader, listType);
                zones.clear();
                zones.addAll(loadedZones);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
