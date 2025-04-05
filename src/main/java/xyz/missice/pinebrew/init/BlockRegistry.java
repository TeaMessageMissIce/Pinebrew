package xyz.missice.pinebrew.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.missice.pinebrew.Pinebrew;
public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK,Pinebrew.MODID);

    public static final DeferredHolder<Block,Block> DELIVERY_BOX = BLOCKS.register("delivery_box", () -> new xyz.missice.pinebrew.block.custom.DeliveryBoxAnimationBlock(Block.Properties.of()
            .mapColor(MapColor.WOOD)
            // 硬度
            .strength(2.5f)
            // 采集
            .requiresCorrectToolForDrops()));
}
