package net.nerfashton.kubejsig.schemas;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.nerfashton.kubejsig.IGRecipeJS;

public interface IGGeoHintRecipeSchema {
    RecipeComponent<Integer> MATERIAL_TYPE_COMPONENT = new RecipeComponent<>() {
        @Override
        public Class<?> componentClass() {
            return Integer.class;
        }

        @Override
        public JsonElement write(RecipeJS recipe, Integer value) {
            return new JsonPrimitive(value != null ? value : 0);
        }

        @Override
        public Integer read(RecipeJS recipe, Object from) {
            if (from instanceof Number n) {
                return n.intValue();
            }
            if (from != null) {
                String s = from.toString().trim();
                if ("mineral".equalsIgnoreCase(s)) {
                    return 0;
                } else if ("metal".equalsIgnoreCase(s)) {
                    return 1;
                }
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException ignored) {}
            }
            return 0;
        }

        @Override
        public String toString() {
            return "material_type";
        }
    };

    RecipeKey<Integer> MATERIAL_INDEX = NumberComponent.INT.key("material_index");
    RecipeKey<Integer> MATERIAL_TYPE = MATERIAL_TYPE_COMPONENT.key("material_type");

    RecipeSchema SCHEMA = new RecipeSchema(IGRecipeJS.class, IGRecipeJS::new, MATERIAL_INDEX, MATERIAL_TYPE)
            .constructor(MATERIAL_INDEX, MATERIAL_TYPE);
}
