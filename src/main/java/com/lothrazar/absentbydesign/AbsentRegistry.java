package com.lothrazar.absentbydesign;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
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
    //    for (Item item : itemList) {
    //      if (item instanceof ItemBlock) {
    //        continue;
    //      }
    //      name = ModAbsentBD.MODID + ":" + item.getUnlocalizedName().replaceAll("item.", "");
    //      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name, "inventory"));
    //    }
    Item item;
    for (Block b : blocks) {
      item = Item.getItemFromBlock(b);
      name = ModAbsentBD.MODID + ":" + b.getUnlocalizedName().replaceAll("tile.", "");
      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name, "inventory"));
      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name));
    }
  }

  public void registerBlock(Block b, String name) {
    b.setCreativeTab(CreativeTabs.MISC);
    b.setRegistryName(new ResourceLocation(ModAbsentBD.MODID, name));
    b.setUnlocalizedName(name);
    blocks.add(b);
    ItemBlock ib = new ItemBlock(b);
    ib.setRegistryName(b.getRegistryName()); // ok good this should work yes? yes! http://mcforge.readthedocs.io/en/latest/blocks/blocks/#registering-a-block
    itemList.add(ib);
  }
}
