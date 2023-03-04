package me.niresed;

import me.niresed.Commands.Rtp;
import me.niresed.Listeners.RtpRespawnEvent;
import me.niresed.Listeners.Hello;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Hello(), this);
        getServer().getPluginManager().registerEvents(new RtpRespawnEvent(), this);
        Objects.requireNonNull(getCommand("RTP")).setExecutor(new Rtp());
    }
}
