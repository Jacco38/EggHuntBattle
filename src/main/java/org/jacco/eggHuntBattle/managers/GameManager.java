package org.jacco.eggHuntBattle.managers;

import org.jacco.eggHuntBattle.EggHuntBattle;
import org.jacco.eggHuntBattle.arenautils.Arena;
import org.jacco.eggHuntBattle.utils.GameState;
import org.jacco.eggHuntBattle.utils.GameWaitingCountdownTask;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameManager {

    private final EggHuntBattle plugin;
    public GameState gameState = GameState.WAITING;

    private List<Arena> enabledArenas = new LinkedList<>();
    private HashMap<Arena, GameState> arenasGameState = new HashMap<>();

    public GameManager(EggHuntBattle plugin) {
        this.plugin = plugin;
    }

    public void LoadEnabledArenas() {
        List<String> arenas = ArenasManager.GetArenas();
        for (String arena : arenas) {
            Arena a = ArenasManager.GetArena(arena);
            if (a.IsEnabled()) {
                // Load the arena
                plugin.getLogger().info("Loading arena: " + a.GetName());
                enabledArenas.add(a);
                SetGameState(a, GameState.WAITING);
            } else {
                plugin.getLogger().info("Skipping arena: " + a.GetName());
            }
        }
    }

    public void SetGameState(Arena arena, GameState gameState) {
        this.gameState = gameState;
        switch (gameState) {

            case WAITING:
                arenasGameState.put(arena, GameState.WAITING);
                GameWaitingCountdownTask countdownTask = new GameWaitingCountdownTask(this, 30, arena);
                countdownTask.runTaskTimer(plugin, 0, 20);
                break;

            case STARTING:
                arenasGameState.put(arena, GameState.STARTING);
                break;

            case INGAME:
                arenasGameState.put(arena, GameState.INGAME);
                break;

            case ENDING:
                arenasGameState.put(arena, GameState.ENDING);
                break;

        }
    }

}
