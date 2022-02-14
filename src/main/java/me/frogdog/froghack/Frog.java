package me.frogdog.froghack;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import me.frogdog.froghack.Frog;
import me.frogdog.froghack.proxy.CommonProxy;
import me.frogdog.froghack.command.CommandLoader;
import me.frogdog.froghack.event.EventProcessor;
import me.frogdog.froghack.module.ModuleManager;
import me.frogdog.froghack.ui.Hud;
import me.frogdog.froghack.utils.DiscordRP;

@Mod(modid = Frog.MODID, name = Frog.NAME, version = Frog.VERSION)

public class Frog {
	
    public static final String MODID = "froghack";
    public static final String NAME = "Froghack";
    public static final String VERSION = "Beta 1.0";
    public static final String CLIENT_PROXY_CLASS = "me.frogdog.froghack.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "me.frogdog.froghack.proxy.CommonProxy";
    
    public static ModuleManager moduleManager;
    public static Hud hud;
    public static DiscordRP rpc;
    public static CommandLoader commandLoader;
    public static EventProcessor eventProcessor;

    public static final Logger log = LogManager.getLogger(MODID);
    
	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
    
	@Instance
	public static Frog INSTANCE;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	Display.setTitle("FrogHack " + VERSION);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        
        hud = new Hud();
        MinecraftForge.EVENT_BUS.register(new Hud());
        log.info("Hud loaded");
        
        eventProcessor = new EventProcessor();
        MinecraftForge.EVENT_BUS.register(new EventProcessor());
        log.info("Events loaded");
        
        moduleManager = new ModuleManager();
        log.info("Modules loaded");
        
        commandLoader = new CommandLoader();
        commandLoader.loadCommands();
        log.info("Commands loaded");
        
        discordRPInit();  
    	
    }
    
	@EventHandler
    public void init(FMLPostInitializationEvent event) {
       
    }
	
	public void discordRPInit() {
        rpc = new DiscordRP();
        rpc.start();
        rpc.update("Playing FrogHacks", "In Game");
	}
}
