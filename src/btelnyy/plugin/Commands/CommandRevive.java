package btelnyy.plugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import btelnyy.plugin.Globals;
import btelnyy.plugin.RespawnHandler;
public class CommandRevive implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		if(args.length < 1) {
			sender.sendMessage(ChatColor.RED + "Error: Invalid syntax. Usage: /revive <player>");
			return true;
		}
		if(Bukkit.getPlayer(args[0]) == null) {
			sender.sendMessage(ChatColor.RED + "Error: Player not found.");
			return true;
		}
		Player ReviveTarget = Bukkit.getPlayer(args[0]);
		if(!Globals.DeadPlayers.contains(ReviveTarget)) {
			sender.sendMessage(ChatColor.RED + "Error: Player is not dead.");
			return true;
		}
		RespawnHandler.RevivePlayer(ReviveTarget);
		sender.sendMessage(ChatColor.GRAY + "Player " + ReviveTarget.getName() + " has been revived.");
		return true;
	}
}
