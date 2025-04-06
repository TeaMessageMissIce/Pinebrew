package xyz.missice.pinebrew.block.client;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import xyz.missice.pinebrew.Pinebrew;
import xyz.missice.pinebrew.block.entity.DeliveryBoxBlockEntity;

public class DeliveryBoxModel extends GeoModel<DeliveryBoxBlockEntity> {

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
}
