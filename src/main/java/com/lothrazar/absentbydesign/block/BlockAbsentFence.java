package com.lothrazar.absentbydesign.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.core.Direction;

public class BlockAbsentFence extends FenceBlock implements IBlockAbsent {

  public BlockAbsentFence(Block.Properties p) {
    super(p);
  }

  public boolean doVisibility = false;

  @SuppressWarnings("deprecation")
  @Override
  public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
    if (doVisibility) {
      return adjacentBlockState.getBlock() == this || adjacentBlockState.is(this);
    }
    return super.skipRendering(state, adjacentBlockState, side); // seems to be always false
  }

  @Override
  public void setTransparent() {
    doVisibility = true;
  }
}
