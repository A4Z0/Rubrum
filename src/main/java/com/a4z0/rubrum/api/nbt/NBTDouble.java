package com.a4z0.rubrum.api.nbt;

public class NBTDouble extends NBTBase<Double> {

    public NBTDouble(double Value) {
        this.Data = Value;
    };

    @Override
    public byte getTypeID() {
        return 6;
    };
};