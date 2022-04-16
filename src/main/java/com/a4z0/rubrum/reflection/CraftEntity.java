package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.enums.Version;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CraftEntity {

    private static final Class<?> A = A();
    private static final Class<?> B = B();

    /**
    * @return a {@link Class}.
    */

    private static @NotNull Class<?> A() {
        try {
            return Class.forName("org.bukkit.craftbukkit." + Version.BUKKIT_VERSION + ".entity.CraftEntity");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get CraftEntity class");
        }
    };

    /**
    * @return a {@link Class}.
    */

    private static @NotNull Class<?> B() {
        try {
            return Class.forName(Version.B().D() ? "net.minecraft.world.entity.EntityLiving" : "net.minecraft.server." + Version.BUKKIT_VERSION + ".EntityLiving");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get EntityLiving class");
        }
    };

    /**
    * @param Entity a bukkit {@link Entity}.
    *
    * @return a NMS {@link Entity}.
    */

    public static @NotNull Object asNMSCopy(@NotNull Entity Entity) {
        try {
            return (A.cast(Entity)).getClass().getMethod("getHandle").invoke((A.cast(Entity)));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error converting an bukkit Entity to NMS Entity object");
        }
    };

    /**
    * @param Entity a NMS {@link Entity} object.
    *
    * @return a NBT object.
    */

    public static Object getNBTEntity(@NotNull Object Entity) {
        try {
            Object C = NBTUtils.A.getConstructors()[0].newInstance();

            for(Method M : Entity.getClass().getMethods()){
                if((M.getName().equals("b")) && (M.getParameterTypes().length == 1) && (M.getParameterTypes()[0] == C.getClass())){
                    try {
                        M.invoke(Entity, C);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        throw new IllegalArgumentException("Error reading NBTTagCompound from an NMS Entity object");
                    };
                };
            };

            return C;
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new IllegalArgumentException("Error getting NBTTagCompound from a NMS Entity object");
        }
    };

    /**
    * Defines the NBT of the given NMS {@link Entity} object.
    *
    * @param Entity a NMS {@link Entity} object.
    * @param NBT a NBT object.
    */

    public static void setNBT(@NotNull Object Entity, @NotNull Object NBT) {
        try {
            if(Entity.getClass().getSuperclass().equals(B)) {
                Entity.getClass().getMethod("a", NBT.getClass()).invoke(Entity, NBT);
            }else{
                Entity.getClass().getMethod(Version.B().D() ? "load" : "f", NBT.getClass()).invoke(Entity, NBT);
            };

        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error getting NBTTagCompound from a NMS Entity object");
        }
    };
};