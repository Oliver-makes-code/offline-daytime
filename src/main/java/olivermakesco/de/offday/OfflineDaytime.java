package olivermakesco.de.offday;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class OfflineDaytime implements ModInitializer {
	public static int offlineTicks;
	@Override
	public void onInitialize() {
		ServerTickEvents.START_SERVER_TICK.register((server) -> {
			if (server.getPlayerManager().getCurrentPlayerCount() > 0) {
				offlineTicks = 0;
				return;
			}
			if (++offlineTicks == 12000) server.getOverworld().setTimeOfDay(server.getOverworld().getTimeOfDay()-(server.getOverworld().getTimeOfDay()%24000)+24000);
			if (offlineTicks > 12000) server.getOverworld().setTimeOfDay(server.getOverworld().getTimeOfDay()-1);
		});
	}
}
