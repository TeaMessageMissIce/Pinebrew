package xyz.missice.pinebrew.block.client.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import xyz.missice.pinebrew.Pinebrew;
import xyz.missice.pinebrew.block.entity.DeliveryBoxBlockEntity;

public class DeliveryBoxModel extends DefaultedBlockGeoModel<DeliveryBoxBlockEntity> {

    public DeliveryBoxModel() {
        super(ResourceLocation.fromNamespaceAndPath(Pinebrew.MODID, "delivery_box"));
    }

    @Override
    public ResourceLocation getModelResource(DeliveryBoxBlockEntity animatable, @Nullable GeoRenderer<DeliveryBoxBlockEntity> renderer) {
        return ResourceLocation.fromNamespaceAndPath(Pinebrew.MODID, "geo/delivery_box.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DeliveryBoxBlockEntity animatable, @Nullable GeoRenderer<DeliveryBoxBlockEntity> renderer) {
        return ResourceLocation.fromNamespaceAndPath(Pinebrew.MODID, "textures/block/delivery_box.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DeliveryBoxBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Pinebrew.MODID, "animations/delivery_box.animation.json");
    }

    @Override
    public @Nullable RenderType getRenderType(DeliveryBoxBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
}
