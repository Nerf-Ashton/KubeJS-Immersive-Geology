package net.nerfashton.kubejsig;

import blusunrize.immersiveengineering.common.util.RecipeSerializers;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.CrystallizerRecipe;
import com.igteam.immersivegeology.core.lib.IGLib;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.RegistryObject;
import net.nerfashton.kubejsig.schemas.CrystallizerRecipeSchema;

public class KubeJSImmersiveGeologyPlugin extends KubeJSPlugin {
    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.namespace(IGLib.MODID)
                .shaped(RecipeSerializers.TURN_AND_COPY_SERIALIZER.getId().getPath())
                .register(name(CrystallizerRecipe.SERIALIZER), CrystallizerRecipeSchema.SCHEMA);
    }

    private static String name(RegistryObject<?> reg) {
        return reg.getId().getPath();
    }
}
