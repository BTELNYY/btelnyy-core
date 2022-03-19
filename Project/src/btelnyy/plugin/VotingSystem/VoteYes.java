package btelnyy.plugin.VotingSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import btelnyy.plugin.Utility;
import btelnyy.plugin.main;

import java.util.logging.Level;

import org.bukkit.ChatColor;

public class VoteYes implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		Player s = (Player) sender;
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
		try {
			if(args[1]) = "-o" && sender.hasPermission(btelnyy.override){
				override = true;
			}
		}catch(Exception e) {
			
		}
		switch(VoteOption) {
		case "yes":
			for(Player p : VoteGlobals.VotedPlayers) {
				if(p == s) {
					sender.sendMessage(ChatColor.RED + "Error: You have voted already.");
					return true;
				}
			}
			VoteGlobals.VoteYes += 1;
			sender.sendMessage(ChatColor.GREEN + "Vote successful.");
			VoteGlobals.VotedPlayers.add(s);
			main.log(Level.INFO, "Player " + s.getName() + " has voted YES to " + VoteGlobals.VoteType + " player " + VoteGlobals.target.getName());
			return true;
		case "no":
			for(Player p : VoteGlobals.VotedPlayers) {
				if(p == s) {
					sender.sendMessage(ChatColor.RED + "Error: You have voted already.");
					return true;
				}
			}
			VoteGlobals.VoteNo += 1;
			sender.sendMessage(ChatColor.GREEN + "Vote successful.");
			VoteGlobals.VotedPlayers.add(s);
			main.log(Level.INFO, "Player " + s.getName() + " has voted NO to " + VoteGlobals.VoteType + " player " + VoteGlobals.target.getName());
			return true;
		default:
			sender.sendMessage(ChatColor.RED + "Error: Invalid vote option.");
			return false;
		}
	}
};

