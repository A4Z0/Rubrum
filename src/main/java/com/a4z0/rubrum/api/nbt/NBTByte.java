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
* Stores data with class {@link Byte}.
*/

public class NBTByte extends NBTBase<Byte> {

    /**
    * Construct a {@link NBTByte}.
    */

    public NBTByte() {
        this((byte) 0);
    }

    /**
    * Construct a {@link NBTByte}.
    *
    * @param Byte byte to be stored.
    */

    public NBTByte(byte Byte) {
        super(Byte);
    }

    /**
    * Construct a {@link NBTByte}.
    *
    * @param Byte byte to be stored.
    */

    public NBTByte(@NotNull Byte Byte) {
        super(Byte);
    }

    /**
    * @return the ID of this {@link NBTByte}.
    */

    @Override
    public final byte getTypeID() {
        return 1;
    }

    /**
    * @return a clone of this {@link NBTByte}.
    */

    @Override
    public @NotNull NBTByte clone() {
        return new NBTByte(this.Data);
    }

    /**
    * @return this {@link NBTByte} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data + "b";
    }
}