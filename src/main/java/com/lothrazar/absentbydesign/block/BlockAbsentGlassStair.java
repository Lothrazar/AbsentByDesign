package com.lothrazar.absentbydesign.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockAbsentGlassStair extends BlockAbsentStair {

  public BlockAbsentGlassStair(Block b, Properties p, String reg) {
    super(b, p, reg);
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
    return adjacentBlockState.getBlock() == this;//cant do ispassable here, no world
  }
}
