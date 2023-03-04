package me.niresed.Listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Town;
import me.niresed.Commands.RTPUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RTPEvent implements Listener {
    @EventHandler
    public void onRTP(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        System.out.println(player);
        Town town = TownyAPI.getInstance().getTown(player);
        System.out.println(town);
        if (town == null) {
            Location s = RTPUtils.generateLocation(player);
            System.out.println(s);
            player.teleport(s);
        }
    }
}
