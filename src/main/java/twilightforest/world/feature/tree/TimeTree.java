package twilightforest.world.feature.tree;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import twilightforest.worldgen.ConfiguredFeatures;
import twilightforest.world.feature.config.TFTreeFeatureConfig;

public class TimeTree extends TFTree {

	@Override
	public ConfiguredFeature<TFTreeFeatureConfig, ?> createTreeFeature() {
		return ConfiguredFeatures.TIME_TREE_BASE;
	}
}
