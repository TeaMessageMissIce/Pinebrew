package xyz.missice.pinebrew.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import xyz.missice.pinebrew.block.DeliveryBoxBlock;
import xyz.missice.pinebrew.platform.PinebrewPlatform;

import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static void init() {}
    public static final Supplier<DeliveryBoxBlock> DELIVERY_BOX = registerBlock("delivery_box",
            properties -> new DeliveryBoxBlock(properties
                    .strength(2.5f)
                    .requiresCorrectToolForDrops()
            )
    );




    private static <T extends Block> Supplier<T> registerBlock(String id, Function<BlockBehaviour.Properties, T> block) {
        PinebrewPlatform COMMON_PLATFORM = ServiceLoader.load(PinebrewPlatform.class).findFirst().orElseThrow();
        return COMMON_PLATFORM.registerBlock(id, block);
    }

}
