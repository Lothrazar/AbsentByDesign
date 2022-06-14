package com.lothrazar.absentbydesign.block;

import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
public class BlockAbsentStair extends StairBlock implements IBlockAbsent {

  // could be any particle. currently only used by Crying Obs
  public SimpleParticleType part = null;

  public BlockAbsentStair(Block b, Properties p) {
    super(b.defaultBlockState(), p);
  }

  public boolean doVisibility = false;

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

  @OnlyIn(Dist.CLIENT)
  @Override
  public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
    if (part != null
        && worldIn.random.nextDouble() < 0.2) {
      AbsentRegistry.spawnBlockParticles(part, worldIn, pos, rand);
    }
  }
}
