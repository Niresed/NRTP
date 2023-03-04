package me.niresed.Listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Town;
import me.niresed.Commands.RTPUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RTPEvent implements Listener {
    @EventHandler
    public void onRTP(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Town town = TownyAPI.getInstance().getTown(player);
        if (town == null) {
            player.teleport(RTPUtils.generateLocation(player));
        }

    }
}
