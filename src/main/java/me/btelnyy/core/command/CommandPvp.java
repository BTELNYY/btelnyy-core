package me.btelnyy.core.command;

import com.github.writingbettercodethanyou.gamerpluginframework.command.RegisterForCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.util.MessageUtility;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

@RegisterForCommand("pvp")
public class CommandPvp implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Globals.pvpToggled = !Globals.pvpToggled;
        sender.sendMessage(String.format("%sPVP is now %s.", ChatColor.GRAY, Globals.hardcoreToggled ? "enabled" : "disabled"));
        MessageUtility.messageOperators(String.format("%s PVP", Globals.hardcoreToggled ? "Enabled" : "Disabled"), sender);
        return true;
    }
}
