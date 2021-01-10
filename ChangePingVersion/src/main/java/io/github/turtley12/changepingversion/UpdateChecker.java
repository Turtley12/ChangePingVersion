package io.github.turtley12.changepingversion;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.Bukkit;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class UpdateChecker {
	private static Main plugin = Main.getMain();
	private static String new_version;
	public static void startUpdateChecker() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
				public void run() {
					try {
						Bukkit.getLogger().info("ChangePingVersion checking for updates...");
					//check githubs api for latest version
					URL url = new URL("https://api.github.com/repos/turtley12/changepingversion/releases");
				
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();
					
					//Getting the response code
					int responsecode = conn.getResponseCode();
					
					if (responsecode == 200) {
						String inline = "";
					    Scanner scanner = new Scanner(url.openStream());
					  
					   //Write all the JSON data into a string using a scanner
					    while (scanner.hasNext()) {
					       inline += scanner.nextLine();
					    }
					    
					    //Close the scanner
					    scanner.close();
					    
					    JsonObject[] jobj = new Gson().fromJson(inline, JsonObject[].class);
					    
				
					    new_version = jobj[0].get("tag_name").toString();
					    
					    if (UpdateChecker.getUpdateAvailable()) {
							Bukkit.getLogger().info("ChangePingVersion Update " + UpdateChecker.getNewVersion().replace("\"", "") + " is available");
							Bukkit.getLogger().info("You have version " + Main.getVersion());
							Bukkit.getLogger().info("Download the new release at: ");
							Bukkit.getLogger().info("https://github.com/Turtley12/ChangePingVersion/releases");
					    }
				
					}
					else {
						Bukkit.getLogger().info("Error checking for updates");
						Bukkit.getLogger().info("response code = " + responsecode);
					}
					
				} catch (Exception e) {
					//if something went wrong do this
					Bukkit.getLogger().info("Unable to check for updates");
				}
	        }
	    }, 20 * 60, 20 * 60 * 60 * 3); //start repeated task 1 minute after plugin is enabled, repeat every 3 hours
    }
    
    public static boolean getUpdateAvailable() {
    	if (new_version != Main.getVersion() && new_version != null)
    		return true;
		return false;
    }
    public static String getNewVersion() {
    	return new_version;
    }
}
