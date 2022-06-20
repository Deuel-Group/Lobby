package com.jmsgvn.lobby.listener;

import com.jmsgvn.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;

/**
 * Handles the player join event to ensure the player is logged into the game correctly
 */
public class PlayerJoinListener implements Listener {

    /**
     * Handles the player join event and ensures the player is an appropriate lobby player
     * @param event the join event
     */
    @EventHandler
    public void playerJoinServer(PlayerJoinEvent event) {
        // A regular player should be in adventure mode and not lose hunger
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();

        Location spawn = Lobby.getWorldSpawn(player.getWorld().getName()).clone();

        if (spawn != null) {
            player.teleport(spawn);
        } else {
            player.teleport(player.getWorld().getSpawnLocation());
        }

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setGameMode(GameMode.ADVENTURE);

        inventory.clear();
        inventory.setArmorContents(null);

        // Add players custom items here (lobby items)

        event.setJoinMessage(null);

        if (Lobby.getInstance().getConfig().getBoolean("clear-chat")) {
            for (int i = 0; i < 100; i++) {
                player.sendMessage("");
            }
        }

        for (String string : Lobby.getInstance().getConfig().getStringList("join-message")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
        }

        Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), player::updateInventory, 1L);
    }
}
