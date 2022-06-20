package com.jmsgvn.lobby.listener;

import com.jmsgvn.lobby.Lobby;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Ensure players do not take damage in the lobby and are teleported back to spawn if they fall
 * out of the world
 */
public class EntityDamageListener implements Listener {

    @EventHandler
    public void entityDamage(EntityDamageEvent event) {
        event.setCancelled(true);

        if (event.getCause() == EntityDamageEvent.DamageCause.VOID || event.getEntity().getLocation().getBlockY() < 0) {

            if (!(event.getEntity() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getEntity();

            Location spawn = Lobby.getWorldSpawn(player.getWorld().getName()).clone();

            if (spawn != null) {
                player.teleport(spawn);
            } else {
                player.teleport(player.getWorld().getSpawnLocation());
            }
        }
    }
}
