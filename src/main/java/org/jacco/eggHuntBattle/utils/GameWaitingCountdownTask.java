package org.jacco.eggHuntBattle.utils;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jacco.eggHuntBattle.arenautils.Arena;
import org.jacco.eggHuntBattle.managers.GameManager;

public class GameWaitingCountdownTask extends BukkitRunnable {

    private GameManager gameManager;
    private int timeLeft;
    private int waitingTime;
    private Arena arena;

    public GameWaitingCountdownTask(GameManager gameManager, int timeLeft, Arena arena) {
        this.gameManager = gameManager;
        this.timeLeft = timeLeft;
        this.arena = arena;
        this.waitingTime = timeLeft;
    }

    @Override
    public void run() {
        timeLeft--;

        if (timeLeft < 0) {
            gameManager.SetGameState(arena, GameState.STARTING);
            cancel();
        } else if (timeLeft == 0) {

            int players = arena.GetPlayers().size();
            int minPlayers = arena.GetMinPlayers();

            if (players < minPlayers) {
                for (Player player : arena.GetPlayers()) {
                    player.sendMessage(ColorUtils.translateColorCodes("&cNot enough players to start the game. Waiting for more players..."));
                }
                timeLeft = waitingTime;
            }

        }
    }
}
