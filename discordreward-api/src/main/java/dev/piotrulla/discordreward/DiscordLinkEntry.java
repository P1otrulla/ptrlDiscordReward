package dev.piotrulla.discordreward;

import java.time.Instant;
import java.util.UUID;

public interface DiscordLinkEntry {

    UUID uniqueId();

    long discordId();

    String minecraftName();

    Instant linkedAt();

}
