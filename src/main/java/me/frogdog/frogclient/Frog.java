package me.frogdog.frogclient;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventBus;

import java.io.File;
import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import me.frogdog.api.event.EventProcessor;
import me.frogdog.api.event.basic.BasicEventManager;
import me.frogdog.frogclient.command.CommandManager;
import me.frogdog.frogclient.config.ConfigManager;
import me.frogdog.frogclient.friend.FriendManager;
import me.frogdog.frogclient.keybind.KeybindManager;
import me.frogdog.frogclient.module.ModuleManager;
import me.frogdog.frogclient.plugin.PluginManager;
import me.frogdog.frogclient.ui.Hud;

@Mod(modid = Frog.MODID, name = Frog.NAME, version = Frog.VERSION)
public final class Frog
{
    public static final String MODID = "frogclient";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";
    public static final String TITLE = "FrogClient";
    public final long startTime = System.nanoTime() / 1000000L;
	public Minecraft mc = Minecraft.getMinecraft();
    private static Frog instance = null;
    private BasicEventManager eventManager;
    private KeybindManager keybindManager;
    private ModuleManager moduleManager;
    private CommandManager commandManager;
    private FriendManager friendManager;
    private ConfigManager configManager;
    private PluginManager pluginManager;
    private File directory;

    public static EventBus EVENT_BUS = MinecraftForge.EVENT_BUS;
    
    public static final Logger log = LogManager.getLogger(MODID);

    public Frog() {
        EVENT_BUS.register(EventProcessor.INSTANCE);
        EVENT_BUS.register(Hud.INSTANCE);

        MinecraftForge.EVENT_BUS.register(EventProcessor.INSTANCE);
        MinecraftForge.EVENT_BUS.register(EventProcessor.INSTANCE);

        instance = this;

        this.directory = new File(System.getProperty("user.home"), "frogclient");

        if (!this.directory.exists()) {
        	log.info("Directory alrady exists");
        }
        
        this.eventManager = new BasicEventManager();
        this.configManager = new ConfigManager();
        this.friendManager = new FriendManager();
        this.keybindManager = new KeybindManager();
        this.moduleManager = new ModuleManager();
        this.pluginManager = new PluginManager();
        this.commandManager = new CommandManager();
        this.getConfigManager().getRegistry().forEach(config -> config.load(new Object[0]));
        try {
            this.pluginManager.onLoad();
            System.out.println("Plugin manager started.");
            System.out.println(this.pluginManager.getList() + "has been loaded.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(new Thread("Shutdown Hook Thread"){

            @Override
            public void run() {
                getConfigManager().getRegistry().forEach(config -> config.save(new Object[0]));
            }
        });
        Display.setTitle(NAME + " " + VERSION);
    }

    public static Frog getInstance() {
        return instance;
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public KeybindManager getKeybindManager() {
        return this.keybindManager;
    }

    public FriendManager getFriendManager() {
        return this.friendManager;
    }

    public BasicEventManager getEventManager() {
        return this.eventManager;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    public File getDirectory() {
        return this.directory;
    }
    	
}