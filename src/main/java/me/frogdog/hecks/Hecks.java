package me.frogdog.hecks;

import me.frogdog.hecks.command.CommandManager;
import me.frogdog.hecks.config.ConfigManager;
import me.frogdog.hecks.event.EventProcessor;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.lwjgl.opengl.Display;

import java.io.File;

@Mod(modid = Hecks.MODID, name = Hecks.NAME, version = Hecks.VERSION)
public final class Hecks {
    public static final String MODID = "hecks";
    public static final String NAME = "Hecks.exe";
    public static final String VERSION = "Beta 1.0";
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static EventBus EVENT_BUS = MinecraftForge.EVENT_BUS;
    public static Hecks instance = null;
    private final CommandManager commandManager;
    private final ConfigManager configManager;
    private File directory;

    public Hecks() {
        EVENT_BUS.register(EventProcessor.INSTANCE);
        instance = this;

        this.commandManager = new CommandManager();
        this.configManager = new ConfigManager();
        Display.setTitle(NAME + " " + VERSION);
    }

    public static Hecks getInstance() {
        return instance;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public File getDirectory() {
        return this.directory;
    }
}
