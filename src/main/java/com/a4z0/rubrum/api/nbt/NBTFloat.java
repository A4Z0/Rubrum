package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Float}.
*/

public class NBTFloat extends NBTBase<Float> {

    /**
    * Construct a {@link NBTFloat} with the given params.
    *
    * @param Float float to be stored.
    */

    public NBTFloat(float Float) {
        this.Data = Float;
    };

    /**
    * Construct a {@link NBTFloat} with the given params.
    *
    * @param Float float to be stored.
    */

    public NBTFloat(@NotNull Float Float) {
        this.Data = Float;
    };

    @Override
    protected final byte getTypeID() {
        return 5;
    };

    /**
    * @return this {@link NBTFloat} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "f";
    };
};
