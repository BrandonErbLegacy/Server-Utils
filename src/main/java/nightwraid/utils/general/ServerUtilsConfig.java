package nightwraid.utils.general;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Settings.MODID, name="ServerUtils", category="General")
public class ServerUtilsConfig {
	//Player Settings
	@Config.Comment("Enter the discord endpoint here")
	public static String discordAPI = "";
	
	
	//Config Handler
	@SubscribeEvent
	public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Settings.MODID)) {
			ConfigManager.sync(Settings.MODID, Config.Type.INSTANCE);
		}
	}
}
