package twilightforest.worldgen;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import twilightforest.TwilightForestMod;
import twilightforest.world.TFCavesCarver;

//this was all put into 1 class because it seems like a waste to have it in 2
@Mod.EventBusSubscriber(modid = TwilightForestMod.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfiguredWorldCarvers {
	public static final TFCavesCarver TFCAVES = new TFCavesCarver(ProbabilityFeatureConfiguration.CODEC, 256, false);
	public static final TFCavesCarver HIGHLANDCAVES = new TFCavesCarver(ProbabilityFeatureConfiguration.CODEC, 256, true);
	
	static {
		TFCAVES.setRegistryName(TwilightForestMod.ID, "tf_caves");
		HIGHLANDCAVES.setRegistryName(TwilightForestMod.ID, "highland_caves");
	}
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<WorldCarver<?>> evt) {
		evt.getRegistry().register(TFCAVES);
		evt.getRegistry().register(HIGHLANDCAVES);
	}
	

	public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> TFCAVES_CONFIGURED = TFCAVES.configured(new ProbabilityFeatureConfiguration(0.03F));
	public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> HIGHLANDCAVES_CONFIGURED = HIGHLANDCAVES.configured(new ProbabilityFeatureConfiguration(0.03F));

	public static void registerConfigurations(Registry<ConfiguredWorldCarver<?>> registry) {
		Registry.register(registry, TwilightForestMod.prefix("tf_caves"), ConfiguredWorldCarvers.TFCAVES_CONFIGURED);
		Registry.register(registry, TwilightForestMod.prefix("highland_caves"), ConfiguredWorldCarvers.HIGHLANDCAVES_CONFIGURED);
	}
}
