package com.jmsgvn.lobby.tab;

import com.jmsgvn.deuellib.scoreboard.ScoreboardProvider;
import com.jmsgvn.deuellib.util.Redis;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

/**
 * Provide the scoreboard for all players online
 */
public class SProvider implements ScoreboardProvider {

    private static Map<UUID, String> queues = new HashMap<>();
    @Override public void provide(LinkedList<String> linkedList, Player player) {
        linkedList.add(ChatColor.GRAY + "" + ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
        linkedList.add("&c    ");
        linkedList.add(ChatColor.YELLOW + "Welcome " + player.getName());

        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();

        linkedList.add("&e*" + x + ", " + y + ",  " + z);

        if (queues.containsKey(player.getUniqueId())) {
            String queue = queues.get(player.getUniqueId());

            String[] response = queue.split(",");

            if (response.length == 4) {
                UUID uuid = UUID.fromString(response[0]);
                String name = response[1];
                String pos = response[2];
                String size = response[3];

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

    public static Map<UUID, String> getQueues() {
        return queues;
    }
}
