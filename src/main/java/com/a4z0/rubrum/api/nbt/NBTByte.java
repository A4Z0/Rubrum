package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Byte}.
*/

public class NBTByte extends NBTBase<Byte> {

    /**
    * Construct a {@link NBTByte} with the given params.
    *
    * @param Byte byte to be stored.
    */

    public NBTByte(byte Byte) {
        this.Data = Byte;
    }

    /**
    * Construct a {@link NBTByte} with the given params.
    *
    * @param Byte byte to be stored.
    */

    public NBTByte(@NotNull Byte Byte) {
        this.Data = Byte;
    }

    @Override
    protected final byte getTypeID() {
        return 1;
    }

    /**
    * @return this {@link NBTByte} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "b";
    }
}