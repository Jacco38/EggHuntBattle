package org.jacco.eggHuntBattle.arenautils;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jacco.eggHuntBattle.managers.PlayerManager;
import org.jacco.eggHuntBattle.utils.EasterEgg;
import org.jacco.eggHuntBattle.utils.GameState;

import java.util.ArrayList;
import java.util.HashMap;

public class Arena {

    private String name;
    private World world;
    private Location playerSpawn;
    private Location lobbySpawn;
    private HashMap<Location, EasterEgg> eggSpawns = new HashMap<>();

    private ArrayList<Player> players = new ArrayList<>();

    private GameState gameState;

    public Arena(String name, World world, Location playerSpawn, Location lobbySpawn, HashMap<Location, EasterEgg> eggSpawns) {
        this.name = name;
        this.world = world;
        this.playerSpawn = playerSpawn;
        this.eggSpawns = eggSpawns;
        this.gameState = GameState.WAITING;
        this.lobbySpawn = lobbySpawn;
    }

    public void ResetArena() {

        // for every egg spawn
        for (Location loc : eggSpawns.keySet()) {
            EasterEgg egg = eggSpawns.get(loc);
            Location eggLoc = loc.clone();

            world.setBlockData(eggLoc, egg.GetBlockData());
        }

    }

    public void LoadGame() {
        gameState = GameState.STARTING;

        // Countdown for 5 seconds
        for (int i = 5; i > 0; i--) {
            for (Player player : players) {
                player.sendMessage("Game starts in " + i + " seconds!");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        StartGame();
    }

    public void StartGame() {

        for (Player player : players) {
            player.teleport(playerSpawn);
            player.setGameMode(GameMode.SURVIVAL);

            PlayerManager.SavePlayerInventory(player);
        }

        gameState = GameState.INGAME;
    }

    public void StopArena() {
        gameState = GameState.ENDING;

        for (Player player : players) {
            player.teleport(playerSpawn);
            player.setGameMode(GameMode.SPECTATOR);

            PlayerManager.RestorePlayerInventory(player);
        }
    }

    public String GetName() {
        return name;
    }

    public World GetWorld() {
        return world;
    }

    public Location GetPlayerSpawn() {
        return playerSpawn;
    }

    public void SetPlayerSpawn(Location location) {
        this.playerSpawn = location;
    }

    public Location GetLobbySpawn() {
        return lobbySpawn;
    }

    public void SetLobbySpawn(Location location) {
        this.lobbySpawn = location;
    }

    public HashMap<Location, EasterEgg> GetEggSpawns() {
        return eggSpawns;
    }

}
