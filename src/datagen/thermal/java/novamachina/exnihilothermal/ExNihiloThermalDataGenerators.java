package novamachina.exnihilothermal;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import novamachina.exnihilothermal.client.ExNihiloThermalItemGenerator;
import novamachina.exnihilothermal.client.ExNihiloThermalLangGenerator;
import novamachina.exnihilothermal.common.ExNihiloThermalRecipeGenerator;
import novamachina.exnihilothermal.common.ExNihiloThermalTagGenerator;
import novamachina.exnihilothermal.common.utility.ExNihiloThermalConstants;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExNihiloThermalDataGenerators {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    if (event.includeServer()) {
      generator.addProvider(true, new ExNihiloThermalRecipeGenerator(generator));
      generator.addProvider(
          true,
          new ExNihiloThermalTagGenerator(
              generator,
              new BlockTagsProvider(
                  generator,
                  ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
                  event.getExistingFileHelper()),
              event.getExistingFileHelper()));
    }
    if (event.includeClient()) {
      // Items
      generator.addProvider(
          true,
          new ExNihiloThermalItemGenerator(
              generator,
              ExNihiloThermalConstants.ModIds.EX_NIHILO_THERMAL,
              event.getExistingFileHelper()));
      // Lang
      generator.addProvider(true, new ExNihiloThermalLangGenerator(generator, "en_us"));
    }
  }
}
