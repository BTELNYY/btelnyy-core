package me.btelnyy.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.util.Utility;

public class CommandHardcore implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		//if you disable hardcore via command, but server hardcore is still on
		if(!Globals.HardcoreToggled) {
			sender.sendMessage(ChatColor.GRAY + "Hardcore is now enabled.");
			Utility.SendToOps("Enabled Hardcore", sender);
			Globals.HardcoreToggled = true;
			return true;
		}
		if(Globals.HardcoreToggled) {
			sender.sendMessage(ChatColor.GRAY + "Hardcore is now disabled.");
			Utility.SendToOps("Disabled Hardcore", sender);
			Globals.HardcoreToggled = false;
			return true;
		}
		return true;
	}
}