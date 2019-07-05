package com.lothrazar.absentbydesign;

import com.lothrazar.absentbydesign.block.BlockAbsentFence;
import com.lothrazar.absentbydesign.setup.ClientProxy;
import com.lothrazar.absentbydesign.setup.IProxy;
import com.lothrazar.absentbydesign.setup.ServerProxy;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(ModAbsentBD.MODID)
//, certificateFingerprint = "@FINGERPRINT@", updateJSON = "https://raw.githubusercontent.com/Lothrazar/AbsentByDesign/master/update.json")
public class ModAbsentBD {
  public static final String MODID = "absentbydesign";
  public static final IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
  public static final Logger LOGGER = LogManager.getLogger();


  public ModAbsentBD(){
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    //only for server starting
    MinecraftForge.EVENT_BUS.register(this);
//    MinecraftForge.EVENT_BUS.register(new DyeEventHandler());
  }

  private void setup(final FMLCommonSetupEvent event) {
  }
//  @GameRegistry.ObjectHolder(ModAbsentBD.MODID + ":stairs_granite")
//  public static final Block icon = null;
//  @Instance(ModAbsentBD.MODID)
//  public static ModAbsentBD instance;
//  public static final CreativeTabs tab = new CreativeTabs(ModAbsentBD.MODID) {
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public ItemStack getTabIconItem() {
//      return new ItemStack(icon);
//    }
//  };

  private AbsentRegistry registry;

  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {
    ////    registry.createFence(Blocks.QUARTZ_BLOCK, new ItemStack(Blocks.QUARTZ_BLOCK), "quartz");
    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
      event.getRegistry()
          .register(new BlockAbsentFence(Blocks.QUARTZ_BLOCK, new ItemStack(Blocks.QUARTZ_BLOCK),"fence_quartz"));
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
      Item.Properties properties = new Item.Properties()
//          .group(setup.itemGroup);
      ;
       event.getRegistry().register(new BlockItem(AbsentRegistry.FENCE_QUARTZ, properties).setRegistryName(AbsentRegistry.FENCE_QUARTZ.rawName()));
    }
  }

//  @EventHandler
//  public void preInit(FMLPreInitializationEvent event) {
//    logger = event.getModLog();
//    registry = new AbsentRegistry();
//    MinecraftForge.EVENT_BUS.register(registry);
//    //now register content
//    registry.createWall(Blocks.STONE, new ItemStack(Blocks.STONE), "stone");
//    registry.createWall(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE.getMetadata()), "granite");
//    registry.createWall(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.ANDESITE.getMetadata()), "andesite");
//    registry.createWall(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.DIORITE.getMetadata()), "diorite");
//    registry.createWall(Blocks.BRICK_BLOCK, new ItemStack(Blocks.BRICK_BLOCK), "brick");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE_SMOOTH.getMetadata()), "granite_smooth");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.ANDESITE_SMOOTH.getMetadata()), "andesite_smooth");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.DIORITE_SMOOTH.getMetadata()), "diorite_smooth");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.DEFAULT.getMetadata()), "stonebrick");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.MOSSY.getMetadata()), "stonebrick_mossy");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.CRACKED.getMetadata()), "stonebrick_cracked");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.CHISELED.getMetadata()), "stonebrick_carved");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.STONE_SLAB), "stone_slab");
//    registry.createWall(Blocks.SANDSTONE, new ItemStack(Blocks.SANDSTONE, 1, BlockStone.EnumType.GRANITE.getMetadata()), "sandstone");
//    registry.createWall(Blocks.SANDSTONE, new ItemStack(Blocks.SANDSTONE, 1, 2), "sandstone_smooth");
//    registry.createWall(Blocks.SANDSTONE, new ItemStack(Blocks.RED_SANDSTONE, 1, BlockStone.EnumType.GRANITE.getMetadata()), "sandstone_red");
//    registry.createWall(Blocks.SANDSTONE, new ItemStack(Blocks.RED_SANDSTONE, 1, 1), "sandstone_red_smooth");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.PURPUR_BLOCK), "purpur");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.END_BRICKS), "end_bricks");
//    registry.createWall(Blocks.STONEBRICK, new ItemStack(Blocks.END_STONE), "end_stone");
//    ////////////// stairs
//    registry.createStair(Blocks.STONE,
//        new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE.getMetadata()), "granite");
//    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE.getMetadata()), "andesite");
//    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.DIORITE.getMetadata()), "diorite");
//    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE_SMOOTH.getMetadata()), "granite_smooth");
//    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.ANDESITE_SMOOTH.getMetadata()), "andesite_smooth");
//    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.DIORITE_SMOOTH.getMetadata()), "diorite_smooth");
//    registry.createStair(Blocks.PRISMARINE, new ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.ROUGH.getMetadata()), "prismarine");
//    registry.createStair(Blocks.PRISMARINE, new ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.BRICKS.getMetadata()), "prismarine_bricks");
//    registry.createStair(Blocks.PRISMARINE, new ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.DARK.getMetadata()), "prismarine_dark");
//    registry.createStair(Blocks.MOSSY_COBBLESTONE, new ItemStack(Blocks.MOSSY_COBBLESTONE), "cobble_moss");
//    registry.createStair(Blocks.STONEBRICK, new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.MOSSY.getMetadata()), "bricks_mossy");
//    registry.createStair(Blocks.STONEBRICK, new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.CRACKED.getMetadata()), "bricks_cracked");
//    registry.createStair(Blocks.END_BRICKS, new ItemStack(Blocks.END_STONE), "end_bricks");
//    registry.createStair(Blocks.RED_NETHER_BRICK, new ItemStack(Blocks.RED_NETHER_BRICK), "netherbrick_red");
//    registry.createStair(Blocks.STONE, new ItemStack(Blocks.STONE), "stone");
//    registry.createStair(Blocks.NETHERRACK, new ItemStack(Blocks.NETHERRACK), "netherrack");
//    registry.createStair(Blocks.END_STONE, new ItemStack(Blocks.END_STONE), "end_stone");
//    registry.createStair(Blocks.DIRT, new ItemStack(Blocks.DIRT, 1, BlockDirt.DirtType.COARSE_DIRT.getMetadata()), "coarse_dirt");
//    registry.createStair(Blocks.SNOW, new ItemStack(Blocks.SNOW), "snow");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.RED.getMetadata()), "concrete_red");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLUE.getMetadata()), "concrete_blue");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BROWN.getMetadata()), "concrete_brown");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.CYAN.getMetadata()), "concrete_cyan");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.GRAY.getMetadata()), "concrete_gray");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.GREEN.getMetadata()), "concrete_green");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.LIGHT_BLUE.getMetadata()), "concrete_light_blue");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.LIME.getMetadata()), "concrete_lime");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.MAGENTA.getMetadata()), "concrete_magenta");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.ORANGE.getMetadata()), "concrete_orange");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.PINK.getMetadata()), "concrete_pink");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.YELLOW.getMetadata()), "concrete_yellow");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.WHITE.getMetadata()), "concrete_white");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.SILVER.getMetadata()), "concrete_silver");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.PURPLE.getMetadata()), "concrete_purple");
//    registry.createStair(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLACK.getMetadata()), "concrete_black");
//    /////////// slabs
//    registry.createSlab(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE.getMetadata()), "granite");
//    registry.createSlab(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.ANDESITE.getMetadata()), "andesite");
//    registry.createSlab(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.DIORITE.getMetadata()), "diorite");
//    registry.createSlab(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE_SMOOTH.getMetadata()), "granite_smooth");
//    registry.createSlab(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.ANDESITE_SMOOTH.getMetadata()), "andesite_smooth");
//    registry.createSlab(Blocks.STONE, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.DIORITE_SMOOTH.getMetadata()), "diorite_smooth");
//    registry.createSlab(Blocks.PRISMARINE, new ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.ROUGH.getMetadata()), "prismarine");
//    registry.createSlab(Blocks.PRISMARINE, new ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.BRICKS.getMetadata()), "prismarine_bricks");
//    registry.createSlab(Blocks.PRISMARINE, new ItemStack(Blocks.PRISMARINE, 1, BlockPrismarine.EnumType.DARK.getMetadata()), "prismarine_dark");
//    registry.createSlab(Blocks.MOSSY_COBBLESTONE, new ItemStack(Blocks.MOSSY_COBBLESTONE), "cobble_moss");
//    registry.createSlab(Blocks.STONEBRICK, new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.CRACKED.getMetadata()), "bricks_cracked");
//    registry.createSlab(Blocks.STONEBRICK, new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.EnumType.MOSSY.getMetadata()), "bricks_mossy");
//    registry.createSlab(Blocks.END_BRICKS, new ItemStack(Blocks.END_BRICKS), "end_bricks");
//    registry.createSlab(Blocks.RED_NETHER_BRICK, new ItemStack(Blocks.RED_NETHER_BRICK), "netherbrick_red");
//    registry.createSlab(Blocks.STONE, new ItemStack(Blocks.STONE_SLAB), "stone");
//    registry.createSlab(Blocks.NETHERRACK, new ItemStack(Blocks.NETHERRACK), "netherrack");
//    registry.createSlab(Blocks.END_STONE, new ItemStack(Blocks.END_STONE), "end_stone");
//    registry.createSlab(Blocks.DIRT, new ItemStack(Blocks.DIRT, 1, 1), "coarse_dirt");
//    registry.createSlab(Blocks.SNOW, new ItemStack(Blocks.SNOW), "snow");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.RED.getMetadata()), "concrete_red");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLUE.getMetadata()), "concrete_blue");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BROWN.getMetadata()), "concrete_brown");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.CYAN.getMetadata()), "concrete_cyan");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.GREEN.getMetadata()), "concrete_green");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.GRAY.getMetadata()), "concrete_gray");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.LIGHT_BLUE.getMetadata()), "concrete_light_blue");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.LIME.getMetadata()), "concrete_lime");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.MAGENTA.getMetadata()), "concrete_magenta");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.ORANGE.getMetadata()), "concrete_orange");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.PINK.getMetadata()), "concrete_pink");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.YELLOW.getMetadata()), "concrete_yellow");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.WHITE.getMetadata()), "concrete_white");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.SILVER.getMetadata()), "concrete_silver");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.PURPLE.getMetadata()), "concrete_purple");
//    registry.createSlab(Blocks.CONCRETE, new ItemStack(Blocks.CONCRETE, 1, EnumDyeColor.BLACK.getMetadata()), "concrete_black");
//    ////////// fence
//    registry.createFence(Blocks.QUARTZ_BLOCK, new ItemStack(Blocks.QUARTZ_BLOCK), "quartz");
//    registry.createFence(Blocks.NETHER_BRICK_FENCE, new ItemStack(Blocks.RED_NETHER_BRICK), "red_netherbrick");
//    registry.createFence(Blocks.LOG, new ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.OAK.getMetadata()), "log_oak");
//    registry.createFence(Blocks.LOG, new ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.BIRCH.getMetadata()), "log_birch");
//    registry.createFence(Blocks.LOG, new ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.SPRUCE.getMetadata()), "log_spruce");
//    registry.createFence(Blocks.LOG, new ItemStack(Blocks.LOG, 1, BlockPlanks.EnumType.JUNGLE.getMetadata()), "log_jungle");
//    registry.createFence(Blocks.LOG, new ItemStack(Blocks.LOG2, 1, 1), "log_darkoak");
//    registry.createFence(Blocks.LOG, new ItemStack(Blocks.LOG2, 1, 0), "log_acacia");
    //TODO: red Nether fences connect to self not wood 
    //STAIRS ONLY: Snow 
    // SLAB ONLY:  chiseled brick, grass_path

    // IRON BARS: gold, obsidian
    // Trapdoor: 6x wood plank
//  }

  @SubscribeEvent
  public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
    // https://tutorials.darkhax.net/tutorials/jar_signing/
    String source = (event.getSource() == null) ? "" : event.getSource().getName() + " ";
    String msg = MODID + "Invalid fingerprint detected! The file " + source + "may have been tampered with. This version will NOT be supported by the author!";
    System.out.println(msg);
  }
}
