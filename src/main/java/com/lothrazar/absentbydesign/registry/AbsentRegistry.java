package com.lothrazar.absentbydesign.registry;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import com.lothrazar.absentbydesign.ModAbsentBD;
import com.lothrazar.absentbydesign.block.BlockAbsentFence;
import com.lothrazar.absentbydesign.block.BlockAbsentSlab;
import com.lothrazar.absentbydesign.block.BlockAbsentSlabDouble;
import com.lothrazar.absentbydesign.block.BlockAbsentSlabHalf;
import com.lothrazar.absentbydesign.block.BlockAbsentStairs;
import com.lothrazar.absentbydesign.block.BlockAbsentWall;
import com.lothrazar.absentbydesign.block.ItemBlockAbsentSlab;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AbsentRegistry {

  public static List<Item> itemList = new ArrayList<Item>();
  public static List<Block> blocks = new ArrayList<Block>();

  @SubscribeEvent
  public void onRegistryEvent(RegistryEvent.Register<Block> event) {

    event.getRegistry().registerAll(blocks.toArray(new Block[0]));
  }

  @SubscribeEvent
  public void registerItems(RegistryEvent.Register<Item> event) {
    for (Item item : itemList) {
      event.getRegistry().register(item);
      //      String block = item.getUnlocalizedName();
      //      block = block.replace("tile.wall_", "").replace("tile.slab_", "").replace("tile.stairs_", "").replace("tile.fence_", "");
      //      String type = item.getUnlocalizedName().replace("tile.", "").split("_")[0];
      //      System.out.println(item.getUnlocalizedName() + ".name=" + WordUtils.capitalize(block) + " " + WordUtils.capitalize(type));
      //      //     OreDictionary.registerOre(string , item);
    }
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void registerModels(ModelRegistryEvent event) {
    String name;
    Item item;
    for (Block block : blocks) {
      item = Item.getItemFromBlock(block);
      name = ModAbsentBD.MODID + ":" + block.getUnlocalizedName().replaceAll("tile.", "");
      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name, "inventory"));
      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name));
    }
  }

  public void createWall(Block baseType, String name) {
    registerBlock(new BlockAbsentWall(baseType), "wall_" + name);
  }

  public void createStair(Block baseType, String name) {
    registerBlock(new BlockAbsentStairs(baseType.getDefaultState()), "stairs_" + name);
  }

  public void createFence(Material mat, MapColor map, String name) {
    registerBlock(new BlockAbsentFence(mat, map), "fence_" + name);
  }


  private Block registerBlock(Block block, String name) {
    return this.registerBlock(block, name, null);
  }

  private Block registerBlock(Block block, String name, @Nullable ItemBlock itemblock) {
    block.setCreativeTab(ModAbsentBD.tab);
    block.setRegistryName(new ResourceLocation(ModAbsentBD.MODID, name));
    block.setUnlocalizedName(name);
    blocks.add(block);
    ItemBlock ib;
    if (itemblock == null) {
      ib = new ItemBlock(block);
    }
    else {
      ib = itemblock;
    }
    ib.setRegistryName(block.getRegistryName()); // ok good this should work yes? yes! http://mcforge.readthedocs.io/en/latest/blocks/blocks/#registering-a-block
    itemList.add(ib);
    return block;
  }

  private Block registerSlabBlock(Block block, String name) {
    block.setCreativeTab(ModAbsentBD.tab);
    block.setRegistryName(new ResourceLocation(ModAbsentBD.MODID, name));
    block.setUnlocalizedName(name);
    blocks.add(block);
    //both block types go here  
    return block;
  }

  public void createSlab(Material type, String name) {
    name = "slab_" + name;
    BlockAbsentSlab half = (BlockAbsentSlab) registerSlabBlock(new BlockAbsentSlabHalf(Material.ROCK), name);
    BlockAbsentSlab dubs = (BlockAbsentSlab) registerSlabBlock(new BlockAbsentSlabDouble(Material.ROCK), name + "_double");
    registerSlabItem(new ItemBlockAbsentSlab(half, half, dubs), name);
    //   registerSlabItem(new ItemBlockAbsentSlab(dubs, half, dubs), name + "_double");
  }

  private void registerSlabItem(ItemBlockAbsentSlab item, String string) {
    item.setRegistryName(string);
    item.setUnlocalizedName(string);
    itemList.add(item);
  }
}
