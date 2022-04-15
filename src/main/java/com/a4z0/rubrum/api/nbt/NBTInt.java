package com.a4z0.rubrum.api.nbt;

public class NBTInt extends NBTBase<Integer> {

    public NBTInt(int Value) {
        this.Data = Value;
    };

    @Override
    public byte getTypeID() {
        return 3;
    };
};