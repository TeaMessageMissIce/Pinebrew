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
    public ResourceLocation getAnimationResource(DeliveryBoxBlockEntity animatable) {
        if (animatable.isOpen) {
            // Return the animation resource for the "open" animation
            return ResourceLocation.fromNamespaceAndPath(Pinebrew.MODID, "animations/open_delivery_box.animation.json");
        } else {
            // Return the animation resource for the "close" animation
            return ResourceLocation.fromNamespaceAndPath(Pinebrew.MODID, "animations/close_delivery_box.animation.json");
        }}

    @Override
    public @Nullable RenderType getRenderType(DeliveryBoxBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
}
