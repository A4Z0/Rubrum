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
* Stores data with class {@link Long}.
*/

public class NBTLong extends NBTBase<Long> {

    /**
    * Construct a {@link NBTLong}.
    */

    public NBTLong() {
        this(0L);
    }

    /**
    * Construct a {@link NBTLong}.
    *
    * @param Long long to be stored.
    */

    public NBTLong(long Long) {
        super(Long);
    }

    /**
    * Construct a {@link NBTLong}.
    *
    * @param Long long to be stored.
    */

    public NBTLong(@NotNull Long Long) {
        super(Long);
    }

    /**
    * @return the ID of this {@link NBTLong}.
    */

    @Override
    public final byte getTypeID() {
        return 4;
    }

    /**
    * @return a clone of this {@link NBTLong}.
    */

    @Override
    public @NotNull NBTLong clone() {
        return new NBTLong(this.Data);
    }

    /**
    * @return this {@link NBTLong} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "L";
    }
}