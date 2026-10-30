package net.nerfashton.kubejsig;

import blusunrize.immersiveengineering.api.IEApi;
import blusunrize.immersiveengineering.api.crafting.FluidTagInput;
import blusunrize.immersiveengineering.api.crafting.IngredientWithSize;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.latvian.mods.kubejs.fluid.EmptyFluidStackJS;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import dev.latvian.mods.kubejs.fluid.InputFluid;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import dev.latvian.mods.kubejs.recipe.RecipeExceptionJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.RecipesEventJS;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentValue;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.MapJS;
import dev.latvian.mods.kubejs.util.Tags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;

import java.util.Map;

public class IGRecipeJS extends RecipeJS {

    @Override
    public JsonElement writeInputItem(InputItem value) {
        if (value == null || value.isEmpty()) {
            JsonObject json = new JsonObject();
            json.add("base_ingredient", new JsonArray());
            json.addProperty("count", 0);
            return json;
        }
        if (value.count > 1) {
            JsonObject json = new JsonObject();
            json.add("base_ingredient", value.ingredient.toJson());
            json.addProperty("count", value.count);
            return json;
        }
        return value.ingredient.toJson();
    }

    @Override
    public InputItem readInputItem(Object from) {
        try {
            if (from instanceof JsonObject json && json.has("base_ingredient")) {
                int count = GsonHelper.getAsInt(json, "count", 1);
                if (count <= 0) {
                    return InputItem.EMPTY;
                }
                JsonElement base = json.get("base_ingredient");
                if (base.isJsonArray() && base.getAsJsonArray().isEmpty()) {
                    return InputItem.EMPTY;
                }
                Ingredient ing = IngredientJS.of(base);
                return InputItem.of(ing, count);
            }
            return super.readInputItem(from);
        } catch (Exception e) {
            ConsoleJS.SERVER.error("[%s] Failed to read IE/IG input item from %s".formatted(this.type != null ? this.type.id : "unknown", from), e, RecipesEventJS.SKIP_ERROR);
            return InputItem.EMPTY;
        }
    }

    @Override
    public OutputItem readOutputItem(Object from) {
        try {
            if (from instanceof JsonObject json && !json.has("item")) {
                int count = GsonHelper.getAsInt(json, "count", 1);
                if (count <= 0) {
                    return OutputItem.EMPTY;
                }
                if (json.has("base_ingredient") && json.get("base_ingredient").isJsonArray() && json.getAsJsonArray("base_ingredient").isEmpty()) {
                    return OutputItem.EMPTY;
                }
                IngredientWithSize iws = IngredientWithSize.deserialize(json);
                ItemStack[] matching = iws.getMatchingStacks();
                if (matching == null || matching.length == 0) {
                    return OutputItem.EMPTY;
                }
                return OutputItem.of(IEApi.getPreferredStackbyMod(matching));
            }
            return super.readOutputItem(from);
        } catch (Exception e) {
            ConsoleJS.SERVER.error("[%s] Failed to read IE/IG output item from %s".formatted(this.type != null ? this.type.id : "unknown", from), e, RecipesEventJS.SKIP_ERROR);
            return OutputItem.EMPTY;
        }
    }

    @Override
    public InputFluid readInputFluid(Object fluid) {
        if (fluid == null) {
            return null;
        }
        if (fluid instanceof IGInputFluid in) {
            return in;
        } else if (fluid instanceof FluidTagInput in) {
            return new IGInputFluid(in);
        } else if (fluid instanceof FluidStackJS stack) {
            if (stack.kjs$isEmpty() || stack.getAmount() <= 0) {
                return null;
            }
            String id = stack.getId();
            if (id.startsWith("#")) {
                ResourceLocation tagLoc = ResourceLocation.tryParse(id.substring(1));
                TagKey<Fluid> tag = Tags.fluid(tagLoc);
                return new IGInputFluid(new FluidTagInput(tag, (int) stack.getAmount()));
            }
            TagKey<Fluid> tag = null;
            if (!stack.getTags().isEmpty()) {
                for (ResourceLocation t : stack.getTags()) {
                    if ("forge".equals(t.getNamespace())) {
                        tag = Tags.fluid(t);
                        break;
                    }
                }
                if (tag == null) {
                    tag = Tags.fluid(stack.getTags().iterator().next());
                }
            } else {
                ResourceLocation res = ResourceLocation.tryParse(id);
                if (res != null) {
                    tag = Tags.fluid(res);
                }
            }
            if (tag != null) {
                return new IGInputFluid(new FluidTagInput(tag, (int) stack.getAmount()));
            }
            throw new RecipeExceptionJS("[%s] Failed to read IG input fluid from %s! Note that Immersive Geology requires a fluid tag (e.g. '#forge:water' or Fluid.of('#forge:water', 100)) as input!".formatted(this.type != null ? this.type.id : "unknown", fluid));
        } else if (fluid instanceof CharSequence seq) {
            String str = seq.toString();
            if (str.isEmpty() || "minecraft:empty".equals(str) || "empty".equals(str)) {
                return null;
            }
            int amount = 1000;
            if (str.startsWith("#")) {
                str = str.substring(1);
            }
            int lastColon = str.lastIndexOf(':');
            if (lastColon > 0) {
                String maybeAmount = str.substring(lastColon + 1);
                try {
                    amount = Integer.parseInt(maybeAmount);
                    str = str.substring(0, lastColon);
                } catch (NumberFormatException ignored) {}
            }
            ResourceLocation tagLoc = ResourceLocation.tryParse(str);
            if (tagLoc != null) {
                TagKey<Fluid> tag = Tags.fluid(tagLoc);
                return new IGInputFluid(new FluidTagInput(tag, amount));
            }
        } else if (fluid instanceof Map || fluid instanceof JsonObject) {
            JsonObject json = MapJS.json(fluid);
            if (json.has("fluid") && !json.has("tag")) {
                throw new RecipeExceptionJS("Invalid key 'fluid' in IG input fluid json %s! Note that Immersive Geology only accepts fluid tags as inputs!".formatted(fluid));
            }
            try {
                return new IGInputFluid(FluidTagInput.deserialize(json));
            } catch (JsonParseException e) {
                throw new RecipeExceptionJS("Failed to deserialize IG input fluid json!", e);
            }
        }
        throw new RecipeExceptionJS("[%s] Failed to read IG input fluid from %s! Note that Immersive Geology requires a fluid tag as input!".formatted(this.type != null ? this.type.id : "unknown", fluid));
    }

    @Override
    public JsonElement writeInputFluid(InputFluid fluid) {
        if (fluid == null || fluid.kjs$isEmpty()) {
            return null;
        }
        if (fluid instanceof IGInputFluid in) {
            return in.input().serialize();
        }
        throw new RecipeExceptionJS("[%s] Failed to write IG input fluid from %s! Note that Immersive Geology only accepts fluid tags as inputs!".formatted(this.type != null ? this.type.id : "unknown", fluid));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> RecipeJS setValue(RecipeKey<T> key, T value) {
        if (value == null && key != null) {
            if (key.component instanceof IGRecipes.OptionalFluidOutputComponent) {
                value = (T) EmptyFluidStackJS.INSTANCE;
            } else if (key.component instanceof IGRecipes.OptionalOutputItemComponent) {
                value = (T) OutputItem.EMPTY;
            } else if (key.component instanceof IGRecipes.OptionalInputItemComponent) {
                value = (T) InputItem.EMPTY;
            } else if (key.optional != null && key.alwaysWrite) {
                Object def = key.optional.getDefaultValue(this.type != null ? this.type.schemaType : null);
                if (def != null) {
                    value = (T) key.component.read(this, def);
                }
            } else {
                RecipeComponentValue<?> holder = this.getAllValueMap().get(key.name);
                if (holder != null) {
                    ((RecipeComponentValue<T>) holder).value = null;
                    holder.write = false;
                    return this;
                }
            }
        }
        return super.setValue(key, value);
    }

    @Override
    public void serialize() {
        super.serialize();
        if (this.json.has("type")) {
            JsonElement typeElem = this.json.get("type");
            if (typeElem != null && typeElem.isJsonPrimitive()) {
                String typeStr = typeElem.getAsString();
                if ("immersivegeology:turbine_fuel".equals(typeStr)) {
                    this.json.addProperty("type", "immersivegeology:tubrine_fuel");
                }
            }
        }
        if (this.json.has("fluidTag")) {
            JsonElement ft = this.json.get("fluidTag");
            if (ft != null && ft.isJsonPrimitive() && ft.getAsString().startsWith("#")) {
                this.json.addProperty("fluidTag", ft.getAsString().substring(1));
            }
        }
        if (this.json.has("byproducts") && !this.json.has("amount_of_byproducts")) {
            JsonElement byproducts = this.json.get("byproducts");
            if (byproducts.isJsonArray()) {
                this.json.addProperty("amount_of_byproducts", byproducts.getAsJsonArray().size());
            }
        }
        if (this.json.has("biome")) {
            JsonElement biomeElem = this.json.get("biome");
            if (biomeElem != null && !biomeElem.isJsonNull()) {
                if (biomeElem.isJsonArray()) {
                    this.json.remove("biome");
                    this.json.add("biome_tags", biomeElem);
                } else if (biomeElem.isJsonPrimitive() && biomeElem.getAsString().startsWith("#")) {
                    this.json.remove("biome");
                    JsonArray arr = new JsonArray();
                    arr.add(biomeElem.getAsString().substring(1));
                    this.json.add("biome_tags", arr);
                }
            }
        }
        if (this.json.has("ore")) {
            JsonElement oreElem = this.json.get("ore");
            if (oreElem == null || oreElem.isJsonNull() || (oreElem.isJsonObject() && oreElem.getAsJsonObject().entrySet().isEmpty())) {
                this.json.remove("ore");
            }
        }
        if (this.json.has("melt_input")) {
            JsonElement miElem = this.json.get("melt_input");
            if (miElem == null || miElem.isJsonNull() || (miElem.isJsonObject() && miElem.getAsJsonObject().entrySet().isEmpty())) {
                this.json.remove("melt_input");
            }
        }
        if (this.json.has("rich_melt")) {
            JsonElement rmElem = this.json.get("rich_melt");
            if (rmElem == null || rmElem.isJsonNull() || (rmElem.isJsonObject() && rmElem.getAsJsonObject().has("amount") && rmElem.getAsJsonObject().get("amount").getAsInt() <= 0)) {
                this.json.remove("rich_melt");
            }
        }
    }
}
