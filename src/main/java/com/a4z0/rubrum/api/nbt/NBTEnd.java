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
* It is used to null-terminate lists and maps.
*/

public class NBTEnd extends NBTBase<String> {

    /**
    * Construct a {@link NBTEnd}.
    */

    public NBTEnd() {
        super("END");
    }

    @Override
    protected @NotNull Object getComponent() {
        return NBTUtils.NBTTagEnd.getNBTObject();
    }

    @Override
    public byte getTypeID() {
        return 0;
    }

    @Override
    public @NotNull NBTEnd clone() {
        return new NBTEnd();
    }

    /**
    * @return this {@link NBTEnd} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Data;
    }
}