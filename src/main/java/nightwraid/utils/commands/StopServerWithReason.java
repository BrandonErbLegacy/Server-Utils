package nightwraid.utils.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nightwraid.utils.general.DiscordHelper;
import nightwraid.utils.general.ServerUtilsMod;

public class StopServerWithReason extends CommandBase {
	private final String COMMAND_NAME = "nwstop";
	private List<String> aliases;
		
	public StopServerWithReason() {
		aliases = new ArrayList();
		aliases.add("nwst");
	}

	public String getName() {
		return COMMAND_NAME;
	}

	public String getUsage(ICommandSender sender) {
		return "/nwstop";
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		String reason = "";
		for (String arg:args) {
			reason = reason + arg + " ";
		}
		if (reason != " " && reason != "") {
			ServerUtilsMod.ServerStopReason = reason;
		}
		try {
			DiscordHelper.postMessage("Server is stopping: "+ServerUtilsMod.ServerStopReason);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.saveAllWorlds(false);
		server.stopServer();
	}

}
