package com.a4z0.rubrum.api.nbt;

public class NBTFloat extends NBTBase<Float> {

    public NBTFloat(float Value) {
        this.Data = Value;
    };

    @Override
    public byte getTypeID() {
        return 5;
    };
};