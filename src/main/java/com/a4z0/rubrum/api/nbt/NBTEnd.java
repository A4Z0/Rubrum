package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* It is used to null-terminate lists and maps.
*/

public class NBTEnd extends NBTBase<String> {

    /**
    * Construct a {@link NBTEnd}.
    */

    public NBTEnd() {
        super("END");
    }

    @Override
    protected @NotNull Object getComponent() {
        return NBTUtils.NBTEND.O();
    }

    @Override
    public byte getTypeID() {
        return 0;
    }

    @Override
    public @NotNull NBTEnd clone() {
        return new NBTEnd();
    }

    /**
    * @return this {@link NBTEnd} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data;
    }
}