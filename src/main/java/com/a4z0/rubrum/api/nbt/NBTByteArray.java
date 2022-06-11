package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
* Base of an NBT component.
*
* Stores data with class {@link Byte} Array.
*/

public class NBTByteArray extends NBTBase<byte[]> {

    /**
    * Construct a {@link NBTByteArray}.
    */

    public NBTByteArray() {
        this(new byte[]{});
    }

    /**
    * Construct a {@link NBTByteArray}.
    *
    * @param ByteArray byte array to be stored.
    */

    public NBTByteArray(byte[] ByteArray) {
        super(ByteArray);
    }

    @Override
    public final byte getTypeID() {
        return 7;
    }

    @Override
    public @NotNull NBTByteArray clone() {
        return new NBTByteArray(this.Data);
    }

    /**
    * @return this {@link NBTByteArray} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(Data);
    }
}