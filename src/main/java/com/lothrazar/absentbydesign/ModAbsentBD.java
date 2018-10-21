package com.lothrazar.absentbydesign;

import org.apache.logging.log4j.Logger;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModAbsentBD.MODID)
public class ModAbsentBD {
	public static final String MODID = "absentbydesign";

	private static Logger logger;
  private AbsentRegistry registry;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
    registry = new AbsentRegistry();
    MinecraftForge.EVENT_BUS.register(registry);
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()),
        "stairs_granite");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
    // some example code 
	}



}
