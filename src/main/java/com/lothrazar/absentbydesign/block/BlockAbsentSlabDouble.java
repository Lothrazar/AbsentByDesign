package com.lothrazar.absentbydesign.block;

import net.minecraft.block.material.Material;

public class BlockAbsentSlabDouble extends BlockAbsentSlab {

  public BlockAbsentSlabDouble(Material materialIn) {
    super(materialIn);
  }

  @Override
  public boolean isDouble() {
    return true;
  }

}
