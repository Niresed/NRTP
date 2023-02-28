package me.niresed.Commands;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.object.TownBlock;

import java.util.Objects;

public class check implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            Town town = null;
            try {
                town = TownyAPI.getInstance().getTownBlock(player.getLocation()).getTown();
            } catch (NotRegisteredException e) {
                if (town == null){
                    System.out.println("S");
                }
            }
            if (TownyAPI.getInstance().isWilderness(player.getLocation()))
                System.out.println("Check Работает");
        }
        return true;
    }
}
