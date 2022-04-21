package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Long}.
*/

public class NBTLong extends NBTBase<Long> {

    /**
    * Construct a {@link NBTLong} with the given params.
    *
    * @param Long long to be stored.
    */

    public NBTLong(long Long) {
        this.Data = Long;
    };

    /**
    * Construct a {@link NBTLong} with the given params.
    *
    * @param Long long to be stored.
    */

    public NBTLong(@NotNull Long Long) {
        this.Data = Long;
    };

    @Override
    protected final byte getTypeID() {
        return 4;
    };

    /**
    * @return this {@link NBTLong} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "L";
    };
};