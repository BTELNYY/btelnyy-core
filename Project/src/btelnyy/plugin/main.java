package btelnyy.plugin;
import org.bukkit.plugin.java.JavaPlugin;
import btelnyy.plugin.Commands.CommandDisconnect;
import btelnyy.plugin.Commands.CommandRules;
import btelnyy.plugin.Commands.CommandSuicide;
import btelnyy.plugin.VotingSystem.CommandVTP;
import btelnyy.plugin.VotingSystem.VoteNo;
import btelnyy.plugin.VotingSystem.VoteYes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
public class main extends JavaPlugin {
    // Fired when plugin is first enabled
	private static main instance;
	public static main getInstance(){
	    return instance;
	}
	public static void log(java.util.logging.Level l, String m) {
		instance.getLogger().log(l, m);
	}
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
    	getServer().getPluginManager().registerEvents(new EventHandle(), this);
    	this.getCommand("suicide").setExecutor(new CommandSuicide());
    	this.getCommand("dc").setExecutor(new CommandDisconnect());
    	this.getCommand("rules").setExecutor(new CommandRules());
    	this.getCommand("vtp").setExecutor(new CommandVTP());
    	this.getCommand("voteyes").setExecutor(new VoteYes());
    	this.getCommand("voteno").setExecutor(new VoteNo());
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	
    }
}