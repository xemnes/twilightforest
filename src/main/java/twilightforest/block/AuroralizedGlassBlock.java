package twilightforest.block;

import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class AuroralizedGlassBlock extends AbstractGlassBlock {

    public AuroralizedGlassBlock(Properties props) {
        super(props);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("twilightforest.misc.nyi"));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}
