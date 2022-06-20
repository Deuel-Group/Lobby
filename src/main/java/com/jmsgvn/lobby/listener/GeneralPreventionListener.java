package com.jmsgvn.lobby.listener;

import com.jmsgvn.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * General listener to stop events that should not be taking place in the lobby
 */
public class GeneralPreventionListener implements Listener {

    @EventHandler
    public void playerKick(PlayerKickEvent event) {
        event.setLeaveMessage(null);
    }

    @EventHandler
    public void projectileHit(ProjectileHitEvent event) {
        if (event.getEntity().getType() == EntityType.ARROW) {
            event.getEntity().remove();
        }
    }

    @EventHandler
    public void foodChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void weatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void entitySpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG
            || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.getDrops().clear();
        event.setDroppedExp(0);
    }

    @EventHandler
    public void blockSpread(BlockSpreadEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void leaveDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void blockFade(BlockFadeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void blockForm(BlockFormEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void playerPortal(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        if (cannotPerform(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        if (cannotPerform(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void bucketFill(PlayerBucketFillEvent event) {
        if (cannotPerform(event.getPlayer())) {
            event.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), event.getPlayer()::updateInventory, 1L);
        }
    }

    @EventHandler
    public void bucketEmpty(PlayerBucketEmptyEvent event) {
        if (cannotPerform(event.getPlayer())) {
            event.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), event.getPlayer()::updateInventory, 1L);
        }
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent event) {
        if (cannotPerform(event.getPlayer())) {
            event.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), event.getPlayer()::updateInventory, 1L);
        } else {
            Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), ()-> event.getItemDrop().remove(), 1L);
        }
    }

    @EventHandler
    public void pickUp(PlayerPickupItemEvent event) {
        if (cannotPerform(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        if (cannotPerform(player)) {
            if (event.getClickedInventory() == player.getInventory()) {
                event.setCancelled(true);
                Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), player::updateInventory, 1L);
            }
        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (cannotPerform(event.getPlayer()) && event.getAction() == Action.PHYSICAL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void craft(CraftItemEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        if (cannotPerform((Player) event.getWhoClicked())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void itemDamage(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }

    private boolean cannotPerform(Player player) {
        return !player.isOp() || player.getGameMode() != GameMode.CREATIVE;
    }
}
