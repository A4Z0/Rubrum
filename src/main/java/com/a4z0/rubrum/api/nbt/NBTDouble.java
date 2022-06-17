/*
 *     Rubrum
 *     Copyright (C) 2022.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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

    /**
    * @return the ID of this {@link NBTDouble}.
    */

    @Override
    public final byte getTypeID() {
        return 6;
    }

    /**
    * @return a clone of this {@link NBTDouble}.
    */

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
