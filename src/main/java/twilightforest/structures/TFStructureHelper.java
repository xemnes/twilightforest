package twilightforest.structures;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;

/**
 * Created by Joseph on 7/16/2017.
 */
public class TFStructureHelper {

    public static final BlockState stoneSlab = getSlab(Blocks.SMOOTH_STONE_SLAB);
    public static final BlockState stoneSlabTop = getSlabTop(Blocks.SMOOTH_STONE_SLAB);
    public static final BlockState stoneSlabDouble = Blocks.SMOOTH_STONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.DOUBLE);

    public static final BlockState birchSlab = getSlab(Blocks.BIRCH_SLAB);
    public static final BlockState birchSlabTop = getSlabTop(Blocks.BIRCH_SLAB);
    public static final BlockState birchPlanks = Blocks.BIRCH_PLANKS.defaultBlockState();


    private static BlockState getSlabType(Block type, SlabType side) {
        return type.defaultBlockState().setValue(SlabBlock.TYPE, side);
    }

    public static BlockState getSlab(Block type) {
        return getSlabType(type, SlabType.BOTTOM);
    }

    public static BlockState getSlabTop(Block type) {
        return getSlabType(type, SlabType.TOP);
    }

    public static BlockState randomPlant(int i) {
        if(i < 4) return randomSapling(i);
        else return randomMushroom(i-4);
    }

    public static BlockState randomSapling(int i) {
        switch (i) {
			case 0:
			default:
				return Blocks.OAK_SAPLING.defaultBlockState();
			case 1:
				return Blocks.SPRUCE_SAPLING.defaultBlockState();
			case 2:
				return Blocks.BIRCH_SAPLING.defaultBlockState();
			case 3:
				return Blocks.JUNGLE_SAPLING.defaultBlockState();
        }
    }

    public static BlockState randomMushroom(int i) {
        if(i == 0) return Blocks.RED_MUSHROOM.defaultBlockState();
        else return Blocks.BROWN_MUSHROOM.defaultBlockState();
    }
}
