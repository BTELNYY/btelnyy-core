package me.btelnyy.core.listener;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.util.RespawnUtil;
import me.btelnyy.core.util.MOTDUtil;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        //handles event and fires at the method
        MOTDUtil.ChangeMOTD(event);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) {
            return;
        }
        if (event.getDamager().getType() != EntityType.PLAYER) {
            return;
        }
        Player player = (Player) event.getDamager();
        Player Target = (Player) event.getEntity();
        if (!Globals.PvpToggled) {
            event.setCancelled(true);
            return;
        }
        if (Globals.DeadPlayers.contains(Target)) {
            event.setCancelled(true);
            return;
        }
        if (Globals.DeadPlayers.contains(player)) {
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onProjetileHit(ProjectileHitEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) {
            return;
        }
        if (!(event.getHitEntity() instanceof Player)) {
            return;
        }
        if (!Globals.PvpToggled) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDeath(PlayerDeathEvent event) {
        //dont trigger unless the entity is a player and hardcore mode is on
        if (!Globals.HardcoreToggled) {
            return;
        }
        Player p = event.getEntity();
        Location deathlocation = event.getEntity().getLocation();
        RespawnUtil.RespawnPlayer(p, event, deathlocation);
    }
}
