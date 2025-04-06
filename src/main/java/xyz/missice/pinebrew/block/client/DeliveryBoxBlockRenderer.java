package xyz.missice.pinebrew.block.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import xyz.missice.pinebrew.block.entity.DeliveryBoxBlockEntity;

public class DeliveryBoxBlockRenderer extends GeoBlockRenderer<DeliveryBoxBlockEntity> {
    public DeliveryBoxBlockRenderer() {
        super(new DeliveryBoxModel());
    }
}
