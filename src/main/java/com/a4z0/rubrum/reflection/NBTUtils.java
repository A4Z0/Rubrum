package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.api.nbt.Compound;
import com.a4z0.rubrum.enums.Value;
import com.a4z0.rubrum.enums.Version;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class NBTUtils {

    private static final Class<?> A = A();

    /**
    * @return a {@link Class}.
    */

    private static @NotNull Class<?> A() {
        try {
            return Class.forName(Version.B().D() ? "net.minecraft.nbt.NBTTagCompound" : "net.minecraft.server." + Version.BUKKIT_VERSION + ".NBTTagCompound");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get NBTTagCompound class");
        }
    };

    /**
    * @param NBT a NBT object.
    *
    * @return a {@link Compound}.
    */

    public static @NotNull Compound getCompound(@NotNull Object NBT) {

        Map<String, Object> Map = new HashMap<>();

        try {

            Set<String> Keys = (Set<String>) NBT.getClass().getMethod(Version.B().D() ? "d" : "c").invoke(NBT);

            for(String K : Keys) {

                Object B = NBT.getClass().getMethod(Version.B().D() ? "c" : "get", String.class).invoke(NBT, K);

                if((byte) B.getClass().getMethod(Version.B().D() ? "a" : "getTypeId").invoke(B) <= 0) continue;

                Field D = B.getClass().getDeclaredField(Version.B().D() ? Value.R((byte) B.getClass().getMethod("a").invoke(B)).F() : "data");

                D.setAccessible(true);

                boolean I = false;
                boolean V = false;

                if((byte) B.getClass().getMethod(Version.B().D() ? "a" : "getTypeId").invoke(B) == 1) {
                    if(((byte) D.get(B) >= 0) && ((byte) D.get(B) <= 1)) {
                        I = true;
                        V = ((byte) D.get(B)) != 0;
                    };
                };

                boolean J = false;
                Object O = null;

                if((byte) B.getClass().getMethod(Version.B().D() ? "a" : "getTypeId").invoke(B) == 7) {
                    O = NBTUtils.deserialize(((byte[]) D.get(B)));

                    if(!Objects.isNull(O)) J = true;
                };

                Map.put(K, I ? V : (J ? O : D.get(B)));
            };

            return new Compound(Map);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            throw new IllegalArgumentException("Error converting NBT to Compound");
        }
    };

    /**
    * @param Compound a {@link Compound}.
    *
    * @return a {@link Compound} as a NBT object.
    */

    public static @NotNull Object getNBT(@NotNull Compound Compound) {
        try {

            Object N = A.getConstructors()[0].newInstance();

            Compound.serialize().forEach((A, B) -> {
                try {
                    Value V = Value.R(B.getClass());

                    N.getClass().getMethod(V.S(), String.class, (V == Value.OBJECT ? byte[].class : B.getClass())).invoke(N, A, (V == Value.OBJECT ? serialize(B) : B));
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new IllegalArgumentException("Error reading a Compound");
                };
            });

            return N;

        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new IllegalArgumentException("Error converting NBT to Compound");
        }
    };

    /**
    * @param Object {@link Object} to serialize.
    *
    * @return a serialized {@link Object}.
    */

    public static byte[] serialize(@NotNull Object Object) {

        ByteArrayOutputStream A = new ByteArrayOutputStream();
        ObjectOutputStream B;

        try {
            B = new ObjectOutputStream(A);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error compiling Object");
        };

        try {
            B.writeObject(Object);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error writing Object");
        };

        return A.toByteArray();
    };

    /**
    * @param Bytes a serialized {@link Object}.
    *
    * @return a deserialize {@link Object}.
    */

    public static Object deserialize(byte[] Bytes) {

        ByteArrayInputStream A = new ByteArrayInputStream(Bytes);
        ObjectInputStream B;

        try {
            B = new ObjectInputStream(A);
        } catch (IOException e) {
            return null;
        };

        try {
            return B.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Error reading Object");
        }
    };
};