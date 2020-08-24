package com.novamachina.exnihilosequentia.common.jei;

import com.novamachina.exnihilosequentia.common.item.tools.crook.EnumCrook;
import com.novamachina.exnihilosequentia.common.item.tools.hammer.EnumHammer;
import com.novamachina.exnihilosequentia.common.jei.compost.CompostRecipe;
import com.novamachina.exnihilosequentia.common.jei.compost.CompostRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.crook.CrookRecipe;
import com.novamachina.exnihilosequentia.common.jei.crook.CrookRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.crucible.CrucibleRecipe;
import com.novamachina.exnihilosequentia.common.jei.crucible.CrucibleRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.fluiditem.FluidBlockJEIRecipe;
import com.novamachina.exnihilosequentia.common.jei.fluiditem.FluidBlockRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.fluidontop.FluidOnTopJEIRecipe;
import com.novamachina.exnihilosequentia.common.jei.fluidontop.FluidOnTopRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.fluidtransform.FluidTransformCategory;
import com.novamachina.exnihilosequentia.common.jei.fluidtransform.FluidTransformJEIRecipe;
import com.novamachina.exnihilosequentia.common.jei.hammer.HammerRecipe;
import com.novamachina.exnihilosequentia.common.jei.hammer.HammerRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.heat.HeatRecipe;
import com.novamachina.exnihilosequentia.common.jei.heat.HeatRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.sieve.SieveRecipe;
import com.novamachina.exnihilosequentia.common.jei.sieve.dry.DrySieveRecipeCategory;
import com.novamachina.exnihilosequentia.common.jei.sieve.wet.WetSieveRecipeCategory;
import com.novamachina.exnihilosequentia.common.setup.ModBlocks;
import com.novamachina.exnihilosequentia.common.setup.ModItems;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Constants.ModIds.JEI, Constants.ModIds.EX_NIHILO_SEQUENTIA);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new CrookRecipeCategory(guiHelper));
        registration.addRecipeCategories(new DrySieveRecipeCategory(guiHelper));
        registration.addRecipeCategories(new WetSieveRecipeCategory(guiHelper));
        registration.addRecipeCategories(new HammerRecipeCategory(guiHelper));
        registration.addRecipeCategories(new FluidOnTopRecipeCategory(guiHelper));
        registration.addRecipeCategories(new FluidTransformCategory(guiHelper));
        registration.addRecipeCategories(new FluidBlockRecipeCategory(guiHelper));
        registration.addRecipeCategories(new CompostRecipeCategory(guiHelper));
        registration.addRecipeCategories(new CrucibleRecipeCategory(guiHelper, "crucible_wood"));
        registration.addRecipeCategories(new CrucibleRecipeCategory(guiHelper, "crucible_fired"));
        registration.addRecipeCategories(new HeatRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registerCrook(registration);
        registerSieve(registration);
        registerHammer(registration);
        registerFluidOnTop(registration);
        registerFluidTransform(registration);
        registerFluidBlock(registration);
        registerCompost(registration);
        registerFiredCrucible(registration);
        registerWoodCrucible(registration);
        registerHeat(registration);
    }

    private void registerHeat(IRecipeRegistration registration) {
        List<HeatRecipe> recipes = ModRegistries.HEAT.getRecipeList();
        registration.addRecipes(recipes, HeatRecipeCategory.UID);
        LogUtil.info("JEI: Heat Recipes Loaded: " + recipes.size());
    }

    private void registerWoodCrucible(IRecipeRegistration registration) {
        List<CrucibleRecipe> recipes = ModRegistries.CRUCIBLE.getWoodRecipeList();
        registration.addRecipes(recipes, new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "crucible_wood"));
        LogUtil.info("JEI: Wooden Crucible Recipes Loaded: " + recipes.size());
    }

    private void registerFiredCrucible(IRecipeRegistration registration) {
        List<CrucibleRecipe> recipes = ModRegistries.CRUCIBLE.getFiredRecipeList();
        registration.addRecipes(recipes, new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "crucible_fired"));
        LogUtil.info("JEI: Fired Crucible Recipes Loaded: " + recipes.size());
    }

    private void registerCompost(IRecipeRegistration registration) {
        List<CompostRecipe> recipes = ModRegistries.COMPOST.getRecipeList();
        registration.addRecipes(recipes, CompostRecipeCategory.UID);
        LogUtil.info("JEI: Compost Recipes Loaded: " + recipes.size());
    }

    private void registerFluidBlock(IRecipeRegistration registration) {
        List<FluidBlockJEIRecipe> recipes = ModRegistries.FLUID_BLOCK.getRecipeList();
        registration.addRecipes(recipes, FluidBlockRecipeCategory.UID);
        LogUtil.info("JEI: Fluid Item Recipes Loaded: " + recipes.size());
    }

    private void registerFluidTransform(IRecipeRegistration registration) {
        List<FluidTransformJEIRecipe> recipes = ModRegistries.FLUID_TRANSFORM.getRecipeList();
        registration.addRecipes(recipes, FluidTransformCategory.UID);
        LogUtil.info("JEI: Fluid Transform Recipes Loaded: " + recipes.size());
    }

    private void registerFluidOnTop(IRecipeRegistration registration) {
        List<FluidOnTopJEIRecipe> recipes = ModRegistries.FLUID_ON_TOP.getRecipeList();
        registration.addRecipes(recipes, FluidOnTopRecipeCategory.UID);
        LogUtil.info("JEI: Fluid On Top Recipes Loaded: " + recipes.size());
    }

    private void registerHammer(IRecipeRegistration registration) {
        List<HammerRecipe> recipes = ModRegistries.HAMMER.getRecipeList();
        registration.addRecipes(recipes, HammerRecipeCategory.UID);
        LogUtil.info("JEI: Hammer Recipes Loaded: " + recipes.size());
    }

    private void registerSieve(IRecipeRegistration registration) {
        List<SieveRecipe> drySieveRecipes = ModRegistries.SIEVE.getDryRecipeList();
        List<SieveRecipe> wetSieveRecipes = ModRegistries.SIEVE.getWetRecipeList();
        registration.addRecipes(drySieveRecipes, DrySieveRecipeCategory.UID);
        registration.addRecipes(wetSieveRecipes, WetSieveRecipeCategory.UID);
        LogUtil.info("JEI: Sieve Recipes Loaded: " + (drySieveRecipes.size() + wetSieveRecipes.size()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        for(EnumCrook crook : EnumCrook.values()) {
            registration.addRecipeCatalyst(new ItemStack(ModItems.crookMap.get(crook.name).get()), CrookRecipeCategory.UID);
        }
        for(EnumHammer hammer : EnumHammer.values()) {
            registration.addRecipeCatalyst(new ItemStack(ModItems.hammerMap.get(hammer.name).get()), HammerRecipeCategory.UID);
        }

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIEVE.get()), DrySieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.SIEVE.get()), WetSieveRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BARREL_WOOD.get()), FluidOnTopRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BARREL_WOOD.get()), FluidTransformCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BARREL_WOOD.get()), FluidBlockRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BARREL_WOOD.get()), CompostRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUCIBLE_FIRED.get()), new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "crucible_fired"));
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUCIBLE_WOOD.get()), new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "crucible_wood"));
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUCIBLE_FIRED.get()), HeatRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUCIBLE_WOOD.get()), HeatRecipeCategory.UID);
    }

    private void registerCrook(IRecipeRegistration registration) {
        List<CrookRecipe> crookRecipes = ModRegistries.CROOK.getRecipeList();
        registration.addRecipes(crookRecipes, CrookRecipeCategory.UID);
        LogUtil.info("JEI: Crook Recipes Loaded: " + crookRecipes.size());
    }
}
