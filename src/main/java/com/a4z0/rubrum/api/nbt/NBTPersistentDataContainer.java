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

import com.a4z0.rubrum.reflection.CraftPersistentDataContainer;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

/**
* NBT component of an {@link PersistentDataContainer}.
*/

public class NBTPersistentDataContainer extends NBTCompound {

    protected final PersistentDataContainer A;

    /**
    * Construct a {@link NBTPersistentDataContainer}.
    *
    * @param PersistentDataContainer {@link PersistentDataContainer} to be read.
    */

    protected NBTPersistentDataContainer(@NotNull PersistentDataContainer PersistentDataContainer) {
        this.A = PersistentDataContainer;

        super.setTag((NBTCompound) NBTUtils.getNBTBase(CraftPersistentDataContainer.getNBT(this.A)));
    }

    /**
    * Defines the NBT of the PersistentDataContainer stored in this {@link NBTPersistentDataContainer}.
    *
    * @param NBTCompound {@link NBTCompound} to be merged into the PersistentDataContainer.
    */

    @Override
    public void setTag(@NotNull NBTCompound NBTCompound) {
        CraftPersistentDataContainer.setNBT(this.A, NBTCompound.getComponent());
    }
}