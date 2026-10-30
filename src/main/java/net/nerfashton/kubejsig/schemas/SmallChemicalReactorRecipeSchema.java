package net.nerfashton.kubejsig.schemas;

import dev.latvian.mods.kubejs.fluid.EmptyFluidStackJS;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface SmallChemicalReactorRecipeSchema {
    RecipeKey<OutputItem> RESULT = IGRecipes.OPTIONAL_OUTPUT_ITEM.key("result").optional(OutputItem.EMPTY).allowEmpty().alwaysWrite();
    RecipeKey<OutputFluid> FLUID_RESULT = IGRecipes.OPTIONAL_OUTPUT_FLUID.key("fluidResult").optional(EmptyFluidStackJS.INSTANCE).allowEmpty().alwaysWrite();
    RecipeKey<InputItem> ITEM_INPUT = IGRecipes.OPTIONAL_INPUT_ITEM.key("itemInput").optional(InputItem.EMPTY).allowEmpty().alwaysWrite();
    RecipeKey<InputFluid> FLUID_INPUT_A = FluidComponents.INPUT.key("fluidInputA").defaultOptional();
    RecipeKey<InputFluid> FLUID_INPUT_B = FluidComponents.INPUT.key("fluidInputB").defaultOptional();
    RecipeKey<Integer> DAMAGE_PER_SECOND = NumberComponent.INT.key("damage_per_second").optional(0).alwaysWrite();
    RecipeKey<Integer> ENERGY = IGRecipes.energy(25600);
    RecipeKey<Long> TIME = IGRecipes.time(200);

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, RESULT, FLUID_RESULT, ITEM_INPUT, FLUID_INPUT_A, FLUID_INPUT_B, DAMAGE_PER_SECOND, ENERGY, TIME)
            .constructor(RESULT, FLUID_RESULT, ITEM_INPUT, FLUID_INPUT_A)
            .constructor(RESULT, FLUID_RESULT, ITEM_INPUT, FLUID_INPUT_A, FLUID_INPUT_B)
            .constructor(RESULT, FLUID_RESULT, ITEM_INPUT, FLUID_INPUT_A, FLUID_INPUT_B, DAMAGE_PER_SECOND)
            .constructor(RESULT, FLUID_RESULT, ITEM_INPUT, FLUID_INPUT_A, FLUID_INPUT_B, DAMAGE_PER_SECOND, ENERGY)
            .constructor(RESULT, FLUID_RESULT, ITEM_INPUT, FLUID_INPUT_A, FLUID_INPUT_B, DAMAGE_PER_SECOND, ENERGY, TIME);
}
