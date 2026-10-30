package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface FoundryAlloyRecipeSchema {
    RecipeKey<OutputFluid> RESULT = FluidComponents.OUTPUT.key("result");
    RecipeKey<InputFluid[]> INPUTS = FluidComponents.INPUT.asArray().key("inputs");
    RecipeKey<Integer> ENERGY = IGRecipes.energy(16);
    RecipeKey<Long> TIME = IGRecipes.time(60);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, RESULT, INPUTS, ENERGY, TIME)
            .constructor(RESULT, INPUTS)
            .constructor(RESULT, INPUTS, TIME)
            .constructor(RESULT, INPUTS, ENERGY, TIME);
}
