package twilightforest.structures.icetower;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.core.Direction;
import twilightforest.block.TFBlocks;
import twilightforest.structures.TFStructureDecorator;

public class IceTowerDecorator extends TFStructureDecorator {

	public IceTowerDecorator() {
		this.blockState = TFBlocks.aurora_block.get().defaultBlockState();
		this.accentState = Blocks.BIRCH_PLANKS.defaultBlockState();
		this.fenceState = Blocks.OAK_FENCE.defaultBlockState();
		this.stairState = Blocks.BIRCH_STAIRS.defaultBlockState();
		this.pillarState = TFBlocks.aurora_pillar.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
		this.platformState = Blocks.BIRCH_SLAB.defaultBlockState();
		this.floorState = Blocks.BIRCH_PLANKS.defaultBlockState();
		this.randomBlocks = new IceTowerProcessor();
	}

}
