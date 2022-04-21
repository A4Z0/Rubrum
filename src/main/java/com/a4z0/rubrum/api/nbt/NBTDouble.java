package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

/**
* Base of an NBT component.
*
* Stores data with class {@link Double}.
*/

public class NBTDouble extends NBTBase<Double> {

    /**
    * Construct a {@link NBTDouble} with the given params.
    *
    * @param Double double to be stored.
    */

    public NBTDouble(double Double) {
        this.Data = Double;
    };

    /**
    * Construct a {@link NBTDouble} with the given params.
    *
    * @param Double double to be stored.
    */

    public NBTDouble(@NotNull Double Double) {
        this.Data = Double;
    };

    @Override
    protected final byte getTypeID() {
        return 6;
    };

    /**
    * @return this {@link NBTDouble} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "d";
    };
};
