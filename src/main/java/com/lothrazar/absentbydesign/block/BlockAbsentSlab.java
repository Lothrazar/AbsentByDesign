package com.lothrazar.absentbydesign.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public abstract class BlockAbsentSlab extends BlockSlab {

  private static final PropertyBool VARIANT_PROPERTY = PropertyBool.create("variant");
  public BlockAbsentSlab(Material materialIn) {
    super(materialIn);
    this.useNeighborBrightness = true;
  }

  @Override
  public String getUnlocalizedName(int meta) {
    return this.getUnlocalizedName();
  }


  @Override
  public IProperty<?> getVariantProperty() {
    return VARIANT_PROPERTY;
  }

  @Override
  public Comparable<?> getTypeForItem(ItemStack stack) {
    return false;
  }

  @Override
  public final IBlockState getStateFromMeta(final int meta) {
    IBlockState state = this.getDefaultState();
    state = state.withProperty(VARIANT_PROPERTY, false);
    if (!this.isDouble()) {
      EnumBlockHalf value = EnumBlockHalf.BOTTOM;
      if ((meta & 8) != 0) {
        value = EnumBlockHalf.TOP;
      }
      state = state.withProperty(HALF, value);
    }
    return state;
  }

  @Override
  public final int damageDropped(final IBlockState state) {
    return 0;
  }

  @Override
  public final int getMetaFromState(final IBlockState state) {
    if (this.isDouble()) {
      return 0;
    }
    if (state.getValue(HALF) == EnumBlockHalf.TOP) {
      return 8;
    }
    else {
      return 0;
    }
  }

  @Override
  protected final BlockStateContainer createBlockState() {
    if (this.isDouble()) {
      return new BlockStateContainer(this, VARIANT_PROPERTY);
    }
    else {
      return new BlockStateContainer(this, VARIANT_PROPERTY, HALF);
    }
  }
}
