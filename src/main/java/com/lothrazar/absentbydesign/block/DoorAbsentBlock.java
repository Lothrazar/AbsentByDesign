package com.lothrazar.absentbydesign.block;

import net.minecraft.block.DoorBlock;

public class DoorAbsentBlock extends DoorBlock implements IBlockAbsent {

  private final String rawName;

  public DoorAbsentBlock(Properties builder, String reg) {
    super(builder);
    rawName = reg;
    setRegistryName(reg);
  }

  @Override
  public String rawName() {
    return rawName;
  }

  @Override
  public void setTransparent() {}
}
