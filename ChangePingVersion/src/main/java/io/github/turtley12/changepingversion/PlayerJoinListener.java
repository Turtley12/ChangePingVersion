package io.github.turtley12.changepingversion;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player.hasPermission("changepingversion.update") && UpdateChecker.getUpdateAvailable()) {
			//update message to tell player that there is an update
			player.sendMessage(ChatColor.DARK_PURPLE + "ChangePingVersion Update " + ChatColor.WHITE + UpdateChecker.getNewVersion().replace("\"", "") + ChatColor.DARK_PURPLE + " is available");
			player.sendMessage(ChatColor.DARK_PURPLE + "You have version " + ChatColor.WHITE + Main.getVersion());
			player.sendMessage(ChatColor.DARK_PURPLE + "Download the new release at: ");
			player.sendMessage(ChatColor.WHITE + "https://github.com/Turtley12/ChangePingVersion/releases");
		}
			
	}
	

}
