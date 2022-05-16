package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* @param <T> Class of data to be stored.
*/

public abstract class NBTBase<T> {

    protected T Data;

    /**
    * Construct a {@link NBTBase}.
    */

    protected NBTBase() {
        this.Data = null;
    }

    /**
    * Construct a {@link NBTBase} with the given params.
    *
    * @param Data data to be stored.
    */

    protected NBTBase(@NotNull T Data) {
        this.Data = Data;
    }

    /**
    * @return an NBT component.
    */

    protected Object getComponent() {
        return NBTUtils.GET_NBTBASE_INSTANCE(this.getTypeID(), this.Data);
    }

    /**
    * @return the type ID.
    */

    protected abstract byte getTypeID();

    /**
    * @return the hashcode.
    */

    @Override
    public int hashCode() {
        return this.getTypeID();
    }

    /**
    * @return this {@link NBTBase} as a {@link String}.
    */

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + Data;
    }
}