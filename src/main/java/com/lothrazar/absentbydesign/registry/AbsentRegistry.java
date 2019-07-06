package com.lothrazar.absentbydesign.registry;
import com.lothrazar.absentbydesign.ModAbsentBD;
import com.lothrazar.absentbydesign.block.BlockAbsentFence;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;

public class AbsentRegistry {

  public static ItemGroup itemGroup = new ItemGroup(ModAbsentBD.MODID) {

    @Override
    public ItemStack createIcon() {
      return new ItemStack(AbsentRegistry.FENCE_QUARTZ);
    }
  };

  @ObjectHolder(ModAbsentBD.MODID + ":fence_quartz")
  public static BlockAbsentFence FENCE_QUARTZ;
  @ObjectHolder(ModAbsentBD.MODID + ":fence_red_netherbrick")
  public static BlockAbsentFence fence_red_netherbrick;
  @ObjectHolder(ModAbsentBD.MODID + ":fence_log_acacia")
  public static BlockAbsentFence fence_log_acacia;
  @ObjectHolder(ModAbsentBD.MODID + ":fence_log_birch")
  public static BlockAbsentFence fence_log_birch;
  @ObjectHolder(ModAbsentBD.MODID + ":fence_log_darkoak")
  public static BlockAbsentFence fence_log_darkoak;
  @ObjectHolder(ModAbsentBD.MODID + ":fence_log_jungle")
  public static BlockAbsentFence fence_log_jungle;
  @ObjectHolder(ModAbsentBD.MODID + ":fence_log_oak")
  public static BlockAbsentFence fence_log_oak;
  @ObjectHolder(ModAbsentBD.MODID + ":fence_log_spruce")
  public static BlockAbsentFence fence_log_spruce;
  //  @SubscribeEvent
//  public void onRegisterRecipe(RegistryEvent.Register<IRecipe> event) {
//    IForgeRegistryModifiable<IRecipe> modRegistry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//    for (IRecipe r : event.getRegistry()) {
//      ItemStack output = r.getRecipeOutput();
//      if (ItemStack.areItemStacksEqual(output, new ItemStack(Blocks.STONE_SLAB, 6, 5)) ||
//          ItemStack.areItemStacksEqual(output, new ItemStack(Blocks.STONE_BRICK_STAIRS, 4, 0))) {
//        modRegistry.remove(r.getRegistryName());
//      }
//    }
//    RecipeRegistry.addShapedRecipe(new ItemStack(Blocks.STONE_SLAB, 4, 5),
//        "sss",
//        's', new ItemStack(Blocks.STONEBRICK, 1, 0));
//    RecipeRegistry.addShapedRecipe(new ItemStack(Blocks.STONE_BRICK_STAIRS, 4),
//        "s  ",
//        "ss ",
//        "sss",
//        's', new ItemStack(Blocks.STONEBRICK, 1, 0));
//    event.getRegistry().registerAll(RecipeRegistry.recipes.toArray(new IRecipe[0]));
//  }
//
//  @SubscribeEvent
//  public void onRegistryEvent(RegistryEvent.Register<Block> event) {
//    event.getRegistry().registerAll(blocks.toArray(new Block[0]));
//  }
//
//  @SubscribeEvent
//  public void registerItems(RegistryEvent.Register<Item> event) {
//    for (Item item : itemList) {
//      event.getRegistry().register(item);
//      if (item instanceof IHasRecipe) {
//        ((IHasRecipe) item).addRecipe();
//      }
//      Block blockItem = Block.getBlockFromItem(item);
//      if (blockItem instanceof IHasRecipe) {
//        ((IHasRecipe) blockItem).addRecipe();
//      }
//
//    }
//  }

//  @SideOnly(Side.CLIENT)
//  @SubscribeEvent
//  public void registerModels(ModelRegistryEvent event) {
//    String name;
//    Item item;
//    for (Block block : blocks) {
//      item = Item.getItemFromBlock(block);
//      name = ModAbsentBD.MODID + ":" + block.getUnlocalizedName().replaceAll("tile.", "");
//      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name, "inventory"));
//      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name));
//    }
//  }

//  public void createWall(Block baseType, ItemStack s, String name) {
//    registerBlock(new BlockAbsentWall(baseType, s), "wall_" + name);
//  }
//
//  public void createStair(Block baseType, ItemStack ing, String name) {
//    registerBlock(new BlockAbsentStairs(baseType, ing), "stairs_" + name);
//  }
//
public static BlockAbsentFence createFence(Block block, Material mat, String name) {
  return new BlockAbsentFence(Block.Properties.create(mat, MaterialColor.QUARTZ)
      .sound(block.getSoundType(null))
      .hardnessAndResistance(block.getBlockHardness(block.getDefaultState(),
          null, null))
      ,name);
  }
//
//  private Block registerBlock(Block block, String name) {
//    return this.registerBlock(block, name, null);
//  }
//
//  private Block registerBlock(Block block, String name, @Nullable ItemBlock itemblock) {
//    block.setCreativeTab(ModAbsentBD.tab);
//    block.setRegistryName(new ResourceLocation(ModAbsentBD.MODID, name));
//    block.setUnlocalizedName(name);
//    blocks.add(block);
//    ItemBlock ib;
//    if (itemblock == null) {
//      ib = new ItemBlock(block);
//    }
//    else {
//      ib = itemblock;
//    }
//    ib.setRegistryName(block.getRegistryName());
//    addItem(ib);
//    return block;
//  }

//  private void addItem(ItemBlock ib) {
//    itemList.add(ib);
//  }
//
//  private BlockAbsentSlab registerSlabBlock(BlockAbsentSlab block, String name) {
//    block.setCreativeTab(ModAbsentBD.tab);
//    block.setRegistryName(new ResourceLocation(ModAbsentBD.MODID, name));
//    block.setUnlocalizedName(name);
//    blocks.add(block);
//    //both block types go here
//    return block;
//  }
//
//  public void createSlab(Block type, ItemStack i, String name) {
//    name = "slab_" + name;
//    BlockAbsentSlab half = registerSlabBlock(new BlockAbsentSlabHalf(type, i), name);
//    BlockAbsentSlab dubs = registerSlabBlock(new BlockAbsentSlabDouble(type), name + "_double");
//    registerSlabItem(new ItemBlockAbsentSlab(half, half, dubs), name);
//    //   registerSlabItem(new ItemBlockAbsentSlab(dubs, half, dubs), name + "_double");
//  }
//
//  private void registerSlabItem(ItemBlockAbsentSlab item, String string) {
//    item.setRegistryName(string);
//    item.setUnlocalizedName(string);
//    itemList.add(item);
//  }
}
