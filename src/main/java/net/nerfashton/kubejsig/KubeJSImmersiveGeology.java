package net.nerfashton.kubejsig;

import com.igteam.immersivegeology.common.block.multiblocks.recipe.serializer.TurbineFuelSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(KubeJSImmersiveGeology.MODID)
public class KubeJSImmersiveGeology {
    public static final String MODID = "kubejs_immersive_geology";

    public KubeJSImmersiveGeology() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerSerializers);
    }

    private void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.RECIPE_SERIALIZERS)) {
            event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS,
                    ResourceLocation.fromNamespaceAndPath("immersivegeology", "turbine_fuel"),
                    TurbineFuelSerializer::new);
        }
    }
}
