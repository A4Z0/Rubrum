package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Integer}.
*/

public class NBTInt extends NBTBase<Integer> {

    /**
    * Construct a {@link NBTInt}.
    */

    public NBTInt() {
        this(0);
    }

    /**
    * Construct a {@link NBTInt}.
    *
    * @param Int int to be stored.
    */

    public NBTInt(int Int) {
        super(Int);
    }

    /**
    * Construct a {@link NBTInt}.
    *
    * @param Integer integer to be stored.
    */

    public NBTInt(@NotNull Integer Integer) {
        super(Integer);
    }

    /**
    * @return the ID of this {@link NBTInt}.
    */

    @Override
    public final byte getTypeID() {
        return 3;
    }

    /**
    * @return a clone of this {@link NBTInt}.
    */

    @Override
    public @NotNull NBTInt clone() {
        return new NBTInt(this.Data);
    }

    /**
    * @return this {@link NBTInt} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data;
    }
}