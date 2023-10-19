package me.niresed.nrtp.Commands;

import org.bukkit.Bukkit;
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
        String permissionError = plugin.getConfig().getString("permission error");
        String playerError = plugin.getConfig().getString("player error");
        String commandError = plugin.getConfig().getString("command error");

        if (sender instanceof Player player) {
            if (player.hasPermission("nrtp.rtp")) {
                if (args.length == 0) {
                    player.teleport(RtpUtils.generateLocation(player));

                } else if (args.length == 1) {
                    Player rtpPlayer = Bukkit.getPlayer(args[0]);

                    if (rtpPlayer != null) {
                        rtpPlayer.teleport(RtpUtils.generateLocation(rtpPlayer));
                    } else {
                        player.sendMessage(ChatColor.RED + playerError);
                    }

                } else {
                    player.sendMessage(ChatColor.RED + commandError);
                }
            } else {
                player.sendMessage(ChatColor.RED + permissionError);

            }

        } else {
            if (args.length == 1) {

                Player player = Bukkit.getPlayer(args[0]);

                if (player != null) {
                    player.teleport(RtpUtils.generateLocation(player));
                } else {
                    Bukkit.getLogger().info(ChatColor.RED + playerError);
                }

            } else {
                Bukkit.getLogger().info(ChatColor.RED + commandError);
            }
        }
        return true;
    }
}
