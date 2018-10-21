package com.lothrazar.absentbydesign.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockAbsentStairs extends BlockStairs {

  public BlockAbsentStairs(IBlockState modelState) {
    super(modelState);
    this.useNeighborBrightness = true;
  }
}
