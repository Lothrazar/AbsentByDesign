package com.lothrazar.absentbydesign.block;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class BlockAbsentStair extends StairsBlock implements IBlockAbsent {

private final String rawName;
  public BlockAbsentStair(Block b, Properties p, String reg) {
    super(b.getDefaultState(), p);
    rawName = reg;
    setRegistryName(reg);
  }

  @Override
  public String rawName(){
    return rawName;
  }

}
