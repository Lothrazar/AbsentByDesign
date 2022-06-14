package com.lothrazar.absentbydesign.registry;

import com.lothrazar.absentbydesign.ModAbsentBD;
import com.lothrazar.absentbydesign.block.BlockAbsentFence;
import com.lothrazar.absentbydesign.block.BlockAbsentGate;
import com.lothrazar.absentbydesign.block.BlockAbsentSlab;
import com.lothrazar.absentbydesign.block.BlockAbsentStair;
import com.lothrazar.absentbydesign.block.BlockAbsentWall;
import com.lothrazar.absentbydesign.block.DoorAbsentBlock;
import com.lothrazar.absentbydesign.block.IBlockAbsent;
import com.lothrazar.absentbydesign.block.TrapDoorAbsent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AbsentRegistry {

  private static final String HAX = "block." + ModAbsentBD.MODID + ".";
  //
  // STONE
  // SANDSTONE
  // BRICKS
  // ENDSTONE 
  // CONCRETE
  // WOOL
  // GLASS
  // MUSHROOM
  // BASALT
  // OBSIDIAN
  // QUARTZ
  // MAGMA
  // GLOWSTONE
  // 
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
  // AVOID CHISELED
  // AVOID GLAZED TERRACOTTA
  // 
  private static final List<Block> BLOCKLIST = new ArrayList<>();
  private static final String ID = ModAbsentBD.MODID + ":";
  // @ObjectHolder(ID + "wall_glass_white")
  public static BlockAbsentWall wall_glass_white;
  // @ObjectHolder(ID + "wall_glass_orange")
  public static BlockAbsentWall wall_glass_orange;
  // @ObjectHolder(ID + "wall_glass_magenta")
  public static BlockAbsentWall wall_glass_magenta;
  // @ObjectHolder(ID + "wall_glass_purple")
  public static BlockAbsentWall wall_glass_purple;
  // @ObjectHolder(ID + "wall_glass_blue")
  public static BlockAbsentWall wall_glass_blue;
  // @ObjectHolder(ID + "wall_glass_brown")
  public static BlockAbsentWall wall_glass_brown;
  // @ObjectHolder(ID + "wall_glass_black")
  public static BlockAbsentWall wall_glass_black;
  // @ObjectHolder(ID + "wall_glass_cyan")
  public static BlockAbsentWall wall_glass_cyan;
  // @ObjectHolder(ID + "wall_glass_light_gray")
  public static BlockAbsentWall wall_glass_light_gray;
  // @ObjectHolder(ID + "wall_glass_gray")
  public static BlockAbsentWall wall_glass_gray;
  // @ObjectHolder(ID + "wall_glass_pink")
  public static BlockAbsentWall wall_glass_pink;
  // @ObjectHolder(ID + "wall_glass_lime")
  public static BlockAbsentWall wall_glass_lime;
  // @ObjectHolder(ID + "wall_glass_light_blue")
  public static BlockAbsentWall wall_glass_light_blue;
  // @ObjectHolder(ID + "wall_glass_yellow")
  public static BlockAbsentWall wall_glass_yellow;
  // @ObjectHolder(ID + "wall_glass_green")
  public static BlockAbsentWall wall_glass_green;
  // @ObjectHolder(ID + "wall_glass_red")
  public static BlockAbsentWall wall_glass_red;
  // @ObjectHolder(ID + "wall_glass")
  public static BlockAbsentWall wall_glass;
  // @ObjectHolder(ID + "stairs_glass_purple")
  public static BlockAbsentStair stairs_glass_purple;
  // @ObjectHolder(ID + "stairs_glass_blue")
  public static BlockAbsentStair stairs_glass_blue;
  // @ObjectHolder(ID + "stairs_glass_brown")
  public static BlockAbsentStair stairs_glass_brown;
  // @ObjectHolder(ID + "stairs_glass_red")
  public static BlockAbsentStair stairs_glass_red;
  // @ObjectHolder(ID + "stairs_glass_black")
  public static BlockAbsentStair stairs_glass_black;
  // @ObjectHolder(ID + "stairs_glass_cyan")
  public static BlockAbsentStair stairs_glass_cyan;
  // @ObjectHolder(ID + "stairs_glass_light_gray")
  public static BlockAbsentStair stairs_glass_light_gray;
  // @ObjectHolder(ID + "stairs_glass_gray")
  public static BlockAbsentStair stairs_glass_gray;
  // @ObjectHolder(ID + "stairs_glass_pink")
  public static BlockAbsentStair stairs_glass_pink;
  // @ObjectHolder(ID + "stairs_glass_lime")
  public static BlockAbsentStair stairs_glass_lime;
  // @ObjectHolder(ID + "stairs_glass_light_blue")
  public static BlockAbsentStair stairs_glass_light_blue;
  // @ObjectHolder(ID + "stairs_glass_magenta")
  public static BlockAbsentStair stairs_glass_magenta;
  // @ObjectHolder(ID + "stairs_glass_white")
  public static BlockAbsentStair stairs_glass_white;
  // @ObjectHolder(ID + "stairs_glass_green")
  public static BlockAbsentStair stairs_glass_green;
  // @ObjectHolder(ID + "stairs_glass_yellow")
  public static BlockAbsentStair stairs_glass_yellow;
  // @ObjectHolder(ID + "stairs_glass_orange")
  public static BlockAbsentStair stairs_glass_orange;
  // @ObjectHolder(ID + "stairs_glass")
  public static BlockAbsentStair stairs_glass;
  // @ObjectHolder(ID + "slab_glass")
  public static BlockAbsentSlab slab_glass;
  // @ObjectHolder(ID + "slab_glass_white")
  public static BlockAbsentSlab slab_glass_white;
  // @ObjectHolder(ID + "slab_glass_orange")
  public static BlockAbsentSlab slab_glass_orange;
  // @ObjectHolder(ID + "slab_glass_magenta")
  public static BlockAbsentSlab slab_glass_magenta;
  // @ObjectHolder(ID + "slab_glass_light_blue")
  public static BlockAbsentSlab slab_glass_light_blue;
  // @ObjectHolder(ID + "slab_glass_yellow")
  public static BlockAbsentSlab slab_glass_yellow;
  // @ObjectHolder(ID + "slab_glass_lime")
  public static BlockAbsentSlab slab_glass_lime;
  // @ObjectHolder(ID + "slab_glass_pink")
  public static BlockAbsentSlab slab_glass_pink;
  // @ObjectHolder(ID + "slab_glass_gray")
  public static BlockAbsentSlab slab_glass_gray;
  // @ObjectHolder(ID + "slab_glass_light_gray")
  public static BlockAbsentSlab slab_glass_light_gray;
  // @ObjectHolder(ID + "slab_glass_cyan")
  public static BlockAbsentSlab slab_glass_cyan;
  // @ObjectHolder(ID + "slab_glass_purple")
  public static BlockAbsentSlab slab_glass_purple;
  // @ObjectHolder(ID + "slab_glass_blue")
  public static BlockAbsentSlab slab_glass_blue;
  // @ObjectHolder(ID + "slab_glass_brown")
  public static BlockAbsentSlab slab_glass_brown;
  // @ObjectHolder(ID + "slab_glass_green")
  public static BlockAbsentSlab slab_glass_green;
  // @ObjectHolder(ID + "slab_glass_red")
  public static BlockAbsentSlab slab_glass_red;
  public static BlockAbsentSlab slab_glass_black;
  private static BlockAbsentFence FENCE_QUARTZ;
  private static CreativeModeTab itemGroup = new CreativeModeTab(ModAbsentBD.MODID) {

    @Override
    public ItemStack makeIcon() {
      return new ItemStack(AbsentRegistry.FENCE_QUARTZ);
    }
  };

  public static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
    return false;
  }

  public static Boolean neverAllowSpawn(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
    return false;
  }

  public static void setupClient(final FMLClientSetupEvent event) {
    // walls
    //    setCutout(AbsentRegistry.wall_glass);
    //    setTranslucent(AbsentRegistry.wall_glass_white);
    //    setTranslucent(AbsentRegistry.wall_glass_orange);
    //    setTranslucent(AbsentRegistry.wall_glass_magenta);
    //    setTranslucent(AbsentRegistry.wall_glass_light_blue);
    //    setTranslucent(AbsentRegistry.wall_glass_yellow);
    //    setTranslucent(AbsentRegistry.wall_glass_lime);
    //    setTranslucent(AbsentRegistry.wall_glass_pink);
    //    setTranslucent(AbsentRegistry.wall_glass_gray);
    //    setTranslucent(AbsentRegistry.wall_glass_light_gray);
    //    setTranslucent(AbsentRegistry.wall_glass_cyan);
    //    setTranslucent(AbsentRegistry.wall_glass_purple);
    //    setTranslucent(AbsentRegistry.wall_glass_blue);
    //    setTranslucent(AbsentRegistry.wall_glass_brown);
    //    setTranslucent(AbsentRegistry.wall_glass_green);
    //    setTranslucent(AbsentRegistry.wall_glass_red);
    //    setTranslucent(AbsentRegistry.wall_glass_black);
    //    // stairs
    //    setCutout(AbsentRegistry.stairs_glass);
    //    setTranslucent(AbsentRegistry.stairs_glass_white);
    //    setTranslucent(AbsentRegistry.stairs_glass_orange);
    //    setTranslucent(AbsentRegistry.stairs_glass_magenta);
    //    setTranslucent(AbsentRegistry.stairs_glass_light_blue);
    //    setTranslucent(AbsentRegistry.stairs_glass_yellow);
    //    setTranslucent(AbsentRegistry.stairs_glass_lime);
    //    setTranslucent(AbsentRegistry.stairs_glass_pink);
    //    setTranslucent(AbsentRegistry.stairs_glass_gray);
    //    setTranslucent(AbsentRegistry.stairs_glass_light_gray);
    //    setTranslucent(AbsentRegistry.stairs_glass_cyan);
    //    setTranslucent(AbsentRegistry.stairs_glass_purple);
    //    setTranslucent(AbsentRegistry.stairs_glass_blue);
    //    setTranslucent(AbsentRegistry.stairs_glass_brown);
    //    setTranslucent(AbsentRegistry.stairs_glass_green);
    //    setTranslucent(AbsentRegistry.stairs_glass_red);
    //    setTranslucent(AbsentRegistry.stairs_glass_black);
    //    // slabs
    //    setCutout(AbsentRegistry.slab_glass);
    //    setTranslucent(AbsentRegistry.slab_glass_white);
    //    setTranslucent(AbsentRegistry.slab_glass_orange);
    //    setTranslucent(AbsentRegistry.slab_glass_magenta);
    //    setTranslucent(AbsentRegistry.slab_glass_light_blue);
    //    setTranslucent(AbsentRegistry.slab_glass_yellow);
    //    setTranslucent(AbsentRegistry.slab_glass_lime);
    //    setTranslucent(AbsentRegistry.slab_glass_pink);
    //    setTranslucent(AbsentRegistry.slab_glass_gray);
    //    setTranslucent(AbsentRegistry.slab_glass_light_gray);
    //    setTranslucent(AbsentRegistry.slab_glass_cyan);
    //    setTranslucent(AbsentRegistry.slab_glass_purple);
    //    setTranslucent(AbsentRegistry.slab_glass_blue);
    //    setTranslucent(AbsentRegistry.slab_glass_brown);
    //    setTranslucent(AbsentRegistry.slab_glass_green);
    //    setTranslucent(AbsentRegistry.slab_glass_red);
    setTranslucent(AbsentRegistry.slab_glass_black);
  }

  private static void setCutout(IBlockAbsent block) {
    ItemBlockRenderTypes.setRenderLayer((Block) block, RenderType.cutout());
    block.setTransparent();
  }

  private static void setTranslucent(IBlockAbsent block) {
    ItemBlockRenderTypes.setRenderLayer((Block) block, RenderType.translucent());
    block.setTransparent();
  }

  //instead of RegistryEvent.Register<Item> event or <Block>
  @SubscribeEvent
  public static void onBlocksRegistry(RegisterEvent event) {
    Item.Properties properties = new Item.Properties().tab(AbsentRegistry.itemGroup);
    event.register(Registry.ITEM_REGISTRY, reg -> {
      for (Block b : AbsentRegistry.BLOCKLIST) {
        String id = b.getDescriptionId().replace(HAX, "");
        reg.register(id, new BlockItem(b, properties));
      }
    });
    event.register(Registry.BLOCK_REGISTRY, reg -> {
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
      reg.register("fence_warped", createFence(Blocks.OBSIDIAN, Block.Properties.of(Material.STONE)));
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
      reg.register("slab_glass", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.GLASS));
      reg.register("slab_glass_white", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.WHITE_STAINED_GLASS));
      reg.register("slab_glass_orange", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.ORANGE_STAINED_GLASS));
      reg.register("slab_glass_magenta", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.MAGENTA_STAINED_GLASS));
      reg.register("slab_glass_light_blue", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.LIGHT_BLUE_STAINED_GLASS));
      reg.register("slab_glass_yellow", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.YELLOW_STAINED_GLASS));
      reg.register("slab_glass_lime", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.LIME_STAINED_GLASS));
      reg.register("slab_glass_pink", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.PINK_STAINED_GLASS));
      reg.register("slab_glass_gray", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.GRAY_STAINED_GLASS));
      reg.register("slab_glass_light_gray", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.LIGHT_GRAY_STAINED_GLASS));
      reg.register("slab_glass_cyan", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.CYAN_STAINED_GLASS));
      reg.register("slab_glass_purple", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.PURPLE_STAINED_GLASS));
      reg.register("slab_glass_blue", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.BLUE_STAINED_GLASS));
      reg.register("slab_glass_brown", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.BROWN_STAINED_GLASS));
      reg.register("slab_glass_green", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.GREEN_STAINED_GLASS));
      reg.register("slab_glass_red", createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.RED_STAINED_GLASS));
      slab_glass_black = (BlockAbsentSlab) createSlab(Block.Properties.of(Material.GLASS).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.BLACK_STAINED_GLASS);
      reg.register("slab_glass_black", slab_glass_black);
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
      reg.register("stairs_terracotta", createStair(Blocks.TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_white", createStair(Blocks.WHITE_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_orange", createStair(Blocks.ORANGE_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_magenta", createStair(Blocks.MAGENTA_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_light_blue", createStair(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_yellow", createStair(Blocks.YELLOW_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_lime", createStair(Blocks.LIME_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_pink", createStair(Blocks.PINK_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_gray", createStair(Blocks.GRAY_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_light_gray", createStair(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_cyan", createStair(Blocks.CYAN_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_purple", createStair(Blocks.PURPLE_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_blue", createStair(Blocks.BLUE_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_brown", createStair(Blocks.BROWN_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_green", createStair(Blocks.GREEN_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_red", createStair(Blocks.RED_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_terracotta_black", createStair(Blocks.BLACK_GLAZED_TERRACOTTA, Block.Properties.of(Material.STONE, MaterialColor.CLAY)));
      reg.register("stairs_glass", createStair(Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid), Blocks.GLASS));
      reg.register("stairs_glass_white", createStair(Blocks.WHITE_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_orange", createStair(Blocks.ORANGE_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_magenta", createStair(Blocks.MAGENTA_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_light_blue", createStair(Blocks.LIGHT_BLUE_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_yellow", createStair(Blocks.YELLOW_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_lime", createStair(Blocks.LIME_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_pink", createStair(Blocks.PINK_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_gray", createStair(Blocks.GRAY_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_light_gray", createStair(Blocks.LIGHT_GRAY_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_cyan", createStair(Blocks.CYAN_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_purple", createStair(Blocks.PURPLE_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_blue", createStair(Blocks.BLUE_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_brown", createStair(Blocks.BROWN_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_green", createStair(Blocks.GREEN_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_red", createStair(Blocks.RED_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
      reg.register("stairs_glass_black", createStair(Blocks.BLACK_STAINED_GLASS, Block.Properties.of(Material.GLASS, MaterialColor.CLAY).noOcclusion().isValidSpawn(AbsentRegistry::neverAllowSpawn).isRedstoneConductor(AbsentRegistry::isntSolid).isSuffocating(AbsentRegistry::isntSolid).isViewBlocking(AbsentRegistry::isntSolid)));
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
      //
      //                WALLS
      //
      reg.register("wall_stripped_acacia_log", createWall(Blocks.STRIPPED_OAK_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_stripped_birch_log", createWall(Blocks.STRIPPED_OAK_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_stripped_dark_oak_log", createWall(Blocks.STRIPPED_OAK_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_stripped_jungle_log", createWall(Blocks.STRIPPED_OAK_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_stripped_oak_log", createWall(Blocks.STRIPPED_OAK_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_stripped_spruce_log", createWall(Blocks.STRIPPED_OAK_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_acacia_log", createWall(Blocks.ACACIA_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_birch_log", createWall(Blocks.BIRCH_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_dark_oak_log", createWall(Blocks.DARK_OAK_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_jungle_log", createWall(Blocks.JUNGLE_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_oak_log", createWall(Blocks.OAK_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_spruce_log", createWall(Blocks.SPRUCE_LOG, Block.Properties.of(Material.WOOD)));
      reg.register("wall_andesite_smooth", createWall(Blocks.ANDESITE, Block.Properties.of(Material.STONE)));
      reg.register("wall_diorite_smooth", createWall(Blocks.DIORITE, Block.Properties.of(Material.STONE)));
      reg.register("wall_end_stone", createWall(Blocks.END_STONE, Block.Properties.of(Material.STONE)));
      reg.register("wall_granite_smooth", createWall(Blocks.QUARTZ_BLOCK, Block.Properties.of(Material.STONE)));
      reg.register("wall_purpur", createWall(Blocks.PURPUR_BLOCK, Block.Properties.of(Material.STONE)));
      reg.register("wall_quartz", createWall(Blocks.QUARTZ_BLOCK, Block.Properties.of(Material.STONE)));
      reg.register("wall_sandstone_red_smooth", createWall(Blocks.RED_SANDSTONE, Block.Properties.of(Material.STONE)));
      reg.register("wall_sandstone_smooth", createWall(Blocks.SANDSTONE, Block.Properties.of(Material.STONE)));
      reg.register("wall_stone", createWall(Blocks.STONE, Block.Properties.of(Material.STONE)));
      reg.register("wall_stone_slab", createWall(Blocks.STONE, Block.Properties.of(Material.STONE)));
      reg.register("wall_stonebrick_carved", createWall(Blocks.STONE_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("wall_stonebrick_cracked", createWall(Blocks.STONE_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("wall_obsidian", createWall(Blocks.OBSIDIAN, Block.Properties.of(Material.STONE)));
      reg.register("wall_prismarine_bricks", createWall(Blocks.PRISMARINE, Block.Properties.of(Material.STONE)));
      reg.register("wall_dark_prismarine", createWall(Blocks.DARK_PRISMARINE, Block.Properties.of(Material.STONE)));
      reg.register("wall_crimson", createWall(Blocks.CRIMSON_STEM, Block.Properties.of(Material.WOOD)));
      reg.register("wall_warped", createWall(Blocks.WARPED_STEM, Block.Properties.of(Material.WOOD)));
      reg.register("wall_crying_obsidian", createWall(Blocks.CRYING_OBSIDIAN, Block.Properties.of(Material.STONE).lightLevel(state -> 10)));
      reg.register("wall_basalt", createWall(Blocks.BASALT, Block.Properties.of(Material.STONE)));
      reg.register("wall_polished_basalt", createWall(Blocks.POLISHED_BASALT, Block.Properties.of(Material.STONE)));
      reg.register("wall_lodestone", createWall(Blocks.LODESTONE, Block.Properties.of(Material.STONE)));
      reg.register("wall_stripped_crimson", createWall(Blocks.STRIPPED_CRIMSON_HYPHAE, Block.Properties.of(Material.WOOD)));
      reg.register("wall_stripped_warped", createWall(Blocks.STRIPPED_WARPED_HYPHAE, Block.Properties.of(Material.WOOD)));
      reg.register("wall_mushroom_stem", createWall(Blocks.MUSHROOM_STEM, Block.Properties.of(Material.WOOD)));
      reg.register("wall_red_mushroom", createWall(Blocks.RED_MUSHROOM_BLOCK, Block.Properties.of(Material.WOOD)));
      reg.register("wall_brown_mushroom", createWall(Blocks.BROWN_MUSHROOM_BLOCK, Block.Properties.of(Material.WOOD)));
      reg.register("wall_mushroom_polished", createWall(Blocks.MUSHROOM_STEM, Block.Properties.of(Material.WOOD)));
      reg.register("wall_quartz_bricks", createWall(Blocks.QUARTZ_BRICKS, Block.Properties.of(Material.STONE)));
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
      reg.register("wall_crimson_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.CRIMSON_PLANKS));
      reg.register("wall_warped_planks", createWall(Block.Properties.of(Material.WOOD), Blocks.WARPED_PLANKS));
      reg.register("wall_calcite", createWall(Block.Properties.of(Material.STONE), Blocks.CALCITE));
      reg.register("wall_amethyst", createWall(Block.Properties.of(Material.STONE), Blocks.AMETHYST_BLOCK));
      reg.register("wall_tuff", createWall(Block.Properties.of(Material.STONE), Blocks.TUFF));
      reg.register("wall_smooth_basalt", createWall(Block.Properties.of(Material.STONE), Blocks.SMOOTH_BASALT));
      reg.register("wall_cracked_nether_bricks", createWall(Block.Properties.of(Material.STONE), Blocks.CRACKED_NETHER_BRICKS));
      reg.register("wall_cracked_deepslate_bricks", createWall(Block.Properties.of(Material.STONE), Blocks.CRACKED_DEEPSLATE_BRICKS));
      reg.register("wall_cracked_deepslate_tiles", createWall(Block.Properties.of(Material.STONE), Blocks.CRACKED_DEEPSLATE_TILES));
      //
      //                GATE
      //
      reg.register("gate_nether_bricks", createGate(Blocks.NETHER_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("gate_red_nether_bricks", createGate(Blocks.RED_NETHER_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("gate_quartz", createGate(Blocks.QUARTZ_BLOCK, Block.Properties.of(Material.STONE)));
      reg.register("gate_stone_bricks", createGate(Blocks.STONE_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("gate_blackstone_bricks", createGate(Blocks.POLISHED_BLACKSTONE_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("gate_bricks", createGate(Blocks.BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("gate_end_stone_bricks", createGate(Blocks.END_STONE_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("gate_obsidian", createGate(Blocks.OBSIDIAN, Block.Properties.of(Material.STONE)));
      reg.register("gate_prismarine", createGate(Blocks.PRISMARINE, Block.Properties.of(Material.STONE)));
      reg.register("gate_prismarine_brick", createGate(Blocks.PRISMARINE, Block.Properties.of(Material.STONE)));
      reg.register("gate_prismarine_dark", createGate(Blocks.DARK_PRISMARINE, Block.Properties.of(Material.STONE)));
      reg.register("gate_purpur", createGate(Blocks.PURPUR_BLOCK, Block.Properties.of(Material.STONE)));
      //
      //                TRAPDOOR
      //
      reg.register("trapdoor_stone", createTrap(Blocks.STONE, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_granite", createTrap(Blocks.GRANITE, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_andesite", createTrap(Blocks.ANDESITE, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_diorite", createTrap(Blocks.DIORITE, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_bricks", createTrap(Blocks.STONE, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_stone_bricks", createTrap(Blocks.STONE_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_blackstone", createTrap(Blocks.STONE_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_blackstone_bricks", createTrap(Blocks.STONE_BRICKS, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_basalt", createTrap(Blocks.STONE, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_end_stone", createTrap(Blocks.STONE, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_purpur", createTrap(Blocks.PURPUR_BLOCK, Block.Properties.of(Material.STONE)));
      reg.register("trapdoor_quartz", createTrap(Blocks.QUARTZ_BLOCK, Block.Properties.of(Material.STONE)));
    });
  }

  public static Block createFence(Block block, Block.Properties p) {
    return addBlock(new BlockAbsentFence(wrap(p, block)));
  }

  public static Block createWall(Block.Properties p, Block block) {
    Block b = addBlock(new BlockAbsentWall(wrap(p, block)));
    if (block == Blocks.CRYING_OBSIDIAN) {
      ((BlockAbsentWall) b).part = ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
    }
    return b;
  }

  @Deprecated
  public static Block createWall(Block block, Block.Properties p) {
    return createWall(p, block);
  }

  public static Block createSlab(Block.Properties prop, Block block) {
    Block b = addBlock(new BlockAbsentSlab(wrap(prop, block)));
    if (block == Blocks.CRYING_OBSIDIAN) {
      ((BlockAbsentSlab) b).part = ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
    }
    return b;
  }

  public static Block createStair(Block.Properties prop, Block block) {
    Block b = addBlock(new BlockAbsentStair(block, wrap(prop, block)));
    if (block == Blocks.CRYING_OBSIDIAN) {
      ((BlockAbsentStair) b).part = ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
    }
    return b;
  }

  @Deprecated
  public static Block createStair(Block block, Block.Properties prop) {
    return createStair(prop, block);
  }

  public static Block createGate(Block block, Block.Properties p) {
    return addBlock(new BlockAbsentGate(wrap(p, block)));
  }

  public static Block createDoor(Block block, Block.Properties p) {
    return addBlock(new DoorAbsentBlock(wrap(p, block)));
  }

  public static Block createTrap(Block block, Block.Properties p) {
    return addBlock(new TrapDoorAbsent(wrap(p, block)));
  }

  @SuppressWarnings("deprecation")
  private static Block.Properties wrap(Block.Properties propIn, Block blockIn) {
    if (blockIn.properties != null
        && blockIn.properties.materialColor != null) {
      propIn.materialColor = (state) -> {
        return blockIn.properties.materialColor.apply(blockIn.defaultBlockState());
      };
    }
    return propIn
        .sound(blockIn.getSoundType(blockIn.defaultBlockState()))
        .strength(blockIn.defaultBlockState().destroySpeed);
  }

  @OnlyIn(Dist.CLIENT)
  public static void spawnBlockParticles(SimpleParticleType partIn, Level worldIn, BlockPos pos, RandomSource rand) {
    double x = pos.getX() + rand.nextDouble();
    double y = pos.getY() + rand.nextDouble();
    double z = pos.getZ() + rand.nextDouble();
    double xSp = (rand.nextDouble() - 0.5D) * 0.5D;
    double ySp = (rand.nextDouble() - 0.5D) * 0.5D;
    double zSp = (rand.nextDouble() - 0.5D) * 0.5D;
    worldIn.addParticle(partIn, x, y, z, xSp, ySp, zSp);
  }

  public static Block addBlock(Block b) {
    BLOCKLIST.add(b);
    return b;
  }
  //NEW PLANS
  //EVERYTHING:   ,     , cracked_deepslate_tiles , 
  //STAIRS AND SLABS ONLY
  // stripped_acacia_log  stripped_birch_log   stripped_crimson_stem   stripped_dark_oak_log   stripped_jungle_log   stripped_oak_log    stripped_spruce_log  stripped_warped_stem
  //WALL ONLY
  // SMOOTH_QUARTZ_WALL (quartz_block_bottom) , have stair/slab already in vmc
  // CONCRETE x16 !!!
  // WOOL WALL x16
  // terracotta x17 !!! 
}
