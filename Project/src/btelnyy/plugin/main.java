package btelnyy.plugin;
import org.bukkit.plugin.java.JavaPlugin;
public class main extends JavaPlugin {
	// TODO Auto-generated method stub
    // Fired when plugin is first enabled
	
    @Override
    public void onEnable() {
    	System.out.println("Enabling Plugin.");
    	this.getCommand("suicide").setExecutor(new CommandSuicide());
    }
    
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	System.out.println("Disabling Plugin.");
    }
}