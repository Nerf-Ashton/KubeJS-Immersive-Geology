package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;

public interface CoreDrillRecipeSchema {
    RecipeKey<OutputFluid> FLUID_RESULT = FluidComponents.OUTPUT.key("fluidResult");
    RecipeKey<InputFluid> INPUT = FluidComponents.INPUT.key("input");

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, FLUID_RESULT, INPUT)
            .constructor(FLUID_RESULT, INPUT);
}
