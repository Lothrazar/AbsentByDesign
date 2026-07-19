package com.lothrazar.absentbydesign.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class BlockAbsentGate extends FenceGateBlock implements IBlockAbsent {

  public BlockAbsentGate(Properties p, WoodType type) {
    super(type, p);
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
