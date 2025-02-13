package twilightforest.data;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlocks;

import java.util.Set;
import java.util.function.Predicate;

public class BlockTagGenerator extends BlockTagsProvider {
    public static final Tag.Named<Block> TOWERWOOD = BlockTags.bind(TwilightForestMod.prefix("towerwood").toString());

    public static final Tag.Named<Block> TWILIGHT_OAK_LOGS = BlockTags.bind(TwilightForestMod.prefix("twilight_oak_logs").toString());
    public static final Tag.Named<Block> CANOPY_LOGS = BlockTags.bind(TwilightForestMod.prefix("canopy_logs").toString());
    public static final Tag.Named<Block> MANGROVE_LOGS = BlockTags.bind(TwilightForestMod.prefix("mangrove_logs").toString());
    public static final Tag.Named<Block> DARKWOOD_LOGS = BlockTags.bind(TwilightForestMod.prefix("darkwood_logs").toString());
    public static final Tag.Named<Block> TIME_LOGS = BlockTags.bind(TwilightForestMod.prefix("timewood_logs").toString());
    public static final Tag.Named<Block> TRANSFORMATION_LOGS = BlockTags.bind(TwilightForestMod.prefix("transwood_logs").toString());
    public static final Tag.Named<Block> MINING_LOGS = BlockTags.bind(TwilightForestMod.prefix("mining_logs").toString());
    public static final Tag.Named<Block> SORTING_LOGS = BlockTags.bind(TwilightForestMod.prefix("sortwood_logs").toString());

    public static final Tag.Named<Block> TF_LOGS = BlockTags.bind(TwilightForestMod.prefix("logs").toString());
    public static final Tag.Named<Block> TF_FENCES = BlockTags.bind(TwilightForestMod.prefix("fences").toString());
    public static final Tag.Named<Block> TF_FENCE_GATES = BlockTags.bind(TwilightForestMod.prefix("fence_gates").toString());

    public static final Tag.Named<Block> STORAGE_BLOCKS_ARCTIC_FUR = BlockTags.bind("forge:storage_blocks/arctic_fur");
    public static final Tag.Named<Block> STORAGE_BLOCKS_CARMINITE = BlockTags.bind("forge:storage_blocks/carminite");
    public static final Tag.Named<Block> STORAGE_BLOCKS_FIERY = BlockTags.bind("forge:storage_blocks/fiery");
    public static final Tag.Named<Block> STORAGE_BLOCKS_IRONWOOD = BlockTags.bind("forge:storage_blocks/ironwood");
    public static final Tag.Named<Block> STORAGE_BLOCKS_KNIGHTMETAL = BlockTags.bind("forge:storage_blocks/knightmetal");
    public static final Tag.Named<Block> STORAGE_BLOCKS_STEELEAF = BlockTags.bind("forge:storage_blocks/steeleaf");

    public static final Tag.Named<Block> ORES_IRONWOOD = BlockTags.bind("forge:ores/ironwood");
    public static final Tag.Named<Block> ORES_KNIGHTMETAL = BlockTags.bind("forge:ores/knightmetal");

    public static final Tag.Named<Block> PORTAL_EDGE = BlockTags.bind(TwilightForestMod.prefix("portal/edge").toString());
    public static final Tag.Named<Block> PORTAL_POOL = BlockTags.bind(TwilightForestMod.prefix("portal/fluid").toString());
    public static final Tag.Named<Block> PORTAL_DECO = BlockTags.bind(TwilightForestMod.prefix("portal/decoration").toString());

    public static final Tag.Named<Block> SPECIAL_POTS = BlockTags.bind(TwilightForestMod.prefix("dark_tower_excluded_pots").toString());
    public static final Tag.Named<Block> TROPHIES = BlockTags.bind(TwilightForestMod.prefix("trophies").toString());
    public static final Tag.Named<Block> FIRE_JET_FUEL = BlockTags.bind(TwilightForestMod.prefix("fire_jet_fuel").toString());

    public static final Tag.Named<Block> ANNIHILATION_INCLUSIONS = BlockTags.bind(TwilightForestMod.prefix("annihilation_inclusions").toString());
    public static final Tag.Named<Block> ANTIBUILDER_IGNORES = BlockTags.bind(TwilightForestMod.prefix("antibuilder_ignores").toString());
    public static final Tag.Named<Block> CARMINITE_REACTOR_IMMUNE = BlockTags.bind(TwilightForestMod.prefix("carminite_reactor_immune").toString());
    public static final Tag.Named<Block> STRUCTURE_BANNED_INTERACTIONS = BlockTags.bind(TwilightForestMod.prefix("structure_banned_interactions").toString());

    public static final Tag.Named<Block> ORE_MAGNET_SAFE_REPLACE_BLOCK = BlockTags.bind(TwilightForestMod.prefix("ore_magnet/ore_safe_replace_block").toString());
    public static final Tag.Named<Block> ORE_MAGNET_BLOCK_REPLACE_ORE = BlockTags.bind(TwilightForestMod.prefix("ore_magnet/block_replace_ore").toString());
    public static final Tag.Named<Block> ORE_MAGNET_STONE = BlockTags.bind(TwilightForestMod.prefix("ore_magnet/minecraft/stone").toString());
    public static final Tag.Named<Block> ORE_MAGNET_NETHERRACK = BlockTags.bind(TwilightForestMod.prefix("ore_magnet/minecraft/netherrack").toString());
    public static final Tag.Named<Block> ORE_MAGNET_END_STONE = BlockTags.bind(TwilightForestMod.prefix("ore_magnet/minecraft/end_stone").toString());
    public static final Tag.Named<Block> ORE_MAGNET_ROOT = BlockTags.bind(TwilightForestMod.prefix("ore_magnet/" + TwilightForestMod.ID + "/" + TFBlocks.root.getId().getPath()).toString());
    // TODO 1.17
    // public static final ITag.INamedTag<Block> ORE_MAGNET_DEEPSLATE = BlockTags.makeWrapperTag(TwilightForestMod.prefix("ore_magnet/minecraft/deepslate").toString());

    public BlockTagGenerator(DataGenerator generator, ExistingFileHelper exFileHelper) {
        super(generator, TwilightForestMod.ID, exFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags() {
        tag(TWILIGHT_OAK_LOGS)
                .add(TFBlocks.oak_log.get(), TFBlocks.stripped_oak_log.get(), TFBlocks.oak_wood.get(), TFBlocks.stripped_oak_wood.get());
        tag(CANOPY_LOGS)
                .add(TFBlocks.canopy_log.get(), TFBlocks.stripped_canopy_log.get(), TFBlocks.canopy_wood.get(), TFBlocks.stripped_canopy_wood.get());
        tag(MANGROVE_LOGS)
                .add(TFBlocks.mangrove_log.get(), TFBlocks.stripped_mangrove_log.get(), TFBlocks.mangrove_wood.get(), TFBlocks.stripped_mangrove_wood.get());
        tag(DARKWOOD_LOGS)
                .add(TFBlocks.dark_log.get(), TFBlocks.stripped_dark_log.get(), TFBlocks.dark_wood.get(), TFBlocks.stripped_dark_wood.get());
        tag(TIME_LOGS)
                .add(TFBlocks.time_log.get(), TFBlocks.stripped_time_log.get(), TFBlocks.time_wood.get(), TFBlocks.stripped_time_wood.get());
        tag(TRANSFORMATION_LOGS)
                .add(TFBlocks.transformation_log.get(), TFBlocks.stripped_transformation_log.get(), TFBlocks.transformation_wood.get(), TFBlocks.stripped_transformation_wood.get());
        tag(MINING_LOGS)
                .add(TFBlocks.mining_log.get(), TFBlocks.stripped_mining_log.get(), TFBlocks.mining_wood.get(), TFBlocks.stripped_mining_wood.get());
        tag(SORTING_LOGS)
                .add(TFBlocks.sorting_log.get(), TFBlocks.stripped_sorting_log.get(), TFBlocks.sorting_wood.get(), TFBlocks.stripped_sorting_wood.get());

        tag(TF_LOGS)
                .addTags(TWILIGHT_OAK_LOGS, CANOPY_LOGS, MANGROVE_LOGS, DARKWOOD_LOGS, TIME_LOGS, TRANSFORMATION_LOGS, MINING_LOGS, SORTING_LOGS);
        tag(BlockTags.LOGS)
                .addTag(TF_LOGS);

        tag(BlockTags.LOGS_THAT_BURN)
                .addTags(TWILIGHT_OAK_LOGS, CANOPY_LOGS, MANGROVE_LOGS, TIME_LOGS, TRANSFORMATION_LOGS, MINING_LOGS, SORTING_LOGS);

        tag(BlockTags.SAPLINGS)
                .add(TFBlocks.oak_sapling.get(), TFBlocks.canopy_sapling.get(), TFBlocks.mangrove_sapling.get(), TFBlocks.darkwood_sapling.get())
                .add(TFBlocks.time_sapling.get(), TFBlocks.transformation_sapling.get(), TFBlocks.mining_sapling.get(), TFBlocks.sorting_sapling.get())
                .add(TFBlocks.hollow_oak_sapling.get(), TFBlocks.rainboak_sapling.get());
        tag(BlockTags.LEAVES)
                .add(TFBlocks.rainboak_leaves.get(), TFBlocks.oak_leaves.get(), TFBlocks.canopy_leaves.get(), TFBlocks.mangrove_leaves.get(), TFBlocks.dark_leaves.get())
                .add(TFBlocks.time_leaves.get(), TFBlocks.transformation_leaves.get(), TFBlocks.mining_leaves.get(), TFBlocks.sorting_leaves.get())
                .add(TFBlocks.thorn_leaves.get(), TFBlocks.beanstalk_leaves.get()/*, TFBlocks.giant_leaves.get()*/);

        tag(BlockTags.PLANKS)
                .add(TFBlocks.twilight_oak_planks.get(), TFBlocks.canopy_planks.get(), TFBlocks.mangrove_planks.get(), TFBlocks.dark_planks.get())
                .add(TFBlocks.time_planks.get(), TFBlocks.trans_planks.get(), TFBlocks.mine_planks.get(), TFBlocks.sort_planks.get())
                .add(TFBlocks.tower_wood.get(), TFBlocks.tower_wood_encased.get(), TFBlocks.tower_wood_cracked.get(), TFBlocks.tower_wood_mossy.get(), TFBlocks.tower_wood_infested.get());

        tag(TF_FENCES)
                .add(TFBlocks.twilight_oak_fence.get(), TFBlocks.canopy_fence.get(), TFBlocks.mangrove_fence.get(), TFBlocks.dark_fence.get())
                .add(TFBlocks.time_fence.get(), TFBlocks.trans_fence.get(), TFBlocks.mine_fence.get(), TFBlocks.sort_fence.get());
        tag(TF_FENCE_GATES)
                .add(TFBlocks.twilight_oak_gate.get(), TFBlocks.canopy_gate.get(), TFBlocks.mangrove_gate.get(), TFBlocks.dark_gate.get())
                .add(TFBlocks.time_gate.get(), TFBlocks.trans_gate.get(), TFBlocks.mine_gate.get(), TFBlocks.sort_gate.get());
        tag(BlockTags.WOODEN_FENCES)
                .add(TFBlocks.twilight_oak_fence.get(), TFBlocks.canopy_fence.get(), TFBlocks.mangrove_fence.get(), TFBlocks.dark_fence.get())
                .add(TFBlocks.time_fence.get(), TFBlocks.trans_fence.get(), TFBlocks.mine_fence.get(), TFBlocks.sort_fence.get());
        tag(BlockTags.FENCE_GATES)
                .add(TFBlocks.twilight_oak_gate.get(), TFBlocks.canopy_gate.get(), TFBlocks.mangrove_gate.get(), TFBlocks.dark_gate.get())
                .add(TFBlocks.time_gate.get(), TFBlocks.trans_gate.get(), TFBlocks.mine_gate.get(), TFBlocks.sort_gate.get());
        tag(Tags.Blocks.FENCES)
                .add(TFBlocks.twilight_oak_fence.get(), TFBlocks.canopy_fence.get(), TFBlocks.mangrove_fence.get(), TFBlocks.dark_fence.get())
                .add(TFBlocks.time_fence.get(), TFBlocks.trans_fence.get(), TFBlocks.mine_fence.get(), TFBlocks.sort_fence.get());
        tag(Tags.Blocks.FENCE_GATES)
                .add(TFBlocks.twilight_oak_gate.get(), TFBlocks.canopy_gate.get(), TFBlocks.mangrove_gate.get(), TFBlocks.dark_gate.get())
                .add(TFBlocks.time_gate.get(), TFBlocks.trans_gate.get(), TFBlocks.mine_gate.get(), TFBlocks.sort_gate.get());
        tag(Tags.Blocks.FENCES_WOODEN)
                .add(TFBlocks.twilight_oak_fence.get(), TFBlocks.canopy_fence.get(), TFBlocks.mangrove_fence.get(), TFBlocks.dark_fence.get())
                .add(TFBlocks.time_fence.get(), TFBlocks.trans_fence.get(), TFBlocks.mine_fence.get(), TFBlocks.sort_fence.get());
        tag(Tags.Blocks.FENCE_GATES_WOODEN)
                .add(TFBlocks.twilight_oak_gate.get(), TFBlocks.canopy_gate.get(), TFBlocks.mangrove_gate.get(), TFBlocks.dark_gate.get())
                .add(TFBlocks.time_gate.get(), TFBlocks.trans_gate.get(), TFBlocks.mine_gate.get(), TFBlocks.sort_gate.get());

        tag(BlockTags.WOODEN_SLABS)
                .add(TFBlocks.twilight_oak_slab.get(), TFBlocks.canopy_slab.get(), TFBlocks.mangrove_slab.get(), TFBlocks.dark_slab.get())
                .add(TFBlocks.time_slab.get(), TFBlocks.trans_slab.get(), TFBlocks.mine_slab.get(), TFBlocks.sort_slab.get());
        tag(BlockTags.SLABS)
                .add(TFBlocks.aurora_slab.get());
        tag(BlockTags.WOODEN_STAIRS)
                .add(TFBlocks.twilight_oak_stairs.get(), TFBlocks.canopy_stairs.get(), TFBlocks.mangrove_stairs.get(), TFBlocks.dark_stairs.get())
                .add(TFBlocks.time_stairs.get(), TFBlocks.trans_stairs.get(), TFBlocks.mine_stairs.get(), TFBlocks.sort_stairs.get());
        tag(BlockTags.STAIRS)
                .add(TFBlocks.castle_stairs_brick.get(), TFBlocks.castle_stairs_worn.get(), TFBlocks.castle_stairs_cracked.get(), TFBlocks.castle_stairs_mossy.get(), TFBlocks.castle_stairs_encased.get(), TFBlocks.castle_stairs_bold.get())
                .add(TFBlocks.nagastone_stairs_left.get(), TFBlocks.nagastone_stairs_right.get(), TFBlocks.nagastone_stairs_mossy_left.get(), TFBlocks.nagastone_stairs_mossy_right.get(), TFBlocks.nagastone_stairs_weathered_left.get(), TFBlocks.nagastone_stairs_weathered_right.get());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(TFBlocks.twilight_oak_button.get(), TFBlocks.canopy_button.get(), TFBlocks.mangrove_button.get(), TFBlocks.dark_button.get())
                .add(TFBlocks.time_button.get(), TFBlocks.trans_button.get(), TFBlocks.mine_button.get(), TFBlocks.sort_button.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(TFBlocks.twilight_oak_plate.get(), TFBlocks.canopy_plate.get(), TFBlocks.mangrove_plate.get(), TFBlocks.dark_plate.get())
                .add(TFBlocks.time_plate.get(), TFBlocks.trans_plate.get(), TFBlocks.mine_plate.get(), TFBlocks.sort_plate.get());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(TFBlocks.twilight_oak_trapdoor.get(), TFBlocks.canopy_trapdoor.get(), TFBlocks.mangrove_trapdoor.get(), TFBlocks.dark_trapdoor.get())
                .add(TFBlocks.time_trapdoor.get(), TFBlocks.trans_trapdoor.get(), TFBlocks.mine_trapdoor.get(), TFBlocks.sort_trapdoor.get());
        tag(BlockTags.WOODEN_DOORS)
                .add(TFBlocks.twilight_oak_door.get(), TFBlocks.canopy_door.get(), TFBlocks.mangrove_door.get(), TFBlocks.dark_door.get())
                .add(TFBlocks.time_door.get(), TFBlocks.trans_door.get(), TFBlocks.mine_door.get(), TFBlocks.sort_door.get());
        tag(BlockTags.FLOWER_POTS)
                .add(TFBlocks.potted_twilight_oak_sapling.get(), TFBlocks.potted_canopy_sapling.get(), TFBlocks.potted_mangrove_sapling.get(), TFBlocks.potted_darkwood_sapling.get(), TFBlocks.potted_rainboak_sapling.get())
                .add(TFBlocks.potted_hollow_oak_sapling.get(), TFBlocks.potted_time_sapling.get(), TFBlocks.potted_trans_sapling.get(), TFBlocks.potted_mine_sapling.get(), TFBlocks.potted_sort_sapling.get())
                .add(TFBlocks.potted_mayapple.get(), TFBlocks.potted_fiddlehead.get(), TFBlocks.potted_mushgloom.get(), TFBlocks.potted_thorn.get(), TFBlocks.potted_green_thorn.get(), TFBlocks.potted_dead_thorn.get());
        tag(BlockTags.STRIDER_WARM_BLOCKS).add(TFBlocks.fiery_block.get());
        tag(BlockTags.PORTALS).add(TFBlocks.twilight_portal.get());
        tag(BlockTags.CLIMBABLE).add(TFBlocks.iron_ladder.get());

        tag(BlockTags.SIGNS).add(TFBlocks.twilight_oak_sign.get(), TFBlocks.twilight_wall_sign.get(),
                TFBlocks.canopy_sign.get(), TFBlocks.canopy_wall_sign.get(),
                TFBlocks.mangrove_sign.get(), TFBlocks.mangrove_wall_sign.get(),
                TFBlocks.darkwood_sign.get(), TFBlocks.darkwood_wall_sign.get(),
                TFBlocks.time_sign.get(), TFBlocks.time_wall_sign.get(),
                TFBlocks.trans_sign.get(), TFBlocks.trans_wall_sign.get(),
                TFBlocks.mine_sign.get(), TFBlocks.mine_wall_sign.get(),
                TFBlocks.sort_sign.get(), TFBlocks.sort_wall_sign.get());

        tag(BlockTags.STANDING_SIGNS).add(TFBlocks.twilight_oak_sign.get(), TFBlocks.canopy_sign.get(),
                TFBlocks.mangrove_sign.get(), TFBlocks.darkwood_sign.get(),
                TFBlocks.time_sign.get(), TFBlocks.trans_sign.get(),
                TFBlocks.mine_sign.get(), TFBlocks.sort_sign.get());

        tag(BlockTags.WALL_SIGNS).add(TFBlocks.twilight_wall_sign.get(), TFBlocks.canopy_wall_sign.get(),
                TFBlocks.mangrove_wall_sign.get(), TFBlocks.darkwood_wall_sign.get(),
                TFBlocks.time_wall_sign.get(), TFBlocks.trans_wall_sign.get(),
                TFBlocks.mine_wall_sign.get(), TFBlocks.sort_wall_sign.get());

        tag(TOWERWOOD).add(TFBlocks.tower_wood.get(), TFBlocks.tower_wood_mossy.get(), TFBlocks.tower_wood_cracked.get(), TFBlocks.tower_wood_infested.get());

        tag(STORAGE_BLOCKS_ARCTIC_FUR).add(TFBlocks.arctic_fur_block.get());
        tag(STORAGE_BLOCKS_CARMINITE).add(TFBlocks.carminite_block.get());
        tag(STORAGE_BLOCKS_FIERY).add(TFBlocks.fiery_block.get());
        tag(STORAGE_BLOCKS_IRONWOOD).add(TFBlocks.ironwood_block.get());
        tag(STORAGE_BLOCKS_KNIGHTMETAL).add(TFBlocks.knightmetal_block.get());
        tag(STORAGE_BLOCKS_STEELEAF).add(TFBlocks.steeleaf_block.get());

        tag(BlockTags.BEACON_BASE_BLOCKS).addTags(
                STORAGE_BLOCKS_ARCTIC_FUR,
                STORAGE_BLOCKS_CARMINITE,
                STORAGE_BLOCKS_FIERY,
                STORAGE_BLOCKS_IRONWOOD,
                STORAGE_BLOCKS_KNIGHTMETAL,
                STORAGE_BLOCKS_STEELEAF
        );

        tag(Tags.Blocks.STORAGE_BLOCKS).addTags(STORAGE_BLOCKS_ARCTIC_FUR, STORAGE_BLOCKS_CARMINITE, STORAGE_BLOCKS_FIERY,  STORAGE_BLOCKS_IRONWOOD, STORAGE_BLOCKS_KNIGHTMETAL, STORAGE_BLOCKS_STEELEAF);

        tag(Tags.Blocks.ORES).addTags(ORES_IRONWOOD, ORES_KNIGHTMETAL);
        tag(ORES_IRONWOOD);
        tag(ORES_KNIGHTMETAL);

        tag(PORTAL_EDGE)
                .add(Blocks.GRASS_BLOCK, Blocks.MYCELIUM).add(getAllFilteredBlocks(b -> /*b.material == Material.ORGANIC ||*/ b.material == Material.DIRT));
        // So yes, we could do fluid tags for the portal pool but the problem is that we're -replacing- the block, effectively replacing what would be waterlogged, with the portal block
        // In the future if we can "portal log" blocks then we can re-explore doing it as a fluid
        tag(PORTAL_POOL).add(Blocks.WATER);
        tag(PORTAL_DECO)
                .addTags(BlockTags.FLOWERS, BlockTags.LEAVES, BlockTags.SAPLINGS, BlockTags.CROPS)
                .add(Blocks.BAMBOO)
                .add(getAllFilteredBlocks(b -> (b.material == Material.PLANT || b.material == Material.REPLACEABLE_PLANT || b.material == Material.LEAVES) && !plants.contains(b)));

        tag(SPECIAL_POTS)
                .add(TFBlocks.potted_thorn.get(), TFBlocks.potted_green_thorn.get(), TFBlocks.potted_dead_thorn.get())
                .add(TFBlocks.potted_hollow_oak_sapling.get(), TFBlocks.potted_time_sapling.get(), TFBlocks.potted_trans_sapling.get())
                .add(TFBlocks.potted_mine_sapling.get(), TFBlocks.potted_sort_sapling.get());

        tag(TROPHIES)
                .add(TFBlocks.naga_trophy.get(), TFBlocks.naga_wall_trophy.get())
                .add(TFBlocks.lich_trophy.get(), TFBlocks.lich_wall_trophy.get())
                .add(TFBlocks.minoshroom_trophy.get(), TFBlocks.minoshroom_wall_trophy.get())
                .add(TFBlocks.hydra_trophy.get(), TFBlocks.hydra_wall_trophy.get())
                .add(TFBlocks.knight_phantom_trophy.get(), TFBlocks.knight_phantom_wall_trophy.get())
                .add(TFBlocks.ur_ghast_trophy.get(), TFBlocks.ur_ghast_wall_trophy.get())
                .add(TFBlocks.yeti_trophy.get(), TFBlocks.yeti_wall_trophy.get())
                .add(TFBlocks.snow_queen_trophy.get(), TFBlocks.snow_queen_wall_trophy.get())
                .add(TFBlocks.quest_ram_trophy.get(), TFBlocks.quest_ram_wall_trophy.get());

        tag(FIRE_JET_FUEL).add(Blocks.LAVA);

        // TODO Test behavior with giant blocks for immunity stuffs
        tag(BlockTags.DRAGON_IMMUNE)
                .add(TFBlocks.boss_spawner_naga.get(), TFBlocks.boss_spawner_lich.get())
                .add(TFBlocks.boss_spawner_minoshroom.get(), TFBlocks.boss_spawner_hydra.get())
                .add(TFBlocks.boss_spawner_knight_phantom.get(), TFBlocks.boss_spawner_ur_ghast.get())
                .add(TFBlocks.boss_spawner_alpha_yeti.get(), TFBlocks.boss_spawner_snow_queen.get())
                .add(TFBlocks.boss_spawner_final_boss.get())
                .add(TFBlocks.force_field_pink.get(), TFBlocks.force_field_orange.get(), TFBlocks.force_field_green.get(), TFBlocks.force_field_blue.get(), TFBlocks.force_field_purple.get())
                .add(TFBlocks.giant_obsidian.get(), TFBlocks.stronghold_shield.get(), TFBlocks.vanishing_block.get(), TFBlocks.locked_vanishing_block.get(), TFBlocks.keepsake_casket.get());

        tag(BlockTags.WITHER_IMMUNE)
                .add(TFBlocks.boss_spawner_naga.get(), TFBlocks.boss_spawner_lich.get())
                .add(TFBlocks.boss_spawner_minoshroom.get(), TFBlocks.boss_spawner_hydra.get())
                .add(TFBlocks.boss_spawner_knight_phantom.get(), TFBlocks.boss_spawner_ur_ghast.get())
                .add(TFBlocks.boss_spawner_alpha_yeti.get(), TFBlocks.boss_spawner_snow_queen.get())
                .add(TFBlocks.boss_spawner_final_boss.get())
                .add(TFBlocks.force_field_pink.get(), TFBlocks.force_field_orange.get(), TFBlocks.force_field_green.get(), TFBlocks.force_field_blue.get(), TFBlocks.force_field_purple.get())
                .add(TFBlocks.stronghold_shield.get(), TFBlocks.vanishing_block.get(), TFBlocks.locked_vanishing_block.get(), TFBlocks.keepsake_casket.get());

        tag(CARMINITE_REACTOR_IMMUNE)
                // [VanillaCopy] WITHER_IMMUNE - Do NOT put that tag in this tag
                .add(Blocks.BARRIER, Blocks.BEDROCK, Blocks.END_PORTAL, Blocks.END_PORTAL_FRAME, Blocks.END_GATEWAY, Blocks.COMMAND_BLOCK, Blocks.REPEATING_COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.STRUCTURE_BLOCK, Blocks.JIGSAW, Blocks.MOVING_PISTON)
                .add(TFBlocks.boss_spawner_naga.get(), TFBlocks.boss_spawner_lich.get())
                .add(TFBlocks.boss_spawner_minoshroom.get(), TFBlocks.boss_spawner_hydra.get())
                .add(TFBlocks.boss_spawner_knight_phantom.get(), TFBlocks.boss_spawner_ur_ghast.get())
                .add(TFBlocks.boss_spawner_alpha_yeti.get(), TFBlocks.boss_spawner_snow_queen.get())
                .add(TFBlocks.boss_spawner_final_boss.get())
                .add(TFBlocks.force_field_pink.get(), TFBlocks.force_field_orange.get(), TFBlocks.force_field_green.get(), TFBlocks.force_field_blue.get(), TFBlocks.force_field_purple.get())
                .add(TFBlocks.stronghold_shield.get(), TFBlocks.vanishing_block.get(), TFBlocks.locked_vanishing_block.get(), TFBlocks.keepsake_casket.get());

        tag(ANNIHILATION_INCLUSIONS)
                .add(TFBlocks.deadrock.get(), TFBlocks.deadrock_cracked.get(), TFBlocks.deadrock_weathered.get())
                .add(TFBlocks.castle_brick.get(), TFBlocks.castle_brick_cracked.get(), TFBlocks.castle_brick_frame.get(), TFBlocks.castle_brick_mossy.get(), TFBlocks.castle_brick_roof.get(), TFBlocks.castle_brick_worn.get())
                .add(TFBlocks.castle_rune_brick_blue.get(), TFBlocks.castle_rune_brick_purple.get(), TFBlocks.castle_rune_brick_yellow.get(), TFBlocks.castle_rune_brick_pink.get())
                .add(TFBlocks.force_field_pink.get(), TFBlocks.force_field_orange.get(), TFBlocks.force_field_green.get(), TFBlocks.force_field_blue.get(), TFBlocks.force_field_purple.get())
                .add(TFBlocks.brown_thorns.get(), TFBlocks.green_thorns.get());

        tag(ANTIBUILDER_IGNORES)
                .add(Blocks.BEDROCK, Blocks.REDSTONE_LAMP, Blocks.TNT, Blocks.WATER)
                .add(TFBlocks.antibuilder.get(), TFBlocks.carminite_builder.get(), TFBlocks.built_block.get())
                .add(TFBlocks.reactor_debris.get(), TFBlocks.carminite_reactor.get(), TFBlocks.fake_diamond.get(), TFBlocks.fake_gold.get())
                .add(TFBlocks.vanishing_block.get(), TFBlocks.reappearing_block.get(), TFBlocks.locked_vanishing_block.get(), TFBlocks.ghast_trap.get())
                .add(TFBlocks.keepsake_casket.get()).addOptional(new ResourceLocation("gravestone:gravestone"));

        tag(STRUCTURE_BANNED_INTERACTIONS)
                .addTags(BlockTags.BUTTONS, Tags.Blocks.CHESTS).add(Blocks.LEVER)
                .add(TFBlocks.antibuilder.get());

        tag(ORE_MAGNET_SAFE_REPLACE_BLOCK)
                .addTags(ORE_MAGNET_BLOCK_REPLACE_ORE, Tags.Blocks.DIRT, Tags.Blocks.GRAVEL, Tags.Blocks.SAND);

        tag(ORE_MAGNET_BLOCK_REPLACE_ORE)
                .add(Blocks.STONE, Blocks.NETHERRACK, Blocks.END_STONE, TFBlocks.root.get());

        // Don't add general ore taglists here, since those will include non-stone ores
        tag(ORE_MAGNET_STONE)
                .add(Blocks.GOLD_ORE, Blocks.IRON_ORE, /* Blocks.COAL_ORE //No, not you */ Blocks.LAPIS_ORE, Blocks.DIAMOND_ORE, Blocks.REDSTONE_ORE, Blocks.EMERALD_ORE);

        tag(ORE_MAGNET_NETHERRACK)
                .add(Blocks.NETHER_GOLD_ORE, Blocks.NETHER_QUARTZ_ORE);

        tag(ORE_MAGNET_END_STONE)
                // Using a Quark ore as an example filler
                .addOptional(new ResourceLocation("quark", "biotite_ore"));

        tag(ORE_MAGNET_ROOT)
                .add(TFBlocks.liveroot_block.get());
    }

    private static Block[] getAllFilteredBlocks(Predicate<Block> predicate) {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(b -> b.getRegistryName() != null && (b.getRegistryName().getNamespace().equals(TwilightForestMod.ID) || b.getRegistryName().getNamespace().equals("minecraft")) && predicate.test(b))
                .toArray(Block[]::new);
    }

    //private static final MutableBoolean bool = new MutableBoolean();
    //private static final Map<ResourceLocation, ITag<Block>> map = Maps.newHashMap();
    //private static final Function<ResourceLocation, ITag<Block>> mapGetter = map::get;
    private static final Set<Block> plants;
    static {
        plants = ImmutableSet.<Block>builder().add(
                Blocks.DANDELION, Blocks.POPPY, Blocks.BLUE_ORCHID, Blocks.ALLIUM, Blocks.AZURE_BLUET, Blocks.RED_TULIP, Blocks.ORANGE_TULIP, Blocks.WHITE_TULIP, Blocks.PINK_TULIP, Blocks.OXEYE_DAISY, Blocks.CORNFLOWER, Blocks.LILY_OF_THE_VALLEY, Blocks.WITHER_ROSE, // BlockTags.FLOWERS
                Blocks.SUNFLOWER, Blocks.LILAC, Blocks.PEONY, Blocks.ROSE_BUSH, // BlockTags.FLOWERS
                Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, // BlockTags.LEAVES
                Blocks.OAK_SAPLING, Blocks.SPRUCE_SAPLING, Blocks.BIRCH_SAPLING, Blocks.JUNGLE_SAPLING, Blocks.ACACIA_SAPLING, Blocks.DARK_OAK_SAPLING, // BlockTags.SAPLINGS
                Blocks.BEETROOTS, Blocks.CARROTS, Blocks.POTATOES, Blocks.WHEAT, Blocks.MELON_STEM, Blocks.PUMPKIN_STEM, // BlockTags.CROPS

                // TF addons of above taglists
                TFBlocks.oak_sapling.get(), TFBlocks.canopy_sapling.get(), TFBlocks.mangrove_sapling.get(), TFBlocks.darkwood_sapling.get(),
                TFBlocks.time_sapling.get(), TFBlocks.transformation_sapling.get(), TFBlocks.mining_sapling.get(), TFBlocks.sorting_sapling.get(),
                TFBlocks.hollow_oak_sapling.get(), TFBlocks.rainboak_sapling.get(),
                TFBlocks.rainboak_leaves.get(), TFBlocks.oak_leaves.get(), TFBlocks.canopy_leaves.get(), TFBlocks.mangrove_leaves.get(), TFBlocks.dark_leaves.get(),
                TFBlocks.time_leaves.get(), TFBlocks.transformation_leaves.get(), TFBlocks.mining_leaves.get(), TFBlocks.sorting_leaves.get(),
                TFBlocks.thorn_leaves.get(), TFBlocks.beanstalk_leaves.get()
        ).build();
    }

    private boolean isExcludedFromTagBuilder(Block block, Tag.Named<Block> tag) {
		/* FIXME make this work somehow
		getOrCreateBuilder(tag)
				.getInternalBuilder()
				.getProxyStream()
				.forEach(proxy -> proxy
						.getEntry()
						.matches(mapGetter, ForgeRegistries.BLOCKS::getValue, i -> bool.setValue(block.equals(i)))
				);*/

        return !plants.contains(block);
    }
}
