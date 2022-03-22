package me.frogdog.frogclient;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;

import java.io.File;
import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import me.frogdog.frogclient.command.CommandManager;
import me.frogdog.frogclient.config.ConfigManager;
import me.frogdog.frogclient.event.EventProcessor;
import me.frogdog.frogclient.event.basic.BasicEventManager;
import me.frogdog.frogclient.friend.FriendManager;
import me.frogdog.frogclient.keybind.KeybindManager;
import me.frogdog.frogclient.module.ModuleManager;
import me.frogdog.frogclient.ui.Hud;
import me.frogdog.frogclient.ui.HudManager;

@Mod(modid = Frog.MODID, name = Frog.NAME, version = Frog.VERSION)
public final class Frog {
    public static final String MODID = "frogclient";
    public static final String NAME = "Frog Client";
    public static final String VERSION = "Beta 1.0";
	public final Minecraft mc = Minecraft.getMinecraft();
    private static Frog instance = null;
    private final BasicEventManager eventManager;
    private final KeybindManager keybindManager;
    private final ModuleManager moduleManager;
    private final CommandManager commandManager;
    private final FriendManager friendManager;
    private final ConfigManager configManager;
    private final HudManager hudManager;
    private final File directory;

    public static EventBus EVENT_BUS = MinecraftForge.EVENT_BUS;
    
    public static final Logger log = LogManager.getLogger(MODID);

    public Frog() {
        EVENT_BUS.register(EventProcessor.INSTANCE);
        EVENT_BUS.register(Hud.INSTANCE);

        MinecraftForge.EVENT_BUS.register(EventProcessor.INSTANCE);
        MinecraftForge.EVENT_BUS.register(Hud.INSTANCE);

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
        this.commandManager = new CommandManager();
        this.hudManager = new HudManager();
        this.getConfigManager().getRegistry().forEach(config -> config.load(new Object[0]));
        
        Runtime.getRuntime().addShutdownHook(new Thread("Shutdown Hook Thread"){

            @Override
            public void run() {
                getConfigManager().getRegistry().forEach(config -> config.save(new Object[0]));
            }
        });
        Display.setTitle(NAME + " " + VERSION);
    }
    
    @EventHandler
    public void init(FMLPostInitializationEvent event) {
    	/**
    	DiscordRP discordRP = new DiscordRP();
    	discordRP.start();
    	discordRP.update("Playing " + NAME, "In Game");
    	**/

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

    public File getDirectory() {
        return this.directory;
    }

	public HudManager getHudManager() {
		return hudManager;
	}
    	
}