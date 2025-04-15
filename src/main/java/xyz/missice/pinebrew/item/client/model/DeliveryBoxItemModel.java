package xyz.missice.pinebrew.item.client.model;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import xyz.missice.pinebrew.Pinebrew;
import xyz.missice.pinebrew.item.DeliveryBoxItem;

public class DeliveryBoxItemModel extends GeoModel<DeliveryBoxItem> {
    @Override
    public ResourceLocation getModelResource(DeliveryBoxItem animatable, @Nullable GeoRenderer<DeliveryBoxItem> renderer) {
        return ResourceLocation.fromNamespaceAndPath(Pinebrew.MODID, "geo/delivery_box.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DeliveryBoxItem animatable, @Nullable GeoRenderer<DeliveryBoxItem> renderer) {
        return ResourceLocation.fromNamespaceAndPath(Pinebrew.MODID, "textures/block/delivery_box.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DeliveryBoxItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(Pinebrew.MODID, "animations/delivery_box.animation.json");
    }
}
