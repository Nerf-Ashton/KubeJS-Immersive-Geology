package net.nerfashton.kubejsig.schemas;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.fluid.EmptyFluidStackJS;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentValue;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGInputFluid;
import net.nerfashton.kubejsig.IGRecipeJS;
import net.nerfashton.kubejsig.IGRecipes;

public interface BulkBlastFurnaceRecipeSchema {
    RecipeComponent<Object> BLAST_INPUT_COMPONENT = new RecipeComponent<>() {
        @Override
        public Class<?> componentClass() {
            return Object.class;
        }

        @Override
        public void writeToJson(RecipeJS recipe, RecipeComponentValue<Object> cv, JsonObject json) {
            if (cv.value == null) {
                return;
            }
            if (cv.value instanceof IGInputFluid fluid) {
                json.remove("ore");
                json.add("melt_input", fluid.input().serialize());
            } else if (cv.value instanceof InputItem item) {
                json.remove("melt_input");
                json.add("ore", recipe.writeInputItem(item));
            } else if (cv.value instanceof InputFluid fluid) {
                json.remove("ore");
                json.add("melt_input", recipe.writeInputFluid(fluid));
            }
        }

        @Override
        public void readFromJson(RecipeJS recipe, RecipeComponentValue<Object> cv, JsonObject json) {
            if (json.has("ore")) {
                cv.value = recipe.readInputItem(json.get("ore"));
            } else if (json.has("melt_input")) {
                cv.value = recipe.readInputFluid(json.get("melt_input"));
            }
        }

        @Override
        public JsonElement write(RecipeJS recipe, Object value) {
            if (value instanceof InputItem item) {
                return recipe.writeInputItem(item);
            } else if (value instanceof InputFluid fluid) {
                return recipe.writeInputFluid(fluid);
            }
            return null;
        }

        @Override
        public Object read(RecipeJS recipe, Object from) {
            if (from == null) {
                return InputItem.EMPTY;
            }
            if (from instanceof InputFluid || from instanceof IGInputFluid) {
                return from;
            }
            if (from instanceof InputItem) {
                return from;
            }
            String s = from.toString();
            if (s.startsWith("#forge:water") || s.startsWith("#forge:lava") || s.contains("fluid") || s.contains("molten")) {
                try {
                    return recipe.readInputFluid(from);
                } catch (Exception ignored) {}
            }
            return recipe.readInputItem(from);
        }

        @Override
        public String toString() {
            return "blast_input";
        }
    };

    RecipeKey<OutputFluid> MELT = FluidComponents.OUTPUT.key("melt");
    RecipeKey<Object> INPUT = BLAST_INPUT_COMPONENT.key("input");
    RecipeKey<Float> COKE_RATIO = NumberComponent.FLOAT.key("coke_ratio").optional(0.4f).alwaysWrite();
    RecipeKey<Float> FLUX_RATIO = NumberComponent.FLOAT.key("flux_ratio").optional(0.4f).alwaysWrite();
    RecipeKey<Float> MIN_YIELD = NumberComponent.FLOAT.key("min_yield").optional(0.2f).alwaysWrite();
    RecipeKey<Float> MAX_YIELD = NumberComponent.FLOAT.key("max_yield").optional(0.85f).alwaysWrite();
    RecipeKey<Integer> HEAT = IGRecipes.heat(20);
    RecipeKey<Long> TIME = IGRecipes.time(100);
    RecipeKey<OutputFluid> RICH_MELT = IGRecipes.OPTIONAL_OUTPUT_FLUID.key("rich_melt").optional(EmptyFluidStackJS.INSTANCE).alwaysWrite();
    RecipeKey<Float> RICH_COKE_RATIO = NumberComponent.FLOAT.key("rich_coke_ratio").optional(0.0f).alwaysWrite();

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new,
            MELT, INPUT, COKE_RATIO, FLUX_RATIO, MIN_YIELD, MAX_YIELD, HEAT, TIME, RICH_MELT, RICH_COKE_RATIO)
            .constructor(MELT, INPUT)
            .constructor(MELT, INPUT, COKE_RATIO, FLUX_RATIO, MIN_YIELD, MAX_YIELD)
            .constructor(MELT, INPUT, COKE_RATIO, FLUX_RATIO, MIN_YIELD, MAX_YIELD, HEAT, TIME)
            .constructor(MELT, INPUT, COKE_RATIO, FLUX_RATIO, MIN_YIELD, MAX_YIELD, HEAT, TIME, RICH_MELT, RICH_COKE_RATIO);
}
