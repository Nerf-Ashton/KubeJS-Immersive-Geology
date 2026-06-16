package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;

public interface CrystallizerRecipeSchema {
    RecipeKey<OutputItem> RESULT = ItemComponents.OUTPUT.key("result");
    RecipeKey<InputFluid> INPUT_FLUID = FluidComponents.INPUT.key("input");
    RecipeKey<OutputFluid> OUTPUT_FLUID = FluidComponents.OUTPUT.key("fluidResult");
    //RecipeKey<Long> TIME = IERecipes.time(200);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, RESULT, INPUT_FLUID, OUTPUT_FLUID);
}
