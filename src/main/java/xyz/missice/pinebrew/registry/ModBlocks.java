package xyz.missice.pinebrew.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.missice.pinebrew.Pinebrew;
import xyz.missice.pinebrew.block.DeliveryBoxBlock;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Pinebrew.MODID);


//    public static final DeferredBlock<Block> DELIVERY_BOX = registerBlock("delivery_box",
//            () -> new Block(BlockBehaviour.Properties.of()
//                    .strength(2.5f)
//                    .requiresCorrectToolForDrops()));
    public static final Supplier<DeliveryBoxBlock> DELIVERY_BOX = registerBlock("delivery_box", properties -> new DeliveryBoxBlock(properties.noOcclusion()));

    public static <T extends Block> Supplier<T> registerBlock(String id, Function<BlockBehaviour.Properties, T> block) {
        return BLOCKS.register(id, () ->
                block.apply(BlockBehaviour.Properties.of()
                        .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Pinebrew.MODID, id)))));
    }

//    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
//        DeferredBlock<T> b = BLOCKS.register(name, block);
//        registerBlockItem(name, b);
//        return b;
//    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

}
