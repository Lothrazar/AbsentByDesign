package com.lothrazar.absentbydesign.registry;

import java.util.ArrayList;
import java.util.List;
import com.lothrazar.absentbydesign.ModAbsentBD;
import com.lothrazar.absentbydesign.block.BlockAbsentSlab;
import com.lothrazar.absentbydesign.block.BlockAbsentSlabDouble;
import com.lothrazar.absentbydesign.block.BlockAbsentSlabHalf;
import com.lothrazar.absentbydesign.block.BlockAbsentStairs;
import com.lothrazar.absentbydesign.block.ItemBlockAbsentSlab;
import net.minecraft.block.Block;
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
      //     OreDictionary.registerOre(string , item);
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

  public void createStair(Block baseType, String name) {
    registerStairBlock(new BlockAbsentStairs(baseType.getDefaultState()), "stairs_" + name);
  }

  public void createSlab(Material type, String name) {
    name = "slab_" + name;
    BlockAbsentSlab half = (BlockAbsentSlab) registerSlabBlock(new BlockAbsentSlabHalf(Material.ROCK), name);
    BlockAbsentSlab dubs = (BlockAbsentSlab) registerSlabBlock(new BlockAbsentSlabDouble(Material.ROCK), name + "_double");
    registerSlabItem(new ItemBlockAbsentSlab(half, half, dubs), name);
    registerSlabItem(new ItemBlockAbsentSlab(dubs, half, dubs), name + "_double");
  }

  private Block registerStairBlock(Block block, String name) {
    block.setCreativeTab(ModAbsentBD.tab);
    block.setRegistryName(new ResourceLocation(ModAbsentBD.MODID, name));
    block.setUnlocalizedName(name);
    blocks.add(block);
    ItemBlock ib = new ItemBlock(block);
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

  private void registerSlabItem(ItemBlockAbsentSlab item, String string) {
    item.setRegistryName(string);
    item.setUnlocalizedName(string);
    itemList.add(item);
  }
}
