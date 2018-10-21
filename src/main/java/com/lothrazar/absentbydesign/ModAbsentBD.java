package com.lothrazar.absentbydesign;

import org.apache.logging.log4j.Logger;
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
    registry.createStair(Blocks.STONE, "granite");
    registry.createStair(Blocks.STONE, "andesite");
    registry.createStair(Blocks.STONE, "diorite");
    registry.createStair(Blocks.STONE, "granite_smooth");
    registry.createStair(Blocks.STONE, "andesite_smooth");
    registry.createStair(Blocks.STONE, "diorite_smooth");
    registry.createStair(Blocks.PRISMARINE, "prismarine");
    registry.createStair(Blocks.PRISMARINE, "prismarine_bricks");
    registry.createStair(Blocks.PRISMARINE, "prismarine_dark");
    registry.createStair(Blocks.MOSSY_COBBLESTONE, "cobble_moss");
    registry.createStair(Blocks.STONEBRICK, "bricks_mossy");
    registry.createStair(Blocks.STONEBRICK, "bricks_cracked");
    registry.createStair(Blocks.END_STONE, "end_bricks");
    registry.createStair(Blocks.RED_NETHER_BRICK, "netherbrick_red");
    registry.createSlab(Material.ROCK, "granite");
    registry.createSlab(Material.ROCK, "andesite");
  }

}
