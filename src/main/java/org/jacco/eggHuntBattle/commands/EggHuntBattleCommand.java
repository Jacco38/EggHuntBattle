package org.jacco.eggHuntBattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jacco.eggHuntBattle.EggHuntBattle;
import org.jacco.eggHuntBattle.utils.ColorUtils;

public class EggHuntBattleCommand implements CommandExecutor {

    private final EggHuntBattle plugin;

    public EggHuntBattleCommand(EggHuntBattle plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /egghuntbattle <join | leave>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "join":
                // Start the game
                break;
            case "leave":
                // Stop the game
                break;
            default:
                sender.sendMessage(ColorUtils.translateColorCodes("&cUnknown command. Usage: /egghuntbattle <join | leave>"));
                break;
        }
        return true;
    }
}
