package twilightforest.item;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import twilightforest.TwilightForestMod;
import twilightforest.client.model.armor.KnightmetalArmorModel;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

public class KnightmetalArmorItem extends ArmorItem {

	private static final Map<EquipmentSlot, HumanoidModel<?>> knightlyArmorModel = new EnumMap<>(EquipmentSlot.class);

	public KnightmetalArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties props) {
		super(material, slot, props);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlot slot, String layer) {
		if (slot == EquipmentSlot.LEGS) {
			return TwilightForestMod.ARMOR_DIR + "knightly_2.png";
		} else {
			return TwilightForestMod.ARMOR_DIR + "knightly_1.png";
		}
	}
}
