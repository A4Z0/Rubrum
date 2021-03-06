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

package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.enums.Minecraft;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class CraftItemStack {

    private static final Class<?> A;

    static {
        try {
            A = Class.forName("org.bukkit.craftbukkit." + Minecraft.PACKAGE_VERSION + ".inventory.CraftItemStack");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Could not find CraftItemStack class");
        }
    }

    /**
    * @param Item Item to be converted.
    *
    * @return an NMS version of the given item.
    */

    public static @NotNull Object getNMS(@NotNull ItemStack Item) {
        try {
            return A.getMethod("asNMSCopy", ItemStack.class).invoke(A, Item);
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error converting an ItemStack to NMS ItemStack object");
        }
    }

    /**
    * @param Item NMS version of an item.
    *
    * @return the NBT component of the given item.
    */

    public static Object getNBT(@NotNull Object Item) {
        try {
            return Item.getClass().getMethod(Minecraft.getCurrentVersion().isDrasticallyChanged() ? "s" : "getTag").invoke(Item);
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error getting NBTTagCompound from a NMS ItemStack object");
        }
    }

    /**
    * @param Item NMS version of an item to be changed.
    * @param NBT NBT to be added to Item.
    *
    * @return an NMS version of the Item merged with the NBT.
    */

    public static Object setNBT(@NotNull Object Item, @NotNull Object NBT) {
        try {
            Item.getClass().getMethod(Minecraft.getCurrentVersion().isDrasticallyChanged() ? "c" : "setTag", NBT.getClass()).invoke(Item, NBT);

            return Item;
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error setting NBT on a NMS ItemStack");
        }
    }

    /**
    * @param Item NMS version of an item.
    *
    * @return the NMS version of the Item as an {@link ItemStack}.
    */

    public static ItemStack parseNMSItem(@NotNull Object Item) {
        try {
            return (ItemStack) A.getMethod("asBukkitCopy", Item.getClass()).invoke(A, Item);
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error converting an NMS ItemStack object to bukkit ItemStack");
        }
    }
}