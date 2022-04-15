package com.a4z0.rubrum.api.nbt;

public class NBTLong extends NBTBase<Long> {

    public NBTLong(long Value) {
        this.Data = Value;
    };

    @Override
    public byte getTypeID() {
        return 4;
    };
};
