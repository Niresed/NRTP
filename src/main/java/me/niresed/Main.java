package me.niresed;

import jdk.tools.jlink.plugin.Plugin;
import me.niresed.Commands.Rtp;
import me.niresed.Listeners.RtpJoinEvent;
import me.niresed.Listeners.RtpRespawnEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class Main extends JavaPlugin implements Listener{
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new RtpRespawnEvent(), this);
        getServer().getPluginManager().registerEvents(new RtpJoinEvent(), this);
        Objects.requireNonNull(getCommand("RTP")).setExecutor(new Rtp());
    }
}
