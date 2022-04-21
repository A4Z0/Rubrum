package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Integer}.
*/

public class NBTInt extends NBTBase<Integer> {

    /**
    * Construct a {@link NBTInt} with the given params.
    *
    * @param Int int to be stored.
    */

    public NBTInt(int Int) {
        this.Data = Int;
    };

    /**
    * Construct a {@link NBTInt} with the given params.
    *
    * @param Integer integer to be stored.
    */

    public NBTInt(@NotNull Integer Integer) {
        this.Data = Integer;
    };

    @Override
    protected final byte getTypeID() {
        return 3;
    };

    /**
    * @return this {@link NBTInt} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data;
    };
};