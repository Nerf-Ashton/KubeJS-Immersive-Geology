package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface GravitySeparatorRecipeSchema {
    RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<OutputItem> BYPRODUCT = ItemComponents.OUTPUT.key("byproduct").defaultOptional();
    RecipeKey<Float> BYPRODUCT_CHANCE = NumberComponent.FLOAT.key("byproduct_chance").optional(0.0f).alwaysWrite();
    RecipeKey<Integer> WATER = IGRecipes.water(250);
    RecipeKey<Long> TIME = IGRecipes.time(200);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, RESULT, INPUT, BYPRODUCT, BYPRODUCT_CHANCE, WATER, TIME)
            .constructor(RESULT, INPUT)
            .constructor(RESULT, INPUT, BYPRODUCT, BYPRODUCT_CHANCE)
            .constructor(RESULT, INPUT, BYPRODUCT, BYPRODUCT_CHANCE, WATER, TIME);
}
