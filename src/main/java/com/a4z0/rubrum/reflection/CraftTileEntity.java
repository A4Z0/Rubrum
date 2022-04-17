package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.enums.Version;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CraftTileEntity {

    private static final Class<?> A = A();
    private static final Class<?> B = B();

    /**
    * @return a {@link Class}.
    */

    private static @NotNull Class<?> A() {
        try {
            return Class.forName("org.bukkit.craftbukkit." + Version.BUKKIT_VERSION + ".CraftWorld");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get CraftWorld class");
        }
    };

    /**
    * @return a {@link Class}.
    */

    private static @NotNull Class<?> B() {
        try {
            return Class.forName(Version.B().D() ? "net.minecraft.core.BlockPosition" : "net.minecraft.server." + Version.BUKKIT_VERSION + ".BlockPosition");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get BlockPosition class");
        }
    };

    /**
    * @param Tile a {@link BlockState}.
    *
    * @return a NMS TileEntity object.
    */

    public static Object getNMS(@NotNull BlockState Tile) {
        try {
            Object C = A.cast(Tile.getWorld());
            Object N = C.getClass().getMethod("getHandle").invoke(C);
            Object P = B.getConstructor(int.class, int.class, int.class).newInstance(Tile.getX(), Tile.getY(), Tile.getZ());

            Object T;

            if(Version.B().D()) {
                T = N.getClass().getMethod("getBlockEntity", P.getClass(), boolean.class).invoke(N, P, false);
            }else {
                T = N.getClass().getMethod("getTileEntity", P.getClass()).invoke(N, P);
            };

            return T;
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error getting NMS TileEntity from a BlockState Tile");
        }
    };

    /**
    * @param Tile a NMS TileEntity object.
    *
    * @return a NMS NBT object.
    */

    public static Object getNBT(Object Tile) {
        try {
            if(Tile == null) return null;

            Object NBT = NBTUtils.A.getConstructor().newInstance();

            if(Version.B().D()) {
                NBT = Tile.getClass().getMethod("m").invoke(Tile);
            }else {
                Tile.getClass().getMethod((Version.B().T() ? "save" : "b"), NBT.getClass()).invoke(Tile, NBT);
            };

            return NBT;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new IllegalArgumentException("Error getting NBTTagCompound from a NMS TileEntity object");
        }
    };

    /**
    * Defines the NBT of the given NMS TileEntity object.
    *
    * @param Tile a NMS TileEntity object.
    * @param NBT a NBT object.
    */

    public static void setNBT(Object Tile, Object NBT) {
        try {
            if(Tile == null || NBT == null) return;

            if(Version.B().M(Version.V1_17_R1)) {
                Tile.getClass().getMethod("a", NBT.getClass()).invoke(Tile, NBT);
            }else if(Version.B().M(Version.V1_16_R1)) {
                Object D = Tile.getClass().getMethod("getBlock").invoke(Tile);
                Tile.getClass().getMethod("load", D.getClass(), NBT.getClass()).invoke(Tile, D, NBT);
            }else {
                Tile.getClass().getMethod(Version.B().T() ? "load" : "a", NBT.getClass()).invoke(Tile, NBT);
            };
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error setting NBT on a NMS TileEntity");
        }
    };
};