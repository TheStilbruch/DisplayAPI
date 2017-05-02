package com.eldoggo.displayapi;

import org.bukkit.entity.Player;

import java.util.Collection;

public interface DisplayAPI {

	/**
	 * Sends a Minecraft title to the player.
	 *
	 * @param player The player to send the title to
	 * @param title The first line of text in the title
	 * @param subtitle The second line of text in the title
	 * @param fadeInTicks The time the title takes to fade in, in ticks
	 * @param stackTicks The time the title stays, in ticks
	 * @param fadeOutTicks The time the title takes to fade out, in ticks
	 */
	void sendTitle(Player player, String title, String subtitle, int fadeInTicks, int stackTicks, int fadeOutTicks);


	/**
	 * Sends a Minecraft title to all players in the provided {@link Player}s. This has benefits over simply iterating over
	 * a collection of players and using {@link DisplayAPI#sendTitle(Player, String, String, int, int, int)} because it only creates
	 * the needed objects once, saving memory and time.
	 *
	 * @param players The players to send the title to
	 * @param title The first line of text in the title
	 * @param subtitle The second line of text in the title
	 * @param fadeInTicks The time the title takes to fade in, in ticks
	 * @param stackTicks The time the title stays, in ticks
	 * @param fadeOutTicks The time the title takes to fade out, in ticks
	 */
	void sendTitle(Collection<Player> players,  String title, String subtitle, int fadeInTicks, int stackTicks, int fadeOutTicks);

	/**
	 * Resets the title of the provided {@link Player}.
	 *
	 * @param player Player whose title to reset
	 */
	void sendTitleReset(Player player);

	/**
	 * Resets the title of the provided {@link Player}. This has benefits over simply iterating over
	 * a collection of players and using {@link DisplayAPI#sendTitleReset(Player)} because it only creates
	 * the needed objects once, saving memory and time.
	 *
	 * @param players Players to have their titles reset
	 */
	void sendTitleReset(Collection<Player> players);

	/**
	 * Sends an actionbar message to the provided {@link Player}
	 *
	 * @param player Player to send the message to
	 * @param message Message to send to the player
	 */
	void sendActionMessage(Player player, String message);

	/**
	 * Sends an actionbar message to the provided {@link Player}s
	 *
	 * @param players Players to send the message to
	 * @param message Message to send to the player
	 */
	void sendActionMessage(Collection<Player> players, String message);

	/**
	 * Sends a tablist to the provided {@link Player} (Menu that appears when the player presses tab ingame).
	 *
	 * @param player The player to send the tablist to
	 * @param header The header of the tablist
	 * @param footer The footer of the tablist
	 */
	void sendTablist(Player player, String header, String footer);

	/**
	 * Sends a Tablist to the provided {@link Player}s (Menu that appears when the player presses tab ingame). This has benefits over simply iterating over
	 * a collection of players and using {@link DisplayAPI#sendTablist(Player, String, String)} because it only creates
	 * the needed objects once, saving memory and time.
	 *
	 * @param players The players to send the tablist to
	 * @param header The header of the tablist
	 * @param footer The footer of the tablist
	 */
	void sendTablist(Collection<Player> players, String header, String footer);

	/**
	 * Resets the tablist of the provided {@link Player}.
	 *
	 * @param player Player whose tablist to reset
	 */
	void sendTablistReset(Player player);

	/**
	 * Resets the tablist (Menu that appears when the player presses tab ingame) of the provided {@link Player}. This has benefits over simply iterating over
	 * a collection of players and using {@link DisplayAPI#sendTablistReset(Player)} because it only creates
	 * the needed objects once, saving memory and time.
	 *
	 * @param players Players to have their titles reset
	 */
	void sendTablistReset(Collection<Player> players);

}
