package com.jmsgvn;

import com.jmsgvn.listener.PlayerJoinListener;
import com.jmsgvn.listener.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Handles the creation and destruction of the plugin.
 */
public class Lobby extends JavaPlugin {

    private static Lobby instance;

    /**
     * Enables all essential classes for the plugin to function
     */
    @Override public void onEnable() {
        super.onEnable();

        instance = this;

        loadListeners();
        loadSettings();

        getLogger().info(ChatColor.GREEN + "Lobby has been enabled.");
    }

    /**
     * Disables all essential classes for the plugin to shut down correctly
     */
    @Override public void onDisable() {
        super.onDisable();
        getLogger().info(ChatColor.RED + "Lobby has been disabled.");
    }

    /**
     * Load event listeners
     */
    private void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getLogger().info(ChatColor.YELLOW + "Listeners have been enabled.");
    }

    /**
     * Sets default values for all worlds so the lobby experience is flawless
     */
    private void loadSettings() {
        for (World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.PEACEFUL);
            world.setAmbientSpawnLimit(0);
            world.setAnimalSpawnLimit(0);
            world.setPVP(false);
            world.setStorm(false);
            world.setWeatherDuration(0);

            world.setGameRuleValue("doDaylightCycle", "false");
            world.setGameRuleValue("doMobSpawning", "false");
            world.setTime(6_000L);

            for (Entity entity : world.getEntities()) {
                if (entity instanceof Player) {
                    continue;
                }

                if (entity instanceof LivingEntity || entity instanceof Item ||
                    entity instanceof ExperienceOrb) {
                    entity.remove();
                }
            }

            getLogger().info(ChatColor.YELLOW + "World " + world.getName() + "'s"
                + " settings have been successfully applied.");
        }
    }

    public static Lobby getInstance() {
        return instance;
    }
}
