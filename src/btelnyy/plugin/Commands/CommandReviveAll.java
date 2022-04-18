package btelnyy.plugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import btelnyy.plugin.RespawnHandler;
public class CommandReviveAll implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		RespawnHandler.ReviveAll((Player) sender);
		return true;
	}
}