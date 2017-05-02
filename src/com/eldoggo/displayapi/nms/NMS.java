package com.eldoggo.displayapi.nms;

import org.bukkit.entity.Player;

public interface NMS {

	void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut);
	
	void broadcastTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut);
	
	void sendTitleReset(Player player);
	
	void broadcastTitleReset();
	
	void sendActionMessage(Player player, String message);
	
	void broadcastActionMessage(String message);

	void sendTabHeaderFooter(Player player, String header, String footer);
	
	void broadcastTabHeaderFooter(String header, String footer);
	
	void sendTabHeaderFooterReset(Player player);
	
	void broadcastTabHeaderFooterReset();
}
