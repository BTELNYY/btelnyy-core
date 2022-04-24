package me.btelnyy.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.util.MessageUtility;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class CommandPvp implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
        Globals.PvpToggled = !Globals.PvpToggled;
        if (Globals.PvpToggled) {
            sender.sendMessage(ChatColor.GRAY + "PVP is now enabled.");
            MessageUtility.SendToOps("Enabled PVP", sender);
            return true;
        } else {
            sender.sendMessage(ChatColor.GRAY + "PVP is now disabled.");
            MessageUtility.SendToOps("Disabled PVP", sender);
            return true;
        }
    }
}