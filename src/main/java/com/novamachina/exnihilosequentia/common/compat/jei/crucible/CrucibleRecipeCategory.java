package com.novamachina.exnihilosequentia.common.compat.jei.crucible;

import com.novamachina.exnihilosequentia.common.api.crafting.crucible.CrucibleRecipe;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CrucibleRecipeCategory implements IRecipeCategory<CrucibleRecipe> {
    private static final ResourceLocation texture = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");
    public final ResourceLocation UID;
    private final IDrawableStatic background;
    private final IDrawableStatic slotHighlight;

    public CrucibleRecipeCategory(IGuiHelper guiHelper, String uid) {
        this.background = guiHelper.createDrawable(texture, 0, 168, 166, 58);
        this.slotHighlight = guiHelper.createDrawable(texture, 166, 0, 18, 18);
        this.UID = new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, uid);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends CrucibleRecipe> getRecipeClass() {
        return CrucibleRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Crucible";
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void setIngredients(CrucibleRecipe recipe, IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, recipe.getInputs());
        ingredients.setOutput(VanillaTypes.FLUID, recipe.getResultFluid());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CrucibleRecipe recipe, IIngredients ingredients) {
        recipeLayout.getFluidStacks().init(0, true, 3, 21);
        recipeLayout.getFluidStacks().set(0, recipe.getResultFluid());

        IFocus<?> focus = recipeLayout.getFocus();

        int slotIndex = 1;
        for (int i = 0; i < recipe.getInputs().size(); i++) {
            final int slotX = 38 + (i % 7 * 18);
            final int slotY = 2 + i / 7 * 18;

            ItemStack stack = recipe.getInputs().get(i);

            recipeLayout.getItemStacks().init(i + slotIndex, true, slotX, slotY);
            recipeLayout.getItemStacks().set(i + slotIndex, stack);

            if (focus != null) {
                if (focus.getValue() instanceof ItemStack) {
                    if (stack.isItemEqual((ItemStack) focus.getValue())) {
                        recipeLayout.getItemStacks().setBackground(i + slotIndex, slotHighlight);
                    }
                }
            }
        }

        if (UID.equals(new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "crucible_fired"))) {
            recipeLayout.getItemStacks().addTooltipCallback(new FiredCrucibleTooltipCallback());
        } else {
            recipeLayout.getItemStacks().addTooltipCallback(new WoodCrucibleTooltipCallback());
        }
    }
}
