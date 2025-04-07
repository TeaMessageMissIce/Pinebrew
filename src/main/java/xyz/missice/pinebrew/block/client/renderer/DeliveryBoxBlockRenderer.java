package xyz.missice.pinebrew.block.client.renderer;

import software.bernie.geckolib.renderer.GeoBlockRenderer;
import xyz.missice.pinebrew.block.client.model.DeliveryBoxModel;
import xyz.missice.pinebrew.block.entity.DeliveryBoxBlockEntity;

public class DeliveryBoxBlockRenderer extends GeoBlockRenderer<DeliveryBoxBlockEntity> {
    public DeliveryBoxBlockRenderer() {
        super(new DeliveryBoxModel());
    }
}
