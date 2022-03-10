package btelnyy.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import org.bukkit.ChatColor;

public class VoteNo implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		Player s = (Player) sender;
		if(!VoteGlobals.VoteExists) {
			sender.sendMessage(ChatColor.RED + "Error: No vote is active.");
			return true;
		}
		for(Player p : VoteGlobals.VotedPlayers) {
			if(p == s) {
				sender.sendMessage(ChatColor.RED + "Error: You have voted already.");
				return true;
			}
		}
		if(args[1] == "override" && s.hasPermission("btelnyy.vote.override")) {
			sender.sendMessage(ChatColor.GREEN + "Administrator override activated.");
			VoteGlobals.VoteNo += 999;
			return true;
		}
		VoteGlobals.VoteNo += 1;
		sender.sendMessage(ChatColor.GREEN + "Vote successful.");
	    VoteGlobals.VotedPlayers = Arrays.copyOf(VoteGlobals.VotedPlayers, VoteGlobals.VotedPlayers.length + 1);
	    VoteGlobals.VotedPlayers[VoteGlobals.VotedPlayers.length] = s; // Assign 40 to the last element
		return true;
	}
};