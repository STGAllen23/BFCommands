package me.stgallen.ccommands.commands;

import me.stgallen.ccommands.PluginInfo;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class PCDeny implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource source, CommandContext args) {
        Player player = (Player) source;
        source.sendMessage(Text.of(PluginInfo.PluginPrefix, TextColors.GREEN, "PC interaction Denied."));
        Sponge.getCommandManager().process(player, "cf interact-entity-secondary pixelmon:pc false");
        return CommandResult.success();
    }
}
