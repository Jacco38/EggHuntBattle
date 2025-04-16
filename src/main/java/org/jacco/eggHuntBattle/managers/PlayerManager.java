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
            }
        }
    }

}
