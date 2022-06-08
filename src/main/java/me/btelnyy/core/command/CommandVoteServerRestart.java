package me.btelnyy.core.command;

import me.btelnyy.core.task.RestartServerRunnable;
import me.btelnyy.core.constant.VoteGlobals;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class CommandVoteServerRestart implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (VoteGlobals.voteExists) {
            sender.sendMessage(ChatColor.RED + "Error: A vote is already in progress, wait until it has concluded.");
            return true;
        }
        Player p = (Player) sender;
        VoteGlobals.voteType = "restart";
        Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + " wants to restart the server.");
        Bukkit.broadcastMessage(ChatColor.YELLOW + "You have 30 seconds to vote.");
        try {
            new RestartServerRunnable().start(VoteGlobals.voteTimer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VoteGlobals.voteExists = true;
        return true;
    }
}
