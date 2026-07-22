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
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = ModAbsentBD.MODID)
public class AbsentRegistry {
  //
  // NO INVENTORY SCREEN BLOCKS (chest, furnace, ...)
  // NO ORES
  // NO RECIPE OVERRIDES / REMOVALS (ie no fence_iron since iron fences already exist)
  // AVOID PLANTS (leaves, cactus, melon, pumpkin ...)
  // AVOID GRAVITY & WORLD TRIGGER BLOCKS, NON-SOLID BLOCKS (sand, melting-ice, powder, grass, sponge, infested, ...)
  // AVOID REDSTONE STUFF (levers, piston, slime, honey, buttons, dispenser, pressure_plates ...)
  // BLOCKS have to work the same as their fullsize counterparts.  IE: ice slabs are still slippery
  // dirt only if we get grass spreading to dirt slabs becoming grass slabs. same for path, mycelium, podzol, snow
  // glazed terracotta only if we get facing directions/rotations on slabs fully sorted out
  // concrete powder slabs would have to dampen into the correct slab
  // MAYBE ?? coal block ? the 8 coral blocks ? hay bale? kelp?
  //
  //
  //
  //
  //

  private static final List<Block> BLOCKLIST = new ArrayList<>();
  private static final String HAX = "block." + ModAbsentBD.MODID + ".";
  private static BlockAbsentFence FENCE_QUARTZ;
  private static final ResourceKey<CreativeModeTab> TAB_BLOCKS = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "tab"));

  @SubscribeEvent
  public static void onCreativeModeTabRegister(RegisterEvent event) {
    event.register(Registries.CREATIVE_MODE_TAB, helper -> {
      helper.register(TAB_BLOCKS, CreativeModeTab.builder().icon(() -> new ItemStack(FENCE_QUARTZ))
          .title(Component.translatable("itemGroup." + ModAbsentBD.MODID))
          .displayItems((enabledFlags, populator) -> {
            for (Block b : AbsentRegistry.BLOCKLIST) {
              populator.accept(b);
            }
          }).build());
    });
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
    event.register(Registries.ITEM, reg -> {
      for (Block b : AbsentRegistry.BLOCKLIST) {
        String id = b.getDescriptionId().replace(HAX, "");
        Identifier itemId = Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, id);
        Item.Properties properties = new Item.Properties().setId(ResourceKey.create(Registries.ITEM, itemId)).useBlockDescriptionPrefix();
        reg.register(itemId, new BlockItem(b, properties));
      }
    });
    event.register(Registries.BLOCK, reg -> {
      //
      //                FENCES
      //
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_acacia"), createFence(Blocks.ACACIA_LOG, Block.Properties.of().ignitedByLava(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_acacia")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_birch"), createFence(Blocks.BIRCH_LOG, Block.Properties.of().ignitedByLava(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_birch")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_darkoak"), createFence(Blocks.DARK_OAK_LOG, Block.Properties.of().ignitedByLava(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_darkoak")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_jungle"), createFence(Blocks.JUNGLE_LOG, Block.Properties.of().ignitedByLava(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_jungle")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_oak"), createFence(Blocks.OAK_LOG, Block.Properties.of().ignitedByLava(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_oak")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_spruce"), createFence(Blocks.SPRUCE_LOG, Block.Properties.of().ignitedByLava(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_spruce")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_red_netherbrick"), createFence(Blocks.RED_NETHER_BRICKS, Block.Properties.of(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_red_netherbrick")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_crimson"), createFence(Blocks.CRIMSON_STEM, Block.Properties.of().ignitedByLava(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_crimson")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_warped"), createFence(Blocks.WARPED_STEM, Block.Properties.of(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_warped")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_obsidian"), createFence(Blocks.OBSIDIAN, Block.Properties.of(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_obsidian")));
      FENCE_QUARTZ = (BlockAbsentFence) createFence(Blocks.QUARTZ_BLOCK, Block.Properties.of(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_quartz"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_quartz"), FENCE_QUARTZ);
      //??mangrove log?
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_mangrove"), createFence(Blocks.CHERRY_LOG, Block.Properties.of(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_mangrove")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_cherry"), createFence(Blocks.CHERRY_LOG, Block.Properties.of(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_cherry")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_pale_oak"), createFence(Blocks.PALE_OAK_LOG, Block.Properties.of().ignitedByLava(), Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "fence_log_pale_oak")));
      //
      //                SLABS
      //
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_end_stone"), createSlab(Block.Properties.of(), Blocks.END_STONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_end_stone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_netherrack"), createSlab(Block.Properties.of(), Blocks.NETHERRACK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_netherrack")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_snow"), createSlab(Block.Properties.of(), Blocks.SNOW_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_snow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_bricks_cracked"), createSlab(Block.Properties.of(), Blocks.CRACKED_STONE_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_bricks_cracked")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_coarse_dirt"), createSlab(Block.Properties.of().isRedstoneConductor(AbsentRegistry::never), Blocks.COARSE_DIRT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_coarse_dirt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_obsidian"), createSlab(Block.Properties.of(), Blocks.OBSIDIAN, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_obsidian")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_basalt"), createSlab(Block.Properties.of(), Blocks.BASALT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_basalt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_polished_basalt"), createSlab(Block.Properties.of(), Blocks.POLISHED_BASALT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_polished_basalt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_crying_obsidian"), createSlab(Block.Properties.of().lightLevel(state -> 10), Blocks.CRYING_OBSIDIAN, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_crying_obsidian")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_lodestone"), createSlab(Block.Properties.of(), Blocks.LODESTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_lodestone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_quartz_bricks"), createSlab(Block.Properties.of(), Blocks.QUARTZ_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_quartz_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_magma"), createSlab(Block.Properties.of().lightLevel(state -> 3), Blocks.MAGMA_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_magma")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glowstone"), createSlab(Block.Properties.of().sound(SoundType.GLASS).lightLevel(state -> 15), Blocks.GLOWSTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glowstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_sea_lantern"), createSlab(Block.Properties.of().sound(SoundType.GLASS).lightLevel(state -> 15), Blocks.SEA_LANTERN, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_sea_lantern")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_black"), createSlab(Block.Properties.of(), Blocks.BLACK_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_black")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_blue"), createSlab(Block.Properties.of(), Blocks.BLUE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_brown"), createSlab(Block.Properties.of(), Blocks.BROWN_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_brown")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_cyan"), createSlab(Block.Properties.of(), Blocks.CYAN_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_cyan")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_gray"), createSlab(Block.Properties.of(), Blocks.GRAY_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_green"), createSlab(Block.Properties.of(), Blocks.GREEN_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_green")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_light_blue"), createSlab(Block.Properties.of(), Blocks.LIGHT_BLUE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_light_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_lime"), createSlab(Block.Properties.of(), Blocks.LIME_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_lime")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_magenta"), createSlab(Block.Properties.of(), Blocks.MAGENTA_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_magenta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_orange"), createSlab(Block.Properties.of(), Blocks.ORANGE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_orange")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_pink"), createSlab(Block.Properties.of(), Blocks.PINK_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_pink")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_purple"), createSlab(Block.Properties.of(), Blocks.PURPLE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_purple")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_red"), createSlab(Block.Properties.of(), Blocks.RED_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_red")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_silver"), createSlab(Block.Properties.of(), Blocks.LIGHT_GRAY_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_silver")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_white"), createSlab(Block.Properties.of(), Blocks.WHITE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_white")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_yellow"), createSlab(Block.Properties.of(), Blocks.YELLOW_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_concrete_yellow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_black"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_black")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_blue"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.BLUE_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_brown"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.BROWN_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_brown")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_cyan"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.CYAN_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_cyan")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_gray"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.GRAY_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_green"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.GREEN_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_green")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_light_blue"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.LIGHT_BLUE_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_light_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_lime"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.LIME_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_lime")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_magenta"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.MAGENTA_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_magenta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_orange"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.ORANGE_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_orange")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_pink"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.PINK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_pink")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_purple"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.PURPLE_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_purple")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_red"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.RED_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_red")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_silver"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.LIGHT_GRAY_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_silver")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_white"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.WHITE_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_white")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_yellow"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.YELLOW_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_wool_yellow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_white"), createSlab(Block.Properties.of(), Blocks.WHITE_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_white")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_orange"), createSlab(Block.Properties.of(), Blocks.ORANGE_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_orange")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_magenta"), createSlab(Block.Properties.of(), Blocks.MAGENTA_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_magenta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_light_blue"), createSlab(Block.Properties.of(), Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_light_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_yellow"), createSlab(Block.Properties.of(), Blocks.YELLOW_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_yellow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_lime"), createSlab(Block.Properties.of(), Blocks.LIME_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_lime")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_pink"), createSlab(Block.Properties.of(), Blocks.PINK_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_pink")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_gray"), createSlab(Block.Properties.of(), Blocks.GRAY_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_light_gray"), createSlab(Block.Properties.of(), Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_light_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_cyan"), createSlab(Block.Properties.of(), Blocks.CYAN_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_cyan")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_purple"), createSlab(Block.Properties.of(), Blocks.PURPLE_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_purple")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_blue"), createSlab(Block.Properties.of(), Blocks.BLUE_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_brown"), createSlab(Block.Properties.of(), Blocks.BROWN_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_brown")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_green"), createSlab(Block.Properties.of(), Blocks.GREEN_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_green")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_red"), createSlab(Block.Properties.of(), Blocks.RED_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_red")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_black"), createSlab(Block.Properties.of(), Blocks.BLACK_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta_black")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta"), createSlab(Block.Properties.of(), Blocks.TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_terracotta")));
      Block SLAB_GLASS = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT)
          .noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass"), SLAB_GLASS);
      Block SLAB_GLASS_WHITE = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.WHITE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_white"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_white"), SLAB_GLASS_WHITE);
      Block SLAB_GLASS_ORANGE = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.ORANGE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_orange"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_orange"), SLAB_GLASS_ORANGE);
      Block SLAB_GLASS_MAGENTA = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.MAGENTA_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_magenta"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_magenta"), SLAB_GLASS_MAGENTA);
      Block SLAB_GLASS_LIGHT_BLUE = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIGHT_BLUE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_light_blue"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_light_blue"), SLAB_GLASS_LIGHT_BLUE);
      Block SLAB_GLASS_YELLOW = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.YELLOW_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_yellow"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_yellow"), SLAB_GLASS_YELLOW);
      Block SLAB_GLASS_LIME = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIME_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_lime"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_lime"), SLAB_GLASS_LIME);
      Block SLAB_GLASS_PINK = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.PINK_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_pink"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_pink"), SLAB_GLASS_PINK);
      Block SLAB_GLASS_GRAY = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GRAY_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_gray"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_gray"), SLAB_GLASS_GRAY);
      Block SLAB_GLASS_LIGHT_GRAY = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIGHT_GRAY_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_light_gray"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_light_gray"), SLAB_GLASS_LIGHT_GRAY);
      Block SLAB_GLASS_CYAN = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.CYAN_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_cyan"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_cyan"), SLAB_GLASS_CYAN);
      Block SLAB_GLASS_PURPLE = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.PURPLE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_purple"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_purple"), SLAB_GLASS_PURPLE);
      Block SLAB_GLASS_BLUE = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BLUE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_blue"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_blue"), SLAB_GLASS_BLUE);
      Block SLAB_GLASS_BROWN = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BROWN_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_brown"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_brown"), SLAB_GLASS_BROWN);
      Block SLAB_GLASS_GREEN = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GREEN_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_green"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_green"), SLAB_GLASS_GREEN);
      Block SLAB_GLASS_RED = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.RED_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_red"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_red"), SLAB_GLASS_RED);
      Block SLAB_GLASS_BLACK = createSlab(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BLACK_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_black"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_glass_black"), SLAB_GLASS_BLACK);
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_mushroom_stem"), createSlab(Block.Properties.of(), Blocks.MUSHROOM_STEM, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_mushroom_stem")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_red_mushroom"), createSlab(Block.Properties.of(), Blocks.RED_MUSHROOM, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_red_mushroom")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_brown_mushroom"), createSlab(Block.Properties.of(), Blocks.BROWN_MUSHROOM, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_brown_mushroom")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_mushroom_polished"), createSlab(Block.Properties.of().ignitedByLava(), Blocks.MUSHROOM_STEM, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_mushroom_polished")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_calcite"), createSlab(Block.Properties.of(), Blocks.CALCITE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_calcite")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_amethyst"), createSlab(Block.Properties.of(), Blocks.AMETHYST_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_amethyst")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_chiseled_tuff"), createSlab(Block.Properties.of(), Blocks.CHISELED_TUFF, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_chiseled_tuff")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_chiseled_tuff_bricks"), createSlab(Block.Properties.of(), Blocks.CHISELED_TUFF_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_chiseled_tuff_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_smooth_basalt"), createSlab(Block.Properties.of(), Blocks.SMOOTH_BASALT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_smooth_basalt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_cracked_nether_bricks"), createSlab(Block.Properties.of(), Blocks.CRACKED_NETHER_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_cracked_nether_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_deepslate"), createSlab(Block.Properties.of(), Blocks.DEEPSLATE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_deepslate")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_cracked_deepslate_bricks"), createSlab(Block.Properties.of(), Blocks.CRACKED_DEEPSLATE_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_cracked_deepslate_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_cracked_deepslate_tiles"), createSlab(Block.Properties.of(), Blocks.CRACKED_DEEPSLATE_TILES, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_cracked_deepslate_tiles")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_sculk"), createSlab(Block.Properties.of(), Blocks.SCULK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_sculk")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_mud"), createSlab(Block.Properties.of(), Blocks.MUD, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_mud")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_packed_mud"), createSlab(Block.Properties.of(), Blocks.PACKED_MUD, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_packed_mud")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_reinforced_deepslate"), createSlab(Block.Properties.of(), Blocks.REINFORCED_DEEPSLATE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_reinforced_deepslate")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_ochre_froglight"), createSlab(Block.Properties.of().pushReaction(PushReaction.DESTROY).lightLevel(s -> 15), Blocks.OCHRE_FROGLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_ochre_froglight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_pearlescent_froglight"), createSlab(Block.Properties.of().pushReaction(PushReaction.DESTROY).lightLevel(s -> 15), Blocks.PEARLESCENT_FROGLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_pearlescent_froglight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_verdant_froglight"), createSlab(Block.Properties.of().pushReaction(PushReaction.DESTROY).lightLevel(s -> 15), Blocks.VERDANT_FROGLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_verdant_froglight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_gold"), createSlab(Block.Properties.of(), Blocks.GOLD_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_gold")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_rooted_dirt"), createSlab(Block.Properties.of(), Blocks.ROOTED_DIRT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_rooted_dirt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_muddy_mangrove_roots"), createSlab(Block.Properties.of(), Blocks.MUDDY_MANGROVE_ROOTS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_muddy_mangrove_roots")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_cracked_polished_blackstone_bricks"), createSlab(Block.Properties.of(), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_cracked_polished_blackstone_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_dripstone"), createSlab(Block.Properties.of(), Blocks.DRIPSTONE_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_dripstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_shroomlight"), createSlab(Block.Properties.of().lightLevel(state -> 15), Blocks.SHROOMLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_shroomlight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_gilded_blackstone"), createSlab(Block.Properties.of(), Blocks.GILDED_BLACKSTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_gilded_blackstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_moss"), createSlab(Block.Properties.of(), Blocks.MOSS_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_moss")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_soul_sand"), createSlab(Block.Properties.of().speedFactor(0.4F), Blocks.SOUL_SAND, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_soul_sand")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_soul_soil"), createSlab(Block.Properties.of(), Blocks.SOUL_SOIL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_soul_soil")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_packed_ice"), createSlab(Block.Properties.of().friction(0.98F), Blocks.PACKED_ICE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_packed_ice")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_blue_ice"), createSlab(Block.Properties.of().friction(0.989F), Blocks.BLUE_ICE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_blue_ice")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_honeycomb"), createSlab(Block.Properties.of(), Blocks.HONEYCOMB_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_honeycomb")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_emerald"), createSlab(Block.Properties.of(), Blocks.EMERALD_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_emerald")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_lapis"), createSlab(Block.Properties.of(), Blocks.LAPIS_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_lapis")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_diamond"), createSlab(Block.Properties.of(), Blocks.DIAMOND_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_diamond")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_bone"), createSlab(Block.Properties.of(), Blocks.BONE_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_bone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_netherite"), createSlab(Block.Properties.of(), Blocks.NETHERITE_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_netherite")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_iron"), createSlab(Block.Properties.of(), Blocks.IRON_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_iron")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_raw_iron"), createSlab(Block.Properties.of(), Blocks.RAW_IRON_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_raw_iron")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_raw_gold"), createSlab(Block.Properties.of(), Blocks.RAW_GOLD_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_raw_gold")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_raw_copper"), createSlab(Block.Properties.of(), Blocks.RAW_COPPER_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_raw_copper")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_resin"), createSlab(Block.Properties.of(), Blocks.RESIN_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_resin")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_chiseled_resin"), createSlab(Block.Properties.of(), Blocks.CHISELED_RESIN_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_chiseled_resin")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_pale_moss"), createSlab(Block.Properties.of(), Blocks.PALE_MOSS_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "slab_pale_moss")));
      //
      //                STAIRS
      //
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_coarse_dirt"), createStair(Block.Properties.of(), Blocks.COARSE_DIRT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_coarse_dirt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_smooth_stone"), createStair(Block.Properties.of(), Blocks.SMOOTH_STONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_smooth_stone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_end_stone"), createStair(Block.Properties.of(), Blocks.END_STONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_end_stone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_bricks_cracked"), createStair(Block.Properties.of(), Blocks.CRACKED_STONE_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_bricks_cracked")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_netherrack"), createStair(Block.Properties.of(), Blocks.NETHERRACK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_netherrack")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_snow"), createStair(Block.Properties.of().isRedstoneConductor((state, getter, pos) -> false), Blocks.SNOW_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_snow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_obsidian"), createStair(Block.Properties.of(), Blocks.OBSIDIAN, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_obsidian")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_quartz_bricks"), createStair(Block.Properties.of(), Blocks.QUARTZ_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_quartz_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_basalt"), createStair(Block.Properties.of(), Blocks.BASALT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_basalt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_polished_basalt"), createStair(Block.Properties.of(), Blocks.POLISHED_BASALT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_polished_basalt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_crying_obsidian"), createStair(Block.Properties.of().lightLevel(state -> 10), Blocks.CRYING_OBSIDIAN, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_crying_obsidian")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_lodestone"), createStair(Block.Properties.of(), Blocks.LODESTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_lodestone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_magma"), createStair(Block.Properties.of().lightLevel(s -> 3), Blocks.MAGMA_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_magma")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glowstone"), createStair(Block.Properties.of().sound(SoundType.GLASS).lightLevel(s -> 15), Blocks.GLOWSTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glowstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_sea_lantern"), createStair(Block.Properties.of().sound(SoundType.GLASS).lightLevel(s -> 15), Blocks.SEA_LANTERN, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_sea_lantern")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_black"), createStair(Block.Properties.of(), Blocks.BLACK_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_black")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_blue"), createStair(Block.Properties.of(), Blocks.BLUE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_brown"), createStair(Block.Properties.of(), Blocks.BROWN_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_brown")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_cyan"), createStair(Block.Properties.of(), Blocks.CYAN_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_cyan")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_gray"), createStair(Block.Properties.of(), Blocks.GRAY_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_green"), createStair(Block.Properties.of(), Blocks.GREEN_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_green")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_light_blue"), createStair(Block.Properties.of(), Blocks.LIGHT_BLUE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_light_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_lime"), createStair(Block.Properties.of(), Blocks.LIME_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_lime")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_magenta"), createStair(Block.Properties.of(), Blocks.MAGENTA_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_magenta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_orange"), createStair(Block.Properties.of(), Blocks.ORANGE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_orange")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_pink"), createStair(Block.Properties.of(), Blocks.PINK_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_pink")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_purple"), createStair(Block.Properties.of(), Blocks.PURPLE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_purple")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_red"), createStair(Block.Properties.of(), Blocks.RED_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_red")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_silver"), createStair(Block.Properties.of(), Blocks.LIGHT_GRAY_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_silver")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_white"), createStair(Block.Properties.of(), Blocks.WHITE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_white")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_yellow"), createStair(Block.Properties.of(), Blocks.YELLOW_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_concrete_yellow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_black"), createStair(Block.Properties.of().ignitedByLava(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_black")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_blue"), createStair(Block.Properties.of().ignitedByLava(), Blocks.BLUE_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_brown"), createStair(Block.Properties.of().ignitedByLava(), Blocks.BROWN_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_brown")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_cyan"), createStair(Block.Properties.of().ignitedByLava(), Blocks.CYAN_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_cyan")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_gray"), createStair(Block.Properties.of().ignitedByLava(), Blocks.GRAY_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_green"), createStair(Block.Properties.of().ignitedByLava(), Blocks.GREEN_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_green")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_light_blue"), createStair(Block.Properties.of().ignitedByLava(), Blocks.LIGHT_BLUE_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_light_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_lime"), createStair(Block.Properties.of().ignitedByLava(), Blocks.LIME_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_lime")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_magenta"), createStair(Block.Properties.of().ignitedByLava(), Blocks.MAGENTA_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_magenta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_orange"), createStair(Block.Properties.of().ignitedByLava(), Blocks.ORANGE_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_orange")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_pink"), createStair(Block.Properties.of().ignitedByLava(), Blocks.PINK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_pink")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_purple"), createStair(Block.Properties.of().ignitedByLava(), Blocks.PURPLE_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_purple")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_red"), createStair(Block.Properties.of().ignitedByLava(), Blocks.RED_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_red")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_silver"), createStair(Block.Properties.of().ignitedByLava(), Blocks.LIGHT_GRAY_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_silver")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_white"), createStair(Block.Properties.of().ignitedByLava(), Blocks.WHITE_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_white")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_yellow"), createStair(Block.Properties.of().ignitedByLava(), Blocks.YELLOW_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_wool_yellow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta"), createStair(Block.Properties.of(), Blocks.TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_white"), createStair(Block.Properties.of(), Blocks.WHITE_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_white")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_orange"), createStair(Block.Properties.of(), Blocks.ORANGE_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_orange")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_magenta"), createStair(Block.Properties.of(), Blocks.MAGENTA_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_magenta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_light_blue"), createStair(Block.Properties.of(), Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_light_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_yellow"), createStair(Block.Properties.of(), Blocks.YELLOW_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_yellow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_lime"), createStair(Block.Properties.of(), Blocks.LIME_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_lime")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_pink"), createStair(Block.Properties.of(), Blocks.PINK_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_pink")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_gray"), createStair(Block.Properties.of(), Blocks.GRAY_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_light_gray"), createStair(Block.Properties.of(), Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_light_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_cyan"), createStair(Block.Properties.of(), Blocks.CYAN_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_cyan")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_purple"), createStair(Block.Properties.of(), Blocks.PURPLE_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_purple")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_blue"), createStair(Block.Properties.of(), Blocks.BLUE_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_brown"), createStair(Block.Properties.of(), Blocks.BROWN_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_brown")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_green"), createStair(Block.Properties.of(), Blocks.GREEN_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_green")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_red"), createStair(Block.Properties.of(), Blocks.RED_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_red")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_black"), createStair(Block.Properties.of(), Blocks.BLACK_GLAZED_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_terracotta_black")));
      Block STAIRS_GLASS = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass"), STAIRS_GLASS);
      Block STAIRS_GLASS_WHITE = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.WHITE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_white"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_white"), STAIRS_GLASS_WHITE);
      Block STAIRS_GLASS_ORANGE = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.ORANGE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_orange"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_orange"), STAIRS_GLASS_ORANGE);
      Block STAIRS_GLASS_MAGENTA = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.MAGENTA_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_magenta"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_magenta"), STAIRS_GLASS_MAGENTA);
      Block STAIRS_GLASS_LIGHT_BLUE = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIGHT_BLUE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_light_blue"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_light_blue"), STAIRS_GLASS_LIGHT_BLUE);
      Block STAIRS_GLASS_YELLOW = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.YELLOW_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_yellow"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_yellow"), STAIRS_GLASS_YELLOW);
      Block STAIRS_GLASS_LIME = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIME_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_lime"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_lime"), STAIRS_GLASS_LIME);
      Block STAIRS_GLASS_PINK = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.PINK_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_pink"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_pink"), STAIRS_GLASS_PINK);
      Block STAIRS_GLASS_GRAY = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GRAY_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_gray"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_gray"), STAIRS_GLASS_GRAY);
      Block STAIRS_GLASS_LIGHT_GRAY = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.LIGHT_GRAY_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_light_gray"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_light_gray"), STAIRS_GLASS_LIGHT_GRAY);
      Block STAIRS_GLASS_CYAN = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.CYAN_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_cyan"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_cyan"), STAIRS_GLASS_CYAN);
      Block STAIRS_GLASS_PURPLE = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.PURPLE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_purple"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_purple"), STAIRS_GLASS_PURPLE);
      Block STAIRS_GLASS_BLUE = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BLUE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_blue"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_blue"), STAIRS_GLASS_BLUE);
      Block STAIRS_GLASS_BROWN = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BROWN_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_brown"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_brown"), STAIRS_GLASS_BROWN);
      Block STAIRS_GLASS_GREEN = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.GREEN_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_green"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_green"), STAIRS_GLASS_GREEN);
      Block STAIRS_GLASS_RED = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.RED_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_red"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_red"), STAIRS_GLASS_RED);
      Block STAIRS_GLASS_BLACK = createStair(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT).noOcclusion().isValidSpawn(AbsentRegistry::never).isRedstoneConductor(AbsentRegistry::never).isSuffocating(AbsentRegistry::never).isViewBlocking(AbsentRegistry::never), Blocks.BLACK_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_black"));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_glass_black"), STAIRS_GLASS_BLACK);
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_red_mushroom"), createStair(Block.Properties.of().ignitedByLava(), Blocks.RED_MUSHROOM_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_red_mushroom")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_brown_mushroom"), createStair(Block.Properties.of().ignitedByLava(), Blocks.BROWN_MUSHROOM_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_brown_mushroom")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_mushroom_stem"), createStair(Block.Properties.of().ignitedByLava(), Blocks.MUSHROOM_STEM, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_mushroom_stem")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_mushroom_polished"), createStair(Block.Properties.of().ignitedByLava(), Blocks.MUSHROOM_STEM, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_mushroom_polished")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_calcite"), createStair(Block.Properties.of(), Blocks.CALCITE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_calcite")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_amethyst"), createStair(Block.Properties.of(), Blocks.AMETHYST_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_amethyst")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_chiseled_tuff"), createStair(Block.Properties.of(), Blocks.CHISELED_TUFF, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_chiseled_tuff")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_chiseled_tuff_bricks"), createStair(Block.Properties.of(), Blocks.CHISELED_TUFF_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_chiseled_tuff_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_smooth_basalt"), createStair(Block.Properties.of(), Blocks.SMOOTH_BASALT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_smooth_basalt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_cracked_nether_bricks"), createStair(Block.Properties.of(), Blocks.CRACKED_NETHER_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_cracked_nether_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_deepslate"), createStair(Block.Properties.of(), Blocks.DEEPSLATE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_deepslate")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_cracked_deepslate_bricks"), createStair(Block.Properties.of(), Blocks.CRACKED_DEEPSLATE_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_cracked_deepslate_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_cracked_deepslate_tiles"), createStair(Block.Properties.of(), Blocks.CRACKED_DEEPSLATE_TILES, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_cracked_deepslate_tiles")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_sculk"), createStair(Block.Properties.of(), Blocks.SCULK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_sculk")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_mud"), createStair(Block.Properties.of(), Blocks.MUD, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_mud")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_packed_mud"), createStair(Block.Properties.of(), Blocks.PACKED_MUD, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_packed_mud")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_reinforced_deepslate"), createStair(Block.Properties.of(), Blocks.REINFORCED_DEEPSLATE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_reinforced_deepslate")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_ochre_froglight"), createStair(Block.Properties.of().pushReaction(PushReaction.DESTROY).lightLevel(s -> 15), Blocks.OCHRE_FROGLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_ochre_froglight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_pearlescent_froglight"), createStair(Block.Properties.of().pushReaction(PushReaction.DESTROY).lightLevel(s -> 15), Blocks.PEARLESCENT_FROGLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_pearlescent_froglight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_verdant_froglight"), createStair(Block.Properties.of().pushReaction(PushReaction.DESTROY).lightLevel(s -> 15), Blocks.VERDANT_FROGLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_verdant_froglight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_gold"), createStair(Block.Properties.of(), Blocks.GOLD_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_gold")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_rooted_dirt"), createStair(Block.Properties.of(), Blocks.ROOTED_DIRT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_rooted_dirt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_muddy_mangrove_roots"), createStair(Block.Properties.of(), Blocks.MUDDY_MANGROVE_ROOTS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_muddy_mangrove_roots")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_cracked_polished_blackstone_bricks"), createStair(Block.Properties.of(), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_cracked_polished_blackstone_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_dripstone"), createStair(Block.Properties.of(), Blocks.DRIPSTONE_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_dripstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_shroomlight"), createStair(Block.Properties.of().lightLevel(state -> 15), Blocks.SHROOMLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_shroomlight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_gilded_blackstone"), createStair(Block.Properties.of(), Blocks.GILDED_BLACKSTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_gilded_blackstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_moss"), createStair(Block.Properties.of(), Blocks.MOSS_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_moss")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_soul_sand"), createStair(Block.Properties.of().speedFactor(0.4F), Blocks.SOUL_SAND, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_soul_sand")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_soul_soil"), createStair(Block.Properties.of(), Blocks.SOUL_SOIL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_soul_soil")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_packed_ice"), createStair(Block.Properties.of().friction(0.98F), Blocks.PACKED_ICE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_packed_ice")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_blue_ice"), createStair(Block.Properties.of().friction(0.989F), Blocks.BLUE_ICE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_blue_ice")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_honeycomb"), createStair(Block.Properties.of(), Blocks.HONEYCOMB_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_honeycomb")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_emerald"), createStair(Block.Properties.of(), Blocks.EMERALD_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_emerald")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_lapis"), createStair(Block.Properties.of(), Blocks.LAPIS_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_lapis")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_diamond"), createStair(Block.Properties.of(), Blocks.DIAMOND_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_diamond")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_bone"), createStair(Block.Properties.of(), Blocks.BONE_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_bone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_netherite"), createStair(Block.Properties.of(), Blocks.NETHERITE_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_netherite")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_iron"), createStair(Block.Properties.of(), Blocks.IRON_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_iron")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_raw_iron"), createStair(Block.Properties.of(), Blocks.RAW_IRON_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_raw_iron")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_raw_gold"), createStair(Block.Properties.of(), Blocks.RAW_GOLD_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_raw_gold")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_raw_copper"), createStair(Block.Properties.of(), Blocks.RAW_COPPER_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_raw_copper")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_resin"), createStair(Block.Properties.of(), Blocks.RESIN_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_resin")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_chiseled_resin"), createStair(Block.Properties.of(), Blocks.CHISELED_RESIN_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_chiseled_resin")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_pale_moss"), createStair(Block.Properties.of(), Blocks.PALE_MOSS_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "stairs_pale_moss")));
      //
      //                WALLS
      //
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_acacia_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.STRIPPED_ACACIA_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_acacia_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_birch_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.STRIPPED_BIRCH_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_birch_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_dark_oak_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.STRIPPED_DARK_OAK_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_dark_oak_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_jungle_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.STRIPPED_JUNGLE_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_jungle_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_oak_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.STRIPPED_OAK_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_oak_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_spruce_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.STRIPPED_SPRUCE_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_spruce_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_mangrove_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.STRIPPED_MANGROVE_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_mangrove_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_acacia_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.ACACIA_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_acacia_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_birch_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.BIRCH_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_birch_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_dark_oak_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.DARK_OAK_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_dark_oak_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_jungle_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.JUNGLE_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_jungle_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_oak_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.OAK_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_oak_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_spruce_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.SPRUCE_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_spruce_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_mangrove_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.MANGROVE_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_mangrove_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_andesite_smooth"), createWall(Block.Properties.of(), Blocks.ANDESITE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_andesite_smooth")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_diorite_smooth"), createWall(Block.Properties.of(), Blocks.DIORITE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_diorite_smooth")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_end_stone"), createWall(Block.Properties.of(), Blocks.END_STONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_end_stone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_granite_smooth"), createWall(Block.Properties.of(), Blocks.QUARTZ_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_granite_smooth")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_purpur"), createWall(Block.Properties.of(), Blocks.PURPUR_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_purpur")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_quartz"), createWall(Block.Properties.of(), Blocks.QUARTZ_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_quartz")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_sandstone_red_smooth"), createWall(Block.Properties.of(), Blocks.RED_SANDSTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_sandstone_red_smooth")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_sandstone_smooth"), createWall(Block.Properties.of(), Blocks.SANDSTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_sandstone_smooth")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stone"), createWall(Block.Properties.of(), Blocks.STONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stone_slab"), createWall(Block.Properties.of(), Blocks.STONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stone_slab")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stonebrick_carved"), createWall(Block.Properties.of(), Blocks.STONE_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stonebrick_carved")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stonebrick_cracked"), createWall(Block.Properties.of(), Blocks.STONE_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stonebrick_cracked")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_obsidian"), createWall(Block.Properties.of(), Blocks.OBSIDIAN, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_obsidian")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_prismarine_bricks"), createWall(Block.Properties.of(), Blocks.PRISMARINE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_prismarine_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_dark_prismarine"), createWall(Block.Properties.of(), Blocks.DARK_PRISMARINE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_dark_prismarine")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_crimson"), createWall(Block.Properties.of().ignitedByLava(), Blocks.CRIMSON_STEM, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_crimson")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_warped"), createWall(Block.Properties.of().ignitedByLava(), Blocks.WARPED_STEM, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_warped")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_crying_obsidian"), createWall(Block.Properties.of().lightLevel(state -> 10), Blocks.CRYING_OBSIDIAN, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_crying_obsidian")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_basalt"), createWall(Block.Properties.of(), Blocks.BASALT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_basalt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_polished_basalt"), createWall(Block.Properties.of(), Blocks.POLISHED_BASALT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_polished_basalt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_lodestone"), createWall(Block.Properties.of(), Blocks.LODESTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_lodestone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_crimson"), createWall(Block.Properties.of().ignitedByLava(), Blocks.STRIPPED_CRIMSON_HYPHAE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_crimson")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_warped"), createWall(Block.Properties.of().ignitedByLava(), Blocks.STRIPPED_WARPED_HYPHAE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_warped")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_mushroom_stem"), createWall(Block.Properties.of().ignitedByLava(), Blocks.MUSHROOM_STEM, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_mushroom_stem")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_red_mushroom"), createWall(Block.Properties.of().ignitedByLava(), Blocks.RED_MUSHROOM_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_red_mushroom")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_brown_mushroom"), createWall(Block.Properties.of().ignitedByLava(), Blocks.BROWN_MUSHROOM_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_brown_mushroom")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_mushroom_polished"), createWall(Block.Properties.of().ignitedByLava(), Blocks.MUSHROOM_STEM, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_mushroom_polished"))); // ??
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_quartz_bricks"), createWall(Block.Properties.of(), Blocks.QUARTZ_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_quartz_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_magma"), createWall(Block.Properties.of().lightLevel(s -> 3), Blocks.MAGMA_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_magma")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glowstone"), createWall(Block.Properties.of().instrument(NoteBlockInstrument.SNARE).sound(SoundType.GLASS).lightLevel(s -> 15), Blocks.GLOWSTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glowstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_sea_lantern"), createWall(Block.Properties.of().instrument(NoteBlockInstrument.SNARE).sound(SoundType.GLASS).lightLevel(s -> 15), Blocks.SEA_LANTERN, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_sea_lantern")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_white"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.WHITE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_white")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_orange"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.ORANGE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_orange")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_magenta"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.MAGENTA_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_magenta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_purple"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.PURPLE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_purple")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_blue"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.BLUE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_brown"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.BROWN_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_brown")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_red"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.RED_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_red")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_black"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.BLACK_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_black")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_cyan"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.CYAN_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_cyan")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_light_gray"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.LIGHT_GRAY_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_light_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_gray"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.GRAY_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_pink"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.PINK_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_pink")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_lime"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.LIME_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_lime")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_light_blue"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.LIGHT_BLUE_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_light_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_yellow"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.YELLOW_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_yellow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_green"), createWall(Block.Properties.of().mapColor(MapColor.NONE).instrument(NoteBlockInstrument.HAT), Blocks.GREEN_STAINED_GLASS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_glass_green")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_oak_planks"), createWall(Block.Properties.of().ignitedByLava(), Blocks.OAK_PLANKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_oak_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_dark_oak_planks"), createWall(Block.Properties.of().ignitedByLava(), Blocks.DARK_OAK_PLANKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_dark_oak_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_acacia_planks"), createWall(Block.Properties.of().ignitedByLava(), Blocks.ACACIA_PLANKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_acacia_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_jungle_planks"), createWall(Block.Properties.of().ignitedByLava(), Blocks.JUNGLE_PLANKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_jungle_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_birch_planks"), createWall(Block.Properties.of().ignitedByLava(), Blocks.BIRCH_PLANKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_birch_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_spruce_planks"), createWall(Block.Properties.of().ignitedByLava(), Blocks.SPRUCE_PLANKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_spruce_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_mangrove_planks"), createWall(Block.Properties.of().ignitedByLava(), Blocks.MANGROVE_PLANKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_mangrove_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_crimson_planks"), createWall(Block.Properties.of().ignitedByLava(), Blocks.CRIMSON_PLANKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_crimson_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_warped_planks"), createWall(Block.Properties.of().ignitedByLava(), Blocks.WARPED_PLANKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_warped_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_calcite"), createWall(Block.Properties.of(), Blocks.CALCITE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_calcite")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_amethyst"), createWall(Block.Properties.of(), Blocks.AMETHYST_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_amethyst")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_chiseled_tuff"), createWall(Block.Properties.of(), Blocks.CHISELED_TUFF, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_chiseled_tuff")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_chiseled_tuff_bricks"), createWall(Block.Properties.of(), Blocks.CHISELED_TUFF_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_chiseled_tuff_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_smooth_basalt"), createWall(Block.Properties.of(), Blocks.SMOOTH_BASALT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_smooth_basalt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cracked_nether_bricks"), createWall(Block.Properties.of(), Blocks.CRACKED_NETHER_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cracked_nether_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_deepslate"), createWall(Block.Properties.of(), Blocks.DEEPSLATE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_deepslate")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cracked_deepslate_bricks"), createWall(Block.Properties.of(), Blocks.CRACKED_DEEPSLATE_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cracked_deepslate_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cracked_deepslate_tiles"), createWall(Block.Properties.of(), Blocks.CRACKED_DEEPSLATE_TILES, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cracked_deepslate_tiles")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_sculk"), createWall(Block.Properties.of(), Blocks.SCULK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_sculk")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_mud"), createWall(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT), Blocks.MUD, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_mud")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_packed_mud"), createWall(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT), Blocks.PACKED_MUD, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_packed_mud")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_ochre_froglight"), createWall(Block.Properties.of().pushReaction(PushReaction.DESTROY).lightLevel(s -> 15), Blocks.OCHRE_FROGLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_ochre_froglight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_pearlescent_froglight"), createWall(Block.Properties.of().pushReaction(PushReaction.DESTROY).lightLevel(s -> 15), Blocks.PEARLESCENT_FROGLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_pearlescent_froglight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_verdant_froglight"), createWall(Block.Properties.of().pushReaction(PushReaction.DESTROY).lightLevel(s -> 15), Blocks.VERDANT_FROGLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_verdant_froglight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_reinforced_deepslate"), createWall(Block.Properties.of(), Blocks.REINFORCED_DEEPSLATE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_reinforced_deepslate")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cherry_planks"), createWall(Block.Properties.of(), Blocks.CHERRY_PLANKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cherry_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cherry_stripped_log"), createWall(Block.Properties.of(), Blocks.STRIPPED_CHERRY_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cherry_stripped_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cherry_log"), createWall(Block.Properties.of(), Blocks.CHERRY_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cherry_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_bamboo_planks"), createWall(Block.Properties.of(), Blocks.STRIPPED_BAMBOO_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_bamboo_planks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_bamboo_mosaic"), createWall(Block.Properties.of(), Blocks.BAMBOO_MOSAIC, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_bamboo_mosaic")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_gold"), createWall(Block.Properties.of(), Blocks.GOLD_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_gold")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_rooted_dirt"), createWall(Block.Properties.of(), Blocks.ROOTED_DIRT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_rooted_dirt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_muddy_mangrove_roots"), createWall(Block.Properties.of(), Blocks.MUDDY_MANGROVE_ROOTS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_muddy_mangrove_roots")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cracked_polished_blackstone_bricks"), createWall(Block.Properties.of(), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_cracked_polished_blackstone_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_snow"), createWall(Block.Properties.of(), Blocks.SNOW, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_snow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_netherrack"), createWall(Block.Properties.of(), Blocks.NETHERRACK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_netherrack")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_coarse_dirt"), createWall(Block.Properties.of(), Blocks.COARSE_DIRT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_coarse_dirt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_black"), createWall(Block.Properties.of(), Blocks.BLACK_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_black")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_blue"), createWall(Block.Properties.of(), Blocks.BLUE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_brown"), createWall(Block.Properties.of(), Blocks.BROWN_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_brown")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_cyan"), createWall(Block.Properties.of(), Blocks.CYAN_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_cyan")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_gray"), createWall(Block.Properties.of(), Blocks.GRAY_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_green"), createWall(Block.Properties.of(), Blocks.GREEN_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_green")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_light_blue"), createWall(Block.Properties.of(), Blocks.LIGHT_BLUE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_light_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_light_gray"), createWall(Block.Properties.of(), Blocks.LIGHT_GRAY_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_light_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_lime"), createWall(Block.Properties.of(), Blocks.LIME_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_lime")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_magenta"), createWall(Block.Properties.of(), Blocks.MAGENTA_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_magenta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_orange"), createWall(Block.Properties.of(), Blocks.ORANGE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_orange")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_pink"), createWall(Block.Properties.of(), Blocks.PINK_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_pink")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_purple"), createWall(Block.Properties.of(), Blocks.PURPLE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_purple")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_red"), createWall(Block.Properties.of(), Blocks.RED_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_red")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_white"), createWall(Block.Properties.of(), Blocks.WHITE_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_white")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_yellow"), createWall(Block.Properties.of(), Blocks.YELLOW_CONCRETE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_concrete_yellow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_black"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_black")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_blue"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_brown"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_brown")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_cyan"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_cyan")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_gray"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_green"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_green")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_light_blue"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_light_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_light_gray"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_light_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_lime"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_lime")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_magenta"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_magenta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_orange"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_orange")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_pink"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_pink")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_purple"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_purple")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_red"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_red")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_white"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_white")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_yellow"), createWall(Block.Properties.of(), Blocks.BLACK_TERRACOTTA, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_terracotta_yellow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_black"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_black")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_blue"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_brown"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_brown")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_cyan"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_cyan")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_gray"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_green"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_green")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_light_blue"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_light_blue")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_light_gray"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_light_gray")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_lime"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_lime")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_magenta"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_magenta")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_orange"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_orange")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_pink"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_pink")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_purple"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_purple")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_red"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_red")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_white"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_white")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_yellow"), createWall(Block.Properties.of(), Blocks.BLACK_WOOL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_wool_yellow")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_dripstone"), createWall(Block.Properties.of(), Blocks.DRIPSTONE_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_dripstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_shroomlight"), createWall(Block.Properties.of().lightLevel(state -> 15), Blocks.SHROOMLIGHT, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_shroomlight")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_gilded_blackstone"), createWall(Block.Properties.of(), Blocks.GILDED_BLACKSTONE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_gilded_blackstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_moss"), createWall(Block.Properties.of(), Blocks.MOSS_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_moss")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_soul_sand"), createWall(Block.Properties.of().speedFactor(0.4F), Blocks.SOUL_SAND, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_soul_sand")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_soul_soil"), createWall(Block.Properties.of(), Blocks.SOUL_SOIL, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_soul_soil")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_packed_ice"), createWall(Block.Properties.of().friction(0.98F), Blocks.PACKED_ICE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_packed_ice")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_blue_ice"), createWall(Block.Properties.of().friction(0.989F), Blocks.BLUE_ICE, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_blue_ice")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_honeycomb"), createWall(Block.Properties.of(), Blocks.HONEYCOMB_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_honeycomb")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_emerald"), createWall(Block.Properties.of(), Blocks.EMERALD_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_emerald")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_lapis"), createWall(Block.Properties.of(), Blocks.LAPIS_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_lapis")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_diamond"), createWall(Block.Properties.of(), Blocks.DIAMOND_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_diamond")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_bone"), createWall(Block.Properties.of(), Blocks.BONE_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_bone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_netherite"), createWall(Block.Properties.of(), Blocks.NETHERITE_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_netherite")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_iron"), createWall(Block.Properties.of(), Blocks.IRON_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_iron")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_raw_iron"), createWall(Block.Properties.of(), Blocks.RAW_IRON_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_raw_iron")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_raw_gold"), createWall(Block.Properties.of(), Blocks.RAW_GOLD_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_raw_gold")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_raw_copper"), createWall(Block.Properties.of(), Blocks.RAW_COPPER_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_raw_copper")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_resin"), createWall(Block.Properties.of(), Blocks.RESIN_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_resin")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_chiseled_resin"), createWall(Block.Properties.of(), Blocks.CHISELED_RESIN_BRICKS, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_chiseled_resin")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_pale_moss"), createWall(Block.Properties.of(), Blocks.PALE_MOSS_BLOCK, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_pale_moss")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_pale_oak_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.PALE_OAK_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_pale_oak_log")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_pale_oak_log"), createWall(Block.Properties.of().ignitedByLava(), Blocks.STRIPPED_PALE_OAK_LOG, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "wall_stripped_pale_oak_log")));
      //
      //                GATE
      //
      WoodType def = WoodType.MANGROVE; // no important effects
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_nether_bricks"), createGate(Blocks.NETHER_BRICKS, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_nether_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_red_nether_bricks"), createGate(Blocks.RED_NETHER_BRICKS, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_red_nether_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_quartz"), createGate(Blocks.QUARTZ_BLOCK, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_quartz")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_stone_bricks"), createGate(Blocks.STONE_BRICKS, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_stone_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_blackstone_bricks"), createGate(Blocks.POLISHED_BLACKSTONE_BRICKS, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_blackstone_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_bricks"), createGate(Blocks.BRICKS, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_end_stone_bricks"), createGate(Blocks.END_STONE_BRICKS, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_end_stone_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_obsidian"), createGate(Blocks.OBSIDIAN, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_obsidian")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_prismarine"), createGate(Blocks.PRISMARINE, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_prismarine")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_prismarine_brick"), createGate(Blocks.PRISMARINE, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_prismarine_brick")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_prismarine_dark"), createGate(Blocks.DARK_PRISMARINE, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_prismarine_dark")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_purpur"), createGate(Blocks.PURPUR_BLOCK, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_purpur")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_mud_bricks"), createGate(Blocks.MUD_BRICKS, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_mud_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_cobblestone"), createGate(Blocks.COBBLESTONE, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_cobblestone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_stone"), createGate(Blocks.STONE, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_stone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_blackstone"), createGate(Blocks.BLACKSTONE, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_blackstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_sandstone"), createGate(Blocks.SANDSTONE, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_sandstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_red_sandstone"), createGate(Blocks.RED_SANDSTONE, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_red_sandstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_basalt"), createGate(Blocks.BASALT, Block.Properties.of(), def, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "gate_basalt")));
      //
      //                TRAPDOOR
      //
      BlockSetType stoneType = BlockSetType.STONE;
      BlockSetType ironType = BlockSetType.IRON;
      var AMY = BlockSetType.register(new BlockSetType("amethyst", true, false, false, BlockSetType.PressurePlateSensitivity.MOBS, SoundType.AMETHYST, SoundEvents.AMETHYST_BLOCK_PLACE, SoundEvents.AMETHYST_BLOCK_CHIME, SoundEvents.AMETHYST_BLOCK_PLACE, SoundEvents.AMETHYST_BLOCK_CHIME, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_stone"), createTrap(Blocks.STONE, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_stone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_granite"), createTrap(Blocks.GRANITE, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_granite")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_andesite"), createTrap(Blocks.ANDESITE, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_andesite")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_diorite"), createTrap(Blocks.DIORITE, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_diorite")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_bricks"), createTrap(Blocks.STONE, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_stone_bricks"), createTrap(Blocks.STONE_BRICKS, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_stone_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_blackstone"), createTrap(Blocks.STONE_BRICKS, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_blackstone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_blackstone_bricks"), createTrap(Blocks.STONE_BRICKS, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_blackstone_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_basalt"), createTrap(Blocks.STONE, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_basalt")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_end_stone"), createTrap(Blocks.STONE, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_end_stone")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_purpur"), createTrap(Blocks.PURPUR_BLOCK, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_purpur")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_quartz"), createTrap(Blocks.QUARTZ_BLOCK, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_quartz")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_quartz_bricks"), createTrap(Blocks.QUARTZ_BRICKS, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_quartz_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_mud_bricks"), createTrap(Blocks.MUD_BRICKS, Block.Properties.of(), stoneType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_mud_bricks")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_amethyst"), createTrap(Blocks.AMETHYST_BLOCK, Block.Properties.of(), AMY, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_amethyst")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_obsidian"), createTrap(Blocks.OBSIDIAN, Block.Properties.of(), ironType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_obsidian")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_crying_obsidian"), createTrap(Blocks.CRYING_OBSIDIAN, Block.Properties.of(), ironType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_crying_obsidian")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_gold"), createTrap(Blocks.GOLD_BLOCK, Block.Properties.of(), ironType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_gold")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_diamond"), createTrap(Blocks.DIAMOND_BLOCK, Block.Properties.of(), ironType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_diamond")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_lapis"), createTrap(Blocks.DIAMOND_BLOCK, Block.Properties.of(), ironType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_lapis")));
      reg.register(Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_emerald"), createTrap(Blocks.DIAMOND_BLOCK, Block.Properties.of(), ironType, Identifier.fromNamespaceAndPath(ModAbsentBD.MODID, "trapdoor_emerald")));
    });
  }

  public static Block createFence(Block block, Block.Properties p, Identifier id) {
    return addBlock(new BlockAbsentFence(BlockUtil.wrap(p, block, id)));
  }

  public static BlockAbsentWall createWall(Block.Properties p, Block block, Identifier id) {
    BlockAbsentWall wall = new BlockAbsentWall(BlockUtil.wrap(p, block, id));
    addBlock(wall);
    if (block == Blocks.CRYING_OBSIDIAN) {
      wall.part = ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
    }
    return wall;
  }

  public static Block createSlab(Block.Properties prop, Block block, Identifier id) {
    BlockAbsentSlab slab = new BlockAbsentSlab(BlockUtil.wrap(prop, block, id));
    addBlock(slab);
    if (block == Blocks.CRYING_OBSIDIAN) {
      slab.part = ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
    }
    return slab;
  }

  public static BlockAbsentStair createStair(Block.Properties prop, Block block, Identifier id) {
    BlockAbsentStair stair = new BlockAbsentStair(block, BlockUtil.wrap(prop, block, id));
    addBlock(stair);
    if (block == Blocks.CRYING_OBSIDIAN) {
      stair.part = ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
    }
    return stair;
  }

  public static Block createGate(Block block, Block.Properties p, WoodType type, Identifier id) {
    return addBlock(new BlockAbsentGate(BlockUtil.wrap(p, block, id), type));
  }

  public static Block createDoor(Block block, Block.Properties p, BlockSetType type, Identifier id) {
    return addBlock(new DoorAbsentBlock(BlockUtil.wrap(p, block, id), type));
  }

  public static Block createTrap(Block block, Block.Properties p, BlockSetType type, Identifier id) {
    return addBlock(new TrapDoorAbsent(BlockUtil.wrap(p, block, id), type));
  }

  public static Block addBlock(Block b) {
    BLOCKLIST.add(b);
    return b;
  }
}
