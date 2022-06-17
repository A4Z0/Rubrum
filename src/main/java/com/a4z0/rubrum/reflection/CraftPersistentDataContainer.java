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

import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CraftPersistentDataContainer {

    /**
    * @param PersistentDataContainer a {@link PersistentDataContainer}.
    *
    * @return the NBT component of the given PersistentDataContainer.
    */

    public static @NotNull Object getNBT(@NotNull PersistentDataContainer PersistentDataContainer) {
        try {
            return PersistentDataContainer.getClass().getMethod("toTagCompound").invoke(PersistentDataContainer);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error parsing PersistentDataContainer for NBT");
        }
    }

    /**
    * @param PersistentDataContainer a {@link PersistentDataContainer}.
    * @param NBT NBT to be added to the PersistentDataContainer.
    */

    public static void setNBT(@NotNull PersistentDataContainer PersistentDataContainer, @NotNull Object NBT) {
        try {
            Map<String, Object> Map = (Map<String, Object>) PersistentDataContainer.getClass().getMethod("getRaw").invoke(PersistentDataContainer);
            Map.clear();

            PersistentDataContainer.getClass().getMethod("putAll", NBT.getClass()).invoke(PersistentDataContainer, NBT);
        }catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error setting PersistentDataContainer");
        }
    }
}