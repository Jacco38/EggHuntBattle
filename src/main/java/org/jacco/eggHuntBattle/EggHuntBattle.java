package org.jacco.eggHuntBattle;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jacco.eggHuntBattle.commands.EggHuntBattleAdminCommand;
import org.jacco.eggHuntBattle.commands.EggHuntBattleCommand;
import org.jacco.eggHuntBattle.listeners.PlayerBlockPlace;
import org.jacco.eggHuntBattle.managers.ArenasManager;

import java.io.File;
import java.io.IOException;

public final class EggHuntBattle extends JavaPlugin {

    private File arenasConfigFile;
    private static FileConfiguration arenasConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Register the command
        this.getCommand("egghuntbattle").setExecutor(new EggHuntBattleCommand(this));
        this.getCommand("egghuntbattleadmin").setExecutor(new EggHuntBattleAdminCommand(this));

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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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

}
