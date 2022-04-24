package btelnyy.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Globals {
	public static String ConfigPath = Main.getInstance().getDataFolder().toString();
	public static boolean PvpToggled = true;
	public static boolean ShowDeathTitle = true;
	public static String HardcoreResult = "SPECTATOR";
	public static boolean TpToDeathHardcore = true;
	
	public static final String[] hoptions = {"SPECTATOR","GHOST"};
	public static boolean HardcoreToggled = Bukkit.getServer().isHardcore();
	public static List<Player> DeadPlayers = new ArrayList<Player>();
}
