package btelnyy.plugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import btelnyy.plugin.Globals;
import btelnyy.plugin.Utility;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
public class CommandPvp implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		Globals.PvpToggled = !Globals.PvpToggled;
		if(Globals.PvpToggled) {
			sender.sendMessage(ChatColor.GRAY + "PVP is now enabled.");
			Utility.SendToOps("Enabled PVP", sender);
			return true;
		}else{
			sender.sendMessage(ChatColor.GRAY + "PVP is now disabled.");
			Utility.SendToOps("Disabled PVP", sender);
			return true;
		}
	}
}