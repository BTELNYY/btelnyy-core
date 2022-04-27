package me.btelnyy.core.command;

import com.github.writingbettercodethanyou.gamerpluginframework.command.RegisterForCommand;
import me.btelnyy.core.service.RulesMessageService;
import me.btelnyy.core.service.TextFileMessageService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@RegisterForCommand("rules")
public class CommandRules implements CommandExecutor {

    private final RulesMessageService messageService;

    public CommandRules(RulesMessageService messageService) {
        this.messageService = messageService;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (String rule : messageService.getAllMessages()) {
            sender.sendMessage(rule);
        }
        return true;
    }
}
