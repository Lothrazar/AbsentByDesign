package com.lothrazar.absentbydesign.block;

import net.minecraft.block.Block;

public class BlockAbsentSlabDouble extends BlockAbsentSlab {

  public BlockAbsentSlabDouble(Block modelBlock) {
    super(modelBlock);
  }

  @Override
  public boolean isDouble() {
    return true;
  }

}
