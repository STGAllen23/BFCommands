package me.stgallen.ccommands;

import me.stgallen.ccommands.commands.*;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.nio.file.Path;
import java.util.*;
@Plugin(
        id = PluginInfo.Id,
        name = PluginInfo.Name,
        version = PluginInfo.Version,
        authors = PluginInfo.Authors
)
public class Commands  {

    public static Map<List<String>, CommandSpec> Subcommands = new HashMap<>();

    public static Map<List<String>, CommandSpec> PCCommands = new HashMap<>();
    public static Map<List<String>, CommandSpec> DenyCommands= new HashMap<>();

    private static Commands commands;
    public static Commands getCommands() {return commands;}

    @Inject
    private Logger _logger;
    public Logger getLogger(){ return _logger; }

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path _configDir;
    public Path getConfigDir(){
        return _configDir;
    }

    @Listener
    public void onGameInitialization(GameStartedServerEvent event){
        commands = this;
        initCommands();
        initPCCommands();

            _logger.info("[ExtraCommands] Started.");
    }


    private void initCommands() {
        Subcommands.put(Arrays.asList("help"), CommandSpec.builder()
                .description(Text.of("Displays all available commands."))
                .permission(PluginPermissions.HELP_COMMAND)
                .executor(new HelpCommand())
                .build());

        Subcommands.put(Arrays.asList("allow", "a", "true"), CommandSpec.builder()
                .description(Text.of("Allow Trainer Access"))
                .permission(PluginPermissions.ALLOW_COMMAND)
                .executor(new TrainerAllowCommand())
                .build());
        Subcommands.put(Arrays.asList("deny", "d", "false"), CommandSpec.builder()
                .description(Text.of("Deny Trainer Access"))
                .permission(PluginPermissions.DENY_COMMAND)
                .executor(new TrainerDeny())
                .build());

        CommandSpec trainerCommand = CommandSpec.builder()
                .description(Text.of("Allow pc/trainer interaction"))
                .executor(new TrainerHelp())
                .children(Subcommands)
                .build();
        Sponge.getCommandManager().register(this, trainerCommand, "trainer");
    }
    private void initPCCommands() {

        PCCommands.put(Arrays.asList("help"), CommandSpec.builder()
                .description(Text.of("Displays all available commands."))
                .permission(PluginPermissions.HELP_COMMAND)
                .executor(new PCHelp())
                .build());

        PCCommands.put(Arrays.asList("allow", "a", "true"), CommandSpec.builder()
                .description(Text.of("Allow PC/Trainer Access"))
                .permission(PluginPermissions.ALLOW_COMMAND)
                .executor(new PCAllowCommand())
                .build());
        PCCommands.put(Arrays.asList("deny", "d", "false"), CommandSpec.builder()
                .description(Text.of("Deny PC Access"))
                .permission(PluginPermissions.DENY_COMMAND)
                .executor(new PCDeny())
                .build());

        CommandSpec PcCommand = CommandSpec.builder()
                .description(Text.of("Allow Trainer Interaction"))
                .executor(new PCHelp())
                .children(PCCommands)
                .build();
        Sponge.getCommandManager().register(this, PcCommand, "pc");

    }
}
