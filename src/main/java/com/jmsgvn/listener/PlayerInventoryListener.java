package com.jmsgvn.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.PlayerInventory;

/**
 * Handles the PlayerInventoryEvent to ensure lobby items are working
 */
public class PlayerInventoryListener implements Listener {

    @EventHandler
    public void playerInventory(InventoryInteractEvent event) {
        if (!(event.getInventory() instanceof PlayerInventory)) {
            return;
        }
    }
}
