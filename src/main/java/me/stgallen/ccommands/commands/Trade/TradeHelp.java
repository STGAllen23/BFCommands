package me.stgallen.ccommands.commands.Trade;

import com.google.common.collect.Lists;
import com.sun.javafx.collections.MappingChange;
import me.stgallen.ccommands.Commands;
import me.stgallen.ccommands.PluginInfo;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.service.pagination.PaginationService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.*;

public class TradeHelp implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource source, CommandContext args) throws CommandException {
        Map<List<String>, CommandSpec> commands = Commands.Tradecommands;
        List<Text> helpList = Lists.newArrayList();

        for (List<String> aliases : commands.keySet()) {
            CommandSpec commandSpec = commands.get(aliases);

            Text commandHelp = Text.builder()
                    .append(Text.builder()
                            .append(Text.of(TextColors.GOLD, "/trade " + aliases.toString().replace("[", "").replace("]", "")))
                            .build())
                    .append(Text.builder()
                            .append(Text.of(TextColors.WHITE, " - " + commandSpec.getShortDescription(source).get().toPlain() + "\n"))
                            .build())
                    .append(Text.builder()
                            .append(Text.of(TextColors.GRAY, "Usage: /trade " + aliases.toString().replace("[", "").replace("]", "") + " " + commandSpec.getUsage(source).toPlain()))
                            .build())
                    .build();
            helpList.add(commandHelp);
        }

        helpList.sort(Text::compareTo);
        PaginationService paginationService = Sponge.getServiceManager().provide(PaginationService.class).get();
        PaginationList.Builder paginationBuilder = paginationService.builder().title(Text.of(TextColors.GOLD, "BattleForce Commands v" + PluginInfo.Version)).padding(Text.of(TextColors.DARK_GREEN, "-")).contents(helpList).linesPerPage(14);
        paginationBuilder.sendTo(source);

        return CommandResult.success();
    }
}