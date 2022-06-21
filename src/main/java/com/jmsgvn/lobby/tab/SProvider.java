package com.jmsgvn.lobby.tab;

import com.jmsgvn.deuellib.scoreboard.ScoreboardProvider;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.LinkedList;

/**
 * Provide the scoreboard for all players online
 */
public class SProvider implements ScoreboardProvider {
    @Override public void provide(LinkedList<String> linkedList, Player player) {
        linkedList.add(ChatColor.GRAY + "" + ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
        linkedList.add("&c    ");
        linkedList.add(ChatColor.YELLOW + "Welcome " + player.getName());

        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();

        linkedList.add("&e*" + x + ", " + y + ",  " + z);
        linkedList.add("&c    ");
        linkedList.add(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
    }

    @Override public String title(Player player) {
        return "&e&lThe Lobby &7- &eLobby";
    }
}
