package com.a4z0.rubrum.api.nbt;

public class NBTShort extends NBTBase<Short> {

    public NBTShort(short Value) {
        this.Data = Value;
    };

    @Override
    public byte getTypeID() {
        return 2;
    };
};