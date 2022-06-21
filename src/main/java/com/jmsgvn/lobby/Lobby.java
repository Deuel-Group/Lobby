package com.jmsgvn.lobby;

import com.jmsgvn.deuellib.scoreboard.ScoreboardManager;
import com.jmsgvn.deuellib.tab.TabManager;
import com.jmsgvn.lobby.listener.*;
import com.jmsgvn.lobby.tab.*;
import com.jmsgvn.lobby.command.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the creation and destruction of the plugin.
 */
public class Lobby extends JavaPlugin {

    /**
     * Used to store the location of spawns. This is done to allow for custom world spawns that take
     * exact player position into account.
     */
    private static Map<String, Location> spawns;

    /**
     * Enables all essential classes for the plugin to function
     */
    @Override public void onEnable() {
        super.onEnable();

        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Loading Lobby...");
        getServer().getConsoleSender().sendMessage("");

        long start = System.currentTimeMillis();

        saveDefaultConfig();

        loadListeners();
        loadSettings();
        loadCommands();

        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(
            ChatColor.GREEN + "Lobby has been " + "enabled (" + (System.currentTimeMillis() - start)
                + " ms)");
    }

    /**
     * Disables all essential classes for the plugin to shut down correctly
     */
    @Override public void onDisable() {
        super.onDisable();
        long start = System.currentTimeMillis();

        getServer().getConsoleSender().sendMessage(
            ChatColor.RED + "Lobby has been " + "disabled(" + (System.currentTimeMillis() - start)
                + " ms)");
    }

    /**
     * Load event listeners
     */
    private void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new GeneralPreventionListener(), this);
        Bukkit.getPluginManager().registerEvents(new DoubleJumpListener(), this);
        Bukkit.getPluginManager().registerEvents(new JumpPadListener(), this);

        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender()
            .sendMessage(ChatColor.YELLOW + "    Listeners have been enabled");
        getServer().getConsoleSender().sendMessage("");
    }

    /**
     * Sets default values for all worlds so the lobby experience is flawless
     */
    private void loadSettings() {

        TabManager.setProvider(new TProvider());
        ScoreboardManager.setProvider(new SProvider());
        ScoreboardManager.setScoreboardTitle(
            ChatColor.YELLOW + "" + ChatColor.BOLD + "The Server" + ChatColor.RESET + ""
                + ChatColor.GRAY + " \uFF5C " + ChatColor.YELLOW + "Lobby");
        spawns = new HashMap<>();

        getServer().getConsoleSender().sendMessage("");

        for (World world : Bukkit.getWorlds()) {

            getServer().getConsoleSender().sendMessage(
                ChatColor.YELLOW + "    World " + world.getName() + "'s"
                    + " settings are being loaded");

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

                if (entity instanceof LivingEntity || entity instanceof Item
                    || entity instanceof ExperienceOrb) {
                    entity.remove();
                }
            }

            if (getConfig().contains("spawn-point." + world.getName())) {
                double x = getConfig().getDouble("spawn-point." + world.getName() + ".x");
                double y = getConfig().getDouble("spawn-point." + world.getName() + ".y");
                double z = getConfig().getDouble("spawn-point." + world.getName() + ".z");
                float pitch = Float.parseFloat(
                    getConfig().getString("spawn-point." + world.getName() + ".pitch"));
                float yaw = Float.parseFloat(
                    getConfig().getString("spawn-point." + world.getName() + ".yaw"));

                Location location = new Location(world, x, y, z, yaw, pitch);
                spawns.put(world.getName(), location);
                getServer().getConsoleSender()
                    .sendMessage(ChatColor.YELLOW + "        " + world.getName() + " spawn set");
            }
        }

        getServer().getConsoleSender().sendMessage("");
    }


    /**
     * Load the server commands
     */
    private void loadCommands() {
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand());

        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender()
            .sendMessage(ChatColor.YELLOW + "    Commands have been enabled");
        getServer().getConsoleSender().sendMessage("");
    }

    /**
     * Static method to get the lobby plugin instance
     *
     * @return Lobby
     */
    public static Lobby getInstance() {
        return JavaPlugin.getPlugin(Lobby.class);
    }

    /**
     * Get the world spawn location. If there is no saved location a null location will be returned
     *
     * @param world the name of the world
     * @return the saved location of the worlds spawn.
     */
    public static Location getWorldSpawn(String world) {
        return spawns.get(world);
    }

    /**
     * Update a worlds spawn location
     *
     * @param world    the world to be updated
     * @param location the new spawn location to be set
     */
    public static void updateSpawnLocation(World world, Location location) {
        spawns.put(world.getName(), location);
    }
}
