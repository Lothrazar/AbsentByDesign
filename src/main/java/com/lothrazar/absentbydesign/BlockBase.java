package com.lothrazar.absentbydesign;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

	public BlockBase(Material materialIn) {
		super(materialIn);
		this.setHardness(2.0F).setResistance(2.0F);
	}

}
