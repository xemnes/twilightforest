package twilightforest.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelSimulatedRW;
import twilightforest.util.FeatureUtil;
import twilightforest.world.TFGenerationSettings;
import twilightforest.world.feature.config.TFTreeFeatureConfig;

import java.util.Random;
import java.util.Set;

public class TFGenLargeWinter extends TFTreeGenerator<TFTreeFeatureConfig> {

	public TFGenLargeWinter(Codec<TFTreeFeatureConfig> config) {
		super(config);
	}

	@Override
	protected boolean generate(LevelAccessor world, Random random, BlockPos pos, Set<BlockPos> trunk, Set<BlockPos> leaves, Set<BlockPos> branch, Set<BlockPos> root, BoundingBox mbb, TFTreeFeatureConfig config) {
		// determine a height
		int treeHeight = 35;
		if (random.nextInt(3) == 0) {
			treeHeight += random.nextInt(10);

			if (random.nextInt(8) == 0) {
				treeHeight += random.nextInt(10);
			}
		}

		if (pos.getY() >= TFGenerationSettings.MAXHEIGHT - treeHeight) {
			return false;
		}

		// check if we're on dirt or grass
		BlockState state = world.getBlockState(pos.below());
		if (!state.getBlock().canSustainPlant(state, world, pos.below(), Direction.UP, config.getSapling(random, pos))) {
			return false;
		}

		//okay build a tree!  Go up to the height
		buildTrunk(world, random, pos, trunk, treeHeight, mbb, config);

		// make leaves
		makeLeaves(world, random, pos, treeHeight, trunk, leaves, mbb, config);

		// roots!
		int numRoots = 4 + random.nextInt(3);
		float offset = random.nextFloat();
		for (int b = 0; b < numRoots; b++) {
			buildRoot(world, random, pos, root, offset, b, mbb, config);
		}

		return true;
	}

	private void makeLeaves(LevelAccessor world, Random random, BlockPos pos, int treeHeight, Set<BlockPos> trunk, Set<BlockPos> leaves, BoundingBox mbb, TFTreeFeatureConfig config) {
		int offGround = 3;
		int leafType = 1;

		for (int dy = 0; dy < treeHeight; dy++) {

			int radius = leafRadius(treeHeight, dy, leafType);

			FeatureUtil.makeLeafCircle2(world, pos.above(offGround + treeHeight - dy), radius, config.leavesProvider.getState(random, pos.above(offGround + treeHeight - dy)), leaves);
			this.makePineBranches(world, random, pos.above(offGround + treeHeight - dy), trunk, radius, mbb, config);
		}
	}

	private void makePineBranches(LevelAccessor world, Random rand, BlockPos pos, Set<BlockPos> trunk, int radius, BoundingBox mbb, TFTreeFeatureConfig config) {
		int branchLength = radius > 4 ? radius - 1 : radius - 2;

		switch (pos.getY() % 2) {
			case 0:
				// branches
				for (int i = 1; i <= branchLength; i++) {
					this.placeLogAt(world, rand, pos.offset(-i, 0, 0), Direction.Axis.X, trunk, mbb, config);
					this.placeLogAt(world, rand, pos.offset(0, 0, i + 1), Direction.Axis.Z, trunk, mbb, config);
					this.placeLogAt(world, rand, pos.offset(i + 1, 0, 1), Direction.Axis.X, trunk, mbb, config);
					this.placeLogAt(world, rand, pos.offset(1, 0, -i), Direction.Axis.Z, trunk, mbb, config);
				}
				break;
			case 1:
				for (int i = 1; i <= branchLength; i++) {
					this.placeLogAt(world, rand, pos.offset(-1, 0, 1), Direction.Axis.X, trunk, mbb, config);
					this.placeLogAt(world, rand, pos.offset(1, 0, i + 1), Direction.Axis.Z, trunk, mbb, config);
					this.placeLogAt(world, rand, pos.offset(i + 1, 0, 0), Direction.Axis.X, trunk, mbb, config);
					this.placeLogAt(world, rand, pos.offset(0, 0, -i), Direction.Axis.Z, trunk, mbb, config);
				}
				break;
		}
	}

	private void placeLogAt(LevelSimulatedRW reader, Random rand, BlockPos pos, Direction.Axis axis, Set<BlockPos> logPos, BoundingBox boundingBox, TFTreeFeatureConfig config) {
		this.setBlockState(reader, pos, config.trunkProvider.getState(rand, pos).setValue(RotatedPillarBlock.AXIS, axis), boundingBox);
		logPos.add(pos.immutable());
	}

	private int leafRadius(int treeHeight, int dy, int functionType) {
		switch (functionType) {
			case 0:
			default:
				return (dy - 1) % 4;
			case 1:
				return (int) (4F * dy / treeHeight + (0.75F * dy % 3));
			case 99:
				return (treeHeight - (dy / 2) - 1) % 4; // bad
		}
	}

	private void buildTrunk(LevelAccessor world, Random rand, BlockPos pos, Set<BlockPos> trunk, int treeHeight, BoundingBox mbb, TFTreeFeatureConfig config) {
		for (int dy = 0; dy < treeHeight; dy++) {
			this.setLogBlockState(world, rand, pos.offset(0, dy, 0), trunk, mbb, config);
			this.setLogBlockState(world, rand, pos.offset(1, dy, 0), trunk, mbb, config);
			this.setLogBlockState(world, rand, pos.offset(0, dy, 1), trunk, mbb, config);
			this.setLogBlockState(world, rand, pos.offset(1, dy, 1), trunk, mbb, config);
		}
	}
}
