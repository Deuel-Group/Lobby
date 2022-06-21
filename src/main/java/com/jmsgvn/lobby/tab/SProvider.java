package com.jmsgvn.lobby.tab;

import com.jmsgvn.deuellib.scoreboard.ScoreboardProvider;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class SProvider implements ScoreboardProvider {
    @Override public void provide(LinkedList<String> linkedList, Player player) {
        linkedList.add(ChatColor.GRAY + "" + ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
        linkedList.add("");
        linkedList.add(ChatColor.YELLOW + "Welcome " + player.getName());
        linkedList.add(ChatColor.YELLOW + "Ping " + ChatColor.GOLD + ((CraftPlayer) player).getHandle().ping + "ms");
        linkedList.add("");
        linkedList.add(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
    }
}
