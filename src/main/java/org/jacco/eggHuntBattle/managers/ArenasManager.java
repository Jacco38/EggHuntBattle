package org.jacco.eggHuntBattle.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.jacco.eggHuntBattle.EggHuntBattle;
import org.jacco.eggHuntBattle.arenautils.Arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArenasManager {

    private final Plugin plugin;

    public ArenasManager(Plugin plugin) {
        this.plugin = plugin;
    }

    private static HashMap<String, Arena> arenas = new HashMap<>();

    public static void AddArena(String name, Arena arena) {
        arenas.put(name, arena);

        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".name", name);
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".enabled", false);
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".max-players", 30);
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".min-players", 2);

        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.world", arena.GetPlayerSpawn().getWorld().getName());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.x", arena.GetPlayerSpawn().getX());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.y", arena.GetPlayerSpawn().getY());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.z", arena.GetPlayerSpawn().getZ());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.yaw", arena.GetPlayerSpawn().getYaw());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.pitch", arena.GetPlayerSpawn().getPitch());

        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.world", arena.GetLobbySpawn().getWorld().getName());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.x", arena.GetLobbySpawn().getX());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.y", arena.GetLobbySpawn().getY());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.z", arena.GetLobbySpawn().getZ());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.yaw", arena.GetLobbySpawn().getYaw());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.pitch", arena.GetLobbySpawn().getPitch());

        EggHuntBattle.SaveArenasConfig();

    }

    public static void RemoveArena(String name) {
        arenas.remove(name);
        EggHuntBattle.GetArenasConfig().set("arenas." + name, null);
        EggHuntBattle.SaveArenasConfig();
    }

    public static Arena GetArena(String name) {
        if (!arenas.containsKey(name)) {
            return null;
        }
        return arenas.get(name);
    }

    public static void ClearArenas() {
        arenas.clear();
    }

    public static void SetArenaSpawn(Arena arena, Location location) {
        String name = arena.GetName();
        arena.SetPlayerSpawn(location);

        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.world", arena.GetPlayerSpawn().getWorld().getName());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.x", arena.GetPlayerSpawn().getX());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.y", arena.GetPlayerSpawn().getY());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.z", arena.GetPlayerSpawn().getZ());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.yaw", arena.GetPlayerSpawn().getYaw());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".spawn.pitch", arena.GetPlayerSpawn().getPitch());

        EggHuntBattle.SaveArenasConfig();
    }

    public static void SetArenaLobby(Arena arena, Location location) {
        String name = arena.GetName();
        arena.SetLobbySpawn(location);

        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.world", arena.GetLobbySpawn().getWorld().getName());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.x", arena.GetLobbySpawn().getX());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.y", arena.GetLobbySpawn().getY());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.z", arena.GetLobbySpawn().getZ());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.yaw", arena.GetLobbySpawn().getYaw());
        EggHuntBattle.GetArenasConfig().set("arenas." + name + ".lobby.pitch", arena.GetLobbySpawn().getPitch());

        EggHuntBattle.SaveArenasConfig();
    }

    public static void LoadArenas() {
        ClearArenas();
        EggHuntBattle.GetArenasConfig().getConfigurationSection("arenas").getKeys(false).forEach(arenaName -> {
            EggHuntBattle.Log("Loading arena " + arenaName);

            World spawnWorld = Bukkit.getWorld(EggHuntBattle.GetArenasConfig().get("arenas." + arenaName + ".spawn.world").toString());
            Location spawnLocation = new Location(spawnWorld,
                    EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".spawn.x"),
                    EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".spawn.y"),
                    EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".spawn.z"),
                    (float) EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".spawn.yaw"),
                    (float) EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".spawn.pitch"));

            World lobbyWorld = Bukkit.getWorld(EggHuntBattle.GetArenasConfig().get("arenas." + arenaName + ".lobby.world").toString());
            Location lobbyLocation = new Location(lobbyWorld,
                    EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".lobby.x"),
                    EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".lobby.y"),
                    EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".lobby.z"),
                    (float) EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".lobby.yaw"),
                    (float) EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".lobby.pitch"));

            List<Location> eggSpawns = new ArrayList<Location>();

            EggHuntBattle.GetArenasConfig().getConfigurationSection("arenas." + arenaName + ".eggSpawns").getKeys(false).forEach(eggSpawnName -> {
                World eggWorld = Bukkit.getWorld(EggHuntBattle.GetArenasConfig().get("arenas." + arenaName + ".eggSpawns." + eggSpawnName + ".world").toString());
                Location eggLocation = new Location(eggWorld,
                        EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".eggSpawns." + eggSpawnName + ".x"),
                        EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".eggSpawns." + eggSpawnName + ".y"),
                        EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".eggSpawns." + eggSpawnName + ".z"),
                        (float) EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".eggSpawns." + eggSpawnName + ".yaw"),
                        (float) EggHuntBattle.GetArenasConfig().getDouble("arenas." + arenaName + ".eggSpawns." + eggSpawnName + ".pitch"));
                eggSpawns.add(eggLocation);
            });

            int time = EggHuntBattle.GetArenasConfig().getInt("arenas." + arenaName + ".time-limit");

            AddArena(arenaName, new Arena(arenaName, spawnWorld, spawnLocation, lobbyLocation, eggSpawns, time));

        });

    }

    public static void AddEggSpawn(Arena arena, Location location) {
        arena.AddEggSpawn(location);

        if (EggHuntBattle.GetArenasConfig().get("arenas." + arena.GetName() + ".eggSpawns") == null) {
            EggHuntBattle.GetArenasConfig().set("arenas." + arena.GetName() + ".eggSpawns", null);
        }

        int name = arena.GetEggSpawns().size() + 1;

        boolean done = false;

        while (done == false) {
            if (EggHuntBattle.GetArenasConfig().get("arenas." + arena.GetName() + ".eggSpawns." + name) == null) {
                EggHuntBattle.GetArenasConfig().set("arenas." + arena.GetName() + ".eggSpawns." + name + ".world", location.getWorld().getName());
                EggHuntBattle.GetArenasConfig().set("arenas." + arena.GetName() + ".eggSpawns." + name + ".x", location.getX());
                EggHuntBattle.GetArenasConfig().set("arenas." + arena.GetName() + ".eggSpawns." + name + ".y", location.getY());
                EggHuntBattle.GetArenasConfig().set("arenas." + arena.GetName() + ".eggSpawns." + name + ".z", location.getZ());
                EggHuntBattle.GetArenasConfig().set("arenas." + arena.GetName() + ".eggSpawns." + name + ".yaw", location.getYaw());
                EggHuntBattle.GetArenasConfig().set("arenas." + arena.GetName() + ".eggSpawns." + name + ".pitch", location.getPitch());
                done = true;
            } else {
                name++;
            }
        }

        EggHuntBattle.SaveArenasConfig();

    }

    public static void RemoveEggSpawn(Arena arena, Location location) {
        arena.GetEggSpawns().remove(location);

        EggHuntBattle.GetArenasConfig().getConfigurationSection("arenas." + arena.GetName() + ".eggSpawns").getKeys(false).forEach(eggSpawnName -> {
            if (EggHuntBattle.GetArenasConfig().get("arenas." + arena.GetName() + ".eggSpawns." + eggSpawnName + ".x").equals(location.getX()) &&
                    EggHuntBattle.GetArenasConfig().get("arenas." + arena.GetName() + ".eggSpawns." + eggSpawnName + ".y").equals(location.getY()) &&
                    EggHuntBattle.GetArenasConfig().get("arenas." + arena.GetName() + ".eggSpawns." + eggSpawnName + ".z").equals(location.getZ())) {
                EggHuntBattle.GetArenasConfig().set("arenas." + arena.GetName() + ".eggSpawns." + eggSpawnName, null);
            }
        });

        EggHuntBattle.SaveArenasConfig();

    }

    public static int GetEggSpawnCount(Arena arena) {
        return arena.GetEggSpawns().size();
    }

    public static List<String> GetArenas() {
        return new ArrayList<>(arenas.keySet());
    }

}
