package org.jacco.eggHuntBattle.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jacco.eggHuntBattle.arenautils.Arena;
import org.jacco.eggHuntBattle.managers.ArenasManager;
import org.jacco.eggHuntBattle.managers.PlayerManager;

public class PlayerBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (!PlayerManager.IsPLayerInEdit(player)) {
            return;
        }

        Block placedBlock = event.getBlockPlaced();

        // Check if the block is an Easter egg
        if (placedBlock.getType() == Material.PLAYER_HEAD) {
            Location blockLocation = placedBlock.getLocation();

            Arena arena = PlayerManager.GetPlayerEditArena(player);

            ArenasManager.AddEggSpawn(arena, blockLocation);

        }

    }

    @EventHandler
    public void OnBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (!PlayerManager.IsPLayerInEdit(player)) {
            return;
        }

        Block block = event.getBlock();

        // Check if the block is an Easter egg
        if (block.getType() == Material.PLAYER_HEAD) {
            Location blockLocation = block.getLocation();

            Arena arena = PlayerManager.GetPlayerEditArena(player);

            ArenasManager.RemoveEggSpawn(arena, blockLocation);

        }
    }

}
