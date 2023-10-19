package me.niresed.nrtp;

import me.niresed.nrtp.Commands.Rtp;
import me.niresed.nrtp.Listeners.RtpJoinEvent;
import me.niresed.nrtp.Listeners.RtpRespawnEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NRTP extends JavaPlugin implements Listener {
        @Override
        public void onEnable() {
            getConfig().options().copyDefaults();
            saveDefaultConfig();

            registerEvents();
            Objects.requireNonNull(getCommand("rtp")).setExecutor(new Rtp());
        }

        private void registerEvents() {
            getServer().getPluginManager().registerEvents(this, this);
            getServer().getPluginManager().registerEvents(new RtpRespawnEvent(), this);
            getServer().getPluginManager().registerEvents(new RtpJoinEvent(), this);
        }
}

