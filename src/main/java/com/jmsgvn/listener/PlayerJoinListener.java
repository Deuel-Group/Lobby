package com.jmsgvn.listener;

import com.jmsgvn.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;

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

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setGameMode(GameMode.ADVENTURE);
        
        inventory.clear();
        inventory.setArmorContents(null);

        // Add players custom items here (lobby items)

        Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), player::updateInventory, 1L);
    }
}
