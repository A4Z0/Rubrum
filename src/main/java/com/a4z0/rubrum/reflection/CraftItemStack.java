package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.api.version.enums.Version;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class CraftItemStack {

    /**
    * Stores the CraftItemStack NMS class.
    */

    public static final Class<?> NMS_CRAFTITEMSTACK_CLASS = GET_CRAFTITEMSTACK_CLASS();

    /**
    * @return the NMS class of CraftItemStack.
    */

    private static @NotNull Class<?> GET_CRAFTITEMSTACK_CLASS() {
        try {
            return Class.forName("org.bukkit.craftbukkit." + Version.BUKKIT_VERSION + ".inventory.CraftItemStack");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get CraftItemStack class");
        }
    };

    /**
    * @param Item Item to be converted.
    *
    * @return an NMS version of the given item.
    */

    public static @NotNull Object getNMS(@NotNull ItemStack Item) {
        try {
            return NMS_CRAFTITEMSTACK_CLASS.getMethod("asNMSCopy", ItemStack.class).invoke(NMS_CRAFTITEMSTACK_CLASS, Item);
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error converting an ItemStack to NMS ItemStack object");
        }
    };

    /**
    * @param Item NMS version of an item.
    *
    * @return the NBT component of the given item.
    */

    public static Object getNBT(@NotNull Object Item) {
        try {
            return Item.getClass().getMethod(Version.B().D() ? "s" : "getTag").invoke(Item);
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error getting NBTTagCompound from a NMS ItemStack object");
        }
    };

    /**
    * @param Item NMS version of an item to be changed.
    * @param NBT NBT to be added to Item.
    *
    * @return an NMS version of the Item merged with the NBT.
    */

    public static Object setNBT(@NotNull Object Item, @NotNull Object NBT) {
        try {
            Item.getClass().getMethod(Version.B().D() ? "a" : "setTag", NBT.getClass()).invoke(Item, NBT);

            return Item;
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error setting NBT on a NMS ItemStack");
        }
    };

    /**
    * @param Item NMS version of an item.
    *
    * @return the NMS version of the Item as an {@link ItemStack}.
    */

    public static ItemStack parseNMSItem(@NotNull Object Item) {
        try {
            return (ItemStack) NMS_CRAFTITEMSTACK_CLASS.getMethod("asBukkitCopy", Item.getClass()).invoke(NMS_CRAFTITEMSTACK_CLASS, Item);
        } catch (Error | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error converting an NMS ItemStack object to bukkit ItemStack");
        }
    };
};