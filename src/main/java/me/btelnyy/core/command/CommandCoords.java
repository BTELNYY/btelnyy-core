package me.btelnyy.core.command;

import com.github.writingbettercodethanyou.chadpluginframework.message.MessageService;
import me.btelnyy.core.constant.MessageKeys;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class CommandCoords implements CommandExecutor {

    private final MessageService messageService;

    public CommandCoords(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            messageService.sendMessage(sender, MessageKeys.ERROR_PLAYERS_ONLY);
            return true;
        }

        // Select the player
        Player targetPlayer;
        if (args.length == 0 || !sender.hasPermission("btelnyy.command.whereami.others"))
            targetPlayer = (Player) sender;
        else {
            targetPlayer = Bukkit.getPlayer(args[0]);
            if(targetPlayer == null) {
                messageService.sendMessage(sender, MessageKeys.ERROR_PLAYER_NOT_FOUND, Map.of("player", args[0]));
                return true;
            }
        }

        Location pos = targetPlayer.getEyeLocation();
        messageService.sendMessage(
                sender,
                targetPlayer == sender ? MessageKeys.COMMAND_COORDS_SELF_POSITION : MessageKeys.COMMAND_COORDS_OTHER_POSITION,
                Map.of("target", targetPlayer.getName(), "x", pos.getBlockX(), "y", pos.getBlockY(), "z", pos.getBlockZ()));

        return true;
    }
}
