package twilightforest.item;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import twilightforest.TwilightForestMod;
import twilightforest.client.model.armor.YetiArmorModel;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class YetiArmorItem extends ArmorItem {

	private static final Map<EquipmentSlot, HumanoidModel<?>> yetiArmorModel = new EnumMap<>(EquipmentSlot.class);

	public YetiArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties props) {
		super(material, slot, props);
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlot slot, String layer) {
		if (slot == EquipmentSlot.LEGS || slot == EquipmentSlot.CHEST) {
			return TwilightForestMod.ARMOR_DIR + "yetiarmor_2.png";
		} else {
			return TwilightForestMod.ARMOR_DIR + "yetiarmor_1.png";
		}
	}

	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
		if (allowdedIn(tab)) {
			ItemStack istack = new ItemStack(this);
			switch (this.slot) {
				case HEAD:
				case CHEST:
				case LEGS:
					istack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
					break;
				case FEET:
					istack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
					istack.enchant(Enchantments.FALL_PROTECTION, 4);
					break;
				default:
					break;
			}
			list.add(istack);
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltips, TooltipFlag flags) {
		super.appendHoverText(stack, world, tooltips, flags);
		tooltips.add(new TranslatableComponent(getDescriptionId() + ".tooltip"));
	}
}
