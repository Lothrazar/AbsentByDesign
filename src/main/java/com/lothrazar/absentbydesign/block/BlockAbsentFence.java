package com.lothrazar.absentbydesign.block;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;

public class BlockAbsentFence extends FenceBlock  implements IBlockAbsent {

private final String rawName;
  public BlockAbsentFence(Block.Properties p, String reg) {
    super(p);
    rawName = reg;
    setRegistryName(reg);
  }

  @Override
  public String rawName(){
    return rawName;
  }

}
