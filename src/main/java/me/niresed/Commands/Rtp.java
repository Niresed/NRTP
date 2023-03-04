package me.niresed.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class Rtp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("nrtp.rtp")) {
                player.teleport(RtpUtils.generateLocation(player));
            } else{
                player.sendMessage(ChatColor.RED + "" + "Sorry but you dont have permission (nrtp.rtp) if you think it is not n");
            }

        }
        return true;
    }
}
