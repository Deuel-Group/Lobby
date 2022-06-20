package com.jmsgvn;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {

    @Override public void onEnable() {
        super.onEnable();
        getLogger().info(ChatColor.GREEN + "Lobby has been enabled.");
    }

    @Override public void onDisable() {
        super.onDisable();
        getLogger().info(ChatColor.RED + "Lobby has been disabled.");
    }
}
