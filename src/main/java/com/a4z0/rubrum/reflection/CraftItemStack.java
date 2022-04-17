package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.enums.Version;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class CraftItemStack {

    private static final Class<?> A = A();

    /**
    * @return a {@link Class}.
    */

    private static @NotNull Class<?> A() {
        try {
            return Class.forName("org.bukkit.craftbukkit." + Version.BUKKIT_VERSION + ".inventory.CraftItemStack");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get CraftItemStack class");
        }
    };

    /**
    * @param Item a bukkit {@link ItemStack}.
    *
    * @return a NMS {@link ItemStack}.
    */

    public static @NotNull Object getNMS(@NotNull ItemStack Item) {
        try {
            return A.getMethod("asNMSCopy", ItemStack.class).invoke(A, Item);
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error converting an bukkit ItemStack to NMS ItemStack object");
        }
    };

    /**
    * @param Item a NMS {@link ItemStack} object.
    *
    * @return a bukkit {@link ItemStack}.
    */

    public static ItemStack asBukkitCopy(@NotNull Object Item) {
        try {
            return (ItemStack) A.getMethod("asBukkitCopy", Item.getClass()).invoke(A, Item);
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error converting an NMS ItemStack object to bukkit ItemStack");
        }
    };

    /**
    * @param Item a NMS {@link ItemStack} object.
    *
    * @return a NBT object.
    */

    public static Object getNBT(@NotNull Object Item) {
        try {
            return Item.getClass().getMethod(Version.B().D() ? "s" : "getTag").invoke(Item);
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error getting NBTTagCompound from a NMS ItemStack object");
        }
    };

    /**
    * @param Item a NMS {@link ItemStack} object.
    * @param NBT a NBT object.
    *
    * @return a NMS {@link ItemStack} object with the NBT .
    */

    public static Object setNBT(@NotNull Object Item, @NotNull Object NBT) {
        try {
            Item.getClass().getMethod(Version.B().D() ? "a" : "setTag", NBT.getClass()).invoke(Item, NBT);

            return Item;
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error setting NBT on a NMS ItemStack");
        }
    };
};