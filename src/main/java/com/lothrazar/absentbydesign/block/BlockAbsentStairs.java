package com.lothrazar.absentbydesign.block;

import com.lothrazar.absentbydesign.IHasRecipe;
import com.lothrazar.absentbydesign.registry.RecipeRegistry;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class BlockAbsentStairs extends BlockStairs implements IHasRecipe {

  private ItemStack ingredient;

  public BlockAbsentStairs(IBlockState modelState, ItemStack ing) {
    super(modelState);
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
