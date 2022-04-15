package com.a4z0.rubrum.api.nbt;

public class NBTLongArray extends NBTBase<long[]> {

    public NBTLongArray(long[] Value) {
        this.Data = Value;
    };

    @Override
    public byte getTypeID() {
        return 12;
    };
};