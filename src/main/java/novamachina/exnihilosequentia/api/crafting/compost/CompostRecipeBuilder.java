package novamachina.exnihilosequentia.api.crafting.compost;

import net.minecraft.world.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.ItemLike;
import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;

public class CompostRecipeBuilder extends ExNihiloFinishedRecipe<CompostRecipeBuilder> {
    private CompostRecipeBuilder() {
        super(CompostRecipe.getStaticSerializer().get());
    }

    public static CompostRecipeBuilder builder() {
        return new CompostRecipeBuilder();
    }

    public CompostRecipeBuilder amount(int amount) {
        return addWriter(jsonObj -> jsonObj.addProperty("amount", amount));
    }

    public CompostRecipeBuilder input(Tag.Named<Item> tag) {
        return this.addInput(tag);
    }

    public CompostRecipeBuilder input(ItemLike input) {
        return this.addInput(input);
    }
}
