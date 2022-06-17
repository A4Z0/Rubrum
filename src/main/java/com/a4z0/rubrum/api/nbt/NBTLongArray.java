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

import com.a4z0.rubrum.annotations.Since;
import com.a4z0.rubrum.enums.Minecraft;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
* Base of an NBT component.
*
* Stores data with class {@link Long} Array.
*/

@Since(Version = Minecraft.V1_12_R1) public class NBTLongArray extends NBTBase<long[]> {

    /**
    * Construct a {@link NBTLongArray}.
    */

    public NBTLongArray() {
        this(new long[]{});
    }

    /**
    * Construct a {@link NBTLongArray}.
    *
    * @param LongArray long array to be stored.
    */

    public NBTLongArray(long[] LongArray) {
        super(LongArray);
    }

    /**
    * @return the ID of this {@link NBTLongArray}.
    */

    @Override
    public final byte getTypeID() {
        return 12;
    }

    /**
    * @return a clone of this {@link NBTLongArray}.
    */

    @Override
    public @NotNull NBTLongArray clone() {
        return new NBTLongArray(this.Data);
    }

    /**
    * @return this {@link NBTLongArray} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(Data);
    }
}
