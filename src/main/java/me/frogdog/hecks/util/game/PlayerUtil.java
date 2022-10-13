package me.frogdog.hecks.util.game;

import me.frogdog.hecks.Hecks;
import me.frogdog.hecks.util.math.Timer;
import net.minecraft.util.math.MathHelper;

public class PlayerUtil {

    private static Timer timer = new Timer();

    public static double getSpeed() {
        double speed;
        double prevPosX = 0;
        double prevPosZ = 0;
        if (timer.getPassedMillis(1000)) {
            prevPosX = Hecks.mc.player.prevPosX;
            prevPosZ = Hecks.mc.player.prevPosZ;
        }
        speed = Math.floor(((MathHelper.sqrt((Hecks.mc.player.posX - prevPosX) * (Hecks.mc.player.posX - prevPosX)+ (Hecks.mc.player.posZ - prevPosZ) * (Hecks.mc.player.posZ - prevPosZ))) / 1000.0f)/ (0.05f / 3600.0f));
        return speed;
    }
}
