package net.nerfashton.kubejsig;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.recipe.RecipeJS;

public class IGRecipeJS extends RecipeJS {
    @Override
    public InputFluid readInputFluid(Object fluid) {
        if (fluid instanceof IGInputFluid in) {
            return in;
        }
        //return (InputFluid) fluid;
    }

}
