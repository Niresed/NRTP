package me.niresed.Commands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RTPEvent implements Listener {
    @EventHandler
    public void onRTP(PlayerJoinEvent e) {
        Player player = e.getPlayer();

    }
}
