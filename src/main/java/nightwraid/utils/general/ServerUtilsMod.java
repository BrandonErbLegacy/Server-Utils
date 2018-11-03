package nightwraid.utils.general;

import java.io.IOException;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import nightwraid.utils.commands.StopServerWithReason;

@Mod(modid=Settings.MODID, name=Settings.MODNAME, version=Settings.VERSION, acceptedMinecraftVersions=Settings.ACCEPTED_MINECRAFT_VERSIONS,
serverSideOnly=Settings.SERVER_SIDE_ONLY, acceptableRemoteVersions=Settings.REMOTE_VERSIONS)
public class ServerUtilsMod {
	@Instance
	public static ServerUtilsMod instance;
	
	public static org.apache.logging.log4j.Logger logger = null;
	
	public static String ServerStopReason = "Unknown reason. Probably crash.";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		/*MinecraftForge.EVENT_BUS.register(EntityHandlers.class);
		MinecraftForge.EVENT_BUS.register(PlayerHandlers.class);*/
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		//System.out.println(Settings.MODID + ":init");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//System.out.println(Settings.MODID + ":postInit");
		//System.out.println(Difficulty.playerDifficulty);
	}
	
	@EventHandler
	public void ServerStartingEvent(FMLServerStartingEvent event) {
		//System.out.println("Server starting");
		event.registerServerCommand(new StopServerWithReason());
		try {
			DiscordHelper.postMessage("Server is starting");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Notify Discord of server start
	}
	
	@EventHandler
	public void ServerStoppingEvent(FMLServerStoppingEvent event) {
		//Ensure we save our configs
		//ConfigManager.sync(Settings.MODID, Config.Type.INSTANCE);
		//Notify Discord of server stop
		try {
			if (ServerUtilsMod.ServerStopReason != "") {				
				DiscordHelper.postMessage("Server is stopping: "+ServerStopReason);
			} else {
				DiscordHelper.postMessage("Server has stopped unexpectedly");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
