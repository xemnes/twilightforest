package twilightforest.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.placement.ConfiguredDecorator;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import twilightforest.TwilightForestMod;
import twilightforest.worldgen.treeplacers.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

@Mod.EventBusSubscriber(modid = TwilightForestMod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class TwilightFeatures {
    private static final List<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = new ArrayList<>();
    private static final List<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = new ArrayList<>();

    public static final TrunkPlacerType<BranchingTrunkPlacer> TRUNK_BRANCHING = registerTrunk(TwilightForestMod.prefix("branching_trunk_placer"), BranchingTrunkPlacer.CODEC);
    public static final TrunkPlacerType<TrunkRiser> TRUNK_RISER = registerTrunk(TwilightForestMod.prefix("trunk_mover_upper"), TrunkRiser.CODEC);
    public static final TrunkPlacerType<HollowTrunkPlacer> HOLLOW_TRUNK = registerTrunk(TwilightForestMod.prefix("hollow_trunk_placer"), HollowTrunkPlacer.CODEC);

    public static final FoliagePlacerType<LeafSpheroidFoliagePlacer> FOLIAGE_SPHEROID = registerFoliage(TwilightForestMod.prefix("spheroid_foliage_placer"), LeafSpheroidFoliagePlacer.CODEC);

    public static final TreeDecoratorType<TreeCorePlacer> CORE_PLACER = registerTreeFeature(TwilightForestMod.prefix("core_placer"), TreeCorePlacer.CODEC);
    public static final TreeDecoratorType<TrunkSideDecorator> TRUNKSIDE_DECORATOR = registerTreeFeature(TwilightForestMod.prefix("trunkside_decorator"), TrunkSideDecorator.CODEC);
    public static final TreeDecoratorType<TreeRootsDecorator> TREE_ROOTS = registerTreeFeature(TwilightForestMod.prefix("tree_roots"), TreeRootsDecorator.CODEC);
    public static final TreeDecoratorType<DangleFromTreeDecorator> DANGLING_DECORATOR = registerTreeFeature(TwilightForestMod.prefix("dangle_from_tree_decorator"), DangleFromTreeDecorator.CODEC);

    public static final FeatureDecorator<NoneDecoratorConfiguration> PLACEMENT_NOTFSTRUCTURE = new OutOfStructurePlacement(NoneDecoratorConfiguration.CODEC);

    public static final ConfiguredDecorator<?> CONFIGURED_PLACEMENT_NOTFSTRUCTURE = PLACEMENT_NOTFSTRUCTURE.configured(NoneDecoratorConfiguration.INSTANCE);
    public static final ConfiguredDecorator<?> CONFIGURED_PLACEMENT_NOTFSTRUCTURE_WITHHEIGHTMAP = CONFIGURED_PLACEMENT_NOTFSTRUCTURE.decorated(FeatureDecorator.HEIGHTMAP.configured(NoneDecoratorConfiguration.INSTANCE));


    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliage(ResourceLocation name, Codec<P> codec) {
        FoliagePlacerType<P> type = new FoliagePlacerType<>(codec);
        type.setRegistryName(name);
        FOLIAGE_PLACER_TYPES.add(type);
        return type;
    }

    private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunk(ResourceLocation name, Codec<P> codec) {
        // TRUNK_REPLACER is wrong, it only places, not replacing
        return Registry.register(Registry.TRUNK_PLACER_TYPES, name, new TrunkPlacerType<>(codec));
    }

    private static <P extends TreeDecorator> TreeDecoratorType<P> registerTreeFeature(ResourceLocation name, Codec<P> codec) {
        // TRUNK_REPLACER is wrong, it only places, not replacing
        TreeDecoratorType<P> type = new TreeDecoratorType<>(codec);
        type.setRegistryName(name);
        TREE_DECORATOR_TYPES.add(type);
        return type;
    }

    protected static <FC extends FeatureConfiguration, F extends Feature<FC>> ConfiguredFeature<FC, F> registerWorldFeature(ResourceLocation rl, ConfiguredFeature<FC, F> feature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, rl, feature);
    }

    @SubscribeEvent
    public static void registerFoliagePlacers(RegistryEvent.Register<FoliagePlacerType<?>> evt) {
        evt.getRegistry().registerAll(FOLIAGE_PLACER_TYPES.toArray(new FoliagePlacerType<?>[0]));
    }
    
    @SubscribeEvent
    public static void registerTreeDecorators(RegistryEvent.Register<TreeDecoratorType<?>> evt) {
        evt.getRegistry().registerAll(TREE_DECORATOR_TYPES.toArray(new TreeDecoratorType<?>[0]));
    }

    @SubscribeEvent
    public static void registerPlacementConfigs(RegistryEvent.Register<FeatureDecorator<?>> evt) {
        evt.getRegistry().register(PLACEMENT_NOTFSTRUCTURE.setRegistryName(TwilightForestMod.prefix("nostructure")));
    }
}
