package dev.piotrulla.discordreward.multification;

import com.eternalcode.multification.Multification;
import com.eternalcode.multification.adventure.AudienceConverter;
import com.eternalcode.multification.translation.TranslationProvider;
import com.eternalcode.multification.viewer.ViewerProvider;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.piotrulla.discordreward.configuration.impl.PluginConfig;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class DiscordMultification extends Multification<CommandSource, PluginConfig> {

    private final PluginConfig pluginConfig;
    private final MiniMessage miniMessage;
    private final ProxyServer proxyServer;

    public DiscordMultification(PluginConfig pluginConfig, MiniMessage miniMessage, ProxyServer proxyServer) {
        this.pluginConfig = pluginConfig;
        this.miniMessage = miniMessage;
        this.proxyServer = proxyServer;
    }

    @Override
    protected @NotNull ViewerProvider<CommandSource> viewerProvider() {
        return new VelocityViewerProvider(this.proxyServer);
    }

    @Override
    protected @NotNull ComponentSerializer<Component, Component, String> serializer() {
        return this.miniMessage;
    }

    @Override
    protected @NotNull TranslationProvider<PluginConfig> translationProvider() {
        return locale -> this.pluginConfig;
    }

    @Override
    protected @NotNull AudienceConverter<CommandSource> audienceConverter() {
        return source -> {
            if (source instanceof Audience) {
                return (Audience) source;
            }

            return Audience.empty();
        };
    }
}
