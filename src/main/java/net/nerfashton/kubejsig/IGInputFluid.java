package net.nerfashton.kubejsig;

import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import dev.latvian.mods.kubejs.fluid.FluidLike;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.ingredient.TagContext;

public record IGInputFluid(FluidTagInput input) implements InputFluid {
    @Override
    public long kjs$getAmount() {
        return input.getAmount();
    }

    @Override
    public boolean kjs$isEmpty() {
        return TagContext.INSTANCE.getValue().areTagsBound() ? input.getMatchingFluidStacks().isEmpty() : false;
    }

    @Override
    public FluidLike kjs$copy(long amount) {
        return new IGInputFluid(input.withAmount((int) amount));
    }

    @Override
    public boolean matches(FluidLike other) {
        return false;
    }
}
