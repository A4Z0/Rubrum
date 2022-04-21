package com.a4z0.rubrum.api.nbt;

import java.util.Arrays;

/**
* Base of an NBT component.
*
* Stores data with class {@link Integer} Array.
*/

public class NBTIntArray extends NBTBase<int[]> {

    /**
    * Construct a {@link NBTIntArray} with the given params.
    *
    * @param IntArray int array to be stored.
    */

    public NBTIntArray(int[] IntArray) {
        this.Data = IntArray;
    };

    @Override
    protected final byte getTypeID() {
        return 11;
    };

    /**
    * @return this {@link NBTIntArray} as a {@link String}.
    */

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(Data);
    };
};