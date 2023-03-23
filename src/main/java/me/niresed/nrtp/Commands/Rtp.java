package me.niresed.nrtp.Commands;

import org.bukkit.plugin.Plugin;
import me.niresed.nrtp.NRTP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class Rtp implements CommandExecutor {
    Plugin plugin = NRTP.getPlugin(NRTP.class);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String errorReasons = plugin.getConfig().getString("error reason RTP");
            if(player.hasPermission("nrtp.rtp")) {
                player.teleport(RtpUtils.generateLocation(player));
            } else{
                player.sendMessage(ChatColor.RED + "" + errorReasons);
            }

        }
        return true;
    }
}
