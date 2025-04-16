package org.jacco.eggHuntBattle;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jacco.eggHuntBattle.arenautils.Arena;
import org.jacco.eggHuntBattle.commands.EggHuntBattleAdminCommand;
import org.jacco.eggHuntBattle.commands.EggHuntBattleCommand;
import org.jacco.eggHuntBattle.listeners.PlayerBlockPlace;
import org.jacco.eggHuntBattle.managers.ArenasManager;
import org.jacco.eggHuntBattle.managers.EggsManager;
import org.jacco.eggHuntBattle.managers.GameManager;
import org.jacco.eggHuntBattle.managers.PlayerManager;
import org.jacco.eggHuntBattle.utils.EasterEgg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class EggHuntBattle extends JavaPlugin {

    private File arenasConfigFile;
    private static FileConfiguration arenasConfig;

    private GameManager gameManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        gameManager = new GameManager(this);

        // Register the command
        this.getCommand("egghuntbattle").setExecutor(new EggHuntBattleCommand(this, gameManager));
        this.getCommand("egghuntbattleadmin").setExecutor(new EggHuntBattleAdminCommand(this, gameManager));

        // Register Event Listeners
        this.getServer().getPluginManager().registerEvents(new PlayerBlockPlace(), this);

        // Load Configs
        this.saveDefaultConfig();

        // Load Arenas Config Files
        arenasConfigFile = new File(getDataFolder(), "arenas.yml");

        if (!arenasConfigFile.exists()) {
            arenasConfigFile.getParentFile().mkdirs();
            saveResource("arenas.yml", false);
        }

        arenasConfig = new YamlConfiguration();
        try {
            arenasConfig.load(arenasConfigFile);
            getLogger().info("Loaded arenas.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load Arenas
        ArenasManager.LoadArenas();

        // Add eggs
        EggsManager.AddEgg(new EasterEgg("http://textures.minecraft.net/texture/68ddbba0a10370298c1b4a63fa49770cc99fb3ab11b560eb601e1dae605bcf30", "Normal Egg", 1));
        EggsManager.AddEgg(new EasterEgg("http://textures.minecraft.net/texture/3ed037452223bda1381a17c3daafa71baac6ed8aa3a71ae36163cfeb61227b47", "Special Egg", 3));

        // GameManager
        gameManager.LoadEnabledArenas();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        PlayerManager.RestoreAllPlayersInventory();

        try {
            arenasConfig.save(arenasConfigFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileConfiguration GetArenasConfig() {
        return arenasConfig;
    }

    public static void SaveArenasConfig() {
        try {
            arenasConfig.save(new File(EggHuntBattle.getPlugin(EggHuntBattle.class).getDataFolder(), "arenas.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Log(String message) {
        getPlugin(EggHuntBattle.class).getLogger().info(message);
    }

    public static Plugin GetPlugin() {
        return getPlugin(EggHuntBattle.class);
    }

}
