package com.lothrazar.absentbydesign;

import org.apache.logging.log4j.Logger;
import com.lothrazar.absentbydesign.block.BlockAbsentStairs;
import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import net.minecraft.block.Block;
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
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_granite");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_andesite");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_diorite");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_granite_smooth");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_andesite_smooth");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_diorite_smooth");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_prismarine");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_prismarine_bricks");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_prismarine_dark");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_cobble_moss");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_bricks_mossy");
    registry.registerBlock(new BlockAbsentStairs(Blocks.STONE.getDefaultState()), "stairs_bricks_cracked");
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    // some example code 
  }
}
