package xyz.missice.pinebrew.block;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import xyz.missice.pinebrew.registry.BlockEntitiesRegistry;

public class DeliveryBoxBlock extends BaseEntityBlock implements EntityBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;

    public static final MapCodec<DeliveryBoxBlock> CODEC = simpleCodec(DeliveryBoxBlock::new);

    public DeliveryBoxBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    // 方块朝向
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING,
                context.getHorizontalDirection().getClockWise().getClockWise());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntitiesRegistry.DELIVERY_BOX.get().create(pos,state);
    }

    /**
     * 这个getShape方法根据其FACING属性定义块的碰撞形状或轮廓形状。它是这样做的：
     * 决定面向方向：该方法检索块的BlockState的Facing属性。
     * 返回一个基于面向的形状：
     */

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> Block.box(0, 0, 0, 32, 16, 16);
            case SOUTH -> Block.box(-16, 0, 0, 16, 16, 16);
            case WEST -> Block.box(0, 0, -16, 16, 16, 16);
            default -> Block.box(0, 0, 0, 16, 16, 32);
        };
    }
    // 判断能否放置方块
    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        VoxelShape shape = this.getShape(state, world, pos, CollisionContext.empty());

        // Check all positions covered by the bounding box
        for (BlockPos blockPos : shape.toAabbs().stream()
                .flatMap(aabb -> BlockPos.betweenClosedStream(aabb.move(pos)))
                .toList()) {
            BlockState blockState = world.getBlockState(blockPos);
            if (!blockState.isAir() && !blockState.canBeReplaced()) {
                return false; // If any block is non-replaceable, placement fails
            }

        }
        return true;
    }

    // 可以替换雪片
    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        if (context == null || context.getLevel() == null) {
            return false;
        }

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        VoxelShape shape = this.getShape(state, level, pos, CollisionContext.empty());

        // 遍历碰撞箱覆盖的所有方块位置
        for (BlockPos blockPos : shape.toAabbs().stream()
                .flatMap(aabb -> BlockPos.betweenClosedStream(aabb.move(pos)))
                .toList()) {
            BlockState blockState = level.getBlockState(blockPos);
            if (!blockState.isAir() && !blockState.canBeReplaced(context)) {
                return false; // 如果有任何方块不可替换，则返回 false
            }
        }

        return true; // 所有方块都可替换
    }

    @Override
    protected void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, world, pos, newState, isMoving);
        if (!state.is(newState.getBlock())) {
            Direction facing = state.getValue(FACING);
            BlockPos sidePos = pos.relative(facing.getOpposite());
            if (world.getBlockState(sidePos).is(this)) {
                world.destroyBlock(sidePos, false); // Destroy the second part of the block
            }
        }
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
