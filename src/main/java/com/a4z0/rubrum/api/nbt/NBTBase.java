package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class NBTBase<T> implements Serializable {

    protected T Data;

    /**
    * @return the {@link NBTBase} id.
    */

    public byte getTypeID() {
        return 0;
    };

    /**
    * @return the {@link NBTBase} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": {\"Data\": " + Data + "}";
    };
};