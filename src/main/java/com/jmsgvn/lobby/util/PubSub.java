package com.jmsgvn.lobby.util;

import com.jmsgvn.lobby.Lobby;
import com.jmsgvn.lobby.tab.SProvider;
import net.md_5.bungee.api.ChatColor;
import redis.clients.jedis.JedisPubSub;

import java.util.UUID;

public class PubSub extends JedisPubSub {

    @Override public void onMessage(String channel, String message) {
        if (channel.equalsIgnoreCase("overwatch")) {
            String[] result = message.split(",");

            if (result.length == 2) {
                SProvider.getQueues().remove(UUID.fromString(result[0]));
            } else {
                SProvider.getQueues().put(UUID.fromString(result[0]), message);
            }
        }
    }

    @Override public void onUnsubscribe(String channel, int subscribedChannels) {
        Lobby.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "[PubSub] Unsubscribed to " + channel);
    }

    @Override public void onSubscribe(String channel, int subscribedChannels) {
        Lobby.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "[PubSub] Subscribed to " + channel);
    }
}
