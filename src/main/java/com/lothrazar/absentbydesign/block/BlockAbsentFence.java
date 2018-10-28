package com.lothrazar.absentbydesign.block;

import com.lothrazar.absentbydesign.IHasRecipe;
import com.lothrazar.absentbydesign.registry.RecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class BlockAbsentFence extends BlockFence implements IHasRecipe {

  private ItemStack ingredient;

  public BlockAbsentFence(Block main, ItemStack i) {
    super(main.getMaterial(main.getDefaultState()), main.getMaterial(main.getDefaultState()).getMaterialMapColor());
    this.setSoundType(main.getSoundType());
    this.setHardness(main.getBlockHardness(main.getDefaultState(), null, null));
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
