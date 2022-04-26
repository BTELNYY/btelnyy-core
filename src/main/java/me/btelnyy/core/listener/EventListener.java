package me.btelnyy.core.listener;

import me.btelnyy.core.constant.Globals;
import me.btelnyy.core.service.TextFileMessageService;
import me.btelnyy.core.util.RespawnUtil;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class EventListener implements Listener {
    private final TextFileMessageService motdMessageService;
    public EventListener(TextFileMessageService motdMessageService) {
        this.motdMessageService = motdMessageService;
    }






    @EventHandler
    public void onPing(ServerListPingEvent event) {
        //handles event and fires at the method
        event.setMotd(motdMessageService.getRandomMessage());
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
        if (!Globals.pvpToggled) {
            event.setCancelled(true);
            return;
        }
        if (Globals.deadPlayers.contains(Target)) {
            event.setCancelled(true);
            return;
        }
        if (Globals.deadPlayers.contains(player)) {
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
        if (!Globals.pvpToggled) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void playerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(!player.isGliding()){
            return;
        }
        if(Globals.elytraAllowed){
            return;
        }
        event.setCancelled(true);
        player.setGliding(false);
        player.sendMessage(ChatColor.RED + "Elytra are disabled on this server!");
    }
    @EventHandler
    public void onEntityDeath(PlayerDeathEvent event) {
        //dont trigger unless the entity is a player and hardcore mode is on
        if (!Globals.hardcoreToggled) {
            return;
        }
        Player player = event.getEntity();
        Location deathlocation = event.getEntity().getLocation();
        RespawnUtil.respawnPlayer(player, event, deathlocation);
    }
}
