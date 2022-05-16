package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Short}.
*/

public class NBTShort extends NBTBase<Short> {

    /**
    * Construct a {@link NBTShort} with the given params.
    *
    * @param Short short to be stored.
    */

    public NBTShort(short Short) {
        this.Data = Short;
    }

    /**
    * Construct a {@link NBTShort} with the given params.
    *
    * @param Short short to be stored.
    */

    public NBTShort(Short Short) {
        this.Data = Short;
    }

    @Override
    protected final byte getTypeID() {
        return 2;
    }

    /**
    * @return this {@link NBTShort} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "s";
    }
}