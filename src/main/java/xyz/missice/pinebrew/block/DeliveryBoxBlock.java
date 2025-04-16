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
        Direction facing = state.getValue(FACING);
        BlockPos endPos = pos.relative(facing, 1); // 向朝向延伸一个块
        for (BlockPos testPos : BlockPos.betweenClosed(pos, endPos)) {
            if (!world.getBlockState(testPos).isAir()) {
                return false; // 如果范围内的任何方块不是 air，则该方块无法生成
            }
        }

        // 检查垂直空间（2 格高）
        for (int y = 0; y < 2; y++) {
            BlockPos abovePos = pos.above(y);
            if (!world.getBlockState(abovePos).isAir()) {
                return false; // 如果上面的任何方块不是空气，则该方块无法生存
            }
        }
        return true; // 如果满足所有条件，该区块可以继续存在
    }


    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
