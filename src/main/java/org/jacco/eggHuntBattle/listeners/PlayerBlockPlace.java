package org.jacco.eggHuntBattle.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jacco.eggHuntBattle.managers.PlayerManager;

public class PlayerBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (!PlayerManager.IsPLayerInEdit(player)) {
            return;
        }

        Block placedBlock = event.getBlockPlaced();
        Location blockLocation = placedBlock.getLocation();

    }

}
