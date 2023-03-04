package me.niresed.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RtpJoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        boolean ss = player.hasPlayedBefore();
        System.out.println(ss);
        if (ss) {
            System.out.println("играл");
        } else {
            System.out.println("не играл");
        }
    }

}
