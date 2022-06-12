package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* @param <T> Class of data to be stored.
*/

public abstract class NBTBase<T> implements Cloneable {

    protected final T Data;

    /**
    * Construct a {@link NBTBase}.
    *
    * @param Data data to be stored.
    */

    protected NBTBase(@NotNull T Data) {
        this.Data = Data;
    }

    /**
    * @return an NBTObject.
    */

    protected @NotNull Object getComponent() {
        return NBTUtils.getNBTTag(this.getTypeID()).getNBTObject(this.Data);
    }

    /**
    * @return the ID of this {@link NBTBase}.
    */

    public abstract byte getTypeID();

    /**
    * @return a clone of this {@link NBTBase}.
    */

    @Override
    public abstract @NotNull NBTBase<?> clone();

    /**
    * @return this {@link NBTBase} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data;
    }
}