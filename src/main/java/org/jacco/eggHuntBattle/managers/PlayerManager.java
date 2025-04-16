package org.jacco.eggHuntBattle.managers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerManager {

    static HashMap<Player, HashMap<String, Object>> playersState = new HashMap<>();
    static HashMap<Player, HashMap<String, ItemStack[]>> playersItems = new HashMap<>();

    public static Boolean IsPLayerInEdit(Player player) {
        if (playersState.containsKey(player)) {
            if (playersState.get(player).containsKey("editing")) {
                return (Boolean) playersState.get(player).get("editing");
            }
        }

        return false;
    }

    public static void SetPlayerInEdit(Player player, Boolean editing) {
        if (!playersState.containsKey(player)) {
            playersState.put(player, new HashMap<>());
        }

        playersState.get(player).put("editing", editing);
    }

    public static void SavePlayerInventory(Player player, ItemStack[] items, ItemStack[] armor) {
        if (!playersItems.containsKey(player)) {
            playersItems.put(player, new HashMap<>());
        }

        playersItems.get(player).put("items", items);
        playersItems.get(player).put("armor", armor);
    }

    public static ItemStack[] GetPlayerInventory(Player player) {
        if (playersItems.containsKey(player)) {
            if (playersItems.get(player).containsKey("items")) {
                return playersItems.get(player).get("items");
            }
        }

        return null;
    }

    public static ItemStack[] GetPlayerArmor(Player player) {
        if (playersItems.containsKey(player)) {
            if (playersItems.get(player).containsKey("armor")) {
                return playersItems.get(player).get("armor");
            }
        }

        return null;
    }

}
