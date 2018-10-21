package com.lothrazar.absentbydesign;

import org.apache.logging.log4j.Logger;
import com.lothrazar.absentbydesign.block.BlockAbsentSlab;
import com.lothrazar.absentbydesign.block.BlockAbsentSlabDouble;
import com.lothrazar.absentbydesign.block.BlockAbsentSlabHalf;
import com.lothrazar.absentbydesign.block.BlockAbsentStairs;
import com.lothrazar.absentbydesign.block.ItemBlockAbsentSlab;
import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ModAbsentBD.MODID)
public class ModAbsentBD {

  @GameRegistry.ObjectHolder(ModAbsentBD.MODID + ":stairs_granite")
  public static final Block icon = null;
  @Instance(ModAbsentBD.MODID)
  public static ModAbsentBD instance;
  public static final CreativeTabs tab = new CreativeTabs(ModAbsentBD.MODID) {

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
      return new ItemStack(icon);
    }
  };
  public static final String MODID = "absentbydesign";
  private static Logger logger;
  private AbsentRegistry registry;

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    logger = event.getModLog();
    registry = new AbsentRegistry();
    MinecraftForge.EVENT_BUS.register(registry);
    //now register content
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_granite");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_andesite");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_diorite");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_granite_smooth");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_andesite_smooth");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_diorite_smooth");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.PRISMARINE.getDefaultState()), "stairs_prismarine");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.PRISMARINE.getDefaultState()), "stairs_prismarine_bricks");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.PRISMARINE.getDefaultState()), "stairs_prismarine_dark");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.MOSSY_COBBLESTONE.getDefaultState()), "stairs_cobble_moss");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.STONEBRICK.getDefaultState()), "stairs_bricks_mossy");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.STONEBRICK.getDefaultState()), "stairs_bricks_cracked");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.END_STONE.getDefaultState()), "stairs_end_bricks");
    registry.registerStairBlock(new BlockAbsentStairs(Blocks.RED_NETHER_BRICK.getDefaultState()), "stairs_netherbrick_red");
    BlockAbsentSlab half = (BlockAbsentSlab) registry.registerSlabBlock(new BlockAbsentSlabHalf(Material.ROCK), "slab_granite");
    BlockAbsentSlab dubs = (BlockAbsentSlab) registry.registerSlabBlock(new BlockAbsentSlabDouble(Material.ROCK), "slab_granite_double");
    registry.registerItem(new ItemBlockAbsentSlab(half, half, dubs), "slab_granite");
    registry.registerItem(new ItemBlockAbsentSlab(dubs, half, dubs), "slab_granite_double");
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    // some example code 
  }
}
