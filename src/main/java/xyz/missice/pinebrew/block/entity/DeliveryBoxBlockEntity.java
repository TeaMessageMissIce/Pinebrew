package xyz.missice.pinebrew.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import xyz.missice.pinebrew.registry.ModBlockEntities;

import java.util.UUID;

public class DeliveryBoxBlockEntity extends BlockEntity {
    private final ItemStackHandler inventory = new ItemStackHandler(256) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 64; // 单个槽位的物品堆叠上限
        }

        // 保证与客户端同步数据
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        }
    };

    // TODO 收货箱主人，未测试序列化有效性
    private UUID owner = UUID.fromString("00000000-0000-0000-0000-000000000000");

    public DeliveryBoxBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DELIVERY_BOX.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putUUID("owner", owner);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        this.owner = tag.getUUID("owner");
    }
}
