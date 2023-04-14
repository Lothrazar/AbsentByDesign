package com.lothrazar.absentbydesign.block;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class DoorAbsentBlock extends DoorBlock implements IBlockAbsent {

  public DoorAbsentBlock(Properties builder, BlockSetType type) {
    super(builder, type);
  }

  @Override
  public void setTransparent() {}
}
