package com.lothrazar.absentbydesign.registry;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import com.lothrazar.absentbydesign.IHasRecipe;
import com.lothrazar.absentbydesign.ModAbsentBD;
import com.lothrazar.absentbydesign.block.BlockAbsentFence;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import net.minecraftforge.registries.ObjectHolder;

public class AbsentRegistry {



  @ObjectHolder(ModAbsentBD.MODID + ":fence_quartz")
  public static BlockAbsentFence FENCE_QUARTZ;

  private static List<Item> itemList = new ArrayList<Item>();
  private static List<Block> blocks = new ArrayList<Block>();

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
//  public void createFence(Block mat, ItemStack i, String name) {
//    registerBlock(new BlockAbsentFence(mat, i), "fence_" + name);
//  }
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
