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