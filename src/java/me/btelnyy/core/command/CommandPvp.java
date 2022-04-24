package java.me.btelnyy.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


import me.btelnyy.core.util.MessageUtility;

import java.me.btelnyy.core.constant.Globals;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;

public class CommandPvp implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Globals.pvpToggled = !Globals.pvpToggled;
        if (Globals.pvpToggled) {
            sender.sendMessage(ChatColor.GRAY + "PVP is now enabled.");
            MessageUtility.messageOperators("Enabled PVP", sender);
            return true;
        } else {
            sender.sendMessage(ChatColor.GRAY + "PVP is now disabled.");
            MessageUtility.messageOperators("Disabled PVP", sender);
            return true;
        }
    }
}
