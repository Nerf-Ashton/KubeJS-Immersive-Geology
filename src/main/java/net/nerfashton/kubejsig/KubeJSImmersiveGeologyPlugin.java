package net.nerfashton.kubejsig;

import com.igteam.immersivegeology.common.block.multiblocks.recipe.BallmillRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.BasicChemicalRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.BloomeryFuel;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.BloomeryRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.BulkBlastFluxRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.BulkBlastFurnaceRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.CentrifugeRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.ChemicalRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.ChemicalRepairRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.CoreDrillRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.CrystallizerRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.FoundryAlloyRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.FoundryRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.GeothermalBiomeRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.GeothermalConversionRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.GeothermalExchangerRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.GravitySeparatorRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.PelletizerRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.RevFurnaceRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.RotaryKilnRecipe;
import com.igteam.immersivegeology.common.block.multiblocks.recipe.TurbineFuel;
import com.igteam.immersivegeology.common.recipe.IGGeoRecipe;
import com.igteam.immersivegeology.core.lib.IGLib;
import com.igteam.immersivegeology.core.registration.IGRecipeSerializers;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeNamespace;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.recipe.schema.minecraft.ShapelessRecipeSchema;
import net.minecraftforge.registries.RegistryObject;
import net.nerfashton.kubejsig.schemas.BallmillRecipeSchema;
import net.nerfashton.kubejsig.schemas.BloomeryFuelRecipeSchema;
import net.nerfashton.kubejsig.schemas.BloomeryRecipeSchema;
import net.nerfashton.kubejsig.schemas.BulkBlastFluxRecipeSchema;
import net.nerfashton.kubejsig.schemas.BulkBlastFurnaceRecipeSchema;
import net.nerfashton.kubejsig.schemas.CentrifugeRecipeSchema;
import net.nerfashton.kubejsig.schemas.ChemicalReactorRecipeSchema;
import net.nerfashton.kubejsig.schemas.ChemicalRepairRecipeSchema;
import net.nerfashton.kubejsig.schemas.CoreDrillRecipeSchema;
import net.nerfashton.kubejsig.schemas.CrystallizerRecipeSchema;
import net.nerfashton.kubejsig.schemas.FoundryAlloyRecipeSchema;
import net.nerfashton.kubejsig.schemas.FoundryRecipeSchema;
import net.nerfashton.kubejsig.schemas.GeothermalBiomeRecipeSchema;
import net.nerfashton.kubejsig.schemas.GeothermalConversionRecipeSchema;
import net.nerfashton.kubejsig.schemas.GeothermalExchangerRecipeSchema;
import net.nerfashton.kubejsig.schemas.GravitySeparatorRecipeSchema;
import net.nerfashton.kubejsig.schemas.IGGeoHintRecipeSchema;
import net.nerfashton.kubejsig.schemas.PelletizerRecipeSchema;
import net.nerfashton.kubejsig.schemas.RevFurnaceRecipeSchema;
import net.nerfashton.kubejsig.schemas.RotaryKilnRecipeSchema;
import net.nerfashton.kubejsig.schemas.SmallChemicalReactorRecipeSchema;
import net.nerfashton.kubejsig.schemas.TurbineFuelRecipeSchema;

public class KubeJSImmersiveGeologyPlugin extends KubeJSPlugin {

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        RecipeNamespace ig = event.namespace(IGLib.MODID);

        ig.register(name(CrystallizerRecipe.SERIALIZER), CrystallizerRecipeSchema.SCHEMA);
        ig.register(name(BallmillRecipe.SERIALIZER), BallmillRecipeSchema.SCHEMA);
        ig.register(name(CentrifugeRecipe.SERIALIZER), CentrifugeRecipeSchema.SCHEMA);
        ig.register(name(ChemicalRecipe.SERIALIZER), ChemicalReactorRecipeSchema.SCHEMA);
        ig.register(name(BasicChemicalRecipe.SERIALIZER), SmallChemicalReactorRecipeSchema.SCHEMA);
        ig.register(name(ChemicalRepairRecipe.SERIALIZER), ChemicalRepairRecipeSchema.SCHEMA);
        ig.register(name(FoundryRecipe.SERIALIZER), FoundryRecipeSchema.SCHEMA);
        ig.register(name(FoundryAlloyRecipe.SERIALIZER), FoundryAlloyRecipeSchema.SCHEMA);
        ig.register(name(GravitySeparatorRecipe.SERIALIZER), GravitySeparatorRecipeSchema.SCHEMA);
        ig.register(name(PelletizerRecipe.SERIALIZER), PelletizerRecipeSchema.SCHEMA);
        ig.register(name(RevFurnaceRecipe.SERIALIZER), RevFurnaceRecipeSchema.SCHEMA);
        ig.register(name(RotaryKilnRecipe.SERIALIZER), RotaryKilnRecipeSchema.SCHEMA);
        ig.register(name(BulkBlastFurnaceRecipe.SERIALIZER), BulkBlastFurnaceRecipeSchema.SCHEMA);
        ig.register(name(BulkBlastFluxRecipe.SERIALIZER), BulkBlastFluxRecipeSchema.SCHEMA);
        ig.register(name(BloomeryRecipe.SERIALIZER), BloomeryRecipeSchema.SCHEMA);
        ig.register(name(BloomeryFuel.SERIALIZER), BloomeryFuelRecipeSchema.SCHEMA);
        ig.register(name(CoreDrillRecipe.SERIALIZER), CoreDrillRecipeSchema.SCHEMA);
        ig.register(name(GeothermalExchangerRecipe.SERIALIZER), GeothermalExchangerRecipeSchema.SCHEMA);
        ig.register(name(GeothermalConversionRecipe.SERIALIZER), GeothermalConversionRecipeSchema.SCHEMA);
        ig.register(name(GeothermalBiomeRecipe.SERIALIZER), GeothermalBiomeRecipeSchema.SCHEMA);
        ig.register(name(TurbineFuel.SERIALIZER), TurbineFuelRecipeSchema.SCHEMA);
        ig.register("turbine_fuel", TurbineFuelRecipeSchema.SCHEMA);
        // ig.register(name(IGGeoRecipe.SERIALIZER), IGGeoHintRecipeSchema.SCHEMA); //Does not work
    }

    private static String name(RegistryObject<?> reg) {
        return reg.getId().getPath();
    }
}
