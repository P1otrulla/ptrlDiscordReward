package dev.piotrulla.discordreward;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface DiscordServerRewardRepository {

    CompletableFuture<Boolean> isRewardCollected(UUID uniqueId, DiscordServerReward reward);

    CompletableFuture<Void> collectReward(UUID uniqueId, DiscordServerReward reward);

}
