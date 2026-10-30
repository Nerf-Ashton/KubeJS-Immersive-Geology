package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface GeothermalBiomeRecipeSchema {
    RecipeKey<Object> BIOME = IGRecipes.BIOME_FILTER.key("biome");
    RecipeKey<Integer> MIN_HEAT = NumberComponent.INT.key("min_heat");
    RecipeKey<Integer> MAX_HEAT = NumberComponent.INT.key("max_heat");

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, BIOME, MIN_HEAT, MAX_HEAT);
}
