package com.lothrazar.absentbydesign;

import com.lothrazar.absentbydesign.registry.AbsentRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ModAbsentBD.MODID)
public class ModAbsentBD {

  public static final String MODID = "absentbydesign";
  public static final Logger LOGGER = LogManager.getLogger();

  public ModAbsentBD() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(AbsentRegistry::setupClient);
    //    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    //    AbsentRegistry.ITEMS.register(eventBus);
    //    AbsentRegistry.BLOCKS.register(eventBus);
  }
}
