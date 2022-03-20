package btelnyy.plugin.VotingSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import btelnyy.plugin.Utility;
import btelnyy.plugin.main;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Vote implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		Player s = (Player) sender;
		//not used
		boolean override = false;
		if(Utility.ArrayCounter(args) < 1) {
			sender.sendMessage(ChatColor.RED + "Error: Invalid syntax.");
			return false;
		}
		String VoteOption = args[0];
		if(!VoteGlobals.VoteExists) {
			sender.sendMessage(ChatColor.RED + "Error: No vote is active.");
			return true;
		}
		switch(VoteOption) {
		case "yes":
			if(override) {
				VoteGlobals.VoteYes += 999;
				Bukkit.broadcastMessage(ChatColor.YELLOW + "The vote was overriden by " + sender.getName());
			}
			for(Player p : VoteGlobals.VotedPlayers) {
				if(p == s) {
					sender.sendMessage(ChatColor.RED + "Error: You have voted already.");
					return true;
				}
			}
			VoteGlobals.VoteYes += 1;
			sender.sendMessage(ChatColor.GREEN + "Vote successful.");
			VoteGlobals.VotedPlayers.add(s);
			main.log(Level.INFO, "Player " + s.getName() + " has voted YES to " + VoteGlobals.VoteType);
			return true;
		case "no":
			if(override) {
				VoteGlobals.VoteNo += 999;
				Bukkit.broadcastMessage(ChatColor.YELLOW + "The vote was overriden by " + sender.getName());
			}
			for(Player p : VoteGlobals.VotedPlayers) {
				if(p == s) {
					sender.sendMessage(ChatColor.RED + "Error: You have voted already.");
					return true;
				}
			}
			VoteGlobals.VoteNo += 1;
			sender.sendMessage(ChatColor.GREEN + "Vote successful.");
			VoteGlobals.VotedPlayers.add(s);
			main.log(Level.INFO, "Player " + s.getName() + " has voted NO to " + VoteGlobals.VoteType);
			return true;
		default:
			sender.sendMessage(ChatColor.RED + "Error: Invalid vote option.");
			return false;
		}
	}
};

