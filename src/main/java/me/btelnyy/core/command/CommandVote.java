package me.btelnyy.core.command;

import com.github.writingbettercodethanyou.gamerpluginframework.command.RegisterForCommand;
import me.btelnyy.core.constant.VoteGlobals;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

@RegisterForCommand("vote")
public class CommandVote implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player s = (Player) sender;

        // not used
        boolean override = false;

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Error: Invalid syntax.");
            return false;
        }

        String voteOption = args[0];
        if (!VoteGlobals.voteExists) {
            sender.sendMessage(ChatColor.RED + "Error: No vote is active.");
            return true;
        }

        switch (voteOption) {
            case "yes":
                if (override) {
                    VoteGlobals.yesCount += 999;
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "The vote was overriden by " + sender.getName());
                }

                if (VoteGlobals.playersWhoHaveVoted.contains(s)) {
                    sender.sendMessage(ChatColor.RED + "Error: You have voted already.");
                    return true;
                }

                VoteGlobals.yesCount += 1;
                sender.sendMessage(ChatColor.GRAY + "Vote successful.");
                VoteGlobals.playersWhoHaveVoted.add(s);
                JavaPlugin.getProvidingPlugin(getClass()).getLogger().log(Level.INFO, "Player " + s.getName() + " has voted YES to " + VoteGlobals.voteType);
                return true;
            case "no":
                if (override) {
                    VoteGlobals.noCount += 999;
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "The vote was overriden by " + sender.getName());
                }

                if (VoteGlobals.playersWhoHaveVoted.contains(s)) {
                    sender.sendMessage(ChatColor.RED + "Error: You have voted already.");
                    return true;
                }

                VoteGlobals.noCount += 1;
                sender.sendMessage(ChatColor.GRAY + "Vote successful.");
                VoteGlobals.playersWhoHaveVoted.add(s);
                JavaPlugin.getProvidingPlugin(getClass()).getLogger().log(Level.INFO, "Player " + s.getName() + " has voted NO to " + VoteGlobals.voteType);
                return true;
            default:
                sender.sendMessage(ChatColor.RED + "Error: Invalid vote option.");
                return false;
        }
    }
};
