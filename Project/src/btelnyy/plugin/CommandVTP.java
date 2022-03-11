package btelnyy.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class CommandVTP implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		if(args.length < 2) {
			sender.sendMessage(ChatColor.RED + "Error: Invalid syntax. Usage: /vtp <player> <kick/ban");
			return true;
		}
			Player player = Bukkit.getPlayer(args[0]);
			if(player == null) {
				sender.sendMessage(ChatColor.RED + "Error: Player not found.");
				return true;
			}else {
				if(player.isOp()) {
					sender.sendMessage(ChatColor.RED + "Error: You cannot vote to punish administrators!");
					return true;
				}
				VoteGlobals.target = player;
			}
		switch(args[1]) {
			case "ban":
				if(!sender.hasPermission("btelnyy.vote.ban")) {
					sender.sendMessage(ChatColor.RED + "Error: You do not have permission to start a vote with this type of punishment!");
				return true;
				}
				VoteGlobals.VoteType = "ban";
				Bukkit.broadcastMessage(ChatColor.YELLOW + sender.getName() + " wants to ban " + player.getName());
				VoteGlobals.VoteExists = true;
			try {
				new KickPlayer().start(VoteGlobals.VoteTimer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				Bukkit.broadcastMessage(ChatColor.YELLOW + "You have 30 seconds to vote.");
				break;
			case "kick":
				if(!sender.hasPermission("btelnyy.vote.kick")) {
					sender.sendMessage(ChatColor.RED + "Error: You do not have permission to start a vote with this type of punishment!");
				return true;
				}
				VoteGlobals.VoteType = "kick";
				Bukkit.broadcastMessage(ChatColor.YELLOW + sender.getName() + " wants to kick " + player.getName());
				VoteGlobals.VoteExists = true;
			try {
				new KickPlayer().start(VoteGlobals.VoteTimer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				Bukkit.broadcastMessage(ChatColor.YELLOW + "You have 30 seconds to vote.");
				break;
			default:
				sender.sendMessage(ChatColor.RED + "Error: Invalid punishment.");
				return true;
		}
		return true;
	}
	
}