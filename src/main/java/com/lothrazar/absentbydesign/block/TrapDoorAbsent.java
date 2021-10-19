package com.lothrazar.absentbydesign.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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

  public boolean doVisibility = false;

  @SuppressWarnings("deprecation")
  @Override
  @OnlyIn(Dist.CLIENT)
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
