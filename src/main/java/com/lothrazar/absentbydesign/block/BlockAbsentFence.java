package com.lothrazar.absentbydesign.block;

import com.lothrazar.absentbydesign.IHasRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;

public class BlockAbsentFence extends FenceBlock  implements IBlockAbsent {

private String rawName;
  public BlockAbsentFence(Block.Properties p, String reg) {
    super(p);
    this.rawName = reg;
    this.setRegistryName(reg);
  }

  @Override
  public String rawName(){
    return rawName;
  }

}
