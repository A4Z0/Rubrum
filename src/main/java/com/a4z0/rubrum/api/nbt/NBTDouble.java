package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Double}.
*/

public class NBTDouble extends NBTBase<Double> {

    /**
    * Construct a {@link NBTDouble}.
    */

    public NBTDouble() {
        this(0d);
    }

    /**
    * Construct a {@link NBTDouble}.
    *
    * @param Double double to be stored.
    */

    public NBTDouble(double Double) {
        super(Double);
    }

    /**
    * Construct a {@link NBTDouble}.
    *
    * @param Double double to be stored.
    */

    public NBTDouble(@NotNull Double Double) {
        super(Double);
    }

    @Override
    public final byte getTypeID() {
        return 6;
    }

    @Override
    public @NotNull NBTDouble clone() {
        return new NBTDouble(this.Data);
    }

    /**
    * @return this {@link NBTDouble} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "d";
    }
}
