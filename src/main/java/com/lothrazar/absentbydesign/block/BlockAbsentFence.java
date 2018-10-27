package com.lothrazar.absentbydesign.block;

import com.lothrazar.absentbydesign.IHasRecipe;
import com.lothrazar.absentbydesign.registry.RecipeRegistry;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class BlockAbsentFence extends BlockFence implements IHasRecipe {

  private ItemStack ingredient;

  public BlockAbsentFence(Material materialIn, ItemStack i) {
    super(materialIn, materialIn.getMaterialMapColor());
    ingredient = i;
  }

  @Override
  public IRecipe addRecipe() {
    return RecipeRegistry.addShapedRecipe(new ItemStack(this, 3),
        "sts",
        "sts",
        's', ingredient,
        't', Items.STICK);
  }
}
