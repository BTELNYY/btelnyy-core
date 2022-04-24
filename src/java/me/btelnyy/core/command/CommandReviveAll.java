package java.me.btelnyy.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import me.btelnyy.core.util.RespawnUtil;
import me.btelnyy.core.util.MessageUtility;

public class CommandReviveAll implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        RespawnUtil.reviveAll((Player) sender);
        MessageUtility.messageOperators("Revived all players", sender);
        return true;
    }
}
