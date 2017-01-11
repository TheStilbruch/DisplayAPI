package com.eldoggo.displayapi.nms;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_11_R1.ChatComponentText;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import net.minecraft.server.v1_11_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_11_R1.PlayerConnection;

public class NMS_1_11_R1 implements NMS {

	@Override
	public void sendTitle(Player player, String title, String subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {
		if (title == null && subtitle == null) {
			return;
		}

		PacketPlayOutTitle resetPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, (IChatBaseComponent) null);
		PacketPlayOutTitle timePacket = new PacketPlayOutTitle(fadeInTicks, stayTicks, fadeOutTicks);

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

		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(resetPacket);
		connection.sendPacket(timePacket);

		if (titlePacket != null) {
			connection.sendPacket(titlePacket);
		}
		if (subtitlePacket != null) {
			connection.sendPacket(subtitlePacket);
		}
	}

	@Override
	public void broadcastTitle(String title, String subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks) {
		if (title == null && subtitle == null) {
			return;
		}

		PacketPlayOutTitle resetPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, (IChatBaseComponent) null);
		PacketPlayOutTitle timePacket = new PacketPlayOutTitle(fadeInTicks, stayTicks, fadeOutTicks);

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

		for (Player player : Bukkit.getOnlinePlayers()){
			PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
			connection.sendPacket(resetPacket);
			connection.sendPacket(timePacket);

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
		PacketPlayOutTitle resetPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, (IChatBaseComponent) null);
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(resetPacket);
	}

	@Override
	public void broadcastTitleReset() {
		PacketPlayOutTitle resetPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, (IChatBaseComponent) null);
		
		for (Player player : Bukkit.getOnlinePlayers()){
			PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
			connection.sendPacket(resetPacket);
		}
	}

	@Override
	public void sendActionbarMessage(Player player, String message) {
		PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(message), (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}

	@Override
	public void broadcastActionbarMessage(String message) {
		for (Player player : Bukkit.getOnlinePlayers()){
			PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(message), (byte) 2);
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		}
	}

	
	@Override
	public void sendTabHeaderFooter(Player player, String header, String footer) {
		PacketPlayOutPlayerListHeaderFooter headerFooterPacket = new PacketPlayOutPlayerListHeaderFooter();
		
		IChatBaseComponent headerComp = ChatSerializer.a("{\"text\": \"" + header + "\"}");
		IChatBaseComponent footerComp = ChatSerializer.a("{\"text\": \"" + footer + "\"}");
		
		try {
			Field headerField = headerFooterPacket.getClass().getDeclaredField("a");
			Field footerField = headerFooterPacket.getClass().getDeclaredField("b");
			headerField.setAccessible(true);
			footerField.setAccessible(true);
			headerField.set(headerFooterPacket, headerComp);
			footerField.set(headerFooterPacket, footerComp);
			headerField.setAccessible(false);
			footerField.setAccessible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(headerFooterPacket);
		
	}


	@Override
	public void broadcastTabHeaderFooter(String header, String footer) {
		PacketPlayOutPlayerListHeaderFooter headerFooterPacket = new PacketPlayOutPlayerListHeaderFooter();
		
		IChatBaseComponent headerComp = ChatSerializer.a("{\"text\": \"" + header + "\"}");
		IChatBaseComponent footerComp = ChatSerializer.a("{\"text\": \"" + footer + "\"}");
		
		try {
			Field headerField = headerFooterPacket.getClass().getDeclaredField("a");
			Field footerField = headerFooterPacket.getClass().getDeclaredField("b");
			headerField.setAccessible(true);
			footerField.setAccessible(true);
			headerField.set(headerFooterPacket, headerComp);
			footerField.set(headerFooterPacket, footerComp);
			headerField.setAccessible(false);
			footerField.setAccessible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (Player player : Bukkit.getOnlinePlayers()){
			PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
			connection.sendPacket(headerFooterPacket);
		}
	}

	@Override
	public void resetTabHeaderFooter(Player player) {
		sendTabHeaderFooter(player, "", "");
	}

	@Override
	public void broadcastResetTabHeaderFooter() {
		broadcastTabHeaderFooter("", "");
	}
}
