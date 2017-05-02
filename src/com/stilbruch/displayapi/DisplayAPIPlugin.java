package com.stilbruch.displayapi;

import com.stilbruch.displayapi.nms.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class DisplayAPIPlugin extends JavaPlugin implements Listener {

	private DisplayAPI displayAPI;

	public void onEnable() {

		// Set Variables
		displayAPI = loadDisplayAPI();

		if (displayAPI == null){
			this.getLogger().info("Your server version is not compatible with DisplayAPI!");
			Bukkit.getPluginManager().disablePlugin(this);
		}

		//Add this to the services manager
		this.getServer().getServicesManager().register(DisplayAPI.class, displayAPI, this, ServicePriority.Normal);

	}

	private DisplayAPI loadDisplayAPI() {
		String version;
		try {
			version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		} catch (ArrayIndexOutOfBoundsException ex) {
			return null;
		}
		
		if (version.equals("v1_8_R1")) {
			return new DisplayAPI_1_8_R1();
		} else if (version.equals("v1_8_R2")){
			return new DisplayAPI_1_8_R2();
		} else if (version.equals("v1_8_R3")){
			return new DisplayAPI_1_8_R3();
		} else if (version.equals("v1_9_R1")){
			return new DisplayAPI_1_9_R1();
		} else if (version.equals("v1_9_R2")){
			return new DisplayAPI_1_9_R2();
		} else if (version.equals("v1_10_R1")){
			return new DisplayAPI_1_10_R1();
		} else if (version.equals("v1_11_R1")){
			return new DisplayAPI_1_11_R1();
		}

		return null;
	}
	
	// Getters
	public DisplayAPI getDisplayAPI(){
		return displayAPI;
	}

}
