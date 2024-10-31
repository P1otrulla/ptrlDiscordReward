package dev.piotrulla.discordreward;

import com.eternalcode.multification.shared.Formatter;
import com.velocitypowered.api.proxy.Player;
import dev.piotrulla.discordreward.multification.DiscordMultification;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;

import java.time.format.DateTimeFormatter;

@Command(name = "discord")
public class DiscordCommand {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private final DiscordMultification multification;
    private final DiscordLinkRepository linkRepository;
    private final DiscordCodeRepository codeRepository;

    public DiscordCommand(DiscordMultification multification, DiscordLinkRepository linkRepository, DiscordCodeRepository codeRepository) {
        this.multification = multification;
        this.linkRepository = linkRepository;
        this.codeRepository = codeRepository;
    }

    @Execute
    public void info(@Context Player source) {
        this.multification.viewer(source, cfg -> cfg.discordInfo);
    }

    @Execute(name = "link")
    public void link(@Context Player source) {
        this.linkRepository.isLinked(source.getUniqueId()).thenAccept(isLinked -> {
            if (isLinked) {
                this.multification.viewer(source, cfg -> cfg.alreadyLinked);
                return;
            }

            this.codeRepository.getOrCreate(source.getUniqueId()).thenAccept(code -> {
                this.multification.viewer(source, cfg -> cfg.link, new Formatter().register("{CODE}", code));
            });
        });
    }

    @Execute(name = "unlink")
    public void unlink(@Context Player source) {
        this.linkRepository.isLinked(source.getUniqueId()).thenAccept(isLinked -> {
            if (!isLinked) {
                this.multification.viewer(source, cfg -> cfg.notLinked);
                return;
            }

            this.linkRepository.unlink(source.getUniqueId()).thenAccept(ignored -> {
                this.multification.viewer(source, cfg -> cfg.unlink);
            });
        });
    }

    @Execute(name = "info")
    public void accountInfo(@Context Player source) {
        this.linkRepository.getLink(source.getUniqueId()).thenAccept(link -> {
            if (link == null) {
                this.multification.viewer(source, cfg -> cfg.notLinked);
                return;
            }

            this.multification.viewer(source, cfg -> cfg.accountInfo, new Formatter()
                    .register("{NICK}", source.getUsername())
                    .register("{DISCORD}", link.discordId())
                    .register("{DATE}", FORMATTER.format(link.linkedAt())));
        });
    }
}
