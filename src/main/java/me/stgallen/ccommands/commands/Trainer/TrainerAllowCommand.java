package me.stgallen.ccommands.commands.Trainer;

import me.stgallen.ccommands.PluginInfo;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class TrainerAllowCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        Player player = (Player) src;
        src.sendMessage(Text.of(PluginInfo.PluginPrefix, TextColors.GOLD, "Trainer interaction allowed"));
        Sponge.getCommandManager().process(player, "cf interact-entity-secondary pixelmon:trainer true");
        return CommandResult.success();
    }
}

