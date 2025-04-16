package xyz.missice.pinebrew.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtil;
import xyz.missice.pinebrew.registry.BlockEntitiesRegistry;

import java.util.UUID;

public class DeliveryBoxBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
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
        super(BlockEntitiesRegistry.DELIVERY_BOX.get(), pos, blockState);
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

    // 新建动画控制器 （动画，控制器名称，检测时间，动画播放条件）
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }
    // 动画播放的条件 传入动画名称“第四行那个” 播放方式,循环
    public boolean isOpen = false; // Tracks the current state

    private PlayState predicate(AnimationState<DeliveryBoxBlockEntity> state) {
        if (state.getAnimatable().getLevel() != null) {
            // 获取玩家
            Player player = state.getAnimatable().getLevel().getNearestPlayer(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), 3, false);
            if (player != null) {
                // 玩家靠近，播放正向动画
                isOpen = true;
                state.getController().setAnimation(RawAnimation.begin().then("animation.model.openDeliveryBox", Animation.LoopType.HOLD_ON_LAST_FRAME));
                return PlayState.CONTINUE;
            } else {
                // 玩家离开，播放倒放动画
                isOpen = false;
                state.getController().setAnimation(RawAnimation.begin().then("animation.model.closeDeliveryBox", Animation.LoopType.PLAY_ONCE));
                return PlayState.CONTINUE;
            }
        }
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
    // 获取tick（动画播放时间）
    @Override
    public double getTick(Object blockEntity) {
        return RenderUtil.getCurrentTick();
    }
}
