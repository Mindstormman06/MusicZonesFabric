package com.mindstormman.musiczones;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MusicSelectionScreen extends Screen {

    private final List<String> musicFiles = new ArrayList<>();
    private int selectedFileIndex = -1;

    protected MusicSelectionScreen() {
        super(Text.of("Select a track"));
        File folder = new File("music_zones");
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if(file.getName().endsWith(".ogg") || file.getName().endsWith(".mp3")) {
                    musicFiles.add(file.getName());
                }
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        context.drawTextWithShadow(textRenderer, Text.of("Select a track:"), width / 2, 20, 0xFFFFFF);

        int y = 40;
        for (int i = 0; i < musicFiles.size(); i++) {
            Text text = Text.of((i == selectedFileIndex ? "> " : "") + musicFiles.get(i));
            context.drawCenteredTextWithShadow(textRenderer, text, width / 2, y, 0xFFFFFF);
            y += 15;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int index = (int) ((mouseY - 40) / 15);
        if (index >= 0 && index < musicFiles.size()) {
            selectedFileIndex = index;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

}
