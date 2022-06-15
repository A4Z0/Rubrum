package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.api.nbt.NBTUtils;
import com.a4z0.rubrum.enums.Minecraft;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CraftTileEntity {

    public static final Class<?> A;
    public static final Class<?> B;

    static {
        try {
            A = Class.forName("org.bukkit.craftbukkit." + Minecraft.PACKAGE_VERSION + ".CraftWorld");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Could not find CraftWorld class");
        }
    }

    static {
        try {
            B = Class.forName(Minecraft.getCurrentVersion().isDrasticallyChanged() ? "net.minecraft.core.BlockPosition" : "net.minecraft.server." + Minecraft.PACKAGE_VERSION + ".BlockPosition");
        }catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Could not find BlockPosition class");
        }
    }

    /**
    * @param BlockState BlockState to be converted.
    *
    * @return an NMS version of TileEntity by the given blockstate.
    */

    public static Object getNMS(@NotNull BlockState BlockState) {
        try {
            Object C = A.cast(BlockState.getWorld());
            Object N = C.getClass().getMethod("getHandle").invoke(C);
            Object P = B.getConstructor(int.class, int.class, int.class).newInstance(BlockState.getX(), BlockState.getY(), BlockState.getZ());

            Object TileEntity;

            if(Minecraft.getCurrentVersion().isDrasticallyChanged()) {
                TileEntity = N.getClass().getMethod("getBlockEntity", P.getClass(), boolean.class).invoke(N, P, false);
            }else {
                TileEntity = N.getClass().getMethod("getTileEntity", P.getClass()).invoke(N, P);
            }

            return TileEntity;
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error getting NMS TileEntity from a BlockState");
        }
    }

    /**
    * @param TileEntity NMS version of an TileEntity.
    *
    * @return the NBT component of the given TileEntity.
    */

    public static Object getNBT(Object TileEntity) {
        if(TileEntity == null) return null;

        try {

            Object NBT = NBTUtils.NBTTagCompound.getNBTObject(new HashMap<>());

            for(String Fieldname : new String[]{"save", "m", "b"}) {
                try {
                    if(!Fieldname.equals("m")) {
                        Method Method = TileEntity.getClass().getMethod(Fieldname, NBT.getClass());
                        Method.setAccessible(true);

                        Method.invoke(TileEntity, NBT);

                        break;
                    }

                    Method Method = TileEntity.getClass().getMethod(Fieldname);
                    Method.setAccessible(true);

                    NBT = Method.invoke(TileEntity);

                } catch (NoSuchMethodException ignored) {
                    continue;
                }

                break;
            }

            return NBT;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error getting NBT from a NMS TileEntity");
        }
    }

    /**
    * @param TileEntity NMS version of an TileEntity to be changed.
    * @param NBT NBT to be added to the TilEentity.
    */

    public static void setNBT(Object TileEntity, @NotNull Object NBT) {
        if(TileEntity == null) return;

        try {
            if(Minecraft.V1_17_R1.isEqualOrOlder(Minecraft.getCurrentVersion())) {
                TileEntity.getClass().getMethod("a", NBT.getClass()).invoke(TileEntity, NBT);
            }else if(Minecraft.V1_16_R1.isEqualOrOlder(Minecraft.getCurrentVersion())) {
                Object D = TileEntity.getClass().getMethod("getBlock").invoke(TileEntity);
                TileEntity.getClass().getMethod("load", D.getClass(), NBT.getClass()).invoke(TileEntity, D, NBT);
            }else {
                TileEntity.getClass().getMethod(Minecraft.getCurrentVersion().isTwoHanded() ? "load" : "a", NBT.getClass()).invoke(TileEntity, NBT);
            }

        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error setting NBT on a NMS TileEntity");
        }
    }
}