package twilightforest.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;
import org.lwjgl.opengl.GL11;
import twilightforest.capabilities.CapabilityList;
import twilightforest.entity.boss.LichEntity;

public enum RenderEffect {

	SHIELDS {
		// todo 1.15 just install this layer renderer directly on the entity renders on startup. handle 1st person rendering with the same hook as before. private final LayerRenderer<LivingEntity, EntityModel<LivingEntity>> layer = new LayerShields<>();

		@Override
		public boolean shouldRender(LivingEntity entity, boolean firstPerson) {
			if (entity instanceof LichEntity) return false;
			return entity.getCapability(CapabilityList.SHIELDS).map(c -> c.shieldsLeft() > 0).orElse(false);
		}

		@Override
		public void render(LivingEntity entity, EntityModel<? extends LivingEntity> renderer,
		                   double x, double y, double z, float partialTicks, boolean firstPerson) {

			PoseStack ms = RenderSystem.getModelViewStack();
			ms.pushPose();
			ms.translate(x, y, z);
			//ms.rotatef(180, 1, 0, 0); //FIXME
			ms.translate(0, 0.5F - entity.getEyeHeight(), 0);
			RenderSystem.enableBlend();
			RenderSystem.disableCull();
			RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			// fixme 1.16 layer.render(entity, 0, 0, partialTicks, 0, 0, 0, 0.0625F);
			RenderSystem.enableCull();
			RenderSystem.disableBlend();
			ms.popPose();
		}
	};

	static final RenderEffect[] VALUES = values();

	public boolean shouldRender(LivingEntity entity, boolean firstPerson) {
		return false;
	}

	public void render(LivingEntity entity, EntityModel<? extends LivingEntity> renderer,
	                   double x, double y, double z, float partialTicks, boolean firstPerson) {

	}
}
