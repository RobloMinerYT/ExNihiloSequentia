package novamachina.exnihilosequentia.common.crafting.compost;

import javax.annotation.Nonnull;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.common.crafting.ExNihiloFinishedRecipe;
import novamachina.exnihilosequentia.common.init.ExNihiloSerializers;

public class CompostRecipeBuilder extends ExNihiloFinishedRecipe<CompostRecipeBuilder> {

  private CompostRecipeBuilder() {
    super(ExNihiloSerializers.COMPOST_RECIPE_SERIALIZER.get());
  }

  @Nonnull
  public static CompostRecipeBuilder builder() {
    return new CompostRecipeBuilder();
  }

  @Nonnull
  public CompostRecipeBuilder amount(final int amount) {
    return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
  }

  @Nonnull
  public CompostRecipeBuilder input(@Nonnull final TagKey<Item> tag) {
    return this.addInput(tag);
  }

  @Nonnull
  public CompostRecipeBuilder input(@Nonnull final ItemLike input) {
    return this.addInput(input);
  }
}
