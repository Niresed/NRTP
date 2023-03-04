package me.niresed;

import me.niresed.Commands.RTP;
import me.niresed.Listeners.RTPEvent;
import me.niresed.Listeners.Hello;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Hello(), this);
        getServer().getPluginManager().registerEvents(new RTPEvent(), this);
        Objects.requireNonNull(getCommand("RTP")).setExecutor(new RTP());
    }
}
