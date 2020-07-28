package com.novamachina.exnihilosequentia.common.tileentity.barrel;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.List;
import java.util.function.Supplier;

public class EmptyBarrelMode extends AbstractBarrelMode {
    public EmptyBarrelMode(String name) {
        super(name);
    }

    @Override
    public void tick(BarrelTile barrelTile) {
    }

    @Override
    public ActionResultType onBlockActivated(BarrelTile barrelTile, PlayerEntity player, Hand handIn, IFluidHandler fluidHandler, IItemHandler itemHandler) {
        if(!player.getHeldItem(handIn).isEmpty()) {
            ItemStack stack = player.getHeldItem(handIn);
            List<Supplier<AbstractBarrelMode>> modes = BarrelModeRegistry.getModes(BarrelModeRegistry.TriggerType.ITEM);
            for(Supplier<AbstractBarrelMode> mode : modes) {
                if(mode.get().isTriggerItem(stack)) {
                    barrelTile.setMode(mode.get());
                    barrelTile.getMode().onBlockActivated(barrelTile, player, handIn, fluidHandler, itemHandler);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean checkConditionsToSwitchToState(BarrelTile barrelTile) {
        return barrelTile.getTank().isEmpty() && barrelTile.getInventory().getStackInSlot(0).isEmpty() && barrelTile.getSolidAmount() == 0;
    }

    @Override
    public boolean canFillWithFluid(BarrelTile barrel) {
        return false;
    }

    @Override
    public boolean isEmptyMode() {
        return true;
    }

    @Override
    protected boolean isTriggerItem(ItemStack stack) {
        return false;
    }

    @Override
    public void read(CompoundNBT nbt) {

    }

    @Override
    public CompoundNBT write() {
        return new CompoundNBT();
    }
}
