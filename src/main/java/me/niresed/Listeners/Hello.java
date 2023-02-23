package me.niresed.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
public class Hello implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.YELLOW +  "" + ChatColor.BOLD + player.getDisplayName() + " зашёл все вахуи");
    }

}
