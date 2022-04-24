package me.btelnyy.core.constant;

import java.util.ArrayList;
import java.util.List;

import me.btelnyy.core.CorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Globals {

    public static boolean PvpToggled = true;
    public static boolean ShowDeathTitle = true;
    public static String HardcoreResult = "SPECTATOR";
    public static boolean TpToDeathHardcore = true;

    public static final String[] hoptions = {"SPECTATOR", "GHOST"};
    public static boolean HardcoreToggled = Bukkit.getServer().isHardcore();
    public static List<Player> DeadPlayers = new ArrayList<Player>();
}
