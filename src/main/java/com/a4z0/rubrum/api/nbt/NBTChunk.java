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

import com.a4z0.rubrum.enums.Minecraft;
import org.bukkit.Chunk;
import org.jetbrains.annotations.NotNull;

/**
* NBT component of a {@link Chunk}.
*/

public class NBTChunk extends NBTCompound {

    protected final Chunk A;
    protected final NBTPersistentDataContainer B;

    /**
    * Construct a {@link NBTChunk} with the given params.
    *
    * @param Chunk {@link Chunk} to be read.
    */

    public NBTChunk(@NotNull Chunk Chunk) {

        if(!Minecraft.V1_16_R3.isEqualOrNewer(Minecraft.getCurrentVersion())) {
            throw new IllegalArgumentException("Feature available from version 1.16.4+");
        }

        this.A = Chunk;
        this.B = new NBTPersistentDataContainer(Chunk.getPersistentDataContainer());

        super.setTag(this.B);
    }

    /**
    * Defines the NBT of the chunk stored in this {@link NBTChunk}.
    *
    * @param NBTCompound {@link NBTCompound} to be merged into the chunk.
    */

    @Override
    public void setTag(@NotNull NBTCompound NBTCompound) {
        this.B.setTag(NBTCompound);
    }

    /**
    * @return the given {@link Chunk}.
    */

    public @NotNull Chunk getChunk() {
        return this.A;
    }
}