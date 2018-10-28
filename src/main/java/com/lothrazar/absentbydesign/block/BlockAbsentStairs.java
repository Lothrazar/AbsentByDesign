package com.lothrazar.absentbydesign.block;

import com.lothrazar.absentbydesign.IHasRecipe;
import com.lothrazar.absentbydesign.registry.RecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class BlockAbsentStairs extends BlockStairs implements IHasRecipe {

  private ItemStack ingredient;

  public BlockAbsentStairs(Block modelBlock, ItemStack ing) {
    super(modelBlock.getDefaultState());
    this.setHardness(modelBlock.getBlockHardness(modelBlock.getDefaultState(), null, null));
    this.setSoundType(modelBlock.getSoundType());
    ingredient = ing;
    this.useNeighborBrightness = true;
  }

  @Override
  public IRecipe addRecipe() {
    return RecipeRegistry.addShapedRecipe(new ItemStack(this, 4),
        "s  ",
        "ss ",
        "sss",
        's', ingredient);
  }
}
