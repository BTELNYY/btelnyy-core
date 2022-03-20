package btelnyy.plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import btelnyy.plugin.MOTDHandler.*;
public class EventHandle implements Listener{
	@EventHandler
	public void onPing(ServerListPingEvent event) {
		//handles event and fires at the method
		MOTDHandle.ChangeMOTD(event);
	}
}
