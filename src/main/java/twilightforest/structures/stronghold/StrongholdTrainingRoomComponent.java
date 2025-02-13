package twilightforest.structures.stronghold;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import twilightforest.TFFeature;

import java.util.List;
import java.util.Random;

public class StrongholdTrainingRoomComponent extends StructureTFStrongholdComponent {

	public StrongholdTrainingRoomComponent(ServerLevel level, CompoundTag nbt) {
		super(StrongholdPieces.TFSTR, nbt);
	}

	public StrongholdTrainingRoomComponent(TFFeature feature, int i, Direction facing, int x, int y, int z) {
		super(StrongholdPieces.TFSTR, feature, i, facing, x, y, z);
	}

	@Override
	public BoundingBox generateBoundingBox(Direction facing, int x, int y, int z) {
		return StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -13, -1, 0, 18, 7, 18, facing);
	}

	@Override
	public void addChildren(StructurePiece parent, List<StructurePiece> list, Random random) {
		super.addChildren(parent, list, random);

		this.addDoor(13, 1, 0);
		addNewComponent(parent, list, random, Rotation.NONE, 4, 1, 18);
	}

	@Override
	public boolean postProcess(WorldGenLevel world, StructureFeatureManager manager, ChunkGenerator generator, Random rand, BoundingBox sbb, ChunkPos chunkPosIn, BlockPos blockPos) {
		placeStrongholdWalls(world, sbb, 0, 0, 0, 17, 6, 17, rand, deco.randomBlocks);

		// statues
		placeCornerStatue(world, 2, 1, 2, 0, sbb);
		placeCornerStatue(world, 15, 1, 15, 3, sbb);

		// sand floor
		this.generateMaybeBox(world, sbb, rand, 0.7F, 4, 0, 4, 8, 0, 8, Blocks.SAND.defaultBlockState(), Blocks.SAND.defaultBlockState(), false, false);
		this.generateMaybeBox(world, sbb, rand, 0.7F, 9, 0, 4, 13, 0, 8, Blocks.SAND.defaultBlockState(), Blocks.SAND.defaultBlockState(), false, false);
		this.generateMaybeBox(world, sbb, rand, 0.7F, 9, 0, 9, 13, 0, 13, Blocks.SAND.defaultBlockState(), Blocks.SAND.defaultBlockState(), false, false);

		// training dummies
		placeTrainingDummy(world, sbb, Rotation.NONE);
		placeTrainingDummy(world, sbb, Rotation.CLOCKWISE_90);
		placeTrainingDummy(world, sbb, Rotation.CLOCKWISE_180);

		// anvil pad
		this.generateBox(world, sbb, 5, 0, 10, 7, 0, 12, Blocks.COBBLESTONE.defaultBlockState(), Blocks.COBBLESTONE.defaultBlockState(), false);

		this.placeBlock(world, deco.pillarState, 5, 1, 12, sbb);
		this.placeBlock(world, deco.pillarState, 5, 2, 12, sbb);
		this.placeBlock(world, deco.pillarState, 6, 1, 12, sbb);
		this.placeBlock(world, deco.stairState.setValue(CarvedPumpkinBlock.FACING, Direction.EAST), 6, 2, 12, sbb);
		this.placeBlock(world, deco.stairState.setValue(CarvedPumpkinBlock.FACING, Direction.EAST), 7, 1, 12, sbb);
		this.placeBlock(world, deco.pillarState, 5, 1, 11, sbb);
		this.placeBlock(world, deco.stairState.setValue(CarvedPumpkinBlock.FACING, Direction.NORTH), 5, 2, 11, sbb);
		this.placeBlock(world, deco.stairState.setValue(CarvedPumpkinBlock.FACING, Direction.NORTH), 5, 1, 10, sbb);

		this.placeBlock(world, Blocks.ANVIL.defaultBlockState(), 6, 1, 11, sbb);

		// doors
		placeDoors(world, sbb);

		return true;
	}

	private void placeTrainingDummy(WorldGenLevel world, BoundingBox sbb, Rotation rotation) {
		this.fillBlocksRotated(world, sbb, 5, 0, 5, 7, 0, 7, Blocks.SAND.defaultBlockState(), rotation);
		this.setBlockStateRotated(world, deco.fenceState, 6, 1, 6, rotation, sbb);
		this.setBlockStateRotated(world, Blocks.BIRCH_PLANKS.defaultBlockState(), 6, 2, 6, rotation, sbb);
		this.setBlockStateRotated(world, Blocks.OAK_FENCE.defaultBlockState(), 5, 2, 6, rotation, sbb);
		this.setBlockStateRotated(world, Blocks.OAK_FENCE.defaultBlockState(), 7, 2, 6, rotation, sbb);
		this.setBlockStateRotated(world, Blocks.PUMPKIN.defaultBlockState()/*FIXME .with(CarvedPumpkinBlock.FACING, getStructureRelativeRotation(rotation))*/, 6, 3, 6, rotation, sbb);
	}
}
