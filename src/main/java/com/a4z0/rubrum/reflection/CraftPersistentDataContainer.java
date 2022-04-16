package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.api.nbt.NBTCompound;
import com.a4z0.rubrum.enums.Version;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

public class CraftPersistentDataContainer {

    private static final Class<?> A = A();

    /**
    * @return a {@link Class}.
    */

    private static @NotNull Class<?> A() {
        try {
            return Class.forName("org.bukkit.craftbukkit." + Version.BUKKIT_VERSION + ".persistence.CraftPersistentDataContainer");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get CraftPersistentDataContainer class");
        }
    };

    /**
    * @param Object a {@link PersistentDataHolder} object.
    *
    * @return a {@link PersistentDataContainer}.
    */

    public static PersistentDataContainer getPersistentData(@NotNull Object Object) {
        if(Object instanceof PersistentDataHolder) {
            return ((PersistentDataHolder) Object).getPersistentDataContainer();
        };

        return null;
    };

    /**
    * @param Container a {@link PersistentDataContainer}.
    *
    * @return a NMS NBT object.
    */

    public static Object getNBTContainer(@NotNull PersistentDataContainer Container) {
        try {
            Object Data = A.cast(Container);
            return Data.getClass().getMethod("toTagCompound").invoke(Data);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error parsing PersistentDataContainer for NMS NBT object");
        }
    };

    /**
    * Defines the {@link PersistentDataContainer} with the {@link NBTCompound}.
    *
    * @param Container a {@link PersistentDataContainer}.
    * @param NBTCompound a {@link NBTCompound}.
    */

    public static void setPersistentData(@NotNull PersistentDataContainer Container, @NotNull NBTCompound NBTCompound) {
        try {
            Object NBT = NBTUtils.parseNBT(NBTCompound);
            Object Craft = (A.cast(Container));

            Map<String, Object> Map = (Map<String, Object>) Craft.getClass().getMethod("getRaw").invoke(Craft);
            Map.clear();

            Craft.getClass().getMethod("putAll", NBT.getClass()).invoke(Container, NBT);
        }catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error setting PersistentDataContainer");
        }
    };
};