package com.lothrazar.absentbydesign.registry;

import java.util.ArrayList;
import java.util.List;
import com.lothrazar.absentbydesign.ModAbsentBD;
import com.lothrazar.absentbydesign.block.BlockAbsentFence;
import com.lothrazar.absentbydesign.block.BlockAbsentGate;
import com.lothrazar.absentbydesign.block.BlockAbsentSlab;
import com.lothrazar.absentbydesign.block.BlockAbsentStair;
import com.lothrazar.absentbydesign.block.BlockAbsentWall;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("deprecation")
public class AbsentRegistry {

  private static final List<Block> blocks = new ArrayList<>();
  @ObjectHolder(ModAbsentBD.MODID + ":fence_quartz")
  private static BlockAbsentFence FENCE_QUARTZ;
  public static ItemGroup itemGroup = new ItemGroup(ModAbsentBD.MODID) {

    @Override
    public ItemStack createIcon() {
      return new ItemStack(AbsentRegistry.FENCE_QUARTZ);
    }
  };

  public static List<Block> getBlocks() {
    return blocks;
  }
  //    Blocks.SPAWNER.getDefaultState().hardness = -1;

  public static BlockAbsentFence createFence(Block block, Material mat, String name) {
    BlockAbsentFence b = new BlockAbsentFence(Block.Properties.create(mat, mat.getColor())
        .sound(block.getSoundType(block.getDefaultState()))
        .hardnessAndResistance(block.getDefaultState().hardness),
        name);
    blocks.add(b);
    return b;
  }

  public static BlockAbsentWall createWall(Block block, Block.Properties p, String name) {
    BlockAbsentWall b = new BlockAbsentWall(p
        .sound(block.getSoundType(block.getDefaultState()))
        .hardnessAndResistance(block.getDefaultState().hardness),
        name);
    blocks.add(b);
    return b;
  }

  public static BlockAbsentWall createWall(Block block, Material mat, String name) {
    BlockAbsentWall b = new BlockAbsentWall(Block.Properties.create(mat, mat.getColor())
        .sound(block.getSoundType(block.getDefaultState()))
        .hardnessAndResistance(block.getDefaultState().hardness),
        name);
    blocks.add(b);
    return b;
  }

  public static BlockAbsentSlab createSlab(Block block, Material mat, String name) {
    BlockAbsentSlab b = new BlockAbsentSlab(Block.Properties.create(mat, mat.getColor())
        .sound(block.getSoundType(block.getDefaultState()))
        .hardnessAndResistance(block.getDefaultState().hardness),
        name);
    blocks.add(b);
    return b;
  }

  public static BlockAbsentSlab createSlab(String name, Block.Properties prop) {
    BlockAbsentSlab b = new BlockAbsentSlab(prop, name);
    blocks.add(b);
    return b;
  }

  public static BlockAbsentStair createStair(String name, Block block, Block.Properties prop) {
    BlockAbsentStair b = new BlockAbsentStair(block, prop, name);
    blocks.add(b);
    return b;
  }

  public static BlockAbsentStair createStair(Block block, Material mat, String name) {
    BlockAbsentStair b = new BlockAbsentStair(block, Block.Properties.create(mat, mat.getColor())
        .sound(block.getSoundType(block.getDefaultState()))
        .hardnessAndResistance(block.getDefaultState().hardness),
        name);
    blocks.add(b);
    return b;
  }

  public static BlockAbsentGate createGate(String name, Block block, Block.Properties p) {
    BlockAbsentGate b = new BlockAbsentGate(p
        .sound(block.getSoundType(block.getDefaultState()))
        .hardnessAndResistance(block.getDefaultState().hardness),
        name);
    blocks.add(b);
    return b;
  }
}
