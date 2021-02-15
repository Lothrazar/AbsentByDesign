package com.lothrazar.absentbydesign.block;

import net.minecraft.block.TrapDoorBlock;

public class TrapDoorAbsent extends TrapDoorBlock implements IBlockAbsent {

  private final String rawName;

  public TrapDoorAbsent(Properties properties, String reg) {
    super(properties);
    rawName = reg;
    setRegistryName(reg);
  }

  @Override
  public String rawName() {
    return rawName;
  }
}
