package twilightforest.world.feature.tree;

import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import twilightforest.worldgen.ConfiguredFeatures;

import java.util.Random;

public class CanopyTree extends AbstractTreeGrower {

	@Override
	public ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random rand, boolean largeHive) {
		return ConfiguredFeatures.CANOPY_TREE_BASE;
	}

}
