package com.lothrazar.absentbydesign.block;

import com.lothrazar.absentbydesign.IHasRecipe;
import com.lothrazar.absentbydesign.registry.RecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class BlockAbsentSlabHalf extends BlockAbsentSlab implements IHasRecipe {

  private ItemStack ingredient;

  public BlockAbsentSlabHalf(Block modelBlock, ItemStack i) {
    super(modelBlock);
    ingredient = i;
  }

  @Override
  public boolean isDouble() {
    return false;
  }

  @Override
  public IRecipe addRecipe() {

    return RecipeRegistry.addShapedRecipe(new ItemStack(this, 6),
        "sss",
        's', ingredient);
  }

}
