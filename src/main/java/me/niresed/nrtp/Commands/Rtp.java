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
        // ошибки
        String reasonsError = plugin.getConfig().getString("error reason RTP");
        String playerError = plugin.getConfig().getString("player error RTP");
        String commandError = plugin.getConfig().getString("command error RTP");
        String lackOfUnderCommands = plugin.getConfig().getString("lack of under commands");
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("nrtp.rtp")) {
                if (args.length == 0){
                    player.teleport(RtpUtils.generateLocation(player));

                } else if (args.length == 1) {
                    for(Player onlinePlayers : Bukkit.getOnlinePlayers()){
                        if (onlinePlayers.getName().equals(args[0])){
                            onlinePlayers.teleport(RtpUtils.generateLocation(onlinePlayers));
                            return true;
                        }
                    }
                    player.sendMessage(ChatColor.RED + "" + playerError);

                } else {
                    player.sendMessage(ChatColor.RED + "" + commandError);

                }
            } else {
                player.sendMessage(ChatColor.RED + "" + reasonsError);

            }

        } else {
            if (args.length == 0){
                Bukkit.getLogger().info(ChatColor.RED + "" + lackOfUnderCommands);
                return true;

            } else if (args.length == 1) {
                for(Player onlinePlayers : Bukkit.getOnlinePlayers()){
                    if (onlinePlayers.getName().equals(args[0])){
                        onlinePlayers.teleport(RtpUtils.generateLocation(onlinePlayers));
                        return true;
                    }
                }
                Bukkit.getLogger().info(ChatColor.RED + "" + playerError);
                return true;
            }
            Bukkit.getLogger().info(ChatColor.RED + "" + commandError);

        }
        return true;
    }
}
