package novamachina.exnihilosequentia;

import javax.annotation.Nonnull;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import novamachina.exnihilosequentia.client.setup.ClientSetup;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

@Mod(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA)
public class ExNihiloSequentia {

  @Nonnull private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

  public ExNihiloSequentia() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    Config.loadConfig(
        Config.COMMON_CONFIG,
        FMLPaths.CONFIGDIR
            .get()
            .resolve(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA + "-common.toml"));

    logger.debug("Starting Ex Nihilo: Sequentia");

    ExNihiloInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());
    FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    FMLJavaModLoadingContext.get()
        .getModEventBus()
        .addListener(ExNihiloInitialization::setupNonTagBasedRegistries);
    FMLJavaModLoadingContext.get()
        .getModEventBus()
        .addListener(ExNihiloInitialization::registerTOP);
  }

  @EventBusSubscriber(modid = ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, bus = Bus.MOD)
  public static class EventHandlers {

    private EventHandlers() {}

    //    @SubscribeEvent
    //    public static void registerModifierSerializers(
    //        @Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
    //      logger.debug("Registering Loot Modifiers");
    //      event.getRegistry()
    //          .register(new UseHammerModifier.Serializer()
    //              .setRegistryName(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "use_hammer"));
    //      event.getRegistry()
    //          .register(new UseCrookModifier.Serializer()
    //              .setRegistryName(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "use_crook"));
    //    }
  }
}
