package btelnyy.plugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import btelnyy.plugin.Main;
import btelnyy.plugin.MOTDHandler.MOTDHandle;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
public class CommandReload implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		sender.sendMessage(ChatColor.GRAY + "Reloading plugin configuration and text files.");
		MOTDHandle.LoadMOTD();
		CommandRules.LoadMessages();
		CommandSuicide.LoadMessages();
		Main.LoadConfig();
		sender.sendMessage(ChatColor.GRAY + "Done.");
		return true;
	}
}