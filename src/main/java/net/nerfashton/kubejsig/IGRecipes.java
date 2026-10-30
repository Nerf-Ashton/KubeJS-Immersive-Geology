package net.nerfashton.kubejsig;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dev.latvian.mods.kubejs.fluid.EmptyFluidStackJS;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.fluid.OutputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.component.FluidComponents;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentValue;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentWithParent;
import dev.latvian.mods.kubejs.recipe.component.TimeComponent;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

import java.util.Collection;

public interface IGRecipes {
    class SecondaryOutputComponent implements RecipeComponentWithParent<OutputItem> {
        public static final SecondaryOutputComponent INSTANCE = new SecondaryOutputComponent();

        @Override
        public RecipeComponent<OutputItem> parentComponent() {
            return ItemComponents.OUTPUT;
        }

        @Override
        public JsonElement write(RecipeJS recipe, OutputItem value) {
            JsonObject json = new JsonObject();
            json.add("output", this.parentComponent().write(recipe, value));
            if (value.hasChance()) {
                json.addProperty("chance", value.getChance());
            }
            return json;
        }

        @Override
        public OutputItem read(RecipeJS recipe, Object from) {
            if (from instanceof JsonObject json) {
                OutputItem item = OutputItem.EMPTY;
                if (!json.has("conditions") || CraftingHelper.processConditions(json, "conditions", ICondition.IContext.EMPTY)) {
                    if (json.has("output")) {
                        item = this.parentComponent().read(recipe, json.get("output"));
                    }
                    if (json.has("chance")) {
                        item = item.withChance(json.get("chance").getAsDouble());
                    }
                }
                return item;
            }
            return this.parentComponent().read(recipe, from);
        }

        @Override
        public String toString() {
            return "secondary_output";
        }
    }

    RecipeComponent<OutputItem> SECONDARY_OUTPUT = SecondaryOutputComponent.INSTANCE;

    class OptionalOutputItemComponent implements RecipeComponent<OutputItem> {
        public static final OptionalOutputItemComponent INSTANCE = new OptionalOutputItemComponent();

        @Override
        public Class<?> componentClass() {
            return OutputItem.class;
        }

        @Override
        public void writeToJson(RecipeJS recipe, RecipeComponentValue<OutputItem> cv, JsonObject json) {
            json.add(cv.key.name, this.write(recipe, cv.value));
        }

        @Override
        public void readFromJson(RecipeJS recipe, RecipeComponentValue<OutputItem> cv, JsonObject json) {
            if (json.has(cv.key.name)) {
                cv.value = this.read(recipe, json.get(cv.key.name));
            } else {
                cv.value = OutputItem.EMPTY;
            }
        }

        @Override
        public String checkEmpty(RecipeKey<OutputItem> key, OutputItem value) {
            return "";
        }

        @Override
        public JsonElement write(RecipeJS recipe, OutputItem value) {
            if (value == null || value.isEmpty()) {
                JsonObject emptyJson = new JsonObject();
                emptyJson.add("base_ingredient", new JsonArray());
                emptyJson.addProperty("count", 0);
                return emptyJson;
            }
            return recipe.writeOutputItem(value);
        }

        @Override
        public OutputItem read(RecipeJS recipe, Object from) {
            if (from == null) {
                return OutputItem.EMPTY;
            }
            if (from instanceof JsonObject json) {
                if (json.has("count") && json.get("count").getAsInt() <= 0) {
                    return OutputItem.EMPTY;
                }
                if (json.has("base_ingredient") && json.get("base_ingredient").isJsonArray() && json.getAsJsonArray("base_ingredient").isEmpty()) {
                    return OutputItem.EMPTY;
                }
            }
            return recipe.readOutputItem(from);
        }

        @Override
        public String toString() {
            return "optional_output_item";
        }
    }

    RecipeComponent<OutputItem> OPTIONAL_OUTPUT_ITEM = OptionalOutputItemComponent.INSTANCE;

    class OptionalInputItemComponent implements RecipeComponent<InputItem> {
        public static final OptionalInputItemComponent INSTANCE = new OptionalInputItemComponent();

        @Override
        public Class<?> componentClass() {
            return InputItem.class;
        }

        @Override
        public void writeToJson(RecipeJS recipe, RecipeComponentValue<InputItem> cv, JsonObject json) {
            json.add(cv.key.name, this.write(recipe, cv.value));
        }

        @Override
        public void readFromJson(RecipeJS recipe, RecipeComponentValue<InputItem> cv, JsonObject json) {
            if (json.has(cv.key.name)) {
                cv.value = this.read(recipe, json.get(cv.key.name));
            } else {
                cv.value = InputItem.EMPTY;
            }
        }

        @Override
        public String checkEmpty(RecipeKey<InputItem> key, InputItem value) {
            return "";
        }

        @Override
        public JsonElement write(RecipeJS recipe, InputItem value) {
            if (value == null || value.isEmpty()) {
                JsonObject emptyJson = new JsonObject();
                emptyJson.add("base_ingredient", new JsonArray());
                emptyJson.addProperty("count", 0);
                return emptyJson;
            }
            return recipe.writeInputItem(value);
        }

        @Override
        public InputItem read(RecipeJS recipe, Object from) {
            if (from == null) {
                return InputItem.EMPTY;
            }
            if (from instanceof JsonObject json) {
                if (json.has("count") && json.get("count").getAsInt() <= 0) {
                    return InputItem.EMPTY;
                }
                if (json.has("base_ingredient") && json.get("base_ingredient").isJsonArray() && json.getAsJsonArray("base_ingredient").isEmpty()) {
                    return InputItem.EMPTY;
                }
            }
            return recipe.readInputItem(from);
        }

        @Override
        public String toString() {
            return "optional_input_item";
        }
    }

    RecipeComponent<InputItem> OPTIONAL_INPUT_ITEM = OptionalInputItemComponent.INSTANCE;

    class OptionalFluidOutputComponent implements RecipeComponent<OutputFluid> {
        public static final OptionalFluidOutputComponent INSTANCE = new OptionalFluidOutputComponent();

        @Override
        public Class<?> componentClass() {
            return OutputFluid.class;
        }

        @Override
        public void writeToJson(RecipeJS recipe, RecipeComponentValue<OutputFluid> cv, JsonObject json) {
            json.add(cv.key.name, this.write(recipe, cv.value));
        }

        @Override
        public void readFromJson(RecipeJS recipe, RecipeComponentValue<OutputFluid> cv, JsonObject json) {
            if (json.has(cv.key.name)) {
                cv.value = this.read(recipe, json.get(cv.key.name));
            } else {
                cv.value = EmptyFluidStackJS.INSTANCE;
            }
        }

        @Override
        public String checkEmpty(RecipeKey<OutputFluid> key, OutputFluid value) {
            return "";
        }

        @Override
        public JsonElement write(RecipeJS recipe, OutputFluid value) {
            if (value == null || value.kjs$isEmpty() || value.kjs$getAmount() <= 0) {
                JsonObject emptyJson = new JsonObject();
                emptyJson.addProperty("amount", 0);
                emptyJson.addProperty("fluid", "minecraft:empty");
                return emptyJson;
            }
            return FluidComponents.OUTPUT.write(recipe, value);
        }

        @Override
        public OutputFluid read(RecipeJS recipe, Object from) {
            if (from == null) {
                return EmptyFluidStackJS.INSTANCE;
            }
            if (from instanceof CharSequence str) {
                String s = str.toString();
                if (s.isEmpty() || "minecraft:empty".equals(s) || "empty".equals(s)) {
                    return EmptyFluidStackJS.INSTANCE;
                }
            }
            if (from instanceof FluidStackJS stack) {
                if (stack.kjs$isEmpty() || stack.getAmount() <= 0) {
                    return EmptyFluidStackJS.INSTANCE;
                }
            }
            if (from instanceof JsonObject json) {
                int amount = GsonHelper.getAsInt(json, "amount", 0);
                String fluid = GsonHelper.getAsString(json, "fluid", "minecraft:empty");
                if (amount <= 0 || "minecraft:empty".equals(fluid) || "empty".equals(fluid)) {
                    return EmptyFluidStackJS.INSTANCE;
                }
            }
            return FluidComponents.OUTPUT.read(recipe, from);
        }

        @Override
        public String toString() {
            return "optional_output_fluid";
        }
    }

    RecipeComponent<OutputFluid> OPTIONAL_OUTPUT_FLUID = OptionalFluidOutputComponent.INSTANCE;

    class BiomeFilterComponent implements RecipeComponent<Object> {
        public static final BiomeFilterComponent INSTANCE = new BiomeFilterComponent();

        @Override
        public Class<?> componentClass() {
            return Object.class;
        }

        @Override
        public void writeToJson(RecipeJS recipe, RecipeComponentValue<Object> cv, JsonObject json) {
            if (cv.value == null) {
                return;
            }
            JsonElement written = this.write(recipe, cv.value);
            if (written instanceof JsonArray) {
                json.remove("biome");
                json.add("biome_tags", written);
            } else if (written != null) {
                json.remove("biome_tags");
                json.add("biome", written);
            }
        }

        @Override
        public void readFromJson(RecipeJS recipe, RecipeComponentValue<Object> cv, JsonObject json) {
            if (json.has("biome")) {
                cv.value = this.read(recipe, json.get("biome"));
            } else if (json.has("biome_tags")) {
                cv.value = this.read(recipe, json.get("biome_tags"));
            }
        }

        @Override
        public JsonElement write(RecipeJS recipe, Object value) {
            if (value == null) {
                return null;
            }
            if (value instanceof JsonElement jsonElement) {
                return jsonElement;
            }
            if (value instanceof Collection<?> coll) {
                JsonArray arr = new JsonArray();
                for (Object obj : coll) {
                    String str = String.valueOf(obj);
                    if (str.startsWith("#")) {
                        str = str.substring(1);
                    }
                    arr.add(str);
                }
                return arr;
            }
            if (value instanceof Object[] arr) {
                JsonArray jsonArr = new JsonArray();
                for (Object obj : arr) {
                    String str = String.valueOf(obj);
                    if (str.startsWith("#")) {
                        str = str.substring(1);
                    }
                    jsonArr.add(str);
                }
                return jsonArr;
            }
            String str = String.valueOf(value);
            if (str.startsWith("#")) {
                JsonArray jsonArr = new JsonArray();
                jsonArr.add(str.substring(1));
                return jsonArr;
            }
            return new JsonPrimitive(str);
        }

        @Override
        public Object read(RecipeJS recipe, Object from) {
            if (from == null && recipe.json != null) {
                if (recipe.json.has("biome_tags")) {
                    return recipe.json.get("biome_tags");
                } else if (recipe.json.has("biome")) {
                    return recipe.json.get("biome");
                }
            }
            return from;
        }

        @Override
        public String toString() {
            return "biome_filter";
        }
    }

    RecipeComponent<Object> BIOME_FILTER = BiomeFilterComponent.INSTANCE;

    static RecipeKey<Long> time() {
        return TimeComponent.TICKS.key("time");
    }

    static RecipeKey<Long> time(long defaultTime) {
        return time().optional(defaultTime).alwaysWrite();
    }

    static RecipeKey<Integer> energy() {
        return NumberComponent.INT.key("energy");
    }

    static RecipeKey<Integer> energy(int defaultEnergy) {
        return energy().optional(defaultEnergy).alwaysWrite();
    }

    static RecipeKey<Integer> water() {
        return NumberComponent.INT.key("water");
    }

    static RecipeKey<Integer> water(int defaultWater) {
        return water().optional(defaultWater).alwaysWrite();
    }

    static RecipeKey<Integer> heat() {
        return NumberComponent.INT.key("heat");
    }

    static RecipeKey<Integer> heat(int defaultHeat) {
        return heat().optional(defaultHeat).alwaysWrite();
    }
}
