package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface IndustrialSluiceRecipeSchema {
    RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<OutputItem[]> BYPRODUCTS = IGRecipes.SECONDARY_OUTPUT.asArray().key("byproducts").defaultOptional();
    RecipeKey<Integer> WATER = IGRecipes.water(500);
    RecipeKey<Integer> ENERGY = IGRecipes.energy(2000);
    RecipeKey<Long> TIME = IGRecipes.time(100);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, RESULT, INPUT, BYPRODUCTS, WATER, ENERGY, TIME)
            .constructor(RESULT, INPUT)
            .constructor(RESULT, INPUT, BYPRODUCTS)
            .constructor(RESULT, INPUT, BYPRODUCTS, WATER, ENERGY, TIME);
}
