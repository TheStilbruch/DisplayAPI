package com.stilbruch.displayapi.nms;

import com.stilbruch.displayapi.DisplayAPI;
import net.minecraft.server.v1_10_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Collection;

public class DisplayAPI_1_10_R1 implements DisplayAPI {

	@Override
	public void sendTitle(Player player, String title, String subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {
		if ((title == null || title.isEmpty()) && (subtitle == null || subtitle.isEmpty())) {
			return;
		}

		final PacketPlayOutTitle resetPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, null);
		final PacketPlayOutTitle timePacket = new PacketPlayOutTitle(fadeInTicks, stayTicks, fadeOutTicks);

		PacketPlayOutTitle titlePacket = null;
		PacketPlayOutTitle subtitlePacket = null;

		if (title != null) {
			IChatBaseComponent titleComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
			titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleComponent);
		}

		if (subtitle != null) {
			IChatBaseComponent subtitleComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
			subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleComponent);
		}

		//Getting the connection object is faster than using sendPacket in the situation
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(resetPacket);
		connection.sendPacket(timePacket);

		//Only send the packets that are needed to save speed
		if (titlePacket != null) {
			connection.sendPacket(titlePacket);
		}
		if (subtitlePacket != null) {
			connection.sendPacket(subtitlePacket);
		}
	}

	@Override
	public void sendTitle(Collection<Player> players, String title, String subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {
		if ((title == null || title.isEmpty()) && (subtitle == null || subtitle.isEmpty())) {
			return;
		}

		final PacketPlayOutTitle resetPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, null);
		final PacketPlayOutTitle timePacket = new PacketPlayOutTitle(fadeInTicks, stayTicks, fadeOutTicks);

		PacketPlayOutTitle titlePacket = null;
		PacketPlayOutTitle subtitlePacket = null;

		if (title != null) {
			IChatBaseComponent titleComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
			titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleComponent);
		}

		if (subtitle != null) {
			IChatBaseComponent subtitleComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
			subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleComponent);
		}

		for (Player player : players){

			//Getting the connection object is faster than using sendPacket in the situation
			PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
			connection.sendPacket(resetPacket);
			connection.sendPacket(timePacket);

			//Only send the packets that are needed to save speed
			if (titlePacket != null) {
				connection.sendPacket(titlePacket);
			}
			if (subtitlePacket != null) {
				connection.sendPacket(subtitlePacket);
			}
		}
	}

	@Override
	public void sendTitleReset(Player player) {

		final PacketPlayOutTitle resetPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, null);
		sendPacket(player, resetPacket);
	}

	@Override
	public void sendTitleReset(Collection<Player> players) {

		final PacketPlayOutTitle resetPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, null);
		players.forEach(player -> sendPacket(player, resetPacket));
	}

	@Override
	public void sendActionMessage(Player player, String message) {

		final PacketPlayOutChat chatPacket = new PacketPlayOutChat(new ChatComponentText(message), (byte) 2);
		sendPacket(player, chatPacket);
	}

	@Override
	public void sendActionMessage(Collection<Player> players, String message) {

		final PacketPlayOutChat chatPacket = new PacketPlayOutChat(new ChatComponentText(message), (byte) 2);
		players.forEach(player -> sendPacket(player, chatPacket));
	}


	@Override
	public void sendTablist(Player player, String header, String footer) {

		final PacketPlayOutPlayerListHeaderFooter tablistPacket = new PacketPlayOutPlayerListHeaderFooter();

		IChatBaseComponent headerComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
		IChatBaseComponent footerComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");

		try {
			Field headerField = tablistPacket.getClass().getDeclaredField("a");
			Field footerField = tablistPacket.getClass().getDeclaredField("b");
			headerField.setAccessible(true);
			footerField.setAccessible(true);
			headerField.set(tablistPacket, headerComp);
			footerField.set(tablistPacket, footerComp);
			headerField.setAccessible(false);
			footerField.setAccessible(false);

		} catch (Exception e) {
			Bukkit.getLogger().warning("Error sending tablist!");
			e.printStackTrace();
		}

		sendPacket(player, tablistPacket);
	}


	@Override
	public void sendTablist(Collection<Player> players, String header, String footer) {

		final PacketPlayOutPlayerListHeaderFooter tablistPacket = new PacketPlayOutPlayerListHeaderFooter();

		IChatBaseComponent headerComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
		IChatBaseComponent footerComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");

		try {
			Field headerField = tablistPacket.getClass().getDeclaredField("a");
			Field footerField = tablistPacket.getClass().getDeclaredField("b");
			headerField.setAccessible(true);
			footerField.setAccessible(true);
			headerField.set(tablistPacket, headerComp);
			footerField.set(tablistPacket, footerComp);
			headerField.setAccessible(false);
			footerField.setAccessible(false);

		} catch (Exception e) {
			Bukkit.getLogger().warning("Error sending tablist!");
			e.printStackTrace();
		}

		players.forEach(player -> sendPacket(player, tablistPacket));

	}

	@Override
	public void sendTablistReset(Player player) {
		//This seems like cheating, but.....
		sendTablist(player, "", "");
	}

	@Override
	public void sendTablistReset(Collection<Player> players) {
		sendTablist(players, "", "");
	}

	//Sends a packet to the player
	private void sendPacket(Player player, Packet packet){
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

}
