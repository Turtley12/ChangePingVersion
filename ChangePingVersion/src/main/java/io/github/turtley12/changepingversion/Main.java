package io.github.turtley12.changepingversion;

import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	private static String ping_version;
	private static Main plugin;
	private static String plugin_version;
	@Override
	public void onEnable() {
		//on enable
		plugin = this;
		
		//bstats
		int pluginId = 9943;
		Metrics metrics = new Metrics(this, pluginId);
		
		//save default config file from jar if it doesn't exist
		saveDefaultConfig();
		
		//set version variable to config vaulue
		FileConfiguration config = getConfig();
		ping_version = config.getString("version");
		
		//register listener
		getServer().getPluginManager().registerEvents(new PingListener(), this);
		
		//register player join listener for update notifier
		
		UpdateChecker.startUpdateChecker();
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		PluginDescriptionFile pdf = this.getDescription(); //Gets plugin.yml
		plugin_version = pdf.getVersion(); //Gets the version
		
		//enable message
		if (metrics.isEnabled()) {
			getLogger().info("Enabled metrics. you can turn them off in /plugins/bStats/config.yml");
		}
		getLogger().info("ChangePingVersion enabled!");
		
	}
	@Override
	public void onDisable() {
		//disable message
		getLogger().info("ChangePingVersion disabled!");
		plugin = null;
	}
	public static String getVersionMessage() {
		return ping_version;
	}
	public static Main getMain() {
		return plugin;
	}
	public static String getVersion() {
		String plugin_version_v = "v" + plugin_version;
		return plugin_version_v;
	}


}