package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

public class NBTString extends NBTBase<String> {

    public NBTString(@NotNull String Value) {
        this.Data = Value;
    };

    @Override
    public byte getTypeID() {
        return 8;
    };
};