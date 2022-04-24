package java.me.btelnyy.core.command;

import java.me.btelnyy.core.service.TextFileMessageService;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandRules implements CommandExecutor {

    private final TextFileMessageService messageService;

    public CommandRules(TextFileMessageService messageService) {
        this.messageService = messageService;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (String rule : messageService.getAllMessages()) {
            sender.sendMessage(rule);
        }
        return true;
    }
}
