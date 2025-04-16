package org.jacco.eggHuntBattle.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.jacco.eggHuntBattle.managers.PlayerManager;
import org.jacco.eggHuntBattle.utils.Heads;
import org.jacco.eggHuntBattle.utils.InventoryToBase64;

import java.io.IOException;

public class EggHuntBattleAdminCommand implements CommandExecutor {

    private final Plugin plugin;

    public EggHuntBattleAdminCommand (Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            commandSender.sendMessage("Usage: /egghuntbattleadmin <create|delete|list>");
            return true;
        }

        switch (strings[0].toLowerCase()) {
            case "create":
                // Handle create command
                commandSender.sendMessage("Creating a new Egg Hunt Battle arena...");
                break;
            case "delete":
                // Handle delete command
                commandSender.sendMessage("Deleting an Egg Hunt Battle arena...");
                break;
            case "list":
                // Handle list command
                commandSender.sendMessage("Listing all Egg Hunt Battle arenas...");
                break;
            case "seteggs":
                // Handle setheads command
                commandSender.sendMessage("Setting heads for the Egg Hunt Battle...");

                Player player = (Player) commandSender;

                if (PlayerManager.IsPLayerInEdit(player)) {

                    ItemStack[] armor = PlayerManager.GetPlayerArmor(player);
                    ItemStack[] items = PlayerManager.GetPlayerInventory(player);

                    player.getInventory().clear();
                    player.getInventory().setArmorContents(armor);
                    player.getInventory().setContents(items);

                    PlayerManager.SetPlayerInEdit(player, false);

                } else {

                    ItemStack[] armor = player.getInventory().getArmorContents();
                    ItemStack[] items = player.getInventory().getContents();
                    PlayerManager.SavePlayerInventory(player, items, armor);

                    player.getInventory().clear();

                    player.getInventory().addItem(Heads.GetCustomHead("http://textures.minecraft.net/texture/68ddbba0a10370298c1b4a63fa49770cc99fb3ab11b560eb601e1dae605bcf30"));
                    player.getInventory().addItem(Heads.GetCustomHead("http://textures.minecraft.net/texture/3ed037452223bda1381a17c3daafa71baac6ed8aa3a71ae36163cfeb61227b47"));

                    PlayerManager.SetPlayerInEdit(player, true);

                }

                break;
            case "reload":
                // Handle reload command
                commandSender.sendMessage("Reloading the plugin configuration...");
                break;
            default:
                commandSender.sendMessage("Unknown command. Usage: /egghuntbattleadmin <create|delete|list>");
                break;
        }

        return true;
    }
}
