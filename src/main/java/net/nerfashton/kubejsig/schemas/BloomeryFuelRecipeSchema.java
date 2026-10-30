package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface BloomeryFuelRecipeSchema {
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<Long> TIME = IGRecipes.time(1200);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, INPUT, TIME)
            .constructor(INPUT)
            .constructor(INPUT, TIME);
}
