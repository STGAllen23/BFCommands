package me.stgallen.ccommands.commands.Trade;

import me.stgallen.ccommands.PluginInfo;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class TradeDeny implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource source, CommandContext args)
    {
        Player player = (Player) source;
        source.sendMessage(Text.of(PluginInfo.PluginPrefix, TextColors.RED, "Trade interaction Disabled."));
        Sponge.getCommandManager().process(player, "cf interact-block-secondary pixelmon:trade_machine false");
        Sponge.getCommandManager().process(player, "cf interact-inventory pixelmon:trade_machine false");
        return CommandResult.success();
    }
}
