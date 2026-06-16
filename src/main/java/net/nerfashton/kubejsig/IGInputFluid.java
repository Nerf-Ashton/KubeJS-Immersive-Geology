package net.nerfashton.kubejsig;

import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import dev.architectury.fluid.FluidStack;
import dev.latvian.mods.kubejs.fluid.BoundFluidStackJS;
import dev.latvian.mods.kubejs.fluid.FluidLike;
import dev.latvian.mods.kubejs.fluid.InputFluid;

public record IGInputFluid(FluidStack input) implements InputFluid {
    @Override
    public long kjs$getAmount() {
        return input.getAmount();
    }

    @Override
    public boolean kjs$isEmpty() {
        return input().isEmpty();
    }

    @Override
    public FluidLike kjs$copy(long amount) {
        FluidStack fs = this.input.copy();
        fs.setAmount(amount);
        return new BoundFluidStackJS(fs);
    }

    @Override
    public boolean matches(FluidLike other) {
        return InputFluid.super.matches(other);
    }
}
