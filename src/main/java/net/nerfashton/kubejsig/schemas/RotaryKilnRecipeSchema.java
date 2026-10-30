package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface RotaryKilnRecipeSchema {
    RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<Integer> HEAT = IGRecipes.heat(100);
    RecipeKey<Long> TIME = IGRecipes.time(200);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, RESULT, INPUT, HEAT, TIME)
            .constructor(RESULT, INPUT)
            .constructor(RESULT, INPUT, HEAT)
            .constructor(RESULT, INPUT, HEAT, TIME);
}
