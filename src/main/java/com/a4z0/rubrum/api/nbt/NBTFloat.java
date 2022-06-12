package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Float}.
*/

public class NBTFloat extends NBTBase<Float> {

    /**
    * Construct a {@link NBTFloat}.
    */

    public NBTFloat() {
        this(0f);
    }

    /**
    * Construct a {@link NBTFloat}.
    *
    * @param Float float to be stored.
    */

    public NBTFloat(float Float) {
        super(Float);
    }

    /**
    * Construct a {@link NBTFloat}.
    *
    * @param Float float to be stored.
    */

    public NBTFloat(@NotNull Float Float) {
        super(Float);
    }

    /**
    * @return the ID of this {@link NBTFloat}.
    */

    @Override
    public final byte getTypeID() {
        return 5;
    }

    /**
    * @return a clone of this {@link NBTFloat}.
    */

    @Override
    public @NotNull NBTFloat clone() {
        return new NBTFloat(this.Data);
    }

    /**
    * @return this {@link NBTFloat} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "f";
    }
}
