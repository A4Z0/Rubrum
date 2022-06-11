package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
* Base of an NBT component.
*
* Stores data with class {@link Integer} Array.
*/

public class NBTIntArray extends NBTBase<int[]> {

    /**
    * Construct a {@link NBTIntArray}.
    */

    public NBTIntArray() {
        this(new int[]{});
    }

    /**
    * Construct a {@link NBTIntArray}.
    *
    * @param IntArray int array to be stored.
    */

    public NBTIntArray(int[] IntArray) {
        super(IntArray);
    }

    @Override
    public final byte getTypeID() {
        return 11;
    }

    @Override
    public @NotNull NBTIntArray clone() {
        return new NBTIntArray(this.Data);
    }

    /**
    * @return this {@link NBTIntArray} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(Data);
    }
}