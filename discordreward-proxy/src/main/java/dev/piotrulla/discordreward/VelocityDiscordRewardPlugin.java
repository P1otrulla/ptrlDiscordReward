package dev.piotrulla.discordreward;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.piotrulla.discordreward.configuration.ConfigService;
import dev.piotrulla.discordreward.configuration.impl.PluginConfig;
import dev.piotrulla.discordreward.multification.DiscordMultification;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.adventure.LiteAdventureExtension;
import dev.rollczi.litecommands.velocity.LiteVelocityFactory;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.io.File;
import java.nio.file.Path;

import static dev.rollczi.litecommands.message.LiteMessages.INVALID_USAGE;
import static dev.rollczi.litecommands.message.LiteMessages.MISSING_PERMISSIONS;
import static dev.rollczi.litecommands.velocity.LiteVelocityMessages.PLAYER_NOT_FOUND;
import static dev.rollczi.litecommands.velocity.LiteVelocityMessages.PLAYER_ONLY;

@Plugin(
        id = "ptrl-discordreward",
        name = "ptrlDiscordReward",
        version = "1.0.0",
        authors = {"Piotrulla"}
)
public class VelocityDiscordRewardPlugin {

    private final ProxyServer server;
    private final Path dataFolder;

    private MiniMessage miniMessage;

    private LiteCommands<CommandSource> liteCommands;

    @Inject
    public VelocityDiscordRewardPlugin(ProxyServer server, Path dataFolder) {
        this.server = server;
        this.dataFolder = dataFolder;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        ConfigService configService = new ConfigService();
        PluginConfig pluginConfig = configService.create(PluginConfig.class, new File(this.dataFolder.toFile(), "config.yml"));

        this.miniMessage = MiniMessage.builder()
                .build();

        DiscordMultification multification = new DiscordMultification(pluginConfig, this.miniMessage, this.server);

        // TODO: implement config messages
        this.liteCommands = LiteVelocityFactory.builder(this.server)
                .extension(new LiteAdventureExtension<>(), settings -> settings
                        .miniMessage(true)
                        .legacyColor(true)
                        .colorizeArgument(true)
                        .serializer(this.miniMessage)
                )
                .commands(new DiscordCommand(multification, null, null))
                .message(INVALID_USAGE, usage -> this.miniMessage.deserialize("invalid usage"))
                .message(MISSING_PERMISSIONS, ignored -> this.miniMessage.deserialize("no permission"))
                .message(PLAYER_ONLY, ignored -> this.miniMessage.deserialize("player only"))
                .message(PLAYER_NOT_FOUND, input -> this.miniMessage.deserialize("player not found"))
                .build();
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        this.liteCommands.unregister();
    }
}
