package me.niresed.nrtp.Listeners;

import me.niresed.nrtp.Commands.RtpUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RtpJoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.teleport(RtpUtils.generateLocation(player));
        }

    }

}
