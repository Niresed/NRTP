package me.niresed.Listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;
import me.niresed.Commands.RtpUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RtpRespawnEvent implements Listener {
    @EventHandler
    public void onRTP(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Town town = TownyAPI.getInstance().getTown(player);
        if (town == null) {
            Location location = RtpUtils.generateLocation(player);
            event.setRespawnLocation(location);
        }
    }
}
