package com.eldoggo.displayapi.nms;

import org.bukkit.entity.Player;

public interface NMS {

	public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut);
	
	public void broadcastTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut);
	
	public void sendTitleReset(Player player);
	
	public void broadcastTitleReset();
	
	public void sendActionMessage(Player player, String message);
	
	public void broadcastActionMessage(String message);

	public void sendTabHeaderFooter(Player player, String header, String footer);
	
	public void broadcastTabHeaderFooter(String header, String footer);
	
	public void sendTabHeaderFooterReset(Player player);
	
	public void broadcastTabHeaderFooterReset();
}
