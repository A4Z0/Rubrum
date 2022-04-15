package com.a4z0.rubrum.api.nbt;

public class NBTIntArray extends NBTBase<int[]> {

    public NBTIntArray(int[] Value) {
        this.Data = Value;
    };

    @Override
    public byte getTypeID() {
        return 11;
    };
};
