package xyz.missice.pinebrew.block;

import net.minecraft.util.StringRepresentable;

public enum EnumPartType implements StringRepresentable {
    // 枚举类，用来构造虚拟方块
    MAIN("main"),
    EXTENSION("extension"),
    ;

    private final String name;

    EnumPartType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
