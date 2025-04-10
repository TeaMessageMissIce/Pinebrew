package xyz.missice.pinebrew.registry;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.missice.pinebrew.Pinebrew;
import xyz.missice.pinebrew.block.entity.DeliveryBoxBlockEntity;
import xyz.missice.pinebrew.platform.PinebrewPlatform;

import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Supplier;

public class ModBlockEntities {
    public static void init() {}

    public static final Supplier<BlockEntityType<DeliveryBoxBlockEntity>> DELIVERY_BOX = registerBlockEntity("delivery_box",
            () -> new BlockEntityType<>(DeliveryBoxBlockEntity::new, Set.of(ModBlocks.DELIVERY_BOX.get()))
    );


    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> blockEntity) {
        // 如果不打算兼容其他平台可以不考虑这个
//        PinebrewPlatform COMMON_PLATFORM = ServiceLoader.load(PinebrewPlatform.class).findFirst().orElseThrow();
//        return COMMON_PLATFORM.registerBlockEntity(id, blockEntity);
        return PinebrewPlatform.registerBlockEntity(id, blockEntity);
    }
}
