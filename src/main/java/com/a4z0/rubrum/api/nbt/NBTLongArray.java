package com.a4z0.rubrum.api.nbt;

import java.util.Arrays;

/**
* Base of an NBT component.
*
* Stores data with class {@link Long} Array.
*/

public class NBTLongArray extends NBTBase<long[]> {

    /**
    * Construct a {@link NBTLongArray} with the given params.
    *
    * @param LongArray long array to be stored.
    */

    public NBTLongArray(long[] LongArray) {
        this.Data = LongArray;
    };

    @Override
    protected final byte getTypeID() {
        return 12;
    };

    /**
    * @return this {@link NBTLongArray} as a {@link String}.
    */

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(Data);
    };
};
