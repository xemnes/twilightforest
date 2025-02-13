package twilightforest.entity.boss;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import twilightforest.client.renderer.entity.HydraNeckRenderer;

public class HydraNeckEntity extends HydraPartEntity {

	public final HydraHeadEntity head;

	public HydraNeckEntity(HydraHeadEntity head) {
		super(head.getParent(), 2F, 2F);
		this.head = head;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public EntityRenderer<?> renderer(EntityRendererProvider.Context manager) {
		return new HydraNeckRenderer(manager);
	}
}
