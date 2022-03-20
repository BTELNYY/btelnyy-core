package btelnyy.plugin;
import org.bukkit.plugin.java.JavaPlugin;

import btelnyy.plugin.MOTDHandler.*;
import btelnyy.plugin.Commands.*;
import btelnyy.plugin.VotingSystem.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
public class main extends JavaPlugin {
	//meant for the server to know what to call when doing bukkit timers
	private static main instance;
	public static main getInstance(){
	    return instance;
	}
	public static void log(java.util.logging.Level l, String m) {
		instance.getLogger().log(l, m);
	}
	// Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	instance = this;
    	//check if our config path exists
    	Path config = Path.of("./plugins/btelnyy");
    	if(Files.notExists(config, LinkOption.NOFOLLOW_LINKS)) {
    		try {
				Files.createDirectory(config, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	//event handle
    	getServer().getPluginManager().registerEvents(new EventHandle(), this);
    	//load MOTD on plugin enable
    	MOTDHandle.LoadMOTD();
    	//load suicide messages on enable
    	CommandSuicide.LoadMessages();
    	this.getCommand("suicide").setExecutor(new CommandSuicide());
    	this.getCommand("dc").setExecutor(new CommandDisconnect());
    	this.getCommand("rules").setExecutor(new CommandRules());
    	this.getCommand("vtp").setExecutor(new CommandVTP());
    	this.getCommand("vote").setExecutor(new Vote());
    	this.getCommand("ping").setExecutor(new CommandPing());
    	this.getCommand("voterestart").setExecutor(new VoteServerRestart());
    }
   
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	
    }
}