package com.lothrazar.absentbydesign;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lothrazar.absentbydesign.block.IBlockAbsent;
import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(ModAbsentBD.MODID)
public class ModAbsentBD {

  public static final String MODID = "absentbydesign";
  public static final Logger LOGGER = LogManager.getLogger();

  public ModAbsentBD() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setupClient(final FMLClientSetupEvent event) {
    //
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass, RenderType.getCutout());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_white, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_orange, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_magenta, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_light_blue, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_yellow, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_lime, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_pink, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_gray, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_light_gray, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_cyan, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_purple, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_blue, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_brown, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_green, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_red, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_black, RenderType.getTranslucent());
    //
    //
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass, RenderType.getCutout());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_white, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_orange, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_magenta, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_light_blue, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_yellow, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_lime, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_pink, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_gray, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_light_gray, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_cyan, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_purple, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_blue, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_brown, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_green, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_red, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_black, RenderType.getTranslucent());
  }

  static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
    return false;
  }

  static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
    return false;
  }

  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {

    @SubscribeEvent
    public static void onBlocksRegistry(RegistryEvent.Register<Block> event) {
      IForgeRegistry<Block> reg = event.getRegistry();
      //
      //                FENCES
      //
      reg.register(AbsentRegistry.createFence(Blocks.QUARTZ_BLOCK, Material.ROCK, "fence_quartz"));
      reg.register(AbsentRegistry.createFence(Blocks.RED_NETHER_BRICKS, Material.ROCK, "fence_red_netherbrick"));
      reg.register(AbsentRegistry.createFence(Blocks.ACACIA_LOG, Material.WOOD, "fence_log_acacia"));
      reg.register(AbsentRegistry.createFence(Blocks.BIRCH_LOG, Material.WOOD, "fence_log_birch"));
      reg.register(AbsentRegistry.createFence(Blocks.DARK_OAK_LOG, Material.WOOD, "fence_log_darkoak"));
      reg.register(AbsentRegistry.createFence(Blocks.JUNGLE_LOG, Material.WOOD, "fence_log_jungle"));
      reg.register(AbsentRegistry.createFence(Blocks.OAK_LOG, Material.WOOD, "fence_log_oak"));
      reg.register(AbsentRegistry.createFence(Blocks.SPRUCE_LOG, Material.WOOD, "fence_log_spruce"));
      reg.register(AbsentRegistry.createFence(Blocks.OBSIDIAN, Material.ROCK, "fence_obsidian"));
      reg.register(AbsentRegistry.createFence(Blocks.CRIMSON_STEM, Material.WOOD, "fence_crimson"));
      reg.register(AbsentRegistry.createFence(Blocks.OBSIDIAN, Material.ROCK, "fence_warped"));
      //
      //                SLABS
      //
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
      reg.register(AbsentRegistry.createSlab(Blocks.OBSIDIAN, Material.ROCK, "slab_obsidian"));
      reg.register(AbsentRegistry.createSlab(Blocks.BASALT, Material.ROCK, "slab_basalt"));
      reg.register(AbsentRegistry.createSlab(Blocks.POLISHED_BASALT, Material.ROCK, "slab_polished_basalt"));
      reg.register(AbsentRegistry.createSlab(Blocks.CRYING_OBSIDIAN, Material.ROCK, "slab_crying_obsidian"));
      reg.register(AbsentRegistry.createSlab(Blocks.CRYING_OBSIDIAN, Material.ROCK, "slab_lodestone"));
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
      reg.register(AbsentRegistry.createSlab("slab_terracotta_white", Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_orange", Block.Properties.create(Material.ROCK, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_magenta", Block.Properties.create(Material.ROCK, MaterialColor.MAGENTA_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_light_blue", Block.Properties.create(Material.ROCK, MaterialColor.LIGHT_BLUE_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_yellow", Block.Properties.create(Material.ROCK, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_lime", Block.Properties.create(Material.ROCK, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_pink", Block.Properties.create(Material.ROCK, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_gray", Block.Properties.create(Material.ROCK, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_light_gray", Block.Properties.create(Material.ROCK, MaterialColor.LIGHT_GRAY_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_cyan", Block.Properties.create(Material.ROCK, MaterialColor.CYAN_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_purple", Block.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_blue", Block.Properties.create(Material.ROCK, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_brown", Block.Properties.create(Material.ROCK, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_green", Block.Properties.create(Material.ROCK, MaterialColor.GREEN_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_red", Block.Properties.create(Material.ROCK, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta_black", Block.Properties.create(Material.ROCK, MaterialColor.BLACK_TERRACOTTA).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_terracotta", Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_mushroom_stem", Block.Properties.create(Material.WOOD, MaterialColor.WOOL).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_red_mushroom", Block.Properties.create(Material.WOOD, MaterialColor.WOOL).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_brown_mushroom", Block.Properties.create(Material.WOOD, MaterialColor.WOOL).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab(Blocks.MUSHROOM_STEM, Material.WOOD, "slab_mushroom_polished"));
      reg.register(AbsentRegistry.createSlab("slab_quartz_bricks", Block.Properties.create(Material.ROCK).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createSlab("slab_magma", Block.Properties.create(Material.ROCK).hardnessAndResistance(1.25F, 4.2F).setLightLevel(state -> 3)));
      reg.register(AbsentRegistry.createSlab("slab_glowstone", Block.Properties.create(Material.ROCK).sound(SoundType.GLASS).hardnessAndResistance(1.25F, 4.2F).setLightLevel(state -> 15)));
      reg.register(AbsentRegistry.createSlab("slab_sea_lantern", Block.Properties.create(Material.ROCK).sound(SoundType.GLASS).hardnessAndResistance(1.25F, 4.2F).setLightLevel(state -> 15)));
      // 17 glass slabs   
      reg.register(AbsentRegistry.createSlab("slab_glass", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_white", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_orange", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_magenta", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_light_blue", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_yellow", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_lime", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_pink", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_gray", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_light_gray", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_cyan", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_purple", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_blue", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_brown", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_green", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_red", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createSlab("slab_glass_black", Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      //  
      //                STAIRS
      //
      reg.register(AbsentRegistry.createStair("stairs_glass", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_white", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_orange", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_magenta", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_light_blue", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_yellow", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_lime", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_pink", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_gray", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_light_gray", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_cyan", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_purple", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_blue", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_brown", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_green", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_red", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      reg.register(AbsentRegistry.createStair("stairs_glass_black", Blocks.GLASS, Block.Properties.create(Material.GLASS, MaterialColor.CLAY).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().setAllowsSpawn(ModAbsentBD::neverAllowSpawn).setOpaque(ModAbsentBD::isntSolid).setSuffocates(ModAbsentBD::isntSolid).setBlocksVision(ModAbsentBD::isntSolid)));
      //
      reg.register(AbsentRegistry.createStair("stairs_terracotta", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_white", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_orange", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_magenta", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_light_blue", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_yellow", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_lime", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_pink", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_gray", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_light_gray", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_cyan", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_purple", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_blue", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_brown", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_green", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_red", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_terracotta_black", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_basalt", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_polished_basalt", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_crying_obsidian", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair("stairs_lodestone", Blocks.TERRACOTTA, Block.Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(1.25F, 4.2F)));
      reg.register(AbsentRegistry.createStair(Blocks.COARSE_DIRT, Material.EARTH, "stairs_coarse_dirt"));
      reg.register(AbsentRegistry.createStair(Blocks.SMOOTH_STONE, Material.ROCK, "stairs_smooth_stone"));
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
      reg.register(AbsentRegistry.createStair(Blocks.OBSIDIAN, Material.ROCK, "stairs_obsidian"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_black"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_blue"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_brown"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_cyan"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_gray"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_green"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_light_blue"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_lime"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_magenta"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_orange"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_pink"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_purple"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_red"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_silver"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_white"));
      reg.register(AbsentRegistry.createStair(Blocks.BLACK_WOOL, Material.CARPET, "stairs_wool_yellow"));
      reg.register(AbsentRegistry.createStair(Blocks.BROWN_MUSHROOM_BLOCK, Material.WOOD, "stairs_red_mushroom"));
      reg.register(AbsentRegistry.createStair(Blocks.BROWN_MUSHROOM_BLOCK, Material.WOOD, "stairs_brown_mushroom"));
      reg.register(AbsentRegistry.createStair(Blocks.BROWN_MUSHROOM_BLOCK, Material.WOOD, "stairs_mushroom_stem"));
      reg.register(AbsentRegistry.createStair(Blocks.MUSHROOM_STEM, Material.WOOD, "stairs_mushroom_polished"));
      reg.register(AbsentRegistry.createStair(Blocks.QUARTZ_BRICKS, Material.ROCK, "stairs_quartz_bricks"));
      reg.register(AbsentRegistry.createStair("stairs_magma", Blocks.MAGMA_BLOCK, Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK)
          .hardnessAndResistance(1.25F, 4.2F).setLightLevel(s -> 3)));
      reg.register(AbsentRegistry.createStair("stairs_glowstone", Blocks.GLOWSTONE, Block.Properties.create(Material.ROCK, MaterialColor.SAND)
          .sound(SoundType.GLASS).hardnessAndResistance(1.25F, 4.2F).setLightLevel(s -> 15)));
      reg.register(AbsentRegistry.createStair("stairs_sea_lantern", Blocks.SEA_LANTERN, Block.Properties.create(Material.ROCK, MaterialColor.SAND)
          .sound(SoundType.GLASS).hardnessAndResistance(1.25F, 4.2F).setLightLevel(s -> 15)));
      //
      //                WALLS
      //
      reg.register(AbsentRegistry.createWall(Blocks.STRIPPED_OAK_LOG, Material.WOOD, "wall_stripped_acacia_log"));
      reg.register(AbsentRegistry.createWall(Blocks.STRIPPED_OAK_LOG, Material.WOOD, "wall_stripped_birch_log"));
      reg.register(AbsentRegistry.createWall(Blocks.STRIPPED_OAK_LOG, Material.WOOD, "wall_stripped_dark_oak_log"));
      reg.register(AbsentRegistry.createWall(Blocks.STRIPPED_OAK_LOG, Material.WOOD, "wall_stripped_jungle_log"));
      reg.register(AbsentRegistry.createWall(Blocks.STRIPPED_OAK_LOG, Material.WOOD, "wall_stripped_oak_log"));
      reg.register(AbsentRegistry.createWall(Blocks.STRIPPED_OAK_LOG, Material.WOOD, "wall_stripped_spruce_log"));
      reg.register(AbsentRegistry.createWall(Blocks.ACACIA_LOG, Material.WOOD, "wall_acacia_log"));
      reg.register(AbsentRegistry.createWall(Blocks.BIRCH_LOG, Material.WOOD, "wall_birch_log"));
      reg.register(AbsentRegistry.createWall(Blocks.DARK_OAK_LOG, Material.WOOD, "wall_dark_oak_log"));
      reg.register(AbsentRegistry.createWall(Blocks.JUNGLE_LOG, Material.WOOD, "wall_jungle_log"));
      reg.register(AbsentRegistry.createWall(Blocks.OAK_LOG, Material.WOOD, "wall_oak_log"));
      reg.register(AbsentRegistry.createWall(Blocks.SPRUCE_LOG, Material.WOOD, "wall_spruce_log"));
      reg.register(AbsentRegistry.createWall(Blocks.ANDESITE, Material.ROCK, "wall_andesite_smooth"));
      reg.register(AbsentRegistry.createWall(Blocks.DIORITE, Material.ROCK, "wall_diorite_smooth"));
      reg.register(AbsentRegistry.createWall(Blocks.END_STONE, Material.ROCK, "wall_end_stone"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_granite_smooth"));
      reg.register(AbsentRegistry.createWall(Blocks.PURPUR_BLOCK, Material.ROCK, "wall_purpur"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BLOCK, Material.ROCK, "wall_quartz"));
      reg.register(AbsentRegistry.createWall(Blocks.RED_SANDSTONE, Material.ROCK, "wall_sandstone_red_smooth"));
      reg.register(AbsentRegistry.createWall(Blocks.SANDSTONE, Material.ROCK, "wall_sandstone_smooth"));
      reg.register(AbsentRegistry.createWall(Blocks.STONE, Material.ROCK, "wall_stone"));
      reg.register(AbsentRegistry.createWall(Blocks.STONE, Material.ROCK, "wall_stone_slab"));
      reg.register(AbsentRegistry.createWall(Blocks.STONE_BRICKS, Material.ROCK, "wall_stonebrick_carved"));
      reg.register(AbsentRegistry.createWall(Blocks.STONE_BRICKS, Material.ROCK, "wall_stonebrick_cracked"));
      reg.register(AbsentRegistry.createWall(Blocks.OBSIDIAN, Material.ROCK, "wall_obsidian"));
      reg.register(AbsentRegistry.createWall(Blocks.PRISMARINE, Material.ROCK, "wall_prismarine_bricks"));
      reg.register(AbsentRegistry.createWall(Blocks.DARK_PRISMARINE, Material.ROCK, "wall_dark_prismarine"));
      reg.register(AbsentRegistry.createWall(Blocks.CRIMSON_STEM, Material.WOOD, "wall_crimson"));
      reg.register(AbsentRegistry.createWall(Blocks.WARPED_STEM, Material.WOOD, "wall_warped"));
      reg.register(AbsentRegistry.createWall(Blocks.CRYING_OBSIDIAN, Material.WOOD, "wall_crying_obsidian"));
      reg.register(AbsentRegistry.createWall(Blocks.BASALT, Material.WOOD, "wall_basalt"));
      reg.register(AbsentRegistry.createWall(Blocks.POLISHED_BASALT, Material.WOOD, "wall_polished_basalt"));
      reg.register(AbsentRegistry.createWall(Blocks.LODESTONE, Material.WOOD, "wall_lodestone"));
      reg.register(AbsentRegistry.createWall(Blocks.STRIPPED_CRIMSON_HYPHAE, Material.WOOD, "wall_stripped_crimson"));
      reg.register(AbsentRegistry.createWall(Blocks.STRIPPED_WARPED_HYPHAE, Material.WOOD, "wall_stripped_warped"));
      reg.register(AbsentRegistry.createWall(Blocks.MUSHROOM_STEM, Material.WOOD, "wall_mushroom_stem"));
      reg.register(AbsentRegistry.createWall(Blocks.RED_MUSHROOM_BLOCK, Material.WOOD, "wall_red_mushroom"));
      reg.register(AbsentRegistry.createWall(Blocks.BROWN_MUSHROOM_BLOCK, Material.WOOD, "wall_brown_mushroom"));
      reg.register(AbsentRegistry.createWall(Blocks.MUSHROOM_STEM, Material.WOOD, "wall_mushroom_polished"));
      reg.register(AbsentRegistry.createWall(Blocks.QUARTZ_BRICKS, Material.ROCK, "wall_quartz_bricks"));
      reg.register(AbsentRegistry.createWall(Blocks.MAGMA_BLOCK, Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK)
          .hardnessAndResistance(1.25F, 4.2F).setLightLevel(s -> 3), "wall_magma"));
      reg.register(AbsentRegistry.createWall(Blocks.GLOWSTONE, Block.Properties.create(Material.ROCK, MaterialColor.SAND)
          .hardnessAndResistance(1.25F, 4.2F).sound(SoundType.GLASS).setLightLevel(s -> 15), "wall_glowstone"));
      reg.register(AbsentRegistry.createWall(Blocks.SEA_LANTERN, Block.Properties.create(Material.ROCK, MaterialColor.SAND)
          .hardnessAndResistance(1.25F, 4.2F).sound(SoundType.GLASS).setLightLevel(s -> 15), "wall_sea_lantern"));
      //
      //                GATE 
      //
      reg.register(AbsentRegistry.createGate("gate_nether_bricks", Blocks.NETHER_BRICKS, Block.Properties.create(Material.ROCK)));
      reg.register(AbsentRegistry.createGate("gate_red_nether_bricks", Blocks.NETHER_BRICKS, Block.Properties.create(Material.ROCK)));
      reg.register(AbsentRegistry.createGate("gate_quartz", Blocks.QUARTZ_BLOCK, Block.Properties.create(Material.ROCK)));
    }

    @SubscribeEvent
    public static void onItemsRegistry(RegistryEvent.Register<Item> event) {
      Item.Properties properties = new Item.Properties().group(AbsentRegistry.itemGroup);
      for (Block b : AbsentRegistry.blocks) {
        event.getRegistry().register(new BlockItem(b, properties).setRegistryName(((IBlockAbsent) b).rawName()));
      }
    }
  }
}
