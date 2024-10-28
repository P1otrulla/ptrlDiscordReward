package dev.piotrulla.discordreward;

import java.util.List;
import java.util.UUID;

public interface DiscordServerReward {

    UUID serverId();

    List<RewardType> rewards();

    enum RewardType {
        COMMANDS,
        ITEMS,
        MONEY
    }
}
