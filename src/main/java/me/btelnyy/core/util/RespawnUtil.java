package main.java.me.btelnyy.core.util;

import main.java.me.btelnyy.core.constant.Globals;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class RespawnUtil {

    public static void respawnPlayer(Player player, PlayerDeathEvent event, Location location) {
        JavaPlugin.getProvidingPlugin(RespawnUtil.class).getLogger().log(Level.INFO, "About to respawn player. config: " + Globals.hardcoreResult);
        if (Globals.hardcoreResult.equals(Globals.hOptions[0])) {
            if (Globals.tpToDeathHardcore) {
                player.setGameMode(GameMode.SPECTATOR);
                player.teleport(location);
            } else {
                player.setGameMode(GameMode.SPECTATOR);
            }
        } else if (Globals.hardcoreResult.equals(Globals.hOptions[1])) {
            ghostPlayer(player, event, location);
        }
        if (Globals.showDeathTitle) {
            player.sendTitle(ChatColor.RED + "YOU DIED!", "You cannot respawn in hardcore mode.", 20, 100, 20);
        }
        Globals.deadPlayers.add(player);
        player.spigot().respawn();
    }

    public static void ghostPlayer(Player player, PlayerDeathEvent event, Location location) {
        if (Globals.tpToDeathHardcore) {
            player.setGameMode(GameMode.ADVENTURE);
            player.setAllowFlight(true);
            player.setInvisible(true);
            player.setInvulnerable(true);
            player.setCanPickupItems(false);
            player.setCollidable(false);
            player.teleport(location);
        } else {
            player.setGameMode(GameMode.ADVENTURE);
            player.setAllowFlight(true);
            player.setInvisible(true);
            player.setInvulnerable(true);
            player.setCanPickupItems(false);
            player.setCollidable(false);
        }
        return;
    }

    public static void revivePlayer(Player player) {
        GameMode gamemode = Bukkit.getDefaultGameMode();
        Location spawn = Bukkit.getServer().getWorld(player.getLocation().getWorld().getName()).getSpawnLocation();
        player.setGameMode(gamemode);
        player.setAllowFlight(false);
        player.setInvisible(false);
        player.setInvulnerable(false);
        player.setCanPickupItems(true);
        player.setCollidable(true);
        if (player.getBedSpawnLocation() != null) {
            player.teleport(player.getBedLocation());
        } else {
            player.teleport(spawn);
        }
        Globals.deadPlayers.remove(player);
    }

    public static void reviveAll(Player sender) {
        try {
            for (Player p : Globals.deadPlayers) {
                RespawnUtil.revivePlayer(p);
                sender.sendMessage(ChatColor.GRAY + "Player " + p.getName() + " has been revived.");
            }
            Globals.deadPlayers.clear();
        } catch (Exception e) {
            JavaPlugin.getProvidingPlugin(RespawnUtil.class).getLogger().log(Level.WARNING, "Exception while reviving all dead players: " + e.getMessage());
        }
    }
}
