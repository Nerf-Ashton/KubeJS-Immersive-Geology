# KubeJS Immersive Geology

A KubeJS addon for [Immersive Geology](https://www.curseforge.com/minecraft/mc-mods/immersive-geology) built for Minecraft 1.20.1 (Forge) and KubeJS 6.

This addon allows modpack developers and players to register custom recipes for all machines and processing mechanics introduced by Immersive Geology using KubeJS server scripts (`server_scripts`).

---

## Supported Recipe Types & Examples

All recipes are registered under the `immersivegeology` namespace in the `recipes` event:

```javascript
ServerEvents.recipes(event => {
    // ...
})
```

### 1. Crystallizer
Cool or crystallize solutions into items and secondary fluids.
- **Parameters**: `(resultItem, inputItem, fluidResult, energy, time)`
```javascript
// Outputs 2 diamonds and 500mB water from 1 diamond block and 1000mB water tag, 5000 FE, 200 ticks
event.recipes.immersivegeology.crystallizer('2x minecraft:diamond', 'minecraft:diamond_block', Fluid.of('minecraft:water', 500), 5000, 200)

// Without fluid result (empty fluid output)
event.recipes.immersivegeology.crystallizer('minecraft:quartz', 'minecraft:quartz_block', Fluid.empty(), 2000, 100)
```

### 2. Ball Mill
Grind down ores and geological materials into powders and crushed products.
- **Parameters**: `(resultItem, inputItem, energy, time)`
```javascript
event.recipes.immersivegeology.ballmill('2x minecraft:redstone', 'minecraft:redstone_ore', 4000, 150)
```

### 3. Centrifuge
Separate slurries and fluid mixtures into item products and distinct fluid outputs.
- **Parameters**: `(resultItem, fluidInput, primaryFluidOut, secondaryFluidOut, energy, time)`
```javascript
// Fluid inputs use FluidTagInput syntax ('#forge:lava:1000' or Fluid.of(...))
event.recipes.immersivegeology.centrifuge(
    'minecraft:obsidian',
    '#forge:lava:1000',
    Fluid.of('minecraft:water', 500),
    Fluid.empty(),
    8000,
    300
)
```

### 4. Chemical Reactor
Multi-input chemical processing supporting up to 1 item input, 3 fluid inputs, 1 item output, and 1 fluid output.
- **Parameters**: `(resultItem, fluidResult, itemInput, fluidA, fluidB?, fluidC?, energy, time)`
```javascript
event.recipes.immersivegeology.chemical_reactor(
    'minecraft:emerald',
    Fluid.of('minecraft:water', 250),
    'minecraft:diamond',
    '#forge:water:1000',
    '#forge:lava:500',
    null, // or Fluid.empty()
    10000,
    400
)
```

### 5. Small Chemical Reactor
Compact chemical reactor supporting 1 item input, up to 2 fluid inputs, 1 item output, 1 fluid output, and acid damage rate.
- **Parameters**: `(resultItem, fluidResult, itemInput, fluidA, fluidB?, damagePerSecond, energy, time)`
```javascript
event.recipes.immersivegeology.small_chemical_reactor(
    'minecraft:iron_ingot',
    Fluid.empty(),
    'minecraft:raw_iron',
    '#forge:water:500',
    null,
    2.0,  // damage per second
    2500, // energy
    100   // time
)
```

### 6. Foundry
Cast melted materials with custom molds.
- **Parameters**: `(resultItem, inputItem, moldItem, energy, time)`
```javascript
event.recipes.immersivegeology.foundry(
    'minecraft:iron_axe',
    '3x minecraft:iron_ingot',
    'immersiveengineering:mold_plate',
    6000,
    200
)
```

### 7. Gravity Separator
Separate minerals using water currents and centrifugal gravity, with chance-based byproducts.
- **Parameters**: `(resultItem, inputItem, byproductItem, byproductChance, water, time)`
```javascript
event.recipes.immersivegeology.gravity_separator(
    'minecraft:gold_nugget',
    'minecraft:gravel',
    'minecraft:flint',
    0.25, // 25% chance for byproduct
    250,  // mB water
    100   // ticks
)
```

### 8. Industrial Sluice
High-capacity hydraulic ore washing with multiple probabilistic byproducts.
- **Parameters**: `(resultItem, inputItem, byproducts[], water, energy, time)`
```javascript
event.recipes.immersivegeology.industrial_sluice(
    'minecraft:raw_iron',
    'minecraft:iron_ore',
    [
        Item.of('minecraft:raw_copper').withChance(0.5),
        Item.of('minecraft:raw_gold').withChance(0.15)
    ],
    500,  // water
    4000, // energy
    160   // time
)
```

### 9. Pelletizer
Form powders and crushed materials into dense pellets.
- **Parameters**: `(resultItem, inputItem, energy, time)`
```javascript
event.recipes.immersivegeology.pelletizer('minecraft:clay_ball', 'minecraft:clay', 1500, 80)
```

### 10. Reverberation Furnace
High-temperature smelting producing waste/slag.
- **Parameters**: `(resultItem, inputItem, wasteItem, time)`
```javascript
event.recipes.immersivegeology.reverberation_furnace(
    'minecraft:copper_ingot',
    'minecraft:raw_copper',
    'immersiveengineering:slag',
    300
)
```

### 11. Rotary Kiln
Calcination and high-temperature rotary roasting.
- **Parameters**: `(resultItem, inputItem, heat, time)`
```javascript
event.recipes.immersivegeology.rotary_kiln('minecraft:brick', 'minecraft:clay_ball', 800, 200)
```

### 12. Bloomery & Bloomery Fuel
Early-stage iron smelting bloomery and custom bloomery fuels.
- **Bloomery Parameters**: `(resultItem, inputItem, time)`
```javascript
event.recipes.immersivegeology.bloomery('minecraft:iron_ingot', 'minecraft:raw_iron', 600)
```
- **Fuel Parameters**: `(fuelItem, time)`
```javascript
event.recipes.immersivegeology.bloomery_fuel('minecraft:charcoal', 400)
```

### 13. Core Drill
Extract deep geological fluid samples and drill cores.
- **Parameters**: `(fluidResult, inputItem)`
```javascript
event.recipes.immersivegeology.coredrill(Fluid.of('minecraft:lava', 1000), 'minecraft:deepslate')
```

### 14. Geothermal Exchanger
Harness heat from geothermal fluids or materials.
- **Parameters**: `(fluidResult, inputItem, energy, time)`
```javascript
event.recipes.immersivegeology.geothermal_exchanger(Fluid.of('minecraft:water', 1000), 'minecraft:magma_block', 12000, 240)
```

### 15. Geothermal Conversion
Define heat-dependent block transformations.
- **Parameters**: `(transitionBlock, blockHeat, upperBoundBlock, upperHeat, lowerBoundBlock, lowerHeat)`
```javascript
event.recipes.immersivegeology.geothermal_conversion(
    'minecraft:magma_block',
    500,
    'minecraft:lava',
    1000,
    'minecraft:obsidian',
    200
)
```

### 16. Geothermal Biome
Configure geothermal heat characteristics by biome ID, biome tag, or array of tags.
- **Parameters**: `(biomeOrTag, minHeat, maxHeat)`
```javascript
// By biome ID:
event.recipes.immersivegeology.geothermal_biome('minecraft:desert', 200, 800)

// By single biome tag:
event.recipes.immersivegeology.geothermal_biome('#minecraft:is_overworld', 100, 500)

// By array of biome tags:
event.recipes.immersivegeology.geothermal_biome(['#minecraft:is_nether', '#minecraft:is_hot'], 400, 1200)
```

### 17. Steam Turbine Fuel
Register fluid fuels for steam turbines (supports both `turbine_fuel` and `tubrine_fuel`).
- **Parameters**: `(fluidTag, burnTime, consumeAmount, outputRatio)`
```javascript
event.recipes.immersivegeology.turbine_fuel('#forge:steam', 100, 10, 0.8)
```

### 18. Chemical Repair
Restore durability to machine parts and equipment through chemical treatment.
- **Parameters**: `(inputItem, time)`
```javascript
event.recipes.immersivegeology.chemical_repair('immersiveengineering:drillhead_iron', 150)
```

### 19. Geo Hint
Configure geological exploration hints and rock composition data.
- **Parameters**: `(materialIndex, materialType)`
```javascript
event.recipes.immersivegeology.geo_hint(1, 'granite')
```
