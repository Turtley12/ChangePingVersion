package io.github.turtley12.changepingversion;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;

public class PingListener implements Listener {
	@EventHandler
	public void onClientPing (PaperServerListPingEvent event) {
		//get config version variable from main class
		String version_message = Main.getVersionMessage();
		
		//set version to variable
		event.setVersion(version_message);
	}

}
