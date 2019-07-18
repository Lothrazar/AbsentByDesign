package com.lothrazar.absentbydesign;
import com.lothrazar.absentbydesign.block.IBlockAbsent;
import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import com.lothrazar.absentbydesign.setup.ClientProxy;
import com.lothrazar.absentbydesign.setup.IProxy;
import com.lothrazar.absentbydesign.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(ModAbsentBD.MODID)
public class ModAbsentBD {
  public static final String MODID = "absentbydesign";
  public static final IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
  public static final Logger LOGGER = LogManager.getLogger();

  public ModAbsentBD(){
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    //only for server starting
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setup(FMLCommonSetupEvent event) {
  }

  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {
    @SubscribeEvent
    public static void onBlocksRegistry(RegistryEvent.Register<Block> event) {
      IForgeRegistry<Block> reg = event.getRegistry();
      // REGULAR DIRT ?
      // STRIPPED BARK EVERYTHING?
      // TERRACOTTA EVERYTHING ?
      // ICE?
      // WOOL EVERYTHING?
      // OBSIDIAN EVERYTHING
      // smooth_stone EVERYTHING
      //TODO: DATA TAGS MISSING ON STAIR/SLAB
      reg.register(AbsentRegistry.createFence(Blocks.QUARTZ_BLOCK, Material.ROCK, "fence_quartz"));
      reg.register(AbsentRegistry.createFence(Blocks.RED_NETHER_BRICKS, Material.ROCK, "fence_red_netherbrick"));
      reg.register(AbsentRegistry.createFence(Blocks.ACACIA_LOG, Material.WOOD, "fence_log_acacia"));
      reg.register(AbsentRegistry.createFence(Blocks.BIRCH_LOG, Material.WOOD, "fence_log_birch"));
      reg.register(AbsentRegistry.createFence(Blocks.DARK_OAK_LOG, Material.WOOD, "fence_log_darkoak"));
      reg.register(AbsentRegistry.createFence(Blocks.JUNGLE_LOG, Material.WOOD, "fence_log_jungle"));
      reg.register(AbsentRegistry.createFence(Blocks.OAK_LOG, Material.WOOD, "fence_log_oak"));
      reg.register(AbsentRegistry.createFence(Blocks.SPRUCE_LOG, Material.WOOD, "fence_log_spruce"));
      reg.register(AbsentRegistry.createFence(Blocks.OBSIDIAN, Material.ROCK, "fence_obsidian"));
      //                SLABS
      reg.register(AbsentRegistry.createSlab(Blocks.END_STONE, Material.ROCK, "slab_end_stone"));
      reg.register(AbsentRegistry.createSlab(Blocks.END_STONE, Material.ROCK, "slab_netherrack"));
      reg.register(AbsentRegistry.createSlab(Blocks.SNOW_BLOCK, Material.ROCK, "slab_snow"));
      reg.register(AbsentRegistry.createSlab(Blocks.CRACKED_STONE_BRICKS, Material.ROCK, "slab_bricks_cracked"));
      reg.register(AbsentRegistry.createSlab(Blocks.COARSE_DIRT, Material.SNOW, "slab_coarse_dirt"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_black"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_blue"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_brown"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_cyan"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_gray"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_green"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_light_blue"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_lime"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_magenta"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_orange"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_pink"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_purple"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_red"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_silver"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_white"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_CONCRETE, Material.ROCK, "slab_concrete_yellow"));
      //
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_black"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_blue"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_brown"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_cyan"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_gray"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_green"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_light_blue"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_lime"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_magenta"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_orange"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_pink"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_purple"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_red"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_silver"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_white"));
      reg.register(AbsentRegistry.createSlab(Blocks.BLACK_WOOL, Material.WOOL, "slab_wool_yellow"));
      //               STAIRS
      reg.register(AbsentRegistry.createStair(Blocks.COARSE_DIRT, Material.EARTH, "stairs_coarse_dirt"));
      reg.register(AbsentRegistry.createStair(Blocks.END_STONE, Material.ROCK, "stairs_end_stone"));
      reg.register(AbsentRegistry.createStair(Blocks.CRACKED_STONE_BRICKS, Material.ROCK, "stairs_bricks_cracked"));
      reg.register(AbsentRegistry.createStair(Blocks.NETHERRACK, Material.ROCK, "stairs_netherrack"));
      reg.register(AbsentRegistry.createStair(Blocks.SNOW_BLOCK, Material.SNOW, "stairs_snow"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_CONCRETE, Material.ROCK, "stairs_concrete_black"));
      reg.register(AbsentRegistry.createStair(Blocks.BLUE_CONCRETE, Material.ROCK, "stairs_concrete_blue"));
      reg.register(AbsentRegistry.createStair(Blocks.BROWN_CONCRETE, Material.ROCK, "stairs_concrete_brown"));
      reg.register(AbsentRegistry.createStair(Blocks.CYAN_CONCRETE, Material.ROCK, "stairs_concrete_cyan"));
      reg.register(AbsentRegistry.createStair(Blocks.GRAY_CONCRETE, Material.ROCK, "stairs_concrete_gray"));
      reg.register(AbsentRegistry.createStair(Blocks.GREEN_CONCRETE, Material.ROCK, "stairs_concrete_green"));
      reg.register(AbsentRegistry.createStair(Blocks.LIGHT_BLUE_CONCRETE, Material.ROCK, "stairs_concrete_light_blue"));
      reg.register(AbsentRegistry.createStair(Blocks.LIME_CONCRETE, Material.ROCK, "stairs_concrete_lime"));
      reg.register(AbsentRegistry.createStair(Blocks.MAGENTA_CONCRETE, Material.ROCK, "stairs_concrete_magenta"));
      reg.register(AbsentRegistry.createStair(Blocks.ORANGE_CONCRETE, Material.ROCK, "stairs_concrete_orange"));
      reg.register(AbsentRegistry.createStair(Blocks.PINK_CONCRETE, Material.ROCK, "stairs_concrete_pink"));
      reg.register(AbsentRegistry.createStair(Blocks.PURPLE_CONCRETE, Material.ROCK, "stairs_concrete_purple"));
      reg.register(AbsentRegistry.createStair(Blocks.RED_CONCRETE, Material.ROCK, "stairs_concrete_red"));
      reg.register(AbsentRegistry.createStair(Blocks.LIGHT_GRAY_CONCRETE, Material.ROCK, "stairs_concrete_silver"));
      reg.register(AbsentRegistry.createStair(Blocks.WHITE_CONCRETE, Material.ROCK, "stairs_concrete_white"));
      reg.register(AbsentRegistry.createStair(Blocks.YELLOW_CONCRETE, Material.ROCK, "stairs_concrete_yellow"));
      // WALLS
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_andesite_smooth"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_diorite_smooth"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_end_stone"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_granite_smooth"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_purpur"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_quartz"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_sandstone_red_smooth"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_sandstone_smooth"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_stone"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_stone_slab"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_stonebrick_carved"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_stonebrick_cracked"));

    }

    @SubscribeEvent
    public static void onItemsRegistry(RegistryEvent.Register<Item> event) {
      List<Block> blocks = AbsentRegistry.getBlocks();
      Item.Properties properties = new Item.Properties().group(AbsentRegistry.itemGroup);
      for(Block b : blocks) {
        event.getRegistry().register(new BlockItem(b, properties)
            .setRegistryName(((IBlockAbsent) b).rawName()));
      }
    }
  }

  @SubscribeEvent
  public static void onFingerprintViolation(FMLFingerprintViolationEvent event) {
    // https://tutorials.darkhax.net/tutorials/jar_signing/
    String source = (event.getSource() == null) ? "" : event.getSource().getName() + " ";
    String msg = MODID + "Invalid fingerprint detected! The file " + source + "may have been tampered with. This version will NOT be supported by the author!";
    System.out.println(msg);
  }
}
