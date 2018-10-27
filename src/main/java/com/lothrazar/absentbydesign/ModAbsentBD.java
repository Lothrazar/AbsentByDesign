package com.lothrazar.absentbydesign;

import org.apache.logging.log4j.Logger;
import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
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
  public static Logger logger;
  private AbsentRegistry registry;

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    logger = event.getModLog();
    registry = new AbsentRegistry();
    MinecraftForge.EVENT_BUS.register(registry);
    //now register content
    registry.createWall(Blocks.STONE, "stone");
    registry.createWall(Blocks.STONE, "granite");
    registry.createWall(Blocks.STONE, "andesite");
    registry.createWall(Blocks.STONE, "diorite");
    registry.createWall(Blocks.BRICK_BLOCK, "brick");
    registry.createWall(Blocks.STONEBRICK, "granite_smooth");
    registry.createWall(Blocks.STONEBRICK, "andesite_smooth");
    registry.createWall(Blocks.STONEBRICK, "diorite_smooth");
    registry.createWall(Blocks.STONEBRICK, "stonebrick");
    registry.createWall(Blocks.STONEBRICK, "stonebrick_mossy");
    registry.createWall(Blocks.STONEBRICK, "stonebrick_cracked");
    registry.createWall(Blocks.STONEBRICK, "stonebrick_carved");
    registry.createWall(Blocks.STONEBRICK, "stone_slab");
    registry.createWall(Blocks.SANDSTONE, "sandstone");
    registry.createWall(Blocks.SANDSTONE, "sandstone_red");
    registry.createWall(Blocks.SANDSTONE, "sandstone_smooth");
    registry.createWall(Blocks.SANDSTONE, "sandstone_red_smooth");
    registry.createWall(Blocks.STONEBRICK, "purpur");
    registry.createWall(Blocks.STONEBRICK, "end_bricks");
    registry.createWall(Blocks.STONEBRICK, "end_stone");
    registry.createStair(Blocks.STONE,
        new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE.getMetadata()), "granite");
    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE.getMetadata()), "andesite");
    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.DIORITE.getMetadata()), "diorite");
    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE_SMOOTH.getMetadata()), "granite_smooth");
    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.ANDESITE_SMOOTH.getMetadata()), "andesite_smooth");
    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.DIORITE_SMOOTH.getMetadata()), "diorite_smooth");
    registry.createStair(Blocks.PRISMARINE, new ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.ROUGH.getMetadata()), "prismarine");
    registry.createStair(Blocks.PRISMARINE, new ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.BRICKS.getMetadata()), "prismarine_bricks");
    registry.createStair(Blocks.PRISMARINE, new ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.DARK.getMetadata()), "prismarine_dark");
    registry.createStair(Blocks.MOSSY_COBBLESTONE, new ItemStack(Blocks.MOSSY_COBBLESTONE), "cobble_moss");
    registry.createStair(Blocks.STONEBRICK, new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.MOSSY.getMetadata()), "bricks_mossy");
    registry.createStair(Blocks.STONEBRICK, new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.CRACKED.getMetadata()), "bricks_cracked");
    registry.createStair(Blocks.END_BRICKS, new ItemStack(Blocks.END_STONE), "end_bricks");
    registry.createStair(Blocks.RED_NETHER_BRICK, new ItemStack(Blocks.RED_NETHER_BRICK), "netherbrick_red");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.STONE), "stone");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.NETHERRACK), "netherrack");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.END_STONE), "end_stone");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.DIRT, 1, BlockDirt.DirtType.COARSE_DIRT.getMetadata()), "coarse_dirt");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.SNOW), "snow");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.RED.getMetadata()), "concrete_red");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLUE.getMetadata()), "concrete_blue");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BROWN.getMetadata()), "concrete_brown");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.CYAN.getMetadata()), "concrete_cyan");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.GRAY.getMetadata()), "concrete_gray");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.GREEN.getMetadata()), "concrete_green");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.LIGHT_BLUE.getMetadata()), "concrete_light_blue");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.LIME.getMetadata()), "concrete_lime");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.MAGENTA.getMetadata()), "concrete_magenta");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.ORANGE.getMetadata()), "concrete_orange");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.PINK.getMetadata()), "concrete_pink");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.YELLOW.getMetadata()), "concrete_yellow");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.WHITE.getMetadata()), "concrete_white");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.SILVER.getMetadata()), "concrete_silver");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.PURPLE.getMetadata()), "concrete_purple");
    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLACK.getMetadata()), "concrete_black");
    registry.createSlab(Material.ROCK, "granite");
    registry.createSlab(Material.ROCK, "andesite");
    registry.createSlab(Material.ROCK, "diorite");
    registry.createSlab(Material.ROCK, "granite_smooth");
    registry.createSlab(Material.ROCK, "andesite_smooth");
    registry.createSlab(Material.ROCK, "diorite_smooth");
    registry.createSlab(Material.ROCK, "prismarine");
    registry.createSlab(Material.ROCK, "prismarine_bricks");
    registry.createSlab(Material.ROCK, "prismarine_dark");
    registry.createSlab(Material.ROCK, "cobble_moss");
    registry.createSlab(Material.ROCK, "bricks_cracked");
    registry.createSlab(Material.ROCK, "bricks_mossy");
    registry.createSlab(Material.ROCK, "end_bricks");
    registry.createSlab(Material.ROCK, "netherbrick_red");
    registry.createSlab(Material.ROCK, "concrete_red");
    registry.createSlab(Material.ROCK, "concrete_blue");
    registry.createSlab(Material.ROCK, "concrete_brown");
    registry.createSlab(Material.ROCK, "concrete_cyan");
    registry.createSlab(Material.ROCK, "concrete_gray");
    registry.createSlab(Material.ROCK, "concrete_green");
    registry.createSlab(Material.ROCK, "concrete_light_blue");
    registry.createSlab(Material.ROCK, "concrete_lime");
    registry.createSlab(Material.ROCK, "concrete_magenta");
    registry.createSlab(Material.ROCK, "concrete_orange");
    registry.createSlab(Material.ROCK, "concrete_pink");
    registry.createSlab(Material.ROCK, "concrete_yellow");
    registry.createSlab(Material.ROCK, "concrete_white");
    registry.createSlab(Material.ROCK, "concrete_silver");
    registry.createSlab(Material.ROCK, "concrete_purple");
    registry.createSlab(Material.ROCK, "concrete_black");
    registry.createSlab(Material.ROCK, "stone");
    registry.createSlab(Material.ROCK, "netherrack");
    registry.createSlab(Material.ROCK, "end_stone");
    registry.createSlab(Material.GRASS, "coarse_dirt");
    registry.createSlab(Material.SNOW, "snow");
    registry.createFence(Material.ROCK, MapColor.QUARTZ, "quartz");
    registry.createFence(Material.ROCK, MapColor.QUARTZ, "red_netherbrick");
    registry.createFence(Material.ROCK, MapColor.QUARTZ, "log_oak");
    registry.createFence(Material.ROCK, MapColor.QUARTZ, "log_birch");
    registry.createFence(Material.ROCK, MapColor.QUARTZ, "log_spruce");
    registry.createFence(Material.ROCK, MapColor.QUARTZ, "log_jungle");
    registry.createFence(Material.ROCK, MapColor.QUARTZ, "log_darkoak");
    registry.createFence(Material.ROCK, MapColor.QUARTZ, "log_acacia");
    ////////layer_clay 
    //STAIRS ONLY: Snow 
    // SLAB ONLY:  chiseled brick, grass_path

    // IRON BARS: gold, obsidian
    // Trapdoor: 6x wood plank
  }
}
