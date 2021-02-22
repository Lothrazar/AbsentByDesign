package com.lothrazar.absentbydesign.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.util.Direction;
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
