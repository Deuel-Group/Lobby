package com.jmsgvn.command;

import com.jmsgvn.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Set the world spawn
 */
public class SetSpawnCommand implements CommandExecutor {

    @Override public boolean onCommand(CommandSender commandSender, Command command, String s,
        String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You must be a player to execute this command.");
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.isOp() || !player.hasPermission("lobby.setspawn")) {
            commandSender.sendMessage(ChatColor.RED + "You do not have permission to run this command.");
            return true;
        }

        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        float pitch = player.getLocation().getPitch();
        float yaw = player.getLocation().getYaw();

        Lobby.getInstance().getConfig().set("spawn-point." + player.getWorld().getName() + ".x", x);
        Lobby.getInstance().getConfig().set("spawn-point." + player.getWorld().getName() + ".y", y);
        Lobby.getInstance().getConfig().set("spawn-point." + player.getWorld().getName() + ".z", z);
        Lobby.getInstance().getConfig().set("spawn-point." + player.getWorld().getName() + ".pitch", pitch);
        Lobby.getInstance().getConfig().set("spawn-point." + player.getWorld().getName() + ".yaw", yaw);

        Lobby.getInstance().saveConfig();

        Location location = new Location(player.getWorld(), x, y, z, yaw, pitch);
        Lobby.updateSpawnLocation(player.getWorld(), location);

        player.sendMessage(ChatColor.GREEN + "The spawn-point has been updated successfully.");
        return true;
    }
}
