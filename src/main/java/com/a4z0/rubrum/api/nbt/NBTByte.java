package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Byte}.
*/

public class NBTByte extends NBTBase<Byte> {

    /**
    * Construct a {@link NBTByte}.
    */

    public NBTByte() {
        this((byte) 0);
    }

    /**
    * Construct a {@link NBTByte}.
    *
    * @param Byte byte to be stored.
    */

    public NBTByte(byte Byte) {
        super(Byte);
    }

    /**
    * Construct a {@link NBTByte}.
    *
    * @param Byte byte to be stored.
    */

    public NBTByte(@NotNull Byte Byte) {
        super(Byte);
    }

    /**
    * @return the ID of this {@link NBTByte}.
    */

    @Override
    public final byte getTypeID() {
        return 1;
    }

    /**
    * @return a clone of this {@link NBTByte}.
    */

    @Override
    public @NotNull NBTByte clone() {
        return new NBTByte(this.Data);
    }

    /**
    * @return this {@link NBTByte} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "b";
    }
}