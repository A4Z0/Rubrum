package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.api.nbt.NBTUtils;
import com.a4z0.rubrum.api.version.enums.Version;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CraftEntity {

    public static final Class<?> NMS_CRAFTENTITY_CLASS = GET_CRAFTENTITY_CLASS();
    public static final Class<?> NMS_ENTITYLIVING_CLASS = GET_ENTITYLIVING_CLASS();

    /**
    * @return the NMS class of CraftEntity.
    */

    private static @NotNull Class<?> GET_CRAFTENTITY_CLASS() {
        try {
            return Class.forName("org.bukkit.craftbukkit." + Version.BUKKIT_VERSION + ".entity.CraftEntity");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get CraftEntity class");
        }
    };

    /**
    * @return the NMS class of EntityLiving.
    */

    private static @NotNull Class<?> GET_ENTITYLIVING_CLASS() {
        try {
            return Class.forName(Version.B().D() ? "net.minecraft.world.entity.EntityLiving" : "net.minecraft.server." + Version.BUKKIT_VERSION + ".EntityLiving");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get EntityLiving class");
        }
    };

    /**
    * @param Entity Entity to be converted.
    *
    * @return an NMS version of the given entity.
    */

    public static @NotNull Object getNMS(@NotNull Entity Entity) {
        try {
            return (NMS_CRAFTENTITY_CLASS.cast(Entity)).getClass().getMethod("getHandle").invoke((NMS_CRAFTENTITY_CLASS.cast(Entity)));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error converting an Entity to an NMS Entity");
        }
    };

    /**
    * @param Entity NMS version of an entity.
    *
    * @return the NBT component of the given entity.
    */

    public static @NotNull Object getNBT(@NotNull Object Entity) {
        try {
            Object NBT = NBTUtils.GET_NBTBASE_INSTANCE((byte) 10);

            Method Method = Entity.getClass().getDeclaredMethod("b", NBT.getClass());
            Method.setAccessible(true);
            Method.invoke(Entity, NBT);

            return NBT;
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error when trying to read the NBT of an NMS Entity");
        }
    };

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
                } catch (NoSuchMethodException ignored) {
                    continue;
                }

                break;
            };

        } catch (InvocationTargetException | IllegalAccessException  e) {
            throw new IllegalArgumentException("Error setting NBT on a NMS Entity");
        }
    };
};