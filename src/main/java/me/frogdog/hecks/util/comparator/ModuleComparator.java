package me.frogdog.hecks.util.comparator;

import me.frogdog.hecks.util.game.HudUtil;

import java.util.Comparator;

public class ModuleComparator implements Comparator<String> {

    @Override
    public int compare(String m1, String m2) {
        if (HudUtil.getStringWidth(m1) > HudUtil.getStringWidth(m2)) {
            return -1;
        }
        if (HudUtil.getStringWidth(m1) > HudUtil.getStringWidth(m2)) {
            return 1;
        }
        return 0;
    }

}