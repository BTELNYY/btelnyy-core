package me.btelnyy.core.command;

import java.util.logging.Level;

import me.btelnyy.core.task.PunishPlayerRunnable;
import me.btelnyy.core.constant.VoteGlobals;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandVTP implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Error: Invalid syntax. Usage: /vtp <player> <kick/ban");
            return true;
        }
        if (VoteGlobals.voteExists) {
            sender.sendMessage(ChatColor.RED + "Error: A vote is already in progress, wait until it has concluded.");
            return true;
        }
        Player senderPlayer = (Player) sender;
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Error: Player not found.");
            return true;
        } else {
            if (player.isOp()) {
                sender.sendMessage(ChatColor.RED + "Error: You cannot vote to punish administrators.");
                return true;
            }
            VoteGlobals.target = player;
        }
        switch (args[1]) {
            case "ban":
                if (!sender.hasPermission("btelnyy.vote.ban")) {
                    sender.sendMessage(ChatColor.RED + "Error: You do not have permission to start a vote with this type of punishment.");
                    return true;
                }
                VoteGlobals.voteType = "ban";
                Bukkit.broadcastMessage(ChatColor.YELLOW + sender.getName() + " wants to ban " + player.getName());
                VoteGlobals.voteExists = true;
                JavaPlugin.getProvidingPlugin(getClass()).getLogger().log(Level.INFO, senderPlayer.getName() + " wants to ban " + player.getName());
                try {
                    new PunishPlayerRunnable().start(VoteGlobals.voteTimer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bukkit.broadcastMessage(ChatColor.YELLOW + "You have 30 seconds to vote.");
                VoteGlobals.starter = senderPlayer;
                break;
            case "kick":
                if (!sender.hasPermission("btelnyy.vote.kick")) {
                    sender.sendMessage(ChatColor.RED + "Error: You do not have permission to start a vote with this type of punishment.");
                    return true;
                }
                VoteGlobals.voteType = "kick";
                Bukkit.broadcastMessage(ChatColor.YELLOW + sender.getName() + " wants to kick " + player.getName());
                VoteGlobals.voteExists = true;
                JavaPlugin.getProvidingPlugin(getClass()).getLogger().log(Level.INFO, senderPlayer.getName() + " wants to kick " + player.getName());
                try {
                    new PunishPlayerRunnable().start(VoteGlobals.voteTimer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bukkit.broadcastMessage(ChatColor.YELLOW + "You have " + VoteGlobals.voteTimer + " seconds to vote.");
                VoteGlobals.starter = senderPlayer;
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Error: Invalid punishment.");
                return true;
        }
        return true;
    }

}
