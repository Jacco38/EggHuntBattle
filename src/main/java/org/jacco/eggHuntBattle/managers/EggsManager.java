package org.jacco.eggHuntBattle.managers;

import org.jacco.eggHuntBattle.utils.EasterEgg;

import java.util.ArrayList;
import java.util.List;

public class EggsManager {

    private static List<EasterEgg> eggs = new ArrayList<>();

    public static void AddEgg(EasterEgg egg) {
        eggs.add(egg);
    }

    public static EasterEgg GetRandomEgg() {
        if (eggs.size() == 0) {
            return null;
        }
        int randomIndex = (int) (Math.random() * eggs.size());
        return eggs.get(randomIndex);
    }

    public static EasterEgg GetNormalEgg() {
        for (EasterEgg egg : eggs) {
            if (egg.GetName().equals("Normal Egg")) {
                return egg;
            }
        }
        return null;
    }

}
