package btelnyy.plugin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.server.ServerListPingEvent;
import btelnyy.plugin.MOTDHandler.*;
public class EventHandle implements Listener{
	@EventHandler
	public void onPing(ServerListPingEvent event) {
		//handles event and fires at the method
		MOTDHandle.ChangeMOTD(event);
	}
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if(event.getEntity().getType() != EntityType.PLAYER) {
			return;
		}
		if(event.getDamager().getType() != EntityType.PLAYER) {
			return;
		}
		Player player = (Player) event.getDamager();
		Player Target = (Player) event.getEntity();
		if(!Globals.PvpToggled) {
			event.setCancelled(true);
			return;
		}
		if(Globals.DeadPlayers.contains(Target)) {
			event.setCancelled(true);
			return;
		}
		if(Globals.DeadPlayers.contains(player)) {
			event.setCancelled(true);
			return;
		}
	}
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		//dont trigger unless the entity is a player and hardcore mode is on
		if(event.getEntityType() != EntityType.PLAYER) {
			return;
		}
		if(!Globals.HardcoreToggled) {
			return;
		}
		Player p = (Player)event.getEntity();
		RespawnHandler.RespawnPlayer(p, event);
	}
}
