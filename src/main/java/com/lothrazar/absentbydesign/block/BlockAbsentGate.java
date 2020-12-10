package com.lothrazar.absentbydesign.block;

import net.minecraft.block.FenceGateBlock;

public class BlockAbsentGate extends FenceGateBlock implements IBlockAbsent {

  private String rawName;

  public BlockAbsentGate(Properties p, String reg) {
    super(p);
    this.rawName = reg;
    setRegistryName(reg);
  }

  @Override
  public String rawName() {
    return rawName;
  }
}
