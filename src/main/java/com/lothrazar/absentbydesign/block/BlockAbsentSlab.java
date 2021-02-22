package com.lothrazar.absentbydesign.block;

import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import java.util.Random;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockAbsentSlab extends SlabBlock implements IBlockAbsent {

  private final String rawName;
  // could be any particle. currently only used by Crying Obs
  public BasicParticleType part = null;

  public BlockAbsentSlab(Properties properties, String reg) {
    super(properties);
    rawName = reg;
    setRegistryName(reg);
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

  @OnlyIn(Dist.CLIENT)
  @Override
  public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    if (part != null
        && worldIn.rand.nextDouble() < 0.2) {
      AbsentRegistry.spawnBlockParticles(part, worldIn, pos, rand);
    }
  }

  @Override
  public String rawName() {
    return rawName;
  }
}
