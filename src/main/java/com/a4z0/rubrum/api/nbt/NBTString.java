package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link String}.
*/

public class NBTString extends NBTBase<String> {

    /**
    * Construct a {@link NBTString}.
    */

    public NBTString() {
        this("");
    }

    /**
    * Construct a {@link NBTString}.
    *
    * @param String string to be stored.
    */

    public NBTString(@NotNull String String) {
        super(String);
    }

    /**
    * @return the ID of this {@link NBTString}.
    */

    @Override
    public final byte getTypeID() {
        return 8;
    }

    /**
    * @return a clone of this {@link NBTString}.
    */

    @Override
    public @NotNull NBTString clone() {
        return new NBTString(this.Data);
    }

    /**
    * @return this {@link NBTString} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": \"" + Data + "\"";
    }
}