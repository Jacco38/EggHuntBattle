package org.jacco.eggHuntBattle.commands;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.jacco.eggHuntBattle.arenautils.Arena;
import org.jacco.eggHuntBattle.managers.ArenasManager;
import org.jacco.eggHuntBattle.managers.PlayerManager;
import org.jacco.eggHuntBattle.utils.EasterEgg;
import org.jacco.eggHuntBattle.utils.Heads;
import org.jacco.eggHuntBattle.utils.InventoryToBase64;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EggHuntBattleAdminCommand implements CommandExecutor {

    private final Plugin plugin;

    public EggHuntBattleAdminCommand (Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Arena arena;

        if (strings.length == 0) {
            commandSender.sendMessage("Usage: /egghuntbattleadmin <create | delete | list>");
            return true;
        }

        switch (strings[0].toLowerCase()) {
            case "create":
                // Handle create command

                if (strings.length < 2) {
                    commandSender.sendMessage("Usage: /ehba create <arena>");
                    break;
                }

                arena = new Arena(strings[1], ((Player) commandSender).getWorld(), ((Player) commandSender).getLocation(), ((Player) commandSender).getLocation(), new HashMap<Location, EasterEgg>());
                ArenasManager.AddArena(strings[1], arena);

                commandSender.sendMessage("Created arena " + strings[1] + "!");

                break;
            case "delete":
                // Handle delete command
                commandSender.sendMessage("Deleting an Egg Hunt Battle arena...");
                break;
            case "list":
                // Handle list command
                List<String> arenas = ArenasManager.GetArenas();

                commandSender.sendMessage("Available arenas: " + String.join(", ", arenas));

                break;
            case "seteggs":
                // Handle setheads command
                commandSender.sendMessage("Setting heads for the Egg Hunt Battle...");

                //if strings[1] is not set
                if (strings[1] == null) {
                    commandSender.sendMessage("Usage: /ehba seateggs <arena>");
                    break;
                }

                Player player = (Player) commandSender;

                if (PlayerManager.IsPLayerInEdit(player)) {

                    PlayerManager.RestorePlayerInventory(player);
                    PlayerManager.SetPlayerInEdit(player, false);

                } else {

                    ItemStack[] armor = player.getInventory().getArmorContents();
                    ItemStack[] items = player.getInventory().getContents();
                    PlayerManager.SavePlayerInventory(player);

                    player.getInventory().addItem(Heads.GetCustomHead("http://textures.minecraft.net/texture/68ddbba0a10370298c1b4a63fa49770cc99fb3ab11b560eb601e1dae605bcf30", "Set Egg Spawn"));

                    player.setGameMode(GameMode.CREATIVE);

                    PlayerManager.SetPlayerInEdit(player, true);

                }

                break;
            case "setspawn":

                if (strings.length < 2) {
                    commandSender.sendMessage("Usage: /ehba setspawn <arena>");
                    break;
                }

                if (ArenasManager.GetArena(strings[1]) == null) {
                    commandSender.sendMessage("Arena " + strings[1] + " does not exist!");
                    break;
                }

                arena = ArenasManager.GetArena(strings[1]);

                ArenasManager.SetArenaSpawn(arena, ((Player) commandSender).getLocation());

                commandSender.sendMessage("Set spawn for arena " + strings[1] + " to your location!");

                break;
            case "setlobby":
                if (strings.length < 2) {
                    commandSender.sendMessage("Usage: /ehba setlobby <arena>");
                    break;
                }

                if (ArenasManager.GetArena(strings[1]) == null) {
                    commandSender.sendMessage("Arena " + strings[1] + " does not exist!");
                    break;
                }

                arena = ArenasManager.GetArena(strings[1]);

                ArenasManager.SetArenaLobby(arena, ((Player) commandSender).getLocation());

                commandSender.sendMessage("Set lobby for arena " + strings[1] + " to your location!");

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
