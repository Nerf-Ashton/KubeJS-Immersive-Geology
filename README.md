# KubeJS Immersive Geology

A KubeJS addon for [Immersive Geology](https://www.curseforge.com/minecraft/mc-mods/immersive-geology) built for Forge on Minecraft 1.20.1 and [KubeJS](https://kubejs.com/).

This addon allows modpack developers and players to register custom recipes for all machines and processing mechanics introduced by Immersive Geology using KubeJS server scripts (`server_scripts`).

Keep in mind when working with Immersive Geology, many recipes can only output single items, though it may show up in JEI with multiple items.

In addition, energy and time are optional for most recipes, and default to common values in Immersive Geology.

---

## Supported Recipe Types & Examples

All recipes are registered under the `immersivegeology` namespace in the `recipes` event:

```javascript
ServerEvents.recipes(event => {
    // ...
})
```

### 1. Crystallizer
- **Parameters**: `(resultItem, inputFluid, fluidResult, energy?, time?)`
```javascript
event.recipes.immersivegeology.crystallizer('minecraft:diamond', Fluid.of('minecraft:water', 500), Fluid.of('minecraft:water', 250), 2000, 100)
```

### 2. Ball Mill
- **Parameters**: `(resultItem, inputItem, energy?, time?)`
```javascript
event.recipes.immersivegeology.ballmill('4x minecraft:sand', 'minecraft:sandstone', 1000, 40)
```

### 3. Centrifuge
An output item is required here by Immersive Geology.
- **Parameters**: `(resultItem, fluidInput, primaryFluidOut, secondaryFluidOut?, energy?, time?)`
```javascript
// Fluid inputs use FluidTagInput syntax (Fluid.of(...))
event.recipes.immersivegeology.centrifuge(
    'minecraft:obsidian',
    Fluid.of('minecraft:lava', 500),
    Fluid.of('minecraft:lava', 250),
    null,
    4000,
    150
)
```

### 4. Chemical Reactor
- **Parameters**: `(resultItem, fluidResult, itemInput, fluidA, fluidB?, fluidC?, energy?, time?)`
```javascript
event.recipes.immersivegeology.chemical_reactor(
    'minecraft:emerald',
    null,
    'minecraft:coal',
    Fluid.of('minecraft:water', 200),
    null,
    null,
    5000,
    100
)
```

### 5. Small Chemical Reactor
- **Parameters**: `(resultItem, fluidResult, itemInput, fluidA, fluidB?, damagePerSecond?, energy?, time?)`
```javascript
event.recipes.immersivegeology.small_chemical_reactor(
    'minecraft:gold_ingot',
    null,
    'minecraft:raw_gold',
    Fluid.of('minecraft:water', 200),
    null,
    1,
    2500,
    80
)
```

### 6. Chemical Repair
For restoring durability to the small chemical reactor.
- **Parameters**: `(inputItem, time?)`
```javascript
event.recipes.immersivegeology.chemical_repair('immersiveengineering:drillhead_iron', 100)
```

### 7. Bulk Blast Furnace
Inputs can be an item/ore tag (e.g. `'#forge:crushed_ore/acanthite'`) or a molten fluid tag.
- **Parameters**: `(meltFluid, input, cokeRatio?, fluxRatio?, minYield?, maxYield?, heat?, time?, richMelt?, richCokeRatio?)`
```javascript
event.recipes.immersivegeology.bulk_blast_furnace(
    Fluid.of('immersivegeology:fluid_silver', 144),
    '#forge:crushed_ore/acanthite',
    0.4,
    0.4,
    0.2,
    0.85,
    20,
    100
)
```

### 8. Bulk Blast Flux
Registers a flux material for use in the Bulk Blast Furnace.
- **Parameters**: `(inputItem, fluxValue?)`
```javascript
event.recipes.immersivegeology.bulk_blast_flux('minecraft:calcite', 2)
```

### 9. Foundry
`moldItem` can be any graphite mold or compatible mold plate.
- **Parameters**: `(resultItem, inputFluid, moldItem, energy?, time?)`
```javascript
event.recipes.immersivegeology.foundry(
    'minecraft:iron_ingot',
    Fluid.of('minecraft:lava', 250),
    'immersivegeology:mold_plate_graphite',
    1000,
    50
)
```

### 10. Foundry Alloying
- **Parameters**: `(resultFluid, inputFluids[], energy?, time?)`
```javascript
event.recipes.immersivegeology.foundry_alloying(
    Fluid.of('immersivegeology:fluid_bronze', 4),
    ['#forge:copper:3', '#forge:tin:1'],
    16,
    60
)
```

### 11. Gravity Separator
- **Parameters**: `(resultItem, inputItem, byproductItem, byproductChance, water?, time?)`
```javascript
event.recipes.immersivegeology.gravity_separator(
    'minecraft:gold_nugget',
    'minecraft:gravel',
    'minecraft:flint',
    0.5,  // 50% chance for byproduct
    100,  // mB water
    60    // ticks
)
```

### 12. Pelletizer
- **Parameters**: `(resultItem, inputItem, energy?, time?)`
```javascript
event.recipes.immersivegeology.pelletizer('minecraft:clay_ball', 'minecraft:clay', 500, 40)
```

### 13. Reverberation Furnace
- **Parameters**: `(resultItem, inputItem, waste?, time?)`
```javascript
event.recipes.immersivegeology.reverberation_furnace(
    'minecraft:copper_ingot',
    'minecraft:raw_copper',
    50, // produces 50 mB sulfur dioxide
    100
)
```

### 14. Rotary Kiln
I recommend picking from these values for the heat, as other values may be difficult to sustain in game:
1. LV: 30
2. MV: 75
3. HV: 120
4. EHV: 165
- **Parameters**: `(resultItem, inputItem, heat, time?)`
```javascript
event.recipes.immersivegeology.rotary_kiln('minecraft:brick', 'minecraft:clay_ball', 100, 80)
```

### 15. Bloomery & Bloomery Fuel
- **Bloomery Parameters**: `(resultItem, inputItem, time?)`
```javascript
event.recipes.immersivegeology.bloomery('minecraft:iron_ingot', 'minecraft:raw_iron', 120)
```
- **Fuel Parameters**: `(fuelItem, time?)`
```javascript
event.recipes.immersivegeology.bloomery_fuel('minecraft:charcoal', 200)
```

### 16. Core Drill
Still WIP multiblock machine in Immersive Geology, but recipes can be defined.
- **Parameters**: `(fluidResult, inputFluid)`
```javascript
event.recipes.immersivegeology.coredrill(Fluid.of('immersivegeology:fluid_molten_mantle', 10), '#minecraft:water:1000')
```

### 17. Geothermal Exchanger
- **Parameters**: `(fluidResult, inputFluid, energy?, time?)`
```javascript
event.recipes.immersivegeology.geothermal_exchanger(Fluid.of('minecraft:water', 500), '#forge:lava:500', 3000, 100)
```

### 18. Geothermal Conversion
- **Parameters**: `(transitionBlock, blockHeat, upperBoundBlock, upperHeat, lowerBoundBlock, lowerHeat)`
```javascript
event.recipes.immersivegeology.geothermal_conversion(
    'minecraft:magma_block',
    300,
    'minecraft:lava',
    600,
    'minecraft:obsidian',
    100
)
```

### 19. Geothermal Biome
Configure geothermal heat characteristics by biome ID, biome tag, or array of tags.
- **Parameters**: `(biomeOrTag, minHeat, maxHeat)`
```javascript
// By single biome tag:
event.recipes.immersivegeology.geothermal_biome('#minecraft:is_overworld', 100, 500)
event.recipes.immersivegeology.geothermal_biome('#forge:is_hot', 200, 800)

// By biome ID:
event.recipes.immersivegeology.geothermal_biome('minecraft:desert', 200, 800)

// By array of biome tags:
event.recipes.immersivegeology.geothermal_biome(['#minecraft:is_nether', '#minecraft:is_hot'], 400, 1200)
```

### 20. Steam Turbine Fuel
Register fluid fuels for steam turbines (supports both `turbine_fuel` and `tubrine_fuel`, since IG has a typo in its serializer registry name lol).
- **Parameters**: `(fluidTag, burnTime, consumeAmount, outputRatio)`
```javascript
event.recipes.immersivegeology.turbine_fuel('#forge:steam', 50, 5, 0.5)
event.recipes.immersivegeology.tubrine_fuel('#forge:steam', 100, 10, 0.75)
```
