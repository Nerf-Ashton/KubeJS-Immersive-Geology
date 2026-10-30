package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;

public interface GeothermalConversionRecipeSchema {
    RecipeKey<String> TRANSITION_BLOCK = StringComponent.NON_EMPTY.key("transitionBlock");
    RecipeKey<Integer> BLOCK_HEAT = NumberComponent.INT.key("blockHeat");
    RecipeKey<String> UPPER_BOUND_BLOCK = StringComponent.NON_EMPTY.key("upperBoundBlock").defaultOptional();
    RecipeKey<Integer> UPPER_HEAT = NumberComponent.INT.key("upperHeat").defaultOptional();
    RecipeKey<String> LOWER_BOUND_BLOCK = StringComponent.NON_EMPTY.key("lowerBoundBlock").defaultOptional();
    RecipeKey<Integer> LOWER_HEAT = NumberComponent.INT.key("lowerHeat").defaultOptional();

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, TRANSITION_BLOCK, BLOCK_HEAT, UPPER_BOUND_BLOCK, UPPER_HEAT, LOWER_BOUND_BLOCK, LOWER_HEAT)
            .constructor(TRANSITION_BLOCK, BLOCK_HEAT)
            .constructor(TRANSITION_BLOCK, BLOCK_HEAT, UPPER_BOUND_BLOCK, UPPER_HEAT)
            .constructor(TRANSITION_BLOCK, BLOCK_HEAT, UPPER_BOUND_BLOCK, UPPER_HEAT, LOWER_BOUND_BLOCK, LOWER_HEAT);
}
