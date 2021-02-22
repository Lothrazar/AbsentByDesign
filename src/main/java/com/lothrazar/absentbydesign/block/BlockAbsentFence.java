package com.lothrazar.absentbydesign.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockAbsentFence extends FenceBlock implements IBlockAbsent {

  private final String rawName;

  public BlockAbsentFence(Block.Properties p, String reg) {
    super(p);
    rawName = reg;
    setRegistryName(reg);
  }

  @Override
  public String rawName() {
    return rawName;
  }

  public boolean doVisibility = false;

  @SuppressWarnings("deprecation")
  @Override
  @OnlyIn(Dist.CLIENT)
  public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
    if (doVisibility) {
      return adjacentBlockState.getBlock() == this || adjacentBlockState.isIn(this);
    }
    return super.isSideInvisible(state, adjacentBlockState, side); // seems to be always false
  }

  @Override
  public void setTransparent() {
    doVisibility = true;
  }
}
