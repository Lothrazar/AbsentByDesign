package com.lothrazar.absentbydesign.registry;

import java.util.ArrayList;
import java.util.List;
import com.lothrazar.absentbydesign.ModAbsentBD;
import com.lothrazar.absentbydesign.block.BlockAbsentFence;
import com.lothrazar.absentbydesign.block.BlockAbsentGate;
import com.lothrazar.absentbydesign.block.BlockAbsentSlab;
import com.lothrazar.absentbydesign.block.BlockAbsentStair;
import com.lothrazar.absentbydesign.block.BlockAbsentWall;
import com.lothrazar.absentbydesign.block.DoorAbsentBlock;
import com.lothrazar.absentbydesign.block.TrapDoorAbsent;
import com.lothrazar.library.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AbsentRegistry {
  //
  // NO INVENTORY SCREEN BLOCKS (chest, furnace, ...)
  // NO ORES
  // NO RECIPE OVERRIDES / REMOVALS (example: glass walls vs glass panes, chiseled sandstone -> normal slab)
  // NO METAL / DIAMOND / EMERALD
  // AVOID METAL-LIKE STORAGE BLOCKS (coal, netherwart, lapis, slime, honey, bone, kelp, ... )
  // AVOID PLANTS (leaves, coral, moss, cactus, melon ...)
  // AVOID GRAVITY & WORLD TRIGGER BLOCKS (sand, ice, powder, grass, sponge, tinted_glass, dripstone, infested, ...)
  // AVOID REDSTONE STUFF (levers, piston, buttons, dispenser, pressure_plates ...)
  // AVOID NEW BLOCK TYPES (chain, lantern, glass panes, iron_bars, ...)
  // AVOID PILLAR
  // AVOID CHISELED?
  // AVOID GLAZED TERRACOTTA
  // 
  //1.19 NEW ADD THIS 
  //
  //
  //
  //
  //
  //

  private static final List<Block> BLOCKLIST = new ArrayList<>();
  private static final String HAX = "block." + ModAbsentBD.MODID + ".";
  private static BlockAbsentFence FENCE_QUARTZ;

  @SubscribeEvent
  public static void buildContents(CreativeModeTabEvent.Register event) {
    event.registerCreativeModeTab(new ResourceLocation(ModAbsentBD.MODID, "tab"), builder -> builder
        .title(Component.translatable("itemGroup." + ModAbsentBD.MODID))
        .icon(() -> new ItemStack(FENCE_QUARTZ))
        .displayItems((enabledFlags, populator) -> {
          for (Block b : BLOCKLIST) {
            populator.accept(new ItemStack(b));
          }
        }));
    //    RegistryFactory.buildTab(event, ModAbsentBD.MODID, new ItemStack(FENCE_QUARTZ.asItem()), null);
  }

  public static boolean never(BlockState state, BlockGetter reader, BlockPos pos) {
    return false;
  }

  public static Boolean never(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
    return false;
  }

  //instead of RegistryEvent.Register<Item> event or <Block>
  @SubscribeEvent
  public static void onBlocksRegistry(RegisterEvent event) {
    Item.Properties properties = new Item.Properties();
    event.register(Registries.ITEM, reg -> {
      for (Block b : AbsentRegistry.BLOCKLIST) {
        String id = b.getDescriptionId().replace(HAX, "");
        reg.register(id, new BlockItem(b, properties));
        //        ITEMS.register(id, () -> new BlockItem(b, properties));
      }
    });
    event.register(Registries.BLOCK, reg -> {
      //                FENCES
      //
      reg.register("fence_log_acacia", createFence(Blocks.ACACIA_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("fence_log_birch", createFence(Blocks.BIRCH_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("fence_log_darkoak", createFence(Blocks.DARK_OAK_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("fence_log_jungle", createFence(Blocks.JUNGLE_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("fence_log_oak", createFence(Blocks.OAK_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("fence_log_spruce", createFence(Blocks.SPRUCE_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("fence_red_netherbrick", createFence(Blocks.RED_NETHER_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("fence_crimson", createFence(Blocks.CRIMSON_STEM, Block.Properties.of(Material.WOOD)));
      reg.register("fence_warped", createFence(Blocks.WARPED_STEM, Block.Properties.of(Material.WOOD)));
      reg.register("fence_obsidian", createFence(Blocks.OBSIDIAN, Block.Properties.of(Material.STONE)));
      FENCE_QUARTZ = (BlockAbsentFence) createFence(Blocks.QUARTZ_BLOCK, Block.Properties.of(Material.STONE));
      reg.register("fence_quartz", FENCE_QUARTZ);
      //
      //                SLABS
      //
      reg.register("slab_end_stone", createSlab(Block.Properties.of(Material.STONE), Blocks.END_STONE));
      reg.register("slab_netherrack", createSlab(Block.Properties.of(Material.STONE), Blocks.NETHERRACK));
      reg.register("slab_snow", createSlab(Block.Properties.of(Material.STONE), Blocks.SNOW_BLOCK));
      reg.register("slab_bricks_cracked", createSlab(Block.Properties.of(Material.STONE), Blocks.CRACKED_STONE_BRICKS));
      reg.register("slab_coarse_dirt", createSlab(Block.Properties.of(Material.TOP_SNOW), Blocks.COARSE_DIRT));
      reg.register("slab_obsidian", createSlab(Block.Properties.of(Material.STONE), Blocks.OBSIDIAN));
      reg.register("slab_basalt", createSlab(Block.Properties.of(Material.STONE), Blocks.BASALT));
      reg.register("slab_polished_basalt", createSlab(Block.Properties.of(Material.STONE), Blocks.POLISHED_BASALT));
      reg.register("slab_crying_obsidian", createSlab(Block.Properties.of(Material.STONE).lightLevel(state -> 10), Blocks.CRYING_OBSIDIAN));
      reg.register("slab_lodestone", createSlab(Block.Properties.of(Material.STONE), Blocks.LODESTONE));
      reg.register("slab_quartz_bricks", createSlab(Block.Properties.of(Material.STONE), Blocks.QUARTZ_BRICKS));
      reg.register("slab_magma", createSlab(Block.Properties.of(Material.STONE).lightLevel(state -> 3), Blocks.MAGMA_BLOCK));
      reg.register("slab_glowstone", createSlab(Block.Properties.of(Material.STONE).sound(SoundType.GLASS).lightLevel(state -> 15), Blocks.GLOWSTONE));
      reg.register("slab_sea_lantern", createSlab(Block.Properties.of(Material.STONE).sound(SoundType.GLASS).lightLevel(state -> 15), Blocks.SEA_LANTERN));
      reg.register("slab_concrete_black", createSlab(Block.Properties.of(Material.STONE), Blocks.BLACK_CONCRETE));
      reg.register("slab_concrete_blue", createSlab(Block.Properties.of(Material.STONE), Blocks.BLUE_CONCRETE));
      reg.register("slab_concrete_brown", createSlab(Block.Properties.of(Material.STONE), Blocks.BROWN_CONCRETE));
      reg.register("slab_concrete_cyan", createSlab(Block.Properties.of(Material.STONE), Blocks.CYAN_CONCRETE));
      reg.register("slab_concrete_gray", createSlab(Block.Properties.of(Material.STONE), Blocks.GRAY_CONCRETE));
      reg.register("slab_concrete_green", createSlab(Block.Properties.of(Material.STONE), Blocks.GREEN_CONCRETE));
      reg.register("slab_concrete_light_blue", createSlab(Block.Properties.of(Material.STONE), Blocks.LIGHT_BLUE_CONCRETE));
      reg.register("slab_concrete_lime", createSlab(Block.Properties.of(Material.STONE), Blocks.LIME_CONCRETE));
      reg.register("slab_concrete_magenta", createSlab(Block.Properties.of(Material.STONE), Blocks.MAGENTA_CONCRETE));
      reg.register("slab_concrete_orange", createSlab(Block.Properties.of(Material.STONE), Blocks.ORANGE_CONCRETE));
      reg.register("slab_concrete_pink", createSlab(Block.Properties.of(Material.STONE), Blocks.PINK_CONCRETE));
      reg.register("slab_concrete_purple", createSlab(Block.Properties.of(Material.STONE), Blocks.PURPLE_CONCRETE));
      reg.register("slab_concrete_red", createSlab(Block.Properties.of(Material.STONE), Blocks.RED_CONCRETE));
      reg.register("slab_concrete_silver", createSlab(Block.Properties.of(Material.STONE), Blocks.LIGHT_GRAY_CONCRETE));
      reg.register("slab_concrete_white", createSlab(Block.Properties.of(Material.STONE), Blocks.WHITE_CONCRETE));
      reg.register("slab_concrete_yellow", createSlab(Block.Properties.of(Material.STONE), Blocks.YELLOW_CONCRETE));
      reg.register("slab_wool_black", createSlab(Block.Properties.of(Material.WOOL), Blocks.BLACK_WOOL));
      reg.register("slab_wool_blue", createSlab(Block.Properties.of(Material.WOOL), Blocks.BLUE_WOOL));
      reg.register("slab_wool_brown", createSlab(Block.Properties.of(Material.WOOL), Blocks.BROWN_WOOL));
      reg.register("slab_wool_cyan", createSlab(Block.Properties.of(Material.WOOL), Blocks.CYAN_WOOL));
      reg.register("slab_wool_gray", createSlab(Block.Properties.of(Material.WOOL), Blocks.GRAY_WOOL));
      reg.register("slab_wool_green", createSlab(Block.Properties.of(Material.WOOL), Blocks.GREEN_WOOL));
      reg.register("slab_wool_light_blue", createSlab(Block.Properties.of(Material.WOOL), Blocks.LIGHT_BLUE_WOOL));
      reg.register("slab_wool_lime", createSlab(Block.Properties.of(Material.WOOL), Blocks.LIME_WOOL));
      reg.register("slab_wool_magenta", createSlab(Block.Properties.of(Material.WOOL), Blocks.MAGENTA_WOOL));
      reg.register("slab_wool_orange", createSlab(Block.Properties.of(Material.WOOL), Blocks.ORANGE_WOOL));
      reg.register("slab_wool_pink", createSlab(Block.Properties.of(Material.WOOL), Blocks.PINK_WOOL));
      reg.register("slab_wool_purple", createSlab(Block.Properties.of(Material.WOOL), Blocks.PURPLE_WOOL));
      reg.register("slab_wool_red", createSlab(Block.Properties.of(Material.WOOL), Blocks.RED_WOOL));
      reg.register("slab_wool_silver", createSlab(Block.Properties.of(Material.WOOL), Blocks.LIGHT_GRAY_WOOL));
      reg.register("slab_wool_white", createSlab(Block.Properties.of(Material.WOOL), Blocks.WHITE_WOOL));
      reg.register("slab_wool_yellow", createSlab(Block.Properties.of(Material.WOOL), Blocks.YELLOW_WOOL));
      reg.register("slab_terracotta_white", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE), Blocks.WHITE_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_orange", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE), Blocks.ORANGE_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_magenta", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_MAGENTA), Blocks.MAGENTA_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_light_blue", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_BLUE), Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_yellow", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_YELLOW), Blocks.YELLOW_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_lime", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GREEN), Blocks.LIME_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_pink", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK), Blocks.PINK_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_gray", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY), Blocks.GRAY_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_light_gray", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY), Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_cyan", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_CYAN), Blocks.CYAN_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_purple", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PURPLE), Blocks.PURPLE_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_blue", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLUE), Blocks.BLUE_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_brown", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BROWN), Blocks.BROWN_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_green", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GREEN), Blocks.GREEN_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_red", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED), Blocks.RED_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta_black", createSlab(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLACK), Blocks.BLACK_GLAZED_TERRACOTTA));
      reg.register("slab_terracotta", createSlab(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.TERRACOTTA));
      Block SLAB_GLASS = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GLASS);
      reg.register("slab_glass", SLAB_GLASS);
      Block SLAB_GLASS_WHITE = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.WHITE_STAINED_GLASS);
      reg.register("slab_glass_white", SLAB_GLASS_WHITE);
      Block SLAB_GLASS_ORANGE = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.ORANGE_STAINED_GLASS);
      reg.register("slab_glass_orange", SLAB_GLASS_ORANGE);
      Block SLAB_GLASS_MAGENTA = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.MAGENTA_STAINED_GLASS);
      reg.register("slab_glass_magenta", SLAB_GLASS_MAGENTA);
      Block SLAB_GLASS_LIGHT_BLUE = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIGHT_BLUE_STAINED_GLASS);
      reg.register("slab_glass_light_blue", SLAB_GLASS_LIGHT_BLUE);
      Block SLAB_GLASS_YELLOW = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.YELLOW_STAINED_GLASS);
      reg.register("slab_glass_yellow", SLAB_GLASS_YELLOW);
      Block SLAB_GLASS_LIME = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIME_STAINED_GLASS);
      reg.register("slab_glass_lime", SLAB_GLASS_LIME);
      Block SLAB_GLASS_PINK = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.PINK_STAINED_GLASS);
      reg.register("slab_glass_pink", SLAB_GLASS_PINK);
      Block SLAB_GLASS_GRAY = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GRAY_STAINED_GLASS);
      reg.register("slab_glass_gray", SLAB_GLASS_GRAY);
      Block SLAB_GLASS_LIGHT_GRAY = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIGHT_GRAY_STAINED_GLASS);
      reg.register("slab_glass_light_gray", SLAB_GLASS_LIGHT_GRAY);
      Block SLAB_GLASS_CYAN = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.CYAN_STAINED_GLASS);
      reg.register("slab_glass_cyan", SLAB_GLASS_CYAN);
      Block SLAB_GLASS_PURPLE = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.PURPLE_STAINED_GLASS);
      reg.register("slab_glass_purple", SLAB_GLASS_PURPLE);
      Block SLAB_GLASS_BLUE = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BLUE_STAINED_GLASS);
      reg.register("slab_glass_blue", SLAB_GLASS_BLUE);
      Block SLAB_GLASS_BROWN = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BROWN_STAINED_GLASS);
      reg.register("slab_glass_brown", SLAB_GLASS_BROWN);
      Block SLAB_GLASS_GREEN = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GREEN_STAINED_GLASS);
      reg.register("slab_glass_green", SLAB_GLASS_GREEN);
      Block SLAB_GLASS_RED = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.RED_STAINED_GLASS);
      reg.register("slab_glass_red", SLAB_GLASS_RED);
      Block SLAB_GLASS_BLACK = createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BLACK_STAINED_GLASS);
      reg.register("slab_glass_black", SLAB_GLASS_BLACK);
      reg.register("slab_mushroom_stem", createSlab(Block.Properties.of(Material.WOOD, MaterialColor.WOOL), Blocks.MUSHROOM_STEM));
      reg.register("slab_red_mushroom", createSlab(Block.Properties.of(Material.WOOD, MaterialColor.WOOL), Blocks.RED_MUSHROOM));
      reg.register("slab_brown_mushroom", createSlab(Block.Properties.of(Material.WOOD, MaterialColor.WOOL), Blocks.BROWN_MUSHROOM));
      reg.register("slab_mushroom_polished", createSlab(Block.Properties.of(Material.WOOD), Blocks.MUSHROOM_STEM));
      reg.register("slab_calcite", createSlab(Block.Properties.of(Material.STONE), Blocks.CALCITE));
      reg.register("slab_amethyst", createSlab(Block.Properties.of(Material.STONE), Blocks.AMETHYST_BLOCK));
      reg.register("slab_tuff", createSlab(Block.Properties.of(Material.STONE), Blocks.TUFF));
      reg.register("slab_smooth_basalt", createSlab(Block.Properties.of(Material.STONE), Blocks.SMOOTH_BASALT));
      reg.register("slab_cracked_nether_bricks", createSlab(Block.Properties.of(Material.STONE), Blocks.CRACKED_NETHER_BRICKS));
      reg.register("slab_cracked_deepslate_bricks", createSlab(Block.Properties.of(Material.STONE), Blocks.CRACKED_DEEPSLATE_BRICKS));
      reg.register("slab_cracked_deepslate_tiles", createSlab(Block.Properties.of(Material.STONE), Blocks.CRACKED_DEEPSLATE_TILES));
      reg.register("slab_sculk", createSlab(Block.Properties.of(Material.SCULK), Blocks.SCULK));
      reg.register("slab_mud", createSlab(Block.Properties.of(Material.DIRT), Blocks.MUD));
      reg.register("slab_packed_mud", createSlab(Block.Properties.of(Material.DIRT), Blocks.PACKED_MUD));
      reg.register("slab_reinforced_deepslate", createSlab(Block.Properties.of(Material.STONE), Blocks.REINFORCED_DEEPSLATE));
      reg.register("slab_ochre_froglight", createSlab(Block.Properties.of(Material.FROGLIGHT).lightLevel(s -> 15), Blocks.OCHRE_FROGLIGHT));
      reg.register("slab_pearlescent_froglight", createSlab(Block.Properties.of(Material.FROGLIGHT).lightLevel(s -> 15), Blocks.PEARLESCENT_FROGLIGHT));
      reg.register("slab_verdant_froglight", createSlab(Block.Properties.of(Material.FROGLIGHT).lightLevel(s -> 15), Blocks.VERDANT_FROGLIGHT));
      //
      //                STAIRS
      //
      reg.register("stairs_coarse_dirt", createStair(Block.Properties.of(Material.DIRT), Blocks.COARSE_DIRT));
      reg.register("stairs_smooth_stone", createStair(Block.Properties.of(Material.STONE), Blocks.SMOOTH_STONE));
      reg.register("stairs_end_stone", createStair(Block.Properties.of(Material.STONE), Blocks.END_STONE));
      reg.register("stairs_bricks_cracked", createStair(Block.Properties.of(Material.STONE), Blocks.CRACKED_STONE_BRICKS));
      reg.register("stairs_netherrack", createStair(Block.Properties.of(Material.STONE), Blocks.NETHERRACK));
      reg.register("stairs_snow", createStair(Block.Properties.of(Material.TOP_SNOW), Blocks.SNOW_BLOCK));
      reg.register("stairs_obsidian", createStair(Block.Properties.of(Material.STONE), Blocks.OBSIDIAN));
      reg.register("stairs_quartz_bricks", createStair(Block.Properties.of(Material.STONE), Blocks.QUARTZ_BRICKS));
      reg.register("stairs_basalt", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.BASALT));
      reg.register("stairs_polished_basalt", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.POLISHED_BASALT));
      reg.register("stairs_crying_obsidian", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY).lightLevel(state -> 10), Blocks.CRYING_OBSIDIAN));
      reg.register("stairs_lodestone", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.LODESTONE));
      reg.register("stairs_magma", createStair(Block.Properties.of(Material.STONE, MaterialColor.NETHER).lightLevel(s -> 3), Blocks.MAGMA_BLOCK));
      reg.register("stairs_glowstone", createStair(Block.Properties.of(Material.STONE, MaterialColor.SAND).sound(SoundType.GLASS).lightLevel(s -> 15), Blocks.GLOWSTONE));
      reg.register("stairs_sea_lantern", createStair(Block.Properties.of(Material.STONE, MaterialColor.SAND).sound(SoundType.GLASS).lightLevel(s -> 15), Blocks.SEA_LANTERN));
      reg.register("stairs_concrete_black", createStair(Block.Properties.of(Material.STONE), Blocks.BLACK_CONCRETE));
      reg.register("stairs_concrete_blue", createStair(Block.Properties.of(Material.STONE), Blocks.BLUE_CONCRETE));
      reg.register("stairs_concrete_brown", createStair(Block.Properties.of(Material.STONE), Blocks.BROWN_CONCRETE));
      reg.register("stairs_concrete_cyan", createStair(Block.Properties.of(Material.STONE), Blocks.CYAN_CONCRETE));
      reg.register("stairs_concrete_gray", createStair(Block.Properties.of(Material.STONE), Blocks.GRAY_CONCRETE));
      reg.register("stairs_concrete_green", createStair(Block.Properties.of(Material.STONE), Blocks.GREEN_CONCRETE));
      reg.register("stairs_concrete_light_blue", createStair(Block.Properties.of(Material.STONE), Blocks.LIGHT_BLUE_CONCRETE));
      reg.register("stairs_concrete_lime", createStair(Block.Properties.of(Material.STONE), Blocks.LIME_CONCRETE));
      reg.register("stairs_concrete_magenta", createStair(Block.Properties.of(Material.STONE), Blocks.MAGENTA_CONCRETE));
      reg.register("stairs_concrete_orange", createStair(Block.Properties.of(Material.STONE), Blocks.ORANGE_CONCRETE));
      reg.register("stairs_concrete_pink", createStair(Block.Properties.of(Material.STONE), Blocks.PINK_CONCRETE));
      reg.register("stairs_concrete_purple", createStair(Block.Properties.of(Material.STONE), Blocks.PURPLE_CONCRETE));
      reg.register("stairs_concrete_red", createStair(Block.Properties.of(Material.STONE), Blocks.RED_CONCRETE));
      reg.register("stairs_concrete_silver", createStair(Block.Properties.of(Material.STONE), Blocks.LIGHT_GRAY_CONCRETE));
      reg.register("stairs_concrete_white", createStair(Block.Properties.of(Material.STONE), Blocks.WHITE_CONCRETE));
      reg.register("stairs_concrete_yellow", createStair(Block.Properties.of(Material.STONE), Blocks.YELLOW_CONCRETE));
      reg.register("stairs_wool_black", createStair(Block.Properties.of(Material.WOOL), Blocks.BLACK_WOOL));
      reg.register("stairs_wool_blue", createStair(Block.Properties.of(Material.WOOL), Blocks.BLUE_WOOL));
      reg.register("stairs_wool_brown", createStair(Block.Properties.of(Material.WOOL), Blocks.BROWN_WOOL));
      reg.register("stairs_wool_cyan", createStair(Block.Properties.of(Material.WOOL), Blocks.CYAN_WOOL));
      reg.register("stairs_wool_gray", createStair(Block.Properties.of(Material.WOOL), Blocks.GRAY_WOOL));
      reg.register("stairs_wool_green", createStair(Block.Properties.of(Material.WOOL), Blocks.GREEN_WOOL));
      reg.register("stairs_wool_light_blue", createStair(Block.Properties.of(Material.WOOL), Blocks.LIGHT_BLUE_WOOL));
      reg.register("stairs_wool_lime", createStair(Block.Properties.of(Material.WOOL), Blocks.LIME_WOOL));
      reg.register("stairs_wool_magenta", createStair(Block.Properties.of(Material.WOOL), Blocks.MAGENTA_WOOL));
      reg.register("stairs_wool_orange", createStair(Block.Properties.of(Material.WOOL), Blocks.ORANGE_WOOL));
      reg.register("stairs_wool_pink", createStair(Block.Properties.of(Material.WOOL), Blocks.PINK_WOOL));
      reg.register("stairs_wool_purple", createStair(Block.Properties.of(Material.WOOL), Blocks.PURPLE_WOOL));
      reg.register("stairs_wool_red", createStair(Block.Properties.of(Material.WOOL), Blocks.RED_WOOL));
      reg.register("stairs_wool_silver", createStair(Block.Properties.of(Material.WOOL), Blocks.LIGHT_GRAY_WOOL));
      reg.register("stairs_wool_white", createStair(Block.Properties.of(Material.WOOL), Blocks.WHITE_WOOL));
      reg.register("stairs_wool_yellow", createStair(Block.Properties.of(Material.WOOL), Blocks.YELLOW_WOOL));
      reg.register("stairs_terracotta", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.TERRACOTTA));
      reg.register("stairs_terracotta_white", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.WHITE_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_orange", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.ORANGE_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_magenta", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.MAGENTA_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_light_blue", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_yellow", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.YELLOW_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_lime", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.LIME_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_pink", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.PINK_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_gray", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.GRAY_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_light_gray", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_cyan", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.CYAN_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_purple", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.PURPLE_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_blue", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.BLUE_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_brown", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.BROWN_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_green", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.GREEN_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_red", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.RED_GLAZED_TERRACOTTA));
      reg.register("stairs_terracotta_black", createStair(Block.Properties.of(Material.STONE, MaterialColor.CLAY), Blocks.BLACK_GLAZED_TERRACOTTA));
      Block STAIRS_GLASS = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GLASS);
      reg.register("stairs_glass", STAIRS_GLASS);
      Block STAIRS_GLASS_WHITE = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.WHITE_STAINED_GLASS);
      reg.register("stairs_glass_white", STAIRS_GLASS_WHITE);
      Block STAIRS_GLASS_ORANGE = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.ORANGE_STAINED_GLASS);
      reg.register("stairs_glass_orange", STAIRS_GLASS_ORANGE);
      Block STAIRS_GLASS_MAGENTA = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.MAGENTA_STAINED_GLASS);
      reg.register("stairs_glass_magenta", STAIRS_GLASS_MAGENTA);
      Block STAIRS_GLASS_LIGHT_BLUE = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIGHT_BLUE_STAINED_GLASS);
      reg.register("stairs_glass_light_blue", STAIRS_GLASS_LIGHT_BLUE);
      Block STAIRS_GLASS_YELLOW = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.YELLOW_STAINED_GLASS);
      reg.register("stairs_glass_yellow", STAIRS_GLASS_YELLOW);
      Block STAIRS_GLASS_LIME = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIME_STAINED_GLASS);
      reg.register("stairs_glass_lime", STAIRS_GLASS_LIME);
      Block STAIRS_GLASS_PINK = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.PINK_STAINED_GLASS);
      reg.register("stairs_glass_pink", STAIRS_GLASS_PINK);
      Block STAIRS_GLASS_GRAY = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GRAY_STAINED_GLASS);
      reg.register("stairs_glass_gray", STAIRS_GLASS_GRAY);
      Block STAIRS_GLASS_LIGHT_GRAY = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIGHT_GRAY_STAINED_GLASS);
      reg.register("stairs_glass_light_gray", STAIRS_GLASS_LIGHT_GRAY);
      Block STAIRS_GLASS_CYAN = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.CYAN_STAINED_GLASS);
      reg.register("stairs_glass_cyan", STAIRS_GLASS_CYAN);
      Block STAIRS_GLASS_PURPLE = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.PURPLE_STAINED_GLASS);
      reg.register("stairs_glass_purple", STAIRS_GLASS_PURPLE);
      Block STAIRS_GLASS_BLUE = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BLUE_STAINED_GLASS);
      reg.register("stairs_glass_blue", STAIRS_GLASS_BLUE);
      Block STAIRS_GLASS_BROWN = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BROWN_STAINED_GLASS);
      reg.register("stairs_glass_brown", STAIRS_GLASS_BROWN);
      Block STAIRS_GLASS_GREEN = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GREEN_STAINED_GLASS);
      reg.register("stairs_glass_green", STAIRS_GLASS_GREEN);
      Block STAIRS_GLASS_RED = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.RED_STAINED_GLASS);
      reg.register("stairs_glass_red", STAIRS_GLASS_RED);
      Block STAIRS_GLASS_BLACK = createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BLACK_STAINED_GLASS);
      reg.register("stairs_glass_black", STAIRS_GLASS_BLACK);
      reg.register("stairs_red_mushroom", createStair(Block.Properties.of(Material.WOOD), Blocks.RED_MUSHROOM_BLOCK));
      reg.register("stairs_brown_mushroom", createStair(Block.Properties.of(Material.WOOD), Blocks.BROWN_MUSHROOM_BLOCK));
      reg.register("stairs_mushroom_stem", createStair(Block.Properties.of(Material.WOOD), Blocks.MUSHROOM_STEM));
      reg.register("stairs_mushroom_polished", createStair(Block.Properties.of(Material.WOOD), Blocks.MUSHROOM_STEM));
      reg.register("stairs_calcite", createStair(Block.Properties.of(Material.STONE), Blocks.CALCITE));
      reg.register("stairs_amethyst", createStair(Block.Properties.of(Material.STONE), Blocks.AMETHYST_BLOCK));
      reg.register("stairs_tuff", createStair(Block.Properties.of(Material.STONE), Blocks.TUFF));
      reg.register("stairs_smooth_basalt", createStair(Block.Properties.of(Material.STONE), Blocks.SMOOTH_BASALT));
      reg.register("stairs_cracked_nether_bricks", createStair(Block.Properties.of(Material.STONE), Blocks.CRACKED_NETHER_BRICKS));
      reg.register("stairs_cracked_deepslate_bricks", createStair(Block.Properties.of(Material.STONE), Blocks.CRACKED_DEEPSLATE_BRICKS));
      reg.register("stairs_cracked_deepslate_tiles", createStair(Block.Properties.of(Material.STONE), Blocks.CRACKED_DEEPSLATE_TILES));
      reg.register("stairs_sculk", createStair(Block.Properties.of(Material.SCULK), Blocks.SCULK));
      reg.register("stairs_mud", createStair(Block.Properties.of(Material.DIRT), Blocks.MUD));
      reg.register("stairs_packed_mud", createStair(Block.Properties.of(Material.DIRT), Blocks.PACKED_MUD));
      reg.register("stairs_reinforced_deepslate", createStair(Block.Properties.of(Material.STONE), Blocks.REINFORCED_DEEPSLATE));
      reg.register("stairs_ochre_froglight", createStair(Block.Properties.of(Material.FROGLIGHT).lightLevel(s -> 15), Blocks.OCHRE_FROGLIGHT));
      reg.register("stairs_pearlescent_froglight", createStair(Block.Properties.of(Material.FROGLIGHT).lightLevel(s -> 15), Blocks.PEARLESCENT_FROGLIGHT));
      reg.register("stairs_verdant_froglight", createStair(Block.Properties.of(Material.FROGLIGHT).lightLevel(s -> 15), Blocks.VERDANT_FROGLIGHT));
      //
      //                WALLS
      //
      reg.register("wall_stripped_acacia_log", createWall(Block.Properties.of(Material.WOOD), Blocks.STRIPPED_ACACIA_LOG));
      reg.register("wall_stripped_birch_log", createWall(Block.Properties.of(Material.WOOD), Blocks.STRIPPED_BIRCH_LOG));
      reg.register("wall_stripped_dark_oak_log", createWall(Block.Properties.of(Material.WOOD), Blocks.STRIPPED_DARK_OAK_LOG));
      reg.register("wall_stripped_jungle_log", createWall(Block.Properties.of(Material.WOOD), Blocks.STRIPPED_JUNGLE_LOG));
      reg.register("wall_stripped_oak_log", createWall(Block.Properties.of(Material.WOOD), Blocks.STRIPPED_OAK_LOG));
      reg.register("wall_stripped_spruce_log", createWall(Block.Properties.of(Material.WOOD), Blocks.STRIPPED_SPRUCE_LOG));
      reg.register("wall_stripped_mangrove_log", createWall(Block.Properties.of(Material.WOOD), Blocks.STRIPPED_MANGROVE_LOG));
      reg.register("wall_acacia_log", createWall(Block.Properties.of(Material.WOOD), Blocks.ACACIA_LOG));
      reg.register("wall_birch_log", createWall(Block.Properties.of(Material.WOOD), Blocks.BIRCH_LOG));
      reg.register("wall_dark_oak_log", createWall(Block.Properties.of(Material.WOOD), Blocks.DARK_OAK_LOG));
      reg.register("wall_jungle_log", createWall(Block.Properties.of(Material.WOOD), Blocks.JUNGLE_LOG));
      reg.register("wall_oak_log", createWall(Block.Properties.of(Material.WOOD), Blocks.OAK_LOG));
      reg.register("wall_spruce_log", createWall(Block.Properties.of(Material.WOOD), Blocks.SPRUCE_LOG));
      reg.register("wall_mangrove_log", createWall(Block.Properties.of(Material.WOOD), Blocks.MANGROVE_LOG));
      reg.register("wall_andesite_smooth", createWall(Block.Properties.of(Material.STONE), Blocks.ANDESITE));
      reg.register("wall_diorite_smooth", createWall(Block.Properties.of(Material.STONE), Blocks.DIORITE));
      reg.register("wall_end_stone", createWall(Block.Properties.of(Material.STONE), Blocks.END_STONE));
      reg.register("wall_granite_smooth", createWall(Block.Properties.of(Material.STONE), Blocks.QUARTZ_BLOCK));
      reg.register("wall_purpur", createWall(Block.Properties.of(Material.STONE), Blocks.PURPUR_BLOCK));
      reg.register("wall_quartz", createWall(Block.Properties.of(Material.STONE), Blocks.QUARTZ_BLOCK));
      reg.register("wall_sandstone_red_smooth", createWall(Block.Properties.of(Material.STONE), Blocks.RED_SANDSTONE));
      reg.register("wall_sandstone_smooth", createWall(Block.Properties.of(Material.STONE), Blocks.SANDSTONE));
      reg.register("wall_stone", createWall(Block.Properties.of(Material.STONE), Blocks.STONE));
      reg.register("wall_stone_slab", createWall(Block.Properties.of(Material.STONE), Blocks.STONE));
      reg.register("wall_stonebrick_carved", createWall(Block.Properties.of(Material.STONE), Blocks.STONE_BRICKS));
      reg.register("wall_stonebrick_cracked", createWall(Block.Properties.of(Material.STONE), Blocks.STONE_BRICKS));
      reg.register("wall_obsidian", createWall(Block.Properties.of(Material.STONE), Blocks.OBSIDIAN));
      reg.register("wall_prismarine_bricks", createWall(Block.Properties.of(Material.STONE), Blocks.PRISMARINE));
      reg.register("wall_dark_prismarine", createWall(Block.Properties.of(Material.STONE), Blocks.DARK_PRISMARINE));
      reg.register("wall_crimson", createWall(Block.Properties.of(Material.WOOD), Blocks.CRIMSON_STEM));
      reg.register("wall_warped", createWall(Block.Properties.of(Material.WOOD), Blocks.WARPED_STEM));
      reg.register("wall_crying_obsidian", createWall(Block.Properties.of(Material.STONE).lightLevel(state -> 10), Blocks.CRYING_OBSIDIAN));
      reg.register("wall_basalt", createWall(Block.Properties.of(Material.STONE), Blocks.BASALT));
      reg.register("wall_polished_basalt", createWall(Block.Properties.of(Material.STONE), Blocks.POLISHED_BASALT));
      reg.register("wall_lodestone", createWall(Block.Properties.of(Material.STONE), Blocks.LODESTONE));
      reg.register("wall_stripped_crimson", createWall(Block.Properties.of(Material.WOOD), Blocks.STRIPPED_CRIMSON_HYPHAE));
      reg.register("wall_stripped_warped", createWall(Block.Properties.of(Material.WOOD), Blocks.STRIPPED_WARPED_HYPHAE));
      reg.register("wall_mushroom_stem", createWall(Block.Properties.of(Material.WOOD), Blocks.MUSHROOM_STEM));
      reg.register("wall_red_mushroom", createWall(Block.Properties.of(Material.WOOD), Blocks.RED_MUSHROOM_BLOCK));
      reg.register("wall_brown_mushroom", createWall(Block.Properties.of(Material.WOOD), Blocks.BROWN_MUSHROOM_BLOCK));
      reg.register("wall_mushroom_polished", createWall(Block.Properties.of(Material.WOOD), Blocks.MUSHROOM_STEM)); // ??
      reg.register("wall_quartz_bricks", createWall(Block.Properties.of(Material.STONE), Blocks.QUARTZ_BRICKS));
      reg.register("wall_magma", createWall(Block.Properties.of(Material.STONE, MaterialColor.NETHER).lightLevel(s -> 3), Blocks.MAGMA_BLOCK));
      reg.register("wall_glowstone", createWall(Block.Properties.of(Material.STONE, MaterialColor.SAND).sound(SoundType.GLASS).lightLevel(s -> 15), Blocks.GLOWSTONE));
      reg.register("wall_sea_lantern", createWall(Block.Properties.of(Material.STONE, MaterialColor.SAND).sound(SoundType.GLASS).lightLevel(s -> 15), Blocks.SEA_LANTERN));
      reg.register("wall_glass", createWall(Block.Properties.of(Material.GLASS), Blocks.GLASS));
      reg.register("wall_glass_white", createWall(Block.Properties.of(Material.GLASS), Blocks.WHITE_STAINED_GLASS));
      reg.register("wall_glass_orange", createWall(Block.Properties.of(Material.GLASS), Blocks.ORANGE_STAINED_GLASS));
      reg.register("wall_glass_magenta", createWall(Block.Properties.of(Material.GLASS), Blocks.MAGENTA_STAINED_GLASS));
      reg.register("wall_glass_purple", createWall(Block.Properties.of(Material.GLASS), Blocks.PURPLE_STAINED_GLASS));
      reg.register("wall_glass_blue", createWall(Block.Properties.of(Material.GLASS), Blocks.BLUE_STAINED_GLASS));
      reg.register("wall_glass_brown", createWall(Block.Properties.of(Material.GLASS), Blocks.BROWN_STAINED_GLASS));
      reg.register("wall_glass_red", createWall(Block.Properties.of(Material.GLASS), Blocks.RED_STAINED_GLASS));
      reg.register("wall_glass_black", createWall(Block.Properties.of(Material.GLASS), Blocks.BLACK_STAINED_GLASS));
      reg.register("wall_glass_cyan", createWall(Block.Properties.of(Material.GLASS), Blocks.CYAN_STAINED_GLASS));
      reg.register("wall_glass_light_gray", createWall(Block.Properties.of(Material.GLASS), Blocks.LIGHT_GRAY_STAINED_GLASS));
      reg.register("wall_glass_gray", createWall(Block.Properties.of(Material.GLASS), Blocks.GRAY_STAINED_GLASS));
      reg.register("wall_glass_pink", createWall(Block.Properties.of(Material.GLASS), Blocks.PINK_STAINED_GLASS));
      reg.register("wall_glass_lime", createWall(Block.Properties.of(Material.GLASS), Blocks.LIME_STAINED_GLASS));
      reg.register("wall_glass_light_blue", createWall(Block.Properties.of(Material.GLASS), Blocks.LIGHT_BLUE_STAINED_GLASS));
      reg.register("wall_glass_yellow", createWall(Block.Properties.of(Material.GLASS), Blocks.YELLOW_STAINED_GLASS));
      reg.register("wall_glass_green", createWall(Block.Properties.of(Material.GLASS), Blocks.GREEN_STAINED_GLASS));
      reg.register("wall_oak_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.OAK_PLANKS));
      reg.register("wall_dark_oak_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.DARK_OAK_PLANKS));
      reg.register("wall_acacia_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.ACACIA_PLANKS));
      reg.register("wall_jungle_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.JUNGLE_PLANKS));
      reg.register("wall_birch_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.BIRCH_PLANKS));
      reg.register("wall_spruce_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.SPRUCE_PLANKS));
      reg.register("wall_mangrove_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.MANGROVE_PLANKS));
      reg.register("wall_crimson_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.CRIMSON_PLANKS));
      reg.register("wall_warped_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.WARPED_PLANKS));
      reg.register("wall_calcite", createWall(Block.Properties.of(Material.STONE), Blocks.CALCITE));
      reg.register("wall_amethyst", createWall(Block.Properties.of(Material.STONE), Blocks.AMETHYST_BLOCK));
      reg.register("wall_tuff", createWall(Block.Properties.of(Material.STONE), Blocks.TUFF));
      reg.register("wall_smooth_basalt", createWall(Block.Properties.of(Material.STONE), Blocks.SMOOTH_BASALT));
      reg.register("wall_cracked_nether_bricks", createWall(Block.Properties.of(Material.STONE), Blocks.CRACKED_NETHER_BRICKS));
      reg.register("wall_cracked_deepslate_bricks", createWall(Block.Properties.of(Material.STONE), Blocks.CRACKED_DEEPSLATE_BRICKS));
      reg.register("wall_cracked_deepslate_tiles", createWall(Block.Properties.of(Material.STONE), Blocks.CRACKED_DEEPSLATE_TILES));
      reg.register("wall_sculk", createWall(Block.Properties.of(Material.SCULK), Blocks.SCULK));
      reg.register("wall_mud", createWall(BlockBehaviour.Properties.copy(Blocks.DIRT), Blocks.MUD));
      reg.register("wall_packed_mud", createWall(BlockBehaviour.Properties.copy(Blocks.DIRT), Blocks.PACKED_MUD));
      reg.register("wall_ochre_froglight", createWall(Block.Properties.of(Material.FROGLIGHT).lightLevel(s -> 15), Blocks.OCHRE_FROGLIGHT));
      reg.register("wall_pearlescent_froglight", createWall(Block.Properties.of(Material.FROGLIGHT).lightLevel(s -> 15), Blocks.PEARLESCENT_FROGLIGHT));
      reg.register("wall_verdant_froglight", createWall(Block.Properties.of(Material.FROGLIGHT).lightLevel(s -> 15), Blocks.VERDANT_FROGLIGHT));
      reg.register("wall_reinforced_deepslate", createWall(Block.Properties.of(Material.STONE), Blocks.REINFORCED_DEEPSLATE));
      //
      //
      //                GATE
      //
      WoodType def = WoodType.MANGROVE;
      reg.register("gate_nether_bricks", createGate(Blocks.NETHER_BRICKS, Block.Properties.of(Material.STONE), def));
      reg.register("gate_red_nether_bricks", createGate(Blocks.RED_NETHER_BRICKS, Block.Properties.of(Material.STONE), def));
      reg.register("gate_quartz", createGate(Blocks.QUARTZ_BLOCK, Block.Properties.of(Material.STONE), def));
      reg.register("gate_stone_bricks", createGate(Blocks.STONE_BRICKS, Block.Properties.of(Material.STONE), def));
      reg.register("gate_blackstone_bricks", createGate(Blocks.POLISHED_BLACKSTONE_BRICKS, Block.Properties.of(Material.STONE), def));
      reg.register("gate_bricks", createGate(Blocks.BRICKS, Block.Properties.of(Material.STONE), def));
      reg.register("gate_end_stone_bricks", createGate(Blocks.END_STONE_BRICKS, Block.Properties.of(Material.STONE), def));
      reg.register("gate_obsidian", createGate(Blocks.OBSIDIAN, Block.Properties.of(Material.STONE), def));
      reg.register("gate_prismarine", createGate(Blocks.PRISMARINE, Block.Properties.of(Material.STONE), def));
      reg.register("gate_prismarine_brick", createGate(Blocks.PRISMARINE, Block.Properties.of(Material.STONE), def));
      reg.register("gate_prismarine_dark", createGate(Blocks.DARK_PRISMARINE, Block.Properties.of(Material.STONE), def));
      reg.register("gate_purpur", createGate(Blocks.PURPUR_BLOCK, Block.Properties.of(Material.STONE), def));
      reg.register("gate_mud_bricks", createGate(Blocks.MUD_BRICKS, Block.Properties.of(Material.STONE), def));
      //
      //                TRAPDOOR
      //
      BlockSetType type = BlockSetType.STONE;
      reg.register("trapdoor_stone", createTrap(Blocks.STONE, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_granite", createTrap(Blocks.GRANITE, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_andesite", createTrap(Blocks.ANDESITE, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_diorite", createTrap(Blocks.DIORITE, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_bricks", createTrap(Blocks.STONE, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_stone_bricks", createTrap(Blocks.STONE_BRICKS, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_blackstone", createTrap(Blocks.STONE_BRICKS, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_blackstone_bricks", createTrap(Blocks.STONE_BRICKS, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_basalt", createTrap(Blocks.STONE, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_end_stone", createTrap(Blocks.STONE, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_purpur", createTrap(Blocks.PURPUR_BLOCK, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_quartz", createTrap(Blocks.QUARTZ_BLOCK, Block.Properties.of(Material.STONE), type));
      reg.register("trapdoor_mud_bricks", createTrap(Blocks.MUD_BRICKS, Block.Properties.of(Material.STONE), type));
    });
  }

  public static Block createFence(Block block, Block.Properties p) {
    return addBlock(new BlockAbsentFence(BlockUtil.wrap(p, block)));
  }

  public static BlockAbsentWall createWall(Block.Properties p, Block block) {
    BlockAbsentWall wall = new BlockAbsentWall(BlockUtil.wrap(p, block));
    addBlock(wall);
    if (block == Blocks.CRYING_OBSIDIAN) {
      wall.part = ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
    }
    return wall;
  }

  public static Block createSlab(Block.Properties prop, Block block) {
    BlockAbsentSlab slab = new BlockAbsentSlab(BlockUtil.wrap(prop, block));
    addBlock(slab);
    if (block == Blocks.CRYING_OBSIDIAN) {
      slab.part = ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
    }
    return slab;
  }

  public static BlockAbsentStair createStair(Block.Properties prop, Block block) {
    BlockAbsentStair stair = new BlockAbsentStair(block, BlockUtil.wrap(prop, block));
    addBlock(stair);
    if (block == Blocks.CRYING_OBSIDIAN) {
      stair.part = ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
    }
    return stair;
  }

  public static Block createGate(Block block, Block.Properties p, WoodType type) {
    return addBlock(new BlockAbsentGate(BlockUtil.wrap(p, block), type));
  }

  public static Block createDoor(Block block, Block.Properties p, BlockSetType type) {
    return addBlock(new DoorAbsentBlock(BlockUtil.wrap(p, block), type));
  }

  public static Block createTrap(Block block, Block.Properties p, BlockSetType type) {
    return addBlock(new TrapDoorAbsent(BlockUtil.wrap(p, block), type));
  }

  public static Block addBlock(Block b) {
    BLOCKLIST.add(b);
    return b;
  }
}
