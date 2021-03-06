package io.github.spaicygaming.showbook;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class JoinListener implements Listener {

	private ShowBook main = ShowBook.getInstance();
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		new BukkitRunnable() {
			@Override
			public void run() {
				openBook(p);
			}
		}.runTaskLater(main, 20L);

	}

	private void openBook(Player p) {
		int slot = p.getInventory().getHeldItemSlot();

		ItemStack old = p.getInventory().getItem(slot);
		p.getInventory().setItem(slot, main.getWrittenBook());

		try {
			PacketContainer pContainer = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.CUSTOM_PAYLOAD);
			pContainer.getModifier().writeDefaults();

			// Using netty.io
			ByteBuf bf = Unpooled.buffer(256);
			bf.setByte(0, (byte) 0);
			bf.writerIndex(1);

			pContainer.getStrings().write(0, "MC|BOpen");
			pContainer.getModifier().write(1, MinecraftReflection.getPacketDataSerializer(bf));

			ProtocolLibrary.getProtocolManager().sendServerPacket(p, pContainer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		p.getInventory().setItem(slot, old);
	}

}
