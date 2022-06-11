package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
* Base of an NBT component.
*
* Stores data with class {@link Long} Array.
*/

public class NBTLongArray extends NBTBase<long[]> {

    /**
    * Construct a {@link NBTLongArray}.
    */

    public NBTLongArray() {
        this(new long[]{});
    }

    /**
    * Construct a {@link NBTLongArray}.
    *
    * @param LongArray long array to be stored.
    */

    public NBTLongArray(long[] LongArray) {
        super(LongArray);
    }

    @Override
    public final byte getTypeID() {
        return 12;
    }

    @Override
    public @NotNull NBTLongArray clone() {
        return new NBTLongArray(this.Data);
    }

    /**
    * @return this {@link NBTLongArray} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(Data);
    }
}
