package xyz.missice.pinebrew.block.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.missice.pinebrew.block.client.renderer.DeliveryBoxBlockRenderer;
import xyz.missice.pinebrew.registry.ModBlockEntities;
import xyz.missice.pinebrew.registry.ModBlocks;

import java.util.function.BiConsumer;

public class PinebrewClient {
    public static void registerRenderers(BiConsumer<EntityType<? extends Entity>, EntityRendererProvider> entityRenderers,
                                         BiConsumer<BlockEntityType<? extends BlockEntity>, BlockEntityRendererProvider> blockEntityRenderers) {

        blockEntityRenderers.accept(ModBlockEntities.DELIVERY_BOX.get(),  DeliveryBoxBlockRenderer:: new);
    }
}
