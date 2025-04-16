package org.jacco.eggHuntBattle.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    private final Plugin plugin;

    public TabCompleter(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> ehbCommands = new ArrayList<>();
        ehbCommands.add("egghuntbattle");
        ehbCommands.add("ehb");

        // Check if the command is /egghuntbattle or /ehb
        if (ehbCommands.contains(command.getName().toLowerCase())) {
            List<String> list = new ArrayList<>();
            list.add("join");
            list.add("leave");

            return list;

        }

        return null;
    }
}
