package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Long}.
*/

public class NBTLong extends NBTBase<Long> {

    /**
    * Construct a {@link NBTLong}.
    */

    public NBTLong() {
        this(0L);
    }

    /**
    * Construct a {@link NBTLong}.
    *
    * @param Long long to be stored.
    */

    public NBTLong(long Long) {
        super(Long);
    }

    /**
    * Construct a {@link NBTLong}.
    *
    * @param Long long to be stored.
    */

    public NBTLong(@NotNull Long Long) {
        super(Long);
    }

    /**
    * @return the ID of this {@link NBTLong}.
    */

    @Override
    public final byte getTypeID() {
        return 4;
    }

    /**
    * @return a clone of this {@link NBTLong}.
    */

    @Override
    public @NotNull NBTLong clone() {
        return new NBTLong(this.Data);
    }

    /**
    * @return this {@link NBTLong} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "L";
    }
}