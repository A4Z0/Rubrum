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
import com.a4z0.rubrum.reflection.CraftTileEntity;
import org.bukkit.block.BlockState;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

/**
* NBT component of an {@link BlockState} TileEntity.
*/

public class NBTTileEntity extends NBTCompound {

    protected final BlockState A;
    protected NBTPersistentDataContainer B;

    /**
    * Construct a {@link NBTTileEntity} with the given params.
    *
    * @param BlockState {@link BlockState} to be read.
    */

    public NBTTileEntity(@NotNull BlockState BlockState) {
        this.A = BlockState;

        if(CraftTileEntity.getNBT(CraftTileEntity.getNMS(this.A)) != null) {
            super.setTag((NBTCompound) NBTUtils.getNBTBase(CraftTileEntity.getNBT(CraftTileEntity.getNMS(this.A))));
        }
    }

    /**
    * Defines the NBT of the BlockState's TileEntity stored in this {@link NBTTileEntity}.
    *
    * @param NBTCompound {@link NBTCompound} to be merged into the TileEntity.
    */

    @Override
    public void setTag(@NotNull NBTCompound NBTCompound) {
       CraftTileEntity.setNBT(CraftTileEntity.getNMS(this.A), NBTCompound.getComponent());
    }

    /**
    * @return the given {@link BlockState}.
    */

    public @NotNull BlockState getBlockState() {
        return A;
    }

    /**
    * @return an TileEntity {@link NBTPersistentDataContainer}.
    */

    public NBTPersistentDataContainer getPersistentDataContainer() {

        if(!Minecraft.V1_14_R1.isEqualOrNewer(Minecraft.getCurrentVersion()))
            throw new IllegalArgumentException("Feature available from version 1.14+");

        if(this.B == null) {
            try {
                Object TileEntity = CraftTileEntity.getNMS(this.A);
                Field Data = TileEntity.getClass().getField("persistentDataContainer");
                Data.setAccessible(true);

                PersistentDataContainer PersistentDataContainer = (PersistentDataContainer) Data.get(TileEntity);

                this.B = PersistentDataContainer != null ? new NBTPersistentDataContainer(PersistentDataContainer) : null;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("Error getting PersistentDataContainer from a TileEntity");
            }
        }

        return this.B;
    }
}