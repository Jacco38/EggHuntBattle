package org.jacco.eggHuntBattle.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jacco.eggHuntBattle.EggHuntBattle;
import org.jacco.eggHuntBattle.managers.PlayerManager;

public class PlayerBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (!PlayerManager.IsPLayerInEdit(player)) {
            return;
        }

        EggHuntBattle.Log(event.getBlockPlaced().getBlockData().getAsString());

        event.setCancelled(true);

    }

}
