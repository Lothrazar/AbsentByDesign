package com.lothrazar.absentbydesign;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModAbsentBD.MODID)
public class ModAbsentBD {

  public static final String MODID = "absentbydesign";
  public static final Logger LOGGER = LogManager.getLogger();

  public ModAbsentBD() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setupClient(final FMLClientSetupEvent event) {
    //
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass, RenderType.getCutout());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_white, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_orange, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_magenta, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_light_blue, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_yellow, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_lime, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_pink, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_gray, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_light_gray, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_cyan, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_purple, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_blue, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_brown, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_green, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_red, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.wall_glass_black, RenderType.getTranslucent());
    //
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass, RenderType.getCutout());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_white, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_orange, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_magenta, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_light_blue, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_yellow, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_lime, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_pink, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_gray, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_light_gray, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_cyan, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_purple, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_blue, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_brown, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_green, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_red, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.stairs_glass_black, RenderType.getTranslucent());
    //
    //
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass, RenderType.getCutout());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_white, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_orange, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_magenta, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_light_blue, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_yellow, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_lime, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_pink, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_gray, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_light_gray, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_cyan, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_purple, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_blue, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_brown, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_green, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_red, RenderType.getTranslucent());
    RenderTypeLookup.setRenderLayer(AbsentRegistry.slab_glass_black, RenderType.getTranslucent());
  }

  public static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
    return false;
  }

  public static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
    return false;
  }
}
