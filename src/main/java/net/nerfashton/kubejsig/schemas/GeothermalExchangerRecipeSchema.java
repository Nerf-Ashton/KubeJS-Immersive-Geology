package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface GeothermalExchangerRecipeSchema {
    RecipeKey<OutputFluid> FLUID_RESULT = FluidComponents.OUTPUT.key("fluidResult");
    RecipeKey<InputFluid> INPUT = FluidComponents.INPUT.key("input");
    RecipeKey<Integer> ENERGY = IGRecipes.energy(2560);
    RecipeKey<Long> TIME = IGRecipes.time(20);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, FLUID_RESULT, INPUT, ENERGY, TIME)
            .constructor(FLUID_RESULT, INPUT)
            .constructor(FLUID_RESULT, INPUT, ENERGY, TIME);
}
