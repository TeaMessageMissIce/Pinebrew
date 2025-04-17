package xyz.missice.pinebrew.client.renderer;

import software.bernie.geckolib.renderer.GeoBlockRenderer;
import xyz.missice.pinebrew.client.model.block.DeliveryBoxModel;
import xyz.missice.pinebrew.block.entity.DeliveryBoxBlockEntity;

public class DeliveryBoxBlockRenderer extends GeoBlockRenderer<DeliveryBoxBlockEntity> {
    public DeliveryBoxBlockRenderer() {
        super(new DeliveryBoxModel());
    }
}
