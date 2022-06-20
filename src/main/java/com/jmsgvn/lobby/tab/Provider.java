package com.jmsgvn.lobby.tab;

import com.jmsgvn.deuellib.tab.TabLayout;
import com.jmsgvn.deuellib.tab.TabProvider;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Provider implements TabProvider {
    @Override public TabLayout provide(Player player) {
        TabLayout layout = new TabLayout();

        layout.set(1, 3, ChatColor.GREEN + "Welcome");
        layout.set(2, 3, ChatColor.GREEN + player.getName());

        layout.setHeader(ChatColor.YELLOW + "Lobby");
        layout.setFooter(ChatColor.YELLOW + "play.lobby.com");

        return layout;
    }
}
