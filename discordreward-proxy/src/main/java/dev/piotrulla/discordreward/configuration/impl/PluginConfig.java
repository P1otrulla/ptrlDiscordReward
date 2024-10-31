package dev.piotrulla.discordreward.configuration.impl;

import com.eternalcode.multification.notice.Notice;
import eu.okaeri.configs.OkaeriConfig;

public class PluginConfig extends OkaeriConfig {

    public Notice discordInfo = Notice.chat(
            "&6Nasz discord: &9https://discord.gg/",
            "",
            "&7/discord link &e- połącz swoje konto z discordem",
            "&7/discord unlink &e- rozłącz swoje konto z discordem",
            "&7/discord info &e- informacje o połączeniu"
    );

    public Notice link = Notice.chat(
            "&6Aby połączyć swoje konto z discordem",
            "&6wejdź na nasz serwer discord",
            "&6i wpisz wygenerowany kod w grze",
            "",
            "&6Kod: &7{CODE}"
    );

    public Notice unlink = Notice.chat("&CTwoje konto zostało rozłączone z discordem!");

    public Notice alreadyLinked = Notice.chat("&cTwoje konto jest już połączone z discordem!");
    public Notice notLinked = Notice.chat("&cTwoje konto nie jest połączone z discordem!");

    public Notice accountInfo = Notice.chat(
            "&6Twoje konto jest połączone z discordem!",
            "",
            "&6Nick: &7{NICK}",
            "&6Discord: &7{DISCORD}",
            "&6Kiedy połączono: &7{DATE}"
    );
}
