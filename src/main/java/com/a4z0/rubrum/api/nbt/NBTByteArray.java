package com.a4z0.rubrum.api.nbt;

import java.util.Arrays;

/**
* Base of an NBT component.
*
* Stores data with class {@link Byte} Array.
*/

public class NBTByteArray extends NBTBase<byte[]> {

    /**
    * Construct a {@link NBTByteArray} with the given params.
    *
    * @param ByteArray byte array to be stored.
    */

    public NBTByteArray(byte[] ByteArray) {
        this.Data = ByteArray;
    }

    @Override
    protected final byte getTypeID() {
        return 7;
    }

    /**
    * @return this {@link NBTByteArray} as a {@link String}.
    */

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(Data);
    }
}