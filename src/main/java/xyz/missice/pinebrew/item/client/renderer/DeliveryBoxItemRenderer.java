package xyz.missice.pinebrew.item.client.renderer;

import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import xyz.missice.pinebrew.item.DeliveryBoxItem;
import xyz.missice.pinebrew.item.client.model.DeliveryBoxItemModel;

public class DeliveryBoxItemRenderer extends GeoItemRenderer<DeliveryBoxItem> {
    public DeliveryBoxItemRenderer() {
        super(new DeliveryBoxItemModel());
    }
}
