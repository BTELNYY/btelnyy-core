package btelnyy.plugin;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class RespawnHandler {
	public static void RespawnPlayer(Player player, EntityDeathEvent event) {
		if(Globals.HardcoreResult == "SPECTATOR") {
			if(Globals.TpToDeathHardcore) {
				Location location = event.getEntity().getLocation();
				player.setGameMode(GameMode.SPECTATOR);
				player.teleport(location);
			}else{
				player.setGameMode(GameMode.SPECTATOR);
			}
		}else {
			GhostPlayer(player, event);
		}
		if(Globals.ShowDeathTitle) {
			player.sendTitle(ChatColor.RED + "YOU DIED!", "You cannot respawn in hardcore mode.", 20, 100, 20);
		}
		Globals.DeadPlayers.add(player);
		player.spigot().respawn();
	}
	public static void GhostPlayer(Player player, EntityDeathEvent event) {
		if(Globals.TpToDeathHardcore) {
			Location location = event.getEntity().getLocation();
			player.setGameMode(GameMode.ADVENTURE);
			player.setAllowFlight(true);
			player.setInvisible(true);
			player.setInvulnerable(true);
			player.setCanPickupItems(false);
			player.setCollidable(false);
			player.teleport(location);
		}else{
			player.setGameMode(GameMode.ADVENTURE);
			player.setAllowFlight(true);
			player.setInvisible(true);
			player.setInvulnerable(true);
			player.setCanPickupItems(false);
			player.setCollidable(false);
		}
		return;
	}
	public static void RevivePlayer(Player player) {
		GameMode gm = Bukkit.getDefaultGameMode();
		Location spawn = Bukkit.getServer().getWorld(player.getLocation().getWorld().getName()).getSpawnLocation();
		player.setGameMode(gm);
		player.setAllowFlight(false);
		player.setInvisible(false);
		player.setInvulnerable(false);
		player.setCanPickupItems(true);
		player.setCollidable(true);
		if(player.getBedSpawnLocation() != null) {
			player.teleport(player.getBedLocation());
		}else{
			player.teleport(spawn);
		}
		Globals.DeadPlayers.remove(player);
	}
	public static void ReviveAll(Player sender) {
		try {
		for(Player p : Globals.DeadPlayers) {
			RespawnHandler.RevivePlayer(p);
			sender.sendMessage(ChatColor.GRAY + "Player " + p.getName() + " has been revived.");
		}
		Globals.DeadPlayers.clear();
		}catch(Exception e) {
			Main.log(Level.WARNING, "Exception while reviving all dead players: " + e.getMessage());
		}
	}
}
