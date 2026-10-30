package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface BloomeryRecipeSchema {
    RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<Long> TIME = IGRecipes.time(400);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, RESULT, INPUT, TIME)
            .constructor(RESULT, INPUT)
            .constructor(RESULT, INPUT, TIME);
}
