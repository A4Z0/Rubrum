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

import com.a4z0.rubrum.reflection.CraftItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
* NBT component of an {@link ItemStack}.
*/

public class NBTItem extends NBTCompound {

    private final ItemStack A;
    private ItemStack B;

    /**
    * Construct a {@link NBTItem}.
    *
    * @param Item {@link ItemStack} to be read.
    */

    public NBTItem(@NotNull ItemStack Item) {

        if(Item.getType().equals(Material.AIR))
            throw new NullPointerException("Item Material can't be AIR");

        this.A = Item;
        this.B = Item.clone();

        if(this.hasNBTData()) {
            super.setTag((NBTCompound) NBTUtils.getNBTBase(CraftItemStack.getNBT(CraftItemStack.getNMS(this.A))));
        }
    }

    /**
    * Defines the NBT of the item stored in this {@link NBTItem}.
    *
    * @param NBTCompound {@link NBTCompound} to be merged into the item.
    */

    @Override
    public void setTag(@NotNull NBTCompound NBTCompound) {
        this.B = CraftItemStack.parseNMSItem(CraftItemStack.setNBT(CraftItemStack.getNMS(this.B), NBTCompound.getComponent()));
    }

    /**
    * @param Item Item to be merged with this {@link NBTCompound}.
    *
    * @return the item merged with this {@link NBTCompound}.
    */

    public @NotNull ItemStack merge(@NotNull ItemStack Item) {
        return CraftItemStack.parseNMSItem(CraftItemStack.setNBT(CraftItemStack.getNMS(Item), this.getComponent()));
    }

    /**
    * @return the given {@link ItemStack}.
    */

    public @NotNull ItemStack getRawItem() {
        return this.A;
    }

    /**
    * @return the new {@link ItemStack}.
    */

    public @NotNull ItemStack getItem() {
        return this.B;
    }

    /**
    * @return true if the item contains NBT Data.
    */

    public final boolean hasNBTData() {
        return CraftItemStack.getNBT(CraftItemStack.getNMS(this.A)) != null;
    }
}