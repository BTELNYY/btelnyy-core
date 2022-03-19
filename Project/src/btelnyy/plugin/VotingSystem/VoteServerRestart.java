package btelnyy.plugin.VotingSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class VoteServerRestart implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		if(VoteGlobals.VoteExists) {
			sender.sendMessage(ChatColor.RED + "Error: A vote is already in progress, wait until it has concluded.");
			return true;
		}
		Player p = (Player)sender;
		VoteGlobals.VoteType = "restart";
		Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + " wants to restart the server.");
		Bukkit.broadcastMessage(ChatColor.YELLOW + "You have 30 seconds to vote.");
		try {
			new RestartServer().start(VoteGlobals.VoteTimer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		VoteGlobals.VoteExists = true;
		return true;
	}
}