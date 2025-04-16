package org.jacco.eggHuntBattle;

import org.bukkit.plugin.java.JavaPlugin;
import org.jacco.eggHuntBattle.commands.EggHuntBattleAdminCommand;
import org.jacco.eggHuntBattle.commands.EggHuntBattleCommand;
import org.jacco.eggHuntBattle.listeners.PlayerBlockPlace;

public final class EggHuntBattle extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Register the command
        this.getCommand("egghuntbattle").setExecutor(new EggHuntBattleCommand(this));
        this.getCommand("egghuntbattleadmin").setExecutor(new EggHuntBattleAdminCommand(this));

        // Register Event Listeners
        this.getServer().getPluginManager().registerEvents(new PlayerBlockPlace(), this);

        this.saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void Log(String message) {
        getPlugin(EggHuntBattle.class).getLogger().info(message);
    }

}
