package com.lothrazar.absentbydesign;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Logger;

@Mod(modid = ModAbsentBD.MODID)
public class ModAbsentBD {
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static final String MODID = "absentbydesign";

	private static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
	}

	public static void registerBlock(Block b, String name) {

//	    if (inCreativeTab) {
//	        b.setCreativeTab(ModCyclic.TAB);
//	      }
		b.setRegistryName(new ResourceLocation(MODID, name));
		b.setUnlocalizedName(name);

		blocks.add(b);

	}

	@SubscribeEvent
	public static void onRegistryEvent(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(blocks.toArray(new Block[0]));
	}
}
