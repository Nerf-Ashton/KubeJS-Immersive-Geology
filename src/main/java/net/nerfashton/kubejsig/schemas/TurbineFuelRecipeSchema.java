package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;

public interface TurbineFuelRecipeSchema {
    RecipeKey<String> FLUID_TAG = StringComponent.NON_EMPTY.key("fluidTag");
    RecipeKey<Integer> BURN_TIME = NumberComponent.INT.key("burnTime");
    RecipeKey<Integer> CONSUME_AMOUNT = NumberComponent.INT.key("consume_amount");
    RecipeKey<Float> OUTPUT_RATIO = NumberComponent.FLOAT.key("outputRatio");

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, FLUID_TAG, BURN_TIME, CONSUME_AMOUNT, OUTPUT_RATIO)
            .constructor(FLUID_TAG, BURN_TIME, CONSUME_AMOUNT, OUTPUT_RATIO);
}
