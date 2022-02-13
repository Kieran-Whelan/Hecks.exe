package com.frogdog.froghack;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.frogdog.froghack.command.CommandLoader;
import com.frogdog.froghack.event.EventProcessor;
import com.frogdog.froghack.module.Module;
import com.frogdog.froghack.module.ModuleManager;
import com.frogdog.froghack.proxy.CommonProxy;
import com.frogdog.froghack.ui.Hud;

@Mod(modid = Frog.MODID, name = Frog.NAME, version = Frog.VERSION)

public class Frog {
	
    public static final String MODID = "froghack";
    public static final String NAME = "FrogHack";
    public static final String VERSION = "Beta 1.0";
    public static final String CLIENT_PROXY_CLASS = "com.frogdog.froghack.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.frogdog.froghack.proxy.CommonProxy";
    
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
    	Display.setTitle("FrogHacks" + VERSION);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        hud = new Hud();
        MinecraftForge.EVENT_BUS.register(new Hud());
        eventProcessor = new EventProcessor();
        MinecraftForge.EVENT_BUS.register(new EventProcessor());
        moduleManager = new ModuleManager();
        commandLoader = new CommandLoader();
        commandLoader.loadCommands();
        log.info("Loading Commands");
        //Discord
        rpc = new DiscordRP();
        rpc.start();
        rpc.update("Playing FrogHacks", "In Game");
        
       
    }

	@EventHandler
    public void init(FMLPostInitializationEvent event) {
       
    }
	
	
	@SubscribeEvent
	public void key(KeyInputEvent e) {
		if(Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null)
			return;
		
		try {
			if(Keyboard.isCreated()) {
				if(Keyboard.getEventKeyState()) {
					int keyCode = Keyboard.getEventKey();
					if(keyCode <= 0)
						return;
					for(Module m : moduleManager.modules) {
						if(m.getKey() == keyCode && keyCode > 0) {
							m.toggle();
						}
					}
				}
			}
		} catch (Exception q) {
			q.printStackTrace();
		}
	}
    
}
