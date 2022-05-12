package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Means the end of a component.
*/

public class NBTEnd extends NBTBase<String> {

    /**
    * Construct a {@link NBTEnd}.
    */

    public NBTEnd() {
        this.Data = "End";
    };

    @Override
    protected byte getTypeID() {
        return 0;
    };

    /**
    * @return this {@link NBTEnd} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "";
    };
};