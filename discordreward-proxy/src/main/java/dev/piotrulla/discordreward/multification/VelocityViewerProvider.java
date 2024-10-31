package dev.piotrulla.discordreward.multification;

import com.eternalcode.multification.viewer.ViewerProvider;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class VelocityViewerProvider implements ViewerProvider<CommandSource> {

    private final ProxyServer proxyServer;

    public VelocityViewerProvider(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    public CommandSource console() {
        return this.proxyServer.getConsoleCommandSource();
    }

    @Override
    public CommandSource player(UUID uuid) {
        return this.proxyServer.getPlayer(uuid).orElse(null);
    }

    @Override
    public Collection<CommandSource> onlinePlayers() {
        return this.proxyServer.getAllPlayers().stream().map(player -> (CommandSource) player).toList();
    }

    @Override
    public Collection<CommandSource> all() {
        Collection<CommandSource> all = this.onlinePlayers();
        all.add(this.console());

        return Collections.unmodifiableCollection(all);
    }
}
