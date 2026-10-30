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

public interface CentrifugeRecipeSchema {
    RecipeKey<OutputItem> ITEM_OUTPUT = ItemComponents.OUTPUT.key("item_output");
    RecipeKey<InputFluid> FLUID_INPUT = FluidComponents.INPUT.key("fluid_input");
    RecipeKey<OutputFluid> PRIMARY_FLUID_OUT = FluidComponents.OUTPUT.key("primary_fluid_out");
    RecipeKey<OutputFluid> SECONDARY_FLUID_OUT = IGRecipes.OPTIONAL_OUTPUT_FLUID.key("secondary_fluid_out").optional(EmptyFluidStackJS.INSTANCE).allowEmpty().alwaysWrite();
    RecipeKey<Integer> ENERGY = IGRecipes.energy(614400);
    RecipeKey<Long> TIME = IGRecipes.time(1200);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, ITEM_OUTPUT, FLUID_INPUT, PRIMARY_FLUID_OUT, SECONDARY_FLUID_OUT, ENERGY, TIME)
            .constructor(ITEM_OUTPUT, FLUID_INPUT, PRIMARY_FLUID_OUT)
            .constructor(ITEM_OUTPUT, FLUID_INPUT, PRIMARY_FLUID_OUT, SECONDARY_FLUID_OUT)
            .constructor(ITEM_OUTPUT, FLUID_INPUT, PRIMARY_FLUID_OUT, ENERGY, TIME)
            .constructor(ITEM_OUTPUT, FLUID_INPUT, PRIMARY_FLUID_OUT, SECONDARY_FLUID_OUT, ENERGY, TIME);
}
