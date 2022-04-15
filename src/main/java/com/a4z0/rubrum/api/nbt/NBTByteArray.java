package com.a4z0.rubrum.api.nbt;

public class NBTByteArray extends NBTBase<byte[]> {

    public NBTByteArray(byte[] Value) {
        this.Data = Value;
    };

    @Override
    public byte getTypeID() {
        return 7;
    };
};