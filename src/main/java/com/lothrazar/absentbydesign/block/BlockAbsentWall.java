package com.lothrazar.absentbydesign.block;
import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;

public class BlockAbsentWall extends WallBlock implements IBlockAbsent {

private final String rawName;
  public BlockAbsentWall(Block.Properties p, String reg) {
    super(p);
    rawName = reg;
    setRegistryName(reg);
  }

  @Override
  public String rawName(){
    return rawName;
  }

}
