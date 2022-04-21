package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link String}.
*/

public class NBTString extends NBTBase<String> {

    /**
    * Construct a {@link NBTString} with the given params.
    *
    * @param String string to be stored.
    */

    public NBTString(@NotNull String String) {
        this.Data = String;
    };

    @Override
    protected final byte getTypeID() {
        return 8;
    };

    /**
    * @return this {@link NBTString} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": \"" + Data + "\"";
    };
};