package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.api.nbt.NBTUtils;
import com.a4z0.rubrum.enums.Minecraft;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CraftEntity {

    public static final Class<?> A;

    static {
        try {
            A = Class.forName("org.bukkit.craftbukkit." + Minecraft.PACKAGE_VERSION + ".entity.CraftEntity");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Could not find CraftEntity class");
        }
    }

    /**
    * @param Entity Entity to be converted.
    *
    * @return an NMS version of the given entity.
    */

    public static @NotNull Object getNMS(@NotNull Entity Entity) {
        try {
            return (A.cast(Entity)).getClass().getMethod("getHandle").invoke((A.cast(Entity)));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error converting an Entity to an NMS Entity");
        }
    }

    /**
    * @param Entity NMS version of an entity.
    *
    * @return the NBT component of the given entity.
    */

    public static @NotNull Object getNBT(@NotNull Object Entity) {
        try {
            Object NBT = NBTUtils.NBTCOMPOUND.O();

            Method Method = Entity.getClass().getDeclaredMethod("b", NBT.getClass());
            Method.setAccessible(true);
            Method.invoke(Entity, NBT);

            return NBT;
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error when trying to read the NBT of an NMS Entity");
        }
    }

    /**
    * @param Entity NMS version of an entity to be changed.
    * @param NBT NBT to be added to the entity.
    */

    public static void setNBT(@NotNull Object Entity, @NotNull Object NBT) {
        try {
            for(String Fieldname : new String[]{"load", "a"}) {
                try {
                    Method Method = Entity.getClass().getMethod(Fieldname, NBT.getClass());
                    Method.setAccessible(true);

                    Method.invoke(Entity, NBT);

                    break;
                } catch (NoSuchMethodException ignored) {}
            }

        } catch (InvocationTargetException | IllegalAccessException  e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error setting NBT on a NMS Entity");
        }
    }
}