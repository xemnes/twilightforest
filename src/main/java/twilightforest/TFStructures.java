package twilightforest;

import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import twilightforest.structures.courtyard.NagaCourtyardPieces;
import twilightforest.structures.darktower.DarkTowerPieces;
import twilightforest.structures.finalcastle.FinalCastlePieces;
import twilightforest.structures.icetower.IceTowerPieces;
import twilightforest.structures.lichtower.LichTowerPieces;
import twilightforest.structures.minotaurmaze.MinotaurMazePieces;
import twilightforest.structures.mushroomtower.MushroomTowerPieces;
import twilightforest.structures.start.TFStructure;
import twilightforest.structures.stronghold.StrongholdPieces;
import twilightforest.structures.trollcave.TrollCavePieces;
import twilightforest.world.ChunkGeneratorTwilightBase;

import java.util.HashMap;
import java.util.Map;

public class TFStructures {

	public static final Map<StructureFeature<?>, StructureFeatureConfiguration> SEPARATION_SETTINGS = new HashMap<>();

	public static final StructureFeature<NoneFeatureConfiguration> HEDGE_MAZE = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.HEDGE_MAZE);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_HEDGE_MAZE = HEDGE_MAZE.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> QUEST_GROVE = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.QUEST_GROVE);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_QUEST_GROVE = QUEST_GROVE.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> MUSHROOM_TOWER = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.MUSHROOM_TOWER);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_MUSHROOM_TOWER = MUSHROOM_TOWER.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> HOLLOW_HILL_SMALL = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.SMALL_HILL);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_HOLLOW_HILL_SMALL = HOLLOW_HILL_SMALL.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> HOLLOW_HILL_MEDIUM = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.MEDIUM_HILL);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_HOLLOW_HILL_MEDIUM = HOLLOW_HILL_MEDIUM.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> HOLLOW_HILL_LARGE = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.LARGE_HILL);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_HOLLOW_HILL_LARGE = HOLLOW_HILL_LARGE.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> NAGA_COURTYARD = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.NAGA_COURTYARD, true);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_NAGA_COURTYARD = NAGA_COURTYARD.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> LICH_TOWER = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.LICH_TOWER);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_LICH_TOWER = LICH_TOWER.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> LABYRINTH = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.LABYRINTH);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_LABYRINTH = LABYRINTH.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> HYDRA_LAIR = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.HYDRA_LAIR);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_HYDRA_LAIR = HYDRA_LAIR.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> KNIGHT_STRONGHOLD = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.KNIGHT_STRONGHOLD);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_KNIGHT_STRONGHOLD = KNIGHT_STRONGHOLD.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> DARK_TOWER = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.DARK_TOWER);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_DARK_TOWER = DARK_TOWER.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> YETI_CAVE = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.YETI_CAVE);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_YETI_CAVE = YETI_CAVE.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> AURORA_PALACE = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.ICE_TOWER);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_AURORA_PALACE = AURORA_PALACE.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> TROLL_CAVE = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.TROLL_CAVE);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_TROLL_CAVE = TROLL_CAVE.configured(FeatureConfiguration.NONE);

	public static final StructureFeature<NoneFeatureConfiguration> FINAL_CASTLE = new TFStructure<>(NoneFeatureConfiguration.CODEC, TFFeature.FINAL_CASTLE);
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_FINAL_CASTLE = FINAL_CASTLE.configured(FeatureConfiguration.NONE);

	public static void register(RegistryEvent.Register<StructureFeature<?>> event) {
		SEPARATION_SETTINGS.clear();
		TFFeature.init();
		new MushroomTowerPieces();
		new NagaCourtyardPieces();
		new LichTowerPieces();
		new MinotaurMazePieces();
		new StrongholdPieces();
		new DarkTowerPieces();
		new IceTowerPieces();
		new TrollCavePieces();
		new FinalCastlePieces();
		// FIXME Next version change, change the names to include underscores
		register(event, HEDGE_MAZE, CONFIGURED_HEDGE_MAZE, TwilightForestMod.prefix("hedgemaze"), 1, 2);
		register(event, QUEST_GROVE, CONFIGURED_QUEST_GROVE, TwilightForestMod.prefix("questgrove"), 1, 2);
		register(event, MUSHROOM_TOWER, CONFIGURED_MUSHROOM_TOWER, TwilightForestMod.prefix("mushroomtower"), 1, 2);
		register(event, HOLLOW_HILL_SMALL, CONFIGURED_HOLLOW_HILL_SMALL, TwilightForestMod.prefix("hollowhillsmall"), 1, 2);
		register(event, HOLLOW_HILL_MEDIUM, CONFIGURED_HOLLOW_HILL_MEDIUM, TwilightForestMod.prefix("hollowhillmedium"), 1, 2);
		register(event, HOLLOW_HILL_LARGE, CONFIGURED_HOLLOW_HILL_LARGE, TwilightForestMod.prefix("hollowhilllarge"), 1, 2);
		register(event, NAGA_COURTYARD, CONFIGURED_NAGA_COURTYARD, TwilightForestMod.prefix("courtyard"), 1, 2);
		register(event, LICH_TOWER, CONFIGURED_LICH_TOWER, TwilightForestMod.prefix("lichtower"), 1, 2);
		register(event, LABYRINTH, CONFIGURED_LABYRINTH, TwilightForestMod.prefix("labyrinth"), 1, 2);
		register(event, HYDRA_LAIR, CONFIGURED_HYDRA_LAIR, TwilightForestMod.prefix("hydralair"), 1, 2);
		register(event, KNIGHT_STRONGHOLD, CONFIGURED_KNIGHT_STRONGHOLD, TwilightForestMod.prefix("knightstronghold"), 1, 2);
		register(event, DARK_TOWER, CONFIGURED_DARK_TOWER, TwilightForestMod.prefix("darktower"), 1, 2);
		register(event, YETI_CAVE, CONFIGURED_YETI_CAVE, TwilightForestMod.prefix("yeticave"), 1, 2);
		register(event, AURORA_PALACE, CONFIGURED_AURORA_PALACE, TwilightForestMod.prefix("aurorapalace"), 1, 2);
		register(event, TROLL_CAVE, CONFIGURED_TROLL_CAVE, TwilightForestMod.prefix("trollcave"), 1, 2);
		register(event, FINAL_CASTLE, CONFIGURED_FINAL_CASTLE, TwilightForestMod.prefix("finalcastle"), 1, 2);
	}

	private static void register(RegistryEvent.Register<StructureFeature<?>> event, StructureFeature<?> structure, ConfiguredStructureFeature<?, ?> config, ResourceLocation name, int min, int max) {
		event.getRegistry().register(structure.setRegistryName(name));
		StructureFeature.STRUCTURES_REGISTRY.put(name.toString(), structure);
		StructureFeatureConfiguration seperation = new StructureFeatureConfiguration(max, min, 472681346);
		StructureSettings.DEFAULTS = ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder().putAll(StructureSettings.DEFAULTS).
				put(structure, seperation).build();
		SEPARATION_SETTINGS.put(structure, seperation);
		Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(name.getNamespace(), "configured_".concat(name.getPath())), config);
		FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(structure, config);
	}

	public static void load(WorldEvent.Load event) {
		if(event.getWorld() instanceof ServerLevel && ((ServerLevel) event.getWorld()).getChunkSource().generator instanceof ChunkGeneratorTwilightBase) {
			ServerLevel serverWorld = (ServerLevel)event.getWorld();
			Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
			tempMap.putAll(SEPARATION_SETTINGS);
			serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
		}
	}
}
