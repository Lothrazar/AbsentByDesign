package com.lothrazar.absentbydesign.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAbsentLayer extends BlockSnow {

  private Item itemDropped;

  public BlockAbsentLayer(Material m, Item itemDropped) {
    super();
    this.itemDropped = itemDropped;
    //    this.blockMaterial = m; 
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return itemDropped;
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    IBlockState iblockstate = worldIn.getBlockState(pos.down());
    Block block = iblockstate.getBlock();
    if (block != Blocks.BARRIER) {
      BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP);
      return blockfaceshape == BlockFaceShape.SOLID || iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.down()) || block == this && iblockstate.getValue(LAYERS).intValue() == 8;
    }
    else {
      return false;
    }
  }
}
