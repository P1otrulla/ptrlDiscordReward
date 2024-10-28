package dev.piotrulla.discordreward;

import java.util.UUID;

public interface DiscordLinkEntry {

    UUID uniqueId();

    long discordId();

    String minecraftName();

}
