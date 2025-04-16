package org.jacco.eggHuntBattle.utils;

import org.bukkit.block.data.BlockData;

public class EasterEgg {

    String url;
    String name;
    int points;

    public EasterEgg(String url, String name, int points) {
        this.url = url;
        this.name = name;
        this.points = points;
    }

    public BlockData GetBlockData() {

        Heads.GetCustomHead(url, name);

        return Heads.GetCustomHead(url, name).getType().createBlockData();

    }

    public String GetName() {
        return name;
    }

    public String GetUrl() {
        return url;
    }

}
