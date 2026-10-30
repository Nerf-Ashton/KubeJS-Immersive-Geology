package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.fluid.EmptyFluidStackJS;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface CrystallizerRecipeSchema {
    RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");
    RecipeKey<InputFluid> INPUT = FluidComponents.INPUT.key("input");
    RecipeKey<OutputFluid> FLUID_RESULT = IGRecipes.OPTIONAL_OUTPUT_FLUID.key("fluidResult").optional(EmptyFluidStackJS.INSTANCE).alwaysWrite();
    RecipeKey<Integer> ENERGY = IGRecipes.energy(38400);
    RecipeKey<Long> TIME = IGRecipes.time(300);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, RESULT, INPUT, FLUID_RESULT, ENERGY, TIME)
            .constructor(RESULT, INPUT, FLUID_RESULT)
            .constructor(RESULT, INPUT, FLUID_RESULT, ENERGY, TIME);
}
