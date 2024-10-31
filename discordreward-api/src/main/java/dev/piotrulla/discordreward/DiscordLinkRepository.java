package dev.piotrulla.discordreward;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface DiscordLinkRepository {

    CompletableFuture<Boolean> isLinked(long discordId);

    CompletableFuture<Boolean> isLinked(UUID uniqueId);

    CompletableFuture<UUID> getUniqueId(long discordId);

    CompletableFuture<Long> getDiscordId(UUID uniqueId);

    CompletableFuture<DiscordLinkEntry> getLink(UUID uniqueId);

    CompletableFuture<Void> link(long discordId, UUID uniqueId);

    CompletableFuture<Void> unlink(long discordId);

    CompletableFuture<Void> unlink(UUID uniqueId);
}
