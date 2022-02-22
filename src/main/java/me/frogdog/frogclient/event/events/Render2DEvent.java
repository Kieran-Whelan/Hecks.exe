package me.frogdog.frogclient.event.events;

import me.frogdog.frogclient.event.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Render2DEvent extends Event
{
    public ScaledResolution getResolution()
    {
        return new ScaledResolution(Minecraft.getMinecraft());
    }

}
