package io.github.spaicygaming.showbook;

import org.bukkit.plugin.java.JavaPlugin;

public class ShowBook extends JavaPlugin {
	
	private static ShowBook instance;
	
	public void onEnable(){
		instance = this;
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getCommand("showbook").setExecutor(new ShowBookCommands());
		
		getLogger().info("ShowBook has been enabled.");
	}
	
	public static ShowBook getInstance(){
		return instance;
	}
	
	public void onDisable(){
		getLogger().info("ShowBook has been disabled.");
	}
	
	

}
