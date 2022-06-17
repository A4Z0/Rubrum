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

import java.util.Arrays;

/**
* Base of an NBT component.
*
* Stores data with class {@link Integer} Array.
*/

public class NBTIntArray extends NBTBase<int[]> {

    /**
    * Construct a {@link NBTIntArray}.
    */

    public NBTIntArray() {
        this(new int[]{});
    }

    /**
    * Construct a {@link NBTIntArray}.
    *
    * @param IntArray int array to be stored.
    */

    public NBTIntArray(int[] IntArray) {
        super(IntArray);
    }

    /**
    * @return the ID of this {@link NBTIntArray}.
    */

    @Override
    public final byte getTypeID() {
        return 11;
    }

    /**
    * @return a clone of this {@link NBTIntArray}.
    */

    @Override
    public @NotNull NBTIntArray clone() {
        return new NBTIntArray(this.Data);
    }

    /**
    * @return this {@link NBTIntArray} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(Data);
    }
}