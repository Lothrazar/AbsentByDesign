package com.lothrazar.absentbydesign.block;

import net.minecraft.world.level.block.DoorBlock;

public class DoorAbsentBlock extends DoorBlock implements IBlockAbsent {

  public DoorAbsentBlock(Properties builder) {
    super(builder);
  }

  @Override
  public void setTransparent() {}
}
