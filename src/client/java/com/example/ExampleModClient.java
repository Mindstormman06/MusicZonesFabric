package com.example;

import net.fabricmc.api.ClientModInitializer;

import static com.mojang.text2speech.Narrator.LOGGER;

public class ExampleModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		LOGGER.info("Hello Fabric world!(client)");
		ModEventHandler.register();
		PlayerMusicTracker.register();
		MusicZoneManager.loadZones();
	}
}