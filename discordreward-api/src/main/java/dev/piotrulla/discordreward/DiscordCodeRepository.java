package dev.piotrulla.discordreward;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface DiscordCodeRepository {

    CompletableFuture<String> getOrCreate(UUID uniqueId);

    CompletableFuture<UUID> getUser(String code);
}
