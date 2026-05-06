package org.johnfries.trial;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.johnfries.trial.command.GreetCommand;
import org.johnfries.trial.command.TimecheckCommand;

public final class Trial extends JavaPlugin {

    private static final String DEFAULT_GREETING_TEMPLATE =
            "<gradient:#4facfe:#00f2fe>\u029C\u1D07\u029F\u029F\u1D0F %player%!</gradient> <#ffd166>%sender%</#ffd166> "
                    + "<#f8f9fa>\uA731\u1D07\u0274\u1D05\uA731 \u028F\u1D0F\u1D1C \u1D00 \u0262\u0280\u1D07\u1D07\u1D1B\u026A\u0274\u0262.</#f8f9fa>";
    private static final String DEFAULT_TIMECHECK_TEMPLATE =
            "<gradient:#4facfe:#00f2fe>\uA731\u1D07\u0280\u1D20\u1D07\u0280 \u1D1B\u026A\u1D0D\u1D07:</gradient> "
                    + "<#f8f9fa>%time%</#f8f9fa> <#9aa0a6>(%zone%)</#9aa0a6>";
    private static final String DEFAULT_TIMECHECK_FORMAT = "yyyy-MM-dd HH:mm:ss z";
    private static final String DEFAULT_TIMECHECK_ZONE = "system";

    @Override
    public void onEnable() {
        saveDefaultConfig();

        GreetCommand greetCommand = new GreetCommand(this, DEFAULT_GREETING_TEMPLATE);
        PluginCommand greet = requireCommand("greet");
        greet.setExecutor(greetCommand);
        greet.setTabCompleter(greetCommand);

        requireCommand("timecheck").setExecutor(
                new TimecheckCommand(
                        this,
                        DEFAULT_TIMECHECK_TEMPLATE,
                        DEFAULT_TIMECHECK_FORMAT,
                        DEFAULT_TIMECHECK_ZONE
                )
        );
    }

    private PluginCommand requireCommand(String commandName) {
        PluginCommand command = getCommand(commandName);
        if (command == null) {
            throw new IllegalStateException("Missing command in plugin.yml: " + commandName);
        }
        return command;
    }
}
