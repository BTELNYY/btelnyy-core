package me.btelnyy.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.util.MessageUtility;

public class CommandHardcore implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //if you disable hardcore via command, but server hardcore is still on
        Globals.hardcoreToggled = !Globals.hardcoreToggled;
        sender.sendMessage(String.format("%sHardcore is now %s.", ChatColor.GRAY, Globals.hardcoreToggled ? "enabled" : "disabled"));
        MessageUtility.messageOperators(String.format("%s Hardcore", Globals.hardcoreToggled ? "Enabled" : "Disabled"), sender);
        return true;
    }
}
