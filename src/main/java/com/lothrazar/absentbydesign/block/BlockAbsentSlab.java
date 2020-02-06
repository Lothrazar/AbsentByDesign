package com.lothrazar.absentbydesign.block;

import net.minecraft.block.SlabBlock;

public class BlockAbsentSlab extends SlabBlock implements IBlockAbsent {

  private final String rawName;

  public BlockAbsentSlab(Properties properties, String reg) {
    super(properties);
    rawName = reg;
    setRegistryName(reg);
  }

  @Override
  public String rawName() {
    return rawName;
  }
}
