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