package com.jmsgvn.lobby.command;

import com.jmsgvn.lobby.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Teleport to the world spawn
 */
public class SpawnCommand implements CommandExecutor {
    @Override public boolean onCommand(CommandSender commandSender, Command command, String s,
        String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You must be a player to execute this command.");
            return true;
        }

        Player player = (Player) commandSender;
        Location spawn = Lobby.getWorldSpawn(player.getWorld().getName()).clone();

        if (spawn != null) {
            player.teleport(spawn);
        } else {
            player.teleport(player.getWorld().getSpawnLocation());
        }

        player.sendMessage(ChatColor.GREEN + "You have been teleported to spawn.");
        return true;
    }
}
