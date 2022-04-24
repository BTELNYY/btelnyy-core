package me.btelnyy.core.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import me.btelnyy.core.command.CommandRules;
import org.bukkit.event.server.ServerListPingEvent;

import org.bukkit.plugin.java.JavaPlugin;

public class MOTDUtil {

    static String motds = "";

    public static void loadMOTD() {
        File f = new File(JavaPlugin.getProvidingPlugin(CommandRules.class).getDataFolder(), "random_motd.txt");
        Path p = f.toPath();
        if (!f.exists()) {
            try {
                f.createNewFile();
                JavaPlugin.getProvidingPlugin(CommandRules.class).getLogger().log(java.util.logging.Level.INFO, "No MOTD File Exists");
            } catch (IOException e) {
                e.printStackTrace();
                JavaPlugin.getProvidingPlugin(CommandRules.class).getLogger().log(java.util.logging.Level.SEVERE, "Error parsing MOTD");
            }
        } else {
            try {
                motds = Files.readString(p);
            } catch (IOException e) {
                e.printStackTrace();
                JavaPlugin.getProvidingPlugin(CommandRules.class).getLogger().log(java.util.logging.Level.SEVERE, "Error reading MOTD file");
            }
        }
    }

    public static String getRandomMOTD() {
        String[] array = motds.split("\\r?\\n");
        Random rand = new Random();
        int random = rand.nextInt(array.length) + 0;
        return array[random];
    }
}
