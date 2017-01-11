package com.eldoggo.displayapi;

import org.bukkit.entity.Player;

public class DisplayAPI {
	
	private DisplayAPIPlugin plugin;
	
	public DisplayAPI(DisplayAPIPlugin plugin){
		this.plugin = plugin;
	}
	
	/**
	 * Send a title to the player
	 * 
	 * @param player The player to send to
	 * @param title The title
	 * @param subtitle The subtitle
	 * @param fadeInTicks The time the title takes to fade in, in ticks
	 * @param stayTicks The time the title stays, it ticks
	 * @param fadeOutTicks The time the title takes to fade out, in ticks
	 */
	public void sendTitle(Player player, String title, String subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks){
		plugin.getNMS().sendTitle(player, title, subtitle, fadeInTicks, stayTicks, fadeOutTicks);
	}
	
	/**
	 * Send a title to all players in the server
	 * 
	 * @param title The title
	 * @param subtitle The subtitle
	 * @param fadeInTicks The time the title takes to fade in, in ticks
	 * @param stayTicks The time the title stays, it ticks
	 * @param fadeOutTicks The time the title takes to fade out, in ticks
	 */
	public void broadcastTitle(String title, String subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks){
		plugin.getNMS().broadcastTitle(title, subtitle, fadeInTicks, stayTicks, fadeOutTicks);
	}
	
	/**
	 * Resets the players title
	 * 
	 * @param player
	 */
	public void sendTitleReset(Player player){
		plugin.getNMS().sendTitleReset(player);
	}
	
	/**
	 * Resets the title of every player in the server
	 */
	public void broadcastTitleReset(){
		plugin.getNMS().broadcastTitleReset();
	}
	
	/**
	 * Send an action message to the Player
	 * 
	 * @param player The player to send to
	 * @param message The message to display
	 */
	public void sendActionMessage(Player player, String message){
		plugin.getNMS().sendActionbarMessage(player, message);
	}
	
	/**
	 * Send an action message to every player
	 * 
	 * @param message The message to display
	 */
	public void broadcastActionMessage(String message){
		plugin.getNMS().broadcastActionbarMessage(message);
	}
	
	/**
	 * Set the players Tab header and footer. Use the \n character for multiple lines
	 * 
	 * @param player
	 * @param header The tab header
	 * @param footer The tab footer
	 */
	public void sendTabHeaderFooter(Player player, String header, String footer){
		plugin.getNMS().sendTabHeaderFooter(player, header, footer);
	}
	
	/**
	 * Set the Tab header and footer of all players. Use the \n character for multiple lines
	 * 
	 * @param header The tab header
	 * @param footer The tab footer
	 */
	public void broadcastTabHeaderFooter(String header, String footer){
		plugin.getNMS().broadcastTabHeaderFooter(header, footer);
	}
	
	/**
	 * Reset the tab header and footer of a player
	 * 
	 * @param player
	 */
	public void sendTabHeaderFooterReset(Player player){
		plugin.getNMS().resetTabHeaderFooter(player);
	}
	
	/**
	 * Reset the tab header and footer of all players
	 */
	public void broadcastTabHeaderFooterReset(){
		plugin.getNMS().broadcastResetTabHeaderFooter();
	}
}
