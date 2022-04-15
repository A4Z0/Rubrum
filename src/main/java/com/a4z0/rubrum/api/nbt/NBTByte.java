package com.a4z0.rubrum.api.nbt;

public class NBTByte extends NBTBase<Byte> {

    public NBTByte(byte Value) {
        this.Data = Value;
    };

    @Override
    public byte getTypeID() {
        return 1;
    };
};