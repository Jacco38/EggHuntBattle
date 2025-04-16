package org.jacco.eggHuntBattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jacco.eggHuntBattle.EggHuntBattle;
import org.jacco.eggHuntBattle.arenautils.Arena;
import org.jacco.eggHuntBattle.managers.ArenasManager;
import org.jacco.eggHuntBattle.managers.GameManager;
import org.jacco.eggHuntBattle.managers.PlayerManager;
import org.jacco.eggHuntBattle.utils.ColorUtils;
import org.jacco.eggHuntBattle.utils.GameState;

public class EggHuntBattleCommand implements CommandExecutor {

    private final EggHuntBattle plugin;
    private final GameManager gameManager;

    public EggHuntBattleCommand(EggHuntBattle plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        Arena arena;

        if (args.length == 0) {
            sender.sendMessage("Usage: /egghuntbattle <join | leave>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "join":

                if (args.length < 2) {
                    sender.sendMessage(ColorUtils.translateColorCodes("&cUsage: /egghuntbattle join <arena>"));
                    break;
                }

                if (ArenasManager.GetArena(args[1]) == null) {
                    sender.sendMessage(ColorUtils.translateColorCodes("&cArena not found."));
                    break;
                }

                arena = ArenasManager.GetArena(args[1]);

                if (PlayerManager.IsPlayerInGame(player)) {
                    sender.sendMessage(ColorUtils.translateColorCodes("&cYou are already in a game."));
                    break;
                }

                if (!arena.IsEnabled()) {
                    sender.sendMessage(ColorUtils.translateColorCodes("&cArena is not enabled."));
                    break;
                }

                if (gameManager.gameState != GameState.WAITING && gameManager.gameState != GameState.STARTING) {
                    sender.sendMessage(ColorUtils.translateColorCodes("&cGame is already in progress."));
                    break;
                }

                arena.PlayerJoin(player);

                break;
            case "leave":
                // Stop the game
                if (!PlayerManager.IsPlayerInGame(player)) {
                    sender.sendMessage(ColorUtils.translateColorCodes("&cYou are not in a game."));
                    break;
                }

                if (PlayerManager.GetPlayerGameArena(player) == null) {
                    sender.sendMessage(ColorUtils.translateColorCodes("&cYou are not in a game."));
                    break;
                }

                arena = PlayerManager.GetPlayerGameArena(player);
                arena.PlayerLeave(player);

                break;
            default:
                sender.sendMessage(ColorUtils.translateColorCodes("&cUnknown command. Usage: /egghuntbattle <join | leave>"));
                break;
        }
        return true;
    }
}
