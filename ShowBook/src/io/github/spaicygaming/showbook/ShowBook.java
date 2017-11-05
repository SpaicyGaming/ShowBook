package io.github.spaicygaming.showbook;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ShowBook extends JavaPlugin {

	private static ShowBook instance;

	public void onEnable() {
		instance = this;

		// Dependencies
		if (getServer().getPluginManager().getPlugin("ProtocolLib") == null) {
			getServer().getConsoleSender().sendMessage("[ShowBook] " + ChatColor.RED + "Plugin disabled due to no ProtolLib dependency found");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		saveDefaultConfig();
		// Config.yml version
		if (getConfig().getDouble("ConfigVersion") < 1.0) {
			getServer().getConsoleSender().sendMessage("[ShowBook] " + ChatColor.RED + 
					"OUTDATED CONFIG FILE DETECTED, PLEASE DELETE THE OLD ONE! You can also manually update the file: https://github.com/SpaicyGaming/ShowBook/blob/master/ShowBook/config.yml");
		}

		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getCommand("showbook").setExecutor(new ShowBookCommands());

		getLogger().info("ShowBook has been enabled.");
	}

	public static ShowBook getInstance() {
		return instance;
	}

	public void onDisable() {
		getLogger().info("ShowBook has been disabled.");
	}

	// Chat Utils

	public String c(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public List<String> c(List<String> stringList) {
		List<String> coloredStrings = new ArrayList<>();

		for (String str : stringList) {
			coloredStrings.add(c(str));
		}
		return coloredStrings;

	}

}
