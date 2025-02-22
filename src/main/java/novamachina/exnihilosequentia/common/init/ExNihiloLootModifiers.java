package novamachina.exnihilosequentia.common.init;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegistryObject;
import novamachina.exnihilosequentia.common.loot.modifier.UseCrookModifier;
import novamachina.exnihilosequentia.common.loot.modifier.UseHammerModifier;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;

public class ExNihiloLootModifiers {
  private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
      DeferredRegister.create(
          Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA);

  public static final RegistryObject<Codec<? extends UseHammerModifier>> hammerModifier =
      LOOT_MODIFIERS.register("use_hammer", UseHammerModifier.CODEC);
  public static final RegistryObject<Codec<? extends UseCrookModifier>> crookModifier =
      LOOT_MODIFIERS.register("use_crook", UseCrookModifier.CODEC);

  public static void init(IEventBus modEventBus) {
    LOOT_MODIFIERS.register(modEventBus);
  }
}
