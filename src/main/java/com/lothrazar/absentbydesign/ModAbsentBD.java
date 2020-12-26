package com.lothrazar.absentbydesign;

import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModAbsentBD.MODID)
public class ModAbsentBD {

  public static final String MODID = "absentbydesign";
  //public static final Logger LOGGER = LogManager.getLogger();

  public ModAbsentBD() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(AbsentRegistry::setupClient);
  }
}
