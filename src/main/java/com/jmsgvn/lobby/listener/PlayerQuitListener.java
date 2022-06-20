package com.jmsgvn.lobby.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Handles the player quit event to ensure the player is logged out of the game correctly
 */
public class PlayerQuitListener implements Listener {

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }
}
