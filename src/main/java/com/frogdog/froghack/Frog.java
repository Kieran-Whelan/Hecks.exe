package com.frogdog.froghack;

import com.frogdog.froghack.utils.Logger;

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

import com.frogdog.froghack.event.EventProcessor;
import com.frogdog.froghack.module.ModuleManager;
import com.frogdog.froghack.proxy.CommonProxy;

@Mod(modid = Frog.MODID, name = Frog.NAME, version = Frog.VERSION)

public class Frog {
	
    public static final String MODID = "froghack";
    public static final String NAME = "FrogHack";
    public static final String VERSION = "Beta 1.0";
    public static final String CLIENT_PROXY_CLASS = "com.frogdog.froghack.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.frogdog.froghack.proxy.CommonProxy";
    
    public static ModuleManager moduleManager;
    
	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
    
	@Instance
	public Frog INSTANCE;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		Logger.setLogger(event.getModLog());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        moduleManager = new ModuleManager();
       
    }
    
    @EventHandler
    public void init(FMLPostInitializationEvent event) {
       
    }
    
}
