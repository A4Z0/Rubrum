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
* Stores data with class {@link Byte} Array.
*/

public class NBTByteArray extends NBTBase<byte[]> {

    /**
    * Construct a {@link NBTByteArray}.
    */

    public NBTByteArray() {
        this(new byte[]{});
    }

    /**
    * Construct a {@link NBTByteArray}.
    *
    * @param ByteArray byte array to be stored.
    */

    public NBTByteArray(byte[] ByteArray) {
        super(ByteArray);
    }

    /**
    * @return the ID of this {@link NBTByteArray}.
    */

    @Override
    public final byte getTypeID() {
        return 7;
    }

    /**
    * @return a clone of this {@link NBTByteArray}.
    */

    @Override
    public @NotNull NBTByteArray clone() {
        return new NBTByteArray(this.Data);
    }

    /**
    * @return this {@link NBTByteArray} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(Data);
    }
}