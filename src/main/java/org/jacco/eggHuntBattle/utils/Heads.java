package org.jacco.eggHuntBattle.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class Heads {

    private static final UUID RANDOM_UUID = UUID.fromString("92864445-51c5-4c3b-9039-517c9927d1b4");

    public static PlayerProfile GetOwnerProfile(String url) {
        PlayerProfile profile = org.bukkit.Bukkit.createPlayerProfile(RANDOM_UUID);
        PlayerTextures textures = profile.getTextures();

        URL urlObject;

        try {
            urlObject = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL", e);
        }

        textures.setSkin(urlObject);
        profile.setTextures(textures);

        return profile;
    }

    public static ItemStack GetCustomHead(String url, String name) {

        ItemStack head = new ItemStack(org.bukkit.Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        PlayerProfile profile = org.bukkit.Bukkit.createPlayerProfile(RANDOM_UUID);
        PlayerTextures textures = profile.getTextures();

        URL urlObject;

        try {
            urlObject = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL", e);
        }

        textures.setSkin(urlObject);
        profile.setTextures(textures);

        meta.setOwnerProfile(profile);
        meta.setDisplayName(name);

        head.setItemMeta(meta);

        return head;
    }

    public static SkullMeta GetSkullMeta(String url) {
        ItemStack head = new ItemStack(org.bukkit.Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        PlayerProfile profile = org.bukkit.Bukkit.createPlayerProfile(RANDOM_UUID);
        PlayerTextures textures = profile.getTextures();

        URL urlObject;

        try {
            urlObject = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL", e);
        }

        textures.setSkin(urlObject);
        profile.setTextures(textures);

        meta.setOwnerProfile(profile);

        return meta;
    }

}
