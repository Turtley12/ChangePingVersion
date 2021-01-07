package io.github.turtley12.changepingversion;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	private static String ping_version;
	@Override
	public void onEnable() {
		//on enable
		
		//save default config file from jar if it doesn't exist
		saveDefaultConfig();
		
		//set version variable to config vaulue
		FileConfiguration config = getConfig();
		ping_version = config.getString("version");
		
		//register listener
		getServer().getPluginManager().registerEvents(new PingListener(), this);
		
		//enable message
		getLogger().info("ChangePingVersion enabled!");
	}
	@Override
	public void onDisable() {
		//disable message
		getLogger().info("ChangePingVersion disabled!");
	}
	public static String getVersionMessage() {
		return ping_version;
	}


}
