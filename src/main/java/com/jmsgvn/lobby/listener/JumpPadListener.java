package com.jmsgvn.lobby.listener;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

/**
 * Enables a player to bounce on a sponge block
 */
public class JumpPadListener implements Listener {

    /**
     * Bounce the player when they land on a sponge block
     * @param event the PlayerMoveEvent
     */
    @EventHandler
    public void moveEvent(PlayerMoveEvent event) {
        if (event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.SPONGE) {
            return;
        }

        Player player = event.getPlayer();

        double x = player.getLocation().getDirection().getX();
        double y = player.getLocation().getDirection().getY();

        event.getPlayer().setVelocity(new Vector(x * 2.5, y + 1.0, 0.0));

        // probably do not want to send the sound to everyone as this one could get annoying
        event.getPlayer().playSound(player.getLocation(), Sound.STEP_LADDER, 10.0F, 5.0F);
        event.getPlayer().playEffect(player.getLocation(), Effect.STEP_SOUND, Material.SPONGE);
    }
}
