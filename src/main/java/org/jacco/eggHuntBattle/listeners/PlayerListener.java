package org.jacco.eggHuntBattle.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jacco.eggHuntBattle.managers.PlayerManager;

public class PlayerListener implements Listener {

    @EventHandler
    public void OnPlayerPvp(EntityDamageByEntityEvent event) {

        if (event.getEntity().getType() != EntityType.PLAYER) {
            return;
        }

        Player player = (Player) event.getDamager();

        if (PlayerManager.IsPlayerInGame(player)) {
            event.setCancelled(true);
        }
    }

    // On Disconnect
    @EventHandler
    public void OnPlayerDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (PlayerManager.IsPlayerInGame(player)) {

            PlayerManager.RestorePlayerInventory(player);
            PlayerManager.RestorePlayerLocation(player);

            PlayerManager.SetPlayerInGame(player, false);
            PlayerManager.SetPlayerGameArena(player, null);

        }

        if (PlayerManager.IsPlayerInEdit(player)) {
            PlayerManager.RestorePlayerInventory(player);
            PlayerManager.SetPlayerInEdit(player, false, null);
        }
    }

}
