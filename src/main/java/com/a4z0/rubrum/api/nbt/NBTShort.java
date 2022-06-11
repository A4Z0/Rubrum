package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Short}.
*/

public class NBTShort extends NBTBase<Short> {

    /**
    * Construct a {@link NBTShort}.
    */

    public NBTShort() {
        this((short) 0);
    }

    /**
    * Construct a {@link NBTShort}.
    *
    * @param Short short to be stored.
    */

    public NBTShort(short Short) {
        super(Short);
    }

    /**
    * Construct a {@link NBTShort}.
    *
    * @param Short short to be stored.
    */

    public NBTShort(@NotNull Short Short) {
        super(Short);
    }

    @Override
    public final byte getTypeID() {
        return 2;
    }

    @Override
    public @NotNull NBTShort clone() {
        return new NBTShort(this.Data);
    }

    /**
    * @return this {@link NBTShort} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "s";
    }
}