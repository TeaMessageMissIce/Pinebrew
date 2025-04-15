package xyz.missice.pinebrew.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtil;
import xyz.missice.pinebrew.Pinebrew;
import xyz.missice.pinebrew.item.client.renderer.DeliveryBoxItemRenderer;

import java.util.function.Consumer;


public class DeliveryBoxItem extends BlockItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public DeliveryBoxItem(Block block, Properties properties) {
        super(block, properties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }


    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoItemRenderer<DeliveryBoxItem> renderer = null;
            @Override
            public @Nullable GeoItemRenderer<DeliveryBoxItem> getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new DeliveryBoxItemRenderer();
                }
                return this.renderer;
            }
        });
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtil.getCurrentTick();
    }
}
