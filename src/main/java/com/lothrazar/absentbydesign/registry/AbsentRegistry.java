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

  public static final List<Block> blocks = new ArrayList<>();
  @ObjectHolder(ModAbsentBD.MODID + ":fence_quartz")
  private static BlockAbsentFence FENCE_QUARTZ;
  public static ItemGroup itemGroup = new ItemGroup(ModAbsentBD.MODID) {

    @Override
    public ItemStack createIcon() {
      return new ItemStack(AbsentRegistry.FENCE_QUARTZ);
    }
  };
  static final String ID = ModAbsentBD.MODID + ":";
  //
  @ObjectHolder(ID + "stairs_glass_purple")
  public static Block stairs_glass_purple;
  @ObjectHolder(ID + "stairs_glass_blue")
  public static Block stairs_glass_blue;
  @ObjectHolder(ID + "stairs_glass_brown")
  public static Block stairs_glass_brown;
  @ObjectHolder(ID + "stairs_glass_red")
  public static Block stairs_glass_red;
  @ObjectHolder(ID + "stairs_glass_black")
  public static Block stairs_glass_black;
  @ObjectHolder(ID + "stairs_glass_cyan")
  public static Block stairs_glass_cyan;
  @ObjectHolder(ID + "stairs_glass_light_gray")
  public static Block stairs_glass_light_gray;
  @ObjectHolder(ID + "stairs_glass_gray")
  public static Block stairs_glass_gray;
  @ObjectHolder(ID + "stairs_glass_pink")
  public static Block stairs_glass_pink;
  @ObjectHolder(ID + "stairs_glass_lime")
  public static Block stairs_glass_lime;
  @ObjectHolder(ID + "stairs_glass_light_blue")
  public static Block stairs_glass_light_blue;
  @ObjectHolder(ID + "stairs_glass_magenta")
  public static Block stairs_glass_magenta;
  @ObjectHolder(ID + "stairs_glass_white")
  public static Block stairs_glass_white;
  @ObjectHolder(ID + "stairs_glass_green")
  public static Block stairs_glass_green;
  @ObjectHolder(ID + "stairs_glass_yellow")
  public static Block stairs_glass_yellow;
  @ObjectHolder(ID + "stairs_glass_orange")
  public static Block stairs_glass_orange;
  @ObjectHolder(ID + "stairs_glass")
  public static Block stairs_glass;
  @ObjectHolder(ID + "slab_glass")
  public static Block slab_glass;
  @ObjectHolder(ID + "slab_glass_white")
  public static Block slab_glass_white;
  @ObjectHolder(ID + "slab_glass_orange")
  public static Block slab_glass_orange;
  @ObjectHolder(ID + "slab_glass_magenta")
  public static Block slab_glass_magenta;
  @ObjectHolder(ID + "slab_glass_light_blue")
  public static Block slab_glass_light_blue;
  @ObjectHolder(ID + "slab_glass_yellow")
  public static Block slab_glass_yellow;
  @ObjectHolder(ID + "slab_glass_lime")
  public static Block slab_glass_lime;
  @ObjectHolder(ID + "slab_glass_pink")
  public static Block slab_glass_pink;
  @ObjectHolder(ID + "slab_glass_gray")
  public static Block slab_glass_gray;
  @ObjectHolder(ID + "slab_glass_light_gray")
  public static Block slab_glass_light_gray;
  @ObjectHolder(ID + "slab_glass_cyan")
  public static Block slab_glass_cyan;
  @ObjectHolder(ID + "slab_glass_purple")
  public static Block slab_glass_purple;
  @ObjectHolder(ID + "slab_glass_blue")
  public static Block slab_glass_blue;
  @ObjectHolder(ID + "slab_glass_brown")
  public static Block slab_glass_brown;
  @ObjectHolder(ID + "slab_glass_green")
  public static Block slab_glass_green;
  @ObjectHolder(ID + "slab_glass_red")
  public static Block slab_glass_red;
  @ObjectHolder(ID + "slab_glass_black")
  public static Block slab_glass_black;
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
