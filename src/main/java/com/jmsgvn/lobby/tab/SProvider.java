package com.jmsgvn.lobby.tab;

import com.jmsgvn.deuellib.scoreboard.ScoreboardProvider;
import com.jmsgvn.deuellib.util.Redis;
import org.bukkit.ChatColor;
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

        String queue = Redis.get("overwatch." + player.getUniqueId().toString());

        if (!queue.equalsIgnoreCase("-1")) {

            String[] response = queue.split(",");

            if (response.length == 3) {
                String name = response[0];
                String pos = response[1];
                String size = response[2];

                linkedList.add("&d    ");
                linkedList.add("&eQueue: &6" + name);
                linkedList.add("&ePosition: &6*" + pos + "/" + size);
            }
        } else {
            linkedList.add("&c    ");
        }

        linkedList.add(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
    }

    @Override public String title(Player player) {
        return "&e&lThe Lobby &7- &eLobby";
    }
}
