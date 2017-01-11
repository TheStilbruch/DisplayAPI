package com.eldoggo.displayapi.nms;

import org.bukkit.entity.Player;

public interface NMS {

	public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut);
	
	public void broadcastTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut);
	
	public void sendTitleReset(Player player);
	
	public void broadcastTitleReset();
	
	public void sendActionbarMessage(Player player, String message);
	
	public void broadcastActionbarMessage(String message);

	public void sendTabHeaderFooter(Player player, String header, String footer);
	
	public void broadcastTabHeaderFooter(String header, String footer);
	
	public void resetTabHeaderFooter(Player player);
	
	public void broadcastResetTabHeaderFooter();
}
