package com.jmsgvn.listener;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * Handles the double jump event
 */
public class DoubleJumpListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        event.getPlayer().setAllowFlight(true);
    }

    @EventHandler
    public void toggleFlight(PlayerToggleFlightEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }

        Player player = event.getPlayer();

        player.setAllowFlight(false);
        player.setFlying(false);

        if (player.isInsideVehicle()) {
            event.setCancelled(true);
            return;
        }

        player.setVelocity(player.getLocation().clone().getDirection().multiply(2).setY(1.0));

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.canSee(player)) {
                onlinePlayer.playSound(player.getLocation(), Sound.EXPLODE, 1.0F, 4.0F);
                onlinePlayer.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, null);
            }
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }

        if (event.getPlayer().getAllowFlight()) {
            return;
        }

        // because the player.isOnGround() method is deprecated this is another way to check if they are in the air
        if (event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
            return;
        }

        event.getPlayer().setAllowFlight(true);
    }
}
