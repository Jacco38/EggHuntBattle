package org.jacco.eggHuntBattle.arenautils;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.jacco.eggHuntBattle.managers.EggsManager;
import org.jacco.eggHuntBattle.managers.PlayerManager;
import org.jacco.eggHuntBattle.utils.ColorUtils;
import org.jacco.eggHuntBattle.utils.EasterEgg;
import org.jacco.eggHuntBattle.utils.Heads;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private String name;
    private World world;
    private Location playerSpawn;
    private Location lobbySpawn;
    private List<Location> eggLocations = new ArrayList<>();
    private boolean isEnabled;

    private List<Player> players = new ArrayList<>();

    private int maxPlayers;
    private int minPlayers;
    public Arena(String name, World world, Location playerSpawn, Location lobbySpawn, List<Location> eggSpawns, int secondsLeft, boolean isEnabled, int maxPlayers, int minPlayers) {
        this.name = name;
        this.world = world;
        this.playerSpawn = playerSpawn;
        this.eggLocations = eggSpawns;
        this.lobbySpawn = lobbySpawn;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.isEnabled = isEnabled;
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

    public List<Location> GetEggSpawns() {
        return eggLocations;
    }

    public void AddEggSpawn(Location location) {
        this.eggLocations.add(location);
    }

    public void SetEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public boolean IsEnabled() {
        return isEnabled;
    }

    // Game Editing
    public void ShowAllEggs() {
        for (Location location : eggLocations) {
            EasterEgg egg = EggsManager.GetNormalEgg();
            location.getBlock().setBlockData(egg.GetBlockData());

            Skull skull = (Skull) location.getBlock().getState();
            skull.setOwnerProfile(Heads.GetOwnerProfile(egg.GetUrl()));

            skull.update();
        }
    }

    public void HideEggs() {
        for (Location location : eggLocations) {
            location.getBlock().setType(org.bukkit.Material.AIR);
        }
    }

    public void AddPlayer(Player player) {
        players.add(player);
    }

    public List<Player> GetPlayers() {
        return players;
    }

    public int GetMaxPlayers() {
        return maxPlayers;
    }

    public int GetMinPlayers() {
        return minPlayers;
    }

    public void PlayerJoin(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }

        PlayerManager.SetPlayerInGame(player, true);
        PlayerManager.SetPlayerGameArena(player, this);
        PlayerManager.SavePlayerLocation(player);
        PlayerManager.SavePlayerInventory(player);

        player.teleport(lobbySpawn);
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setFoodLevel(20);

        for (Player p : players) {
            p.sendMessage(ColorUtils.translateColorCodes("&6" + player.getName() + " &ahas joined the game! &7(" + players.size() + "/" + maxPlayers + ")"));
        }

    }

    public void PlayerLeave(Player player) {

        for (Player p : players) {
            p.sendMessage(ColorUtils.translateColorCodes("&6" + player.getName() + " &chas left the game! &7(" + (players.size() - 1) + "/" + maxPlayers + ")"));
        }

        if (players.contains(player)) {
            players.remove(player);
        }

        PlayerManager.SetPlayerInGame(player, false);
        PlayerManager.SetPlayerGameArena(player, null);
        PlayerManager.RestorePlayerLocation(player);
        PlayerManager.RestorePlayerInventory(player);

    }

}
