package me.btelnyy.core.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.CorePlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandSuicide implements CommandExecutor {

    public static String[] array = {};

    public static void LoadMessages() {
        File f = new File(JavaPlugin.getProvidingPlugin(CommandRules.class).getDataFolder(), "death_msg.txt");
        Path p = f.toPath();
        String deaths = "";
        if (!f.exists()) {
            try {
                f.createNewFile();
                JavaPlugin.getProvidingPlugin(CommandRules.class).getLogger().log(java.util.logging.Level.WARNING, JavaPlugin.getProvidingPlugin(CommandRules.class).getDataFolder() + "/death_msg.txt does not exist.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                deaths = Files.readString(p);
                array = deaths.split("\\r?\\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
        if (sender instanceof Player) {
            ((Player) sender).setHealth(0);
            Random rand = new Random();
            int random = rand.nextInt(array.length) + 0;
            sender.sendMessage(ChatColor.RED + array[random]);
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Error: You must be a player to run this command.");
        return true;
    }

}
