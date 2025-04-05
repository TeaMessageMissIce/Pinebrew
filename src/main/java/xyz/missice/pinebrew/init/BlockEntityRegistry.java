package xyz.missice.pinebrew.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.missice.pinebrew.Pinebrew;
import xyz.missice.pinebrew.block.entity.DeliveryBoxBlockEntity;

import java.util.Set;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES  = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Pinebrew.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DeliveryBoxBlockEntity>> DELIVERY_BOX =
            BLOCK_ENTITIES.register("delivery_box", () ->
                    new BlockEntityType<>(
                            DeliveryBoxBlockEntity::new, // BlockEntitySupplier
                            Set.of(BlockRegistry.DELIVERY_BOX.get())
                            )
                    );
}
