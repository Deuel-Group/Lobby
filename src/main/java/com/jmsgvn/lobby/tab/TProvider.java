package com.jmsgvn.lobby.tab;

import com.jmsgvn.deuellib.tab.TabLayout;
import com.jmsgvn.deuellib.tab.TabProvider;
import com.jmsgvn.deuellib.tab.common.TabListCommons;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Provide the tab for all players online
 */
public class TProvider implements TabProvider {
    @Override public TabLayout provide(Player player) {
        TabLayout layout = new TabLayout();

        layout.set(1, 3, ChatColor.GREEN + "Welcome");
        layout.set(2, 3, ChatColor.GREEN + player.getName());

        layout.set(1, 4, ChatColor.GOLD + "Position");

        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();

        layout.set(2, 4, ChatColor.GOLD + "(" + x + ", " + y + ", " + z + ")");

        layout.setHeader(ChatColor.YELLOW + "Lobby");
        layout.setFooter(ChatColor.YELLOW + "play.lobby.com");

        layout.setSkinTextures(2, 3, TabListCommons.TV_TEXTURE);
        return layout;
    }
}
