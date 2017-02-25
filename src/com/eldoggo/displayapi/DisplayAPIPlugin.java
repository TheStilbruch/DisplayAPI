package com.eldoggo.displayapi;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import com.eldoggo.displayapi.nms.NMS;
import com.eldoggo.displayapi.nms.NMS_1_10_R1;
import com.eldoggo.displayapi.nms.NMS_1_11_R1;
import com.eldoggo.displayapi.nms.NMS_1_8_R1;
import com.eldoggo.displayapi.nms.NMS_1_8_R2;
import com.eldoggo.displayapi.nms.NMS_1_8_R3;
import com.eldoggo.displayapi.nms.NMS_1_9_R1;
import com.eldoggo.displayapi.nms.NMS_1_9_R2;

public class DisplayAPIPlugin extends JavaPlugin implements Listener {

	private NMS nms;
	private DisplayAPI displayAPI;

	public void onEnable() {
		// Set Variables
		nms = loadNMS();

		if (nms != null) {
			init();
		} else {
			this.getLogger().info("Your server version is not compatible with DisplayAPI!");
			Bukkit.getPluginManager().disablePlugin((Plugin) this);
		}
	}

	private NMS loadNMS() {
		String version;
		try {
			version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		} catch (ArrayIndexOutOfBoundsException ex) {
			return null;
		}
		
		if (version.equals("v1_8_R1")) {
			return new NMS_1_8_R1();
		} else if (version.equals("v1_8_R2")){
			return new NMS_1_8_R2();
		} else if (version.equals("v1_8_R3")){
			return new NMS_1_8_R3();
		} else if (version.equals("v1_9_R1")){
			return new NMS_1_9_R1();
		} else if (version.equals("v1_9_R2")){
			return new NMS_1_9_R2();
		} else if (version.equals("v1_10_R1")){
			return new NMS_1_10_R1();
		} else if (version.equals("v1_11_R1")){
			return new NMS_1_11_R1();
		}

		return null;
	}

	private void init() {
		//Set variables
		displayAPI = new DisplayAPI(this);
		
		//Register Service
		this.getServer().getServicesManager().register(DisplayAPI.class, displayAPI, this, ServicePriority.Normal);
		
		// Add listeners
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	// Getters
	public NMS getNMS() {
		return nms;
	}

}
