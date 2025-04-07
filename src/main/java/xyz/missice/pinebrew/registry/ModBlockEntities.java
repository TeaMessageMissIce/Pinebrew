package xyz.missice.pinebrew.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.missice.pinebrew.Pinebrew;
import xyz.missice.pinebrew.block.entity.DeliveryBoxBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Pinebrew.MODID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> DELIVERY_BOX = BLOCK_ENTITIES.register("delivery_box", () ->
            new BlockEntityType<>(DeliveryBoxBlockEntity::new, ModBlocks.DELIVERY_BOX.get()));
}
