package java.me.btelnyy.core.constant;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Globals {

    public static boolean pvpToggled = true;
    public static boolean showDeathTitle = true;
    public static String hardcoreResult = "SPECTATOR";
    public static boolean tpToDeathHardcore = true;

    public static final String[] hOptions = {"SPECTATOR", "GHOST"};
    public static boolean hardcoreToggled = Bukkit.getServer().isHardcore();
    public static List<Player> deadPlayers = new ArrayList<Player>();
}
