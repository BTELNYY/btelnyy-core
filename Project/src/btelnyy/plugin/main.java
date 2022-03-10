package btelnyy.plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
public class main extends JavaPlugin {
	// TODO Auto-generated method stub
    // Fired when plugin is first enabled
	
    @Override
    public void onEnable() {
    	//check if our config path exists
    	Path config = Path.of("./plugins/btelnyy");
    	if(Files.notExists(config, LinkOption.NOFOLLOW_LINKS)) {
    		try {
				Files.createDirectory(config, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
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