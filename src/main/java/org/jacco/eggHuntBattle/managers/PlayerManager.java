package org.jacco.eggHuntBattle.managers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jacco.eggHuntBattle.arenautils.Arena;

import java.util.HashMap;

public class PlayerManager {

    static HashMap<Player, HashMap<String, Object>> playersState = new HashMap<>();
    static HashMap<Player, HashMap<String, ItemStack[]>> playersItems = new HashMap<>();

    public static Boolean IsPlayerInEdit(Player player) {
        if (playersState.containsKey(player)) {
            if (playersState.get(player).containsKey("editing")) {
                return (Boolean) playersState.get(player).get("editing");
            }
        }

        return false;
    }

    public static void SetPlayerInEdit(Player player, Boolean editing, Arena arena) {
        if (!playersState.containsKey(player)) {
            playersState.put(player, new HashMap<>());
        }

        playersState.get(player).put("editing", editing);
        playersState.get(player).put("editarena", arena);
    }

    public static void SavePlayerInventory(Player player) {

        ItemStack[] items = player.getInventory().getContents();
        ItemStack[] armor = player.getInventory().getArmorContents();

        if (!playersItems.containsKey(player)) {
            playersItems.put(player, new HashMap<>());
        }

        playersItems.get(player).put("items", items);
        playersItems.get(player).put("armor", armor);

        player.getInventory().clear();
    }

    public static void RestorePlayerInventory(Player player) {
        if (playersItems.containsKey(player)) {
            if (playersItems.get(player).containsKey("items")) {

                player.getInventory().clear();

                ItemStack[] items = playersItems.get(player).get("items");
                ItemStack[] armor = playersItems.get(player).get("armor");

                player.getInventory().setContents(items);
                player.getInventory().setArmorContents(armor);

                playersItems.remove(player);
            }
        }
    }

    public static void RestoreAllPlayersInventory() {
        for (Player player : playersItems.keySet()) {
            if (playersItems.get(player).containsKey("items")) {

                player.getInventory().clear();

                ItemStack[] items = playersItems.get(player).get("items");
                ItemStack[] armor = playersItems.get(player).get("armor");

                player.getInventory().setContents(items);
                player.getInventory().setArmorContents(armor);

                playersItems.remove(player);
            }
        }
    }

    public static Arena GetPlayerEditArena(Player player) {
        if (playersState.containsKey(player)) {
            if (playersState.get(player).containsKey("editarena")) {
                return (Arena) playersState.get(player).get("editarena");
            }
        }
        return null;
    }

    public static boolean IsPlayerInGame(Player player) {
        if (playersState.containsKey(player)) {
            if (playersState.get(player).containsKey("inGame")) {
                return (Boolean) playersState.get(player).get("inGame");
            }
        }
        return false;
    }

    public static void SetPlayerInGame(Player player, Boolean inGame) {
        if (!playersState.containsKey(player)) {
            playersState.put(player, new HashMap<>());
        }

        playersState.get(player).put("inGame", inGame);
    }

    public static void SetPlayerGameArena(Player player, Arena arena) {
        if (!playersState.containsKey(player)) {
            playersState.put(player, new HashMap<>());
        }

        playersState.get(player).put("arena", arena);
    }

    public static void SavePlayerLocation(Player player) {
        if (!playersState.containsKey(player)) {
            playersState.put(player, new HashMap<>());
        }

        playersState.get(player).put("location", player.getLocation());
    }

    public static void RestorePlayerLocation(Player player) {
        if (playersState.containsKey(player)) {
            if (playersState.get(player).containsKey("location")) {
                player.teleport((org.bukkit.Location) playersState.get(player).get("location"));
            }
        }
    }

    public static Arena GetPlayerGameArena(Player player) {
        if (playersState.containsKey(player)) {
            if (playersState.get(player).containsKey("arena")) {
                return (Arena) playersState.get(player).get("arena");
            }
        }
        return null;
    }

}
