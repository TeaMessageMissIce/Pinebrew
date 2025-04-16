package xyz.missice.pinebrew.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import xyz.missice.pinebrew.item.DeliveryBoxItem;
import xyz.missice.pinebrew.platform.PinebrewPlatform;

import java.util.function.Function;
import java.util.function.Supplier;

public class ItemRegistry {
    public static void init() {
    }
    // 方块物品
    public static final Supplier<BlockItem> DELIVERY_BOX= registerItem("delivery_box", properties -> new DeliveryBoxItem(BlockRegistry.DELIVERY_BOX.get(), properties));

    // 注册方法
    private static <T extends Item> Supplier<T> registerItem(String id, Function<Item.Properties, T> item) {
        return PinebrewPlatform.registerItem(id,item);
    }
}
