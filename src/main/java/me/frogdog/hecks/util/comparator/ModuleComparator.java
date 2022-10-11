package me.frogdog.hecks.util.comparator;

import me.frogdog.hecks.util.render.ui.FontUtil;

import java.util.Comparator;

public class ModuleComparator implements Comparator<String> {

    @Override
    public int compare(String m1, String m2) {
        if (FontUtil.getStringWidth(m1) > FontUtil.getStringWidth(m2)) {
            return -1;
        }
        if (FontUtil.getStringWidth(m1) > FontUtil.getStringWidth(m2)) {
            return 1;
        }
        return 0;
    }

}