package twilightforest.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import twilightforest.entity.boss.LichEntity;
import twilightforest.item.TFItems;

import java.util.EnumSet;

import net.minecraft.world.entity.ai.goal.Goal.Flag;

public class LichShadowsGoal extends Goal {

	private final LichEntity lich;

	public LichShadowsGoal(LichEntity boss) {
		lich = boss;
		setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		return lich.getPhase() == 1;
	}

	@Override
	public void start() {
		lich.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(TFItems.twilight_scepter.get()));
	}

	@Override
	public void stop() {
		despawnClones();
	}

	@Override
	public void tick() {
		if (lich.isShadowClone())
			checkForMaster();
		LivingEntity targetedEntity = lich.getTarget();
		if (targetedEntity == null)
			return;
		float dist = lich.distanceTo(targetedEntity);

		if (lich.getAttackCooldown() == 60) {
			lich.teleportToSightOfEntity(targetedEntity);

			if (!lich.isShadowClone()) {
				checkAndSpawnClones();
			}
		}

		if (lich.getSensing().hasLineOfSight(targetedEntity) && lich.getAttackCooldown() == 0 && dist < 20F) {
			if (lich.getNextAttackType() == 0) {
				lich.launchBoltAt();
			} else {
				lich.launchBombAt();
			}

			if (lich.getRandom().nextInt(3) > 0) {
				lich.setNextAttackType(0);
			} else {
				lich.setNextAttackType(1);
			}
			lich.setAttackCooldown(100);
		}
	}

	private void checkForMaster() {
		if (lich.getMasterLich() == null) {
			findNewMaster();
		}
		if (!lich.level.isClientSide && (lich.getMasterLich() == null || !lich.getMasterLich().isAlive())) {
			lich.discard();
		}
	}

	private void checkAndSpawnClones() {
		// if not, spawn one!
		if (lich.countMyClones() < LichEntity.MAX_SHADOW_CLONES)
			spawnShadowClone();
	}

	private void spawnShadowClone() {
		LivingEntity targetedEntity = lich.getTarget();

		// find a good spot
		Vec3 cloneSpot = lich.findVecInLOSOf(targetedEntity);

		if (cloneSpot != null) {
			// put a clone there
			LichEntity newClone = new LichEntity(lich.level, lich);
			newClone.setPos(cloneSpot.x, cloneSpot.y, cloneSpot.z);
			lich.level.addFreshEntity(newClone);

			newClone.setTarget(targetedEntity);
			newClone.setAttackCooldown(60 + lich.getRandom().nextInt(3) - lich.getRandom().nextInt(3));

			// make sparkles leading to it
			lich.makeTeleportTrail(lich.getX(), lich.getY(), lich.getZ(), cloneSpot.x, cloneSpot.y, cloneSpot.z);
		}
	}

	private void despawnClones() {
		for (LichEntity nearbyLich : lich.getNearbyLiches()) {
			if (nearbyLich.isShadowClone()) {
				nearbyLich.remove(true);
			}
		}
	}

	private void findNewMaster() {

		for (LichEntity nearbyLich : lich.getNearbyLiches()) {
			if (!nearbyLich.isShadowClone() && nearbyLich.wantsNewClone(lich)) {
				lich.setMaster(nearbyLich);

				// animate our new linkage!
				lich.makeTeleportTrail(lich.getX(), lich.getY(), lich.getZ(), nearbyLich.getX(), nearbyLich.getY(), nearbyLich.getZ());

				lich.setTarget(nearbyLich.getTarget());
				break;
			}
		}
	}
}
