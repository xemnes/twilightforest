package twilightforest.item;

import com.google.common.collect.Sets;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.ToolType;
import twilightforest.TFSounds;
import twilightforest.entity.ChainBlockEntity;
import twilightforest.entity.TFEntities;
import twilightforest.enums.TwilightItemTier;

import javax.annotation.Nullable;
import java.util.UUID;

import net.minecraft.world.item.Item.Properties;

public class ChainBlockItem extends DiggerItem {

	private static final String THROWN_UUID_KEY = "chainEntity";

	protected ChainBlockItem(Properties props) {
		super(6, -3.0F, TwilightItemTier.TOOL_KNIGHTLY, BlockTags.BASE_STONE_OVERWORLD, props);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity holder, int slot, boolean isSelected) {
		if (!world.isClientSide && getThrownUuid(stack) != null && getThrownEntity(world, stack) == null) {
			stack.getTag().remove(THROWN_UUID_KEY);
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (getThrownUuid(stack) != null)
			return new InteractionResultHolder<>(InteractionResult.PASS, stack);

		player.playSound(TFSounds.BLOCKCHAIN_FIRED, 1.0F, 1.0F / (world.random.nextFloat() * 0.4F + 1.2F));

		if (!world.isClientSide) {
			ChainBlockEntity launchedBlock = new ChainBlockEntity(TFEntities.chain_block, world, player, hand);
			world.addFreshEntity(launchedBlock);
			setThrownEntity(stack, launchedBlock);

			stack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(hand));
		}

		player.startUsingItem(hand);
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
	}

	@Nullable
	protected static UUID getThrownUuid(ItemStack stack) {
		if (stack.hasTag() && stack.getTag().hasUUID(THROWN_UUID_KEY)) {
			return stack.getTag().getUUID(THROWN_UUID_KEY);
		}

		return null;
	}

	@Nullable
	private static ChainBlockEntity getThrownEntity(Level world, ItemStack stack) {
		if (world instanceof ServerLevel) {
			UUID id = getThrownUuid(stack);
			if (id != null) {
				Entity e = ((ServerLevel) world).getEntity(id);
				if (e instanceof ChainBlockEntity) {
					return (ChainBlockEntity) e;
				}
			}
		}

		return null;
	}

	private static void setThrownEntity(ItemStack stack, ChainBlockEntity cube) {
		if (!stack.hasTag()) {
			stack.setTag(new CompoundTag());
		}
		stack.getTag().putUUID(THROWN_UUID_KEY, cube.getUUID());
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BLOCK;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return true;
	}

	@Override
	public int getHarvestLevel(ItemStack stack, ToolType tool, @Nullable Player player, @Nullable BlockState blockState) {
		if (tool == ToolType.PICKAXE) {
			return 2;
		} else {
			return -1;
		}
	}
}
