package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface FoundryRecipeSchema {
    RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");
    RecipeKey<InputFluid> INPUT = FluidComponents.INPUT.key("input");
    RecipeKey<String> MOLD = StringComponent.NON_EMPTY.key("mold");
    RecipeKey<Integer> ENERGY = IGRecipes.energy(51200);
    RecipeKey<Long> TIME = IGRecipes.time(200);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, RESULT, INPUT, MOLD, ENERGY, TIME)
            .constructor(RESULT, INPUT, MOLD)
            .constructor(RESULT, INPUT, MOLD, ENERGY, TIME);
}
