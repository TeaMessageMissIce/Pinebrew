package xyz.missice.pinebrew.platform;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import xyz.missice.pinebrew.Pinebrew;

import java.util.function.Function;
import java.util.function.Supplier;

import static xyz.missice.pinebrew.Pinebrew.MODID;

public class PinebrewPlatform {
    /**
     * mod 的基本服务接口，处理所有非客户端平台特定功能的分发
     * mod的所有东西都走这个类
     */

    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> blockEntityType) {
        return Pinebrew.BLOCK_ENTITIES.register(id, blockEntityType);
    }


    public static <T extends Block> Supplier<T> registerBlock(String id, Function<BlockBehaviour.Properties, T> block) {
        return Pinebrew.BLOCKS.register(id, () -> block.apply(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MODID, id)))));
    }


    public static <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, Supplier<EntityType<T>> entity) {
        return Pinebrew.ENTITIES.register(id, entity);
    }


    public static <T extends Item> Supplier<T> registerItem(String id, Function<Item.Properties, T> item) {
        return Pinebrew.ITEMS.register(id, () -> item.apply(new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, id)))));
    }


    public static <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound) {
        return Pinebrew.SOUND_EVENTS.register(id, sound);
    }


    public static <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab) {
        return Pinebrew.CREATIVE_TABS.register(id, tab);
    }


    public CreativeModeTab.Builder newCreativeTabBuilder() {
        return CreativeModeTab.builder();
    }
}
