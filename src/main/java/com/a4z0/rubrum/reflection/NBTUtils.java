package com.a4z0.rubrum.reflection;

import com.a4z0.rubrum.api.nbt.*;
import com.a4z0.rubrum.enums.Version;
import com.a4z0.rubrum.enums.Value;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class NBTUtils {

    protected static final Class<?> A = A();
    protected static final Class<?> B = B();

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
    * @return a {@link Class}.
    */

    private static @NotNull Class<?> B() {
        try {
            return Class.forName(Version.B().D() ? "net.minecraft.nbt.NBTBase" : "net.minecraft.server." + Version.BUKKIT_VERSION + ".NBTBase");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to get NBTBase class");
        }
    };

    /**
    * @param NBTCompound a {@link NBTCompound}.
    *
    * @return a NBT object.
    */

    public static @NotNull Object parseNBT(@NotNull NBTCompound NBTCompound) {
        try {
            Object NBT = A.getConstructor().newInstance();

            NBTCompound.serialize().forEach((K, N) -> {
                try {
                    NBT.getClass().getMethod(Version.B().D() ? "a" : "set", String.class, B).invoke(NBT, K, parseNBTBase((NBTBase<?>) N));
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new IllegalArgumentException("Error writing an NBT");
                }
            });

            return NBT;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalArgumentException("Error converting Compound to NBT");
        }
    };

    /**
    * @param Object a NBT object.
    *
    * @return a {@link NBTCompound}.
    */

    public static @NotNull NBTCompound parseNBTCompound(@NotNull Object Object) {
        try {
            HashMap<String, Object> Map = new HashMap<>();

            for(String Key : ((Set<String>) Object.getClass().getMethod(Version.B().D() ? "d" : "c").invoke(Object))) {

                Object NBT = Object.getClass().getMethod(Version.B().D() ? "c" : "get", String.class).invoke(Object, Key);
                byte ID = ((byte) NBT.getClass().getMethod(Version.B().D() ? "a" : "getTypeId").invoke(NBT));

                if(ID <= 0) continue;

                Map.put(Key, parseNMSNBTBase(NBT));
            };

            return new NBTCompound(Map);
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error converting NBT to Compound");
        }
    };

    /**
    * @param NBTBase a {@link NBTBase}.
    *
    * @return a NMS {@link NBTBase}.
    */

    public static @NotNull Object parseNBTBase(@NotNull NBTBase<?> NBTBase) {
        try {
            Object Base;

            if(!B.isInterface()) {
                Method Method = B.getDeclaredMethod("createTag", byte.class);
                Method.setAccessible(true);

                Base = Method.invoke(B, NBTBase.getTypeID());

                if(Base == null) Base = Method.invoke(B, (byte) 7);

            } else {
                Class<?> NBTTag = Class.forName("net.minecraft.nbt.NBTTag" + NBTBase.getClass().getSimpleName().substring(3));

                if(NBTBase.getTypeID() == 9 || NBTBase.getTypeID() == 10) {
                    Base = NBTTag.getConstructor().newInstance();
                }else{
                    Constructor<?> Constructor;

                    try {
                        Constructor = NBTTag.getDeclaredConstructor(getData(NBTBase).getClass());
                    }catch (NoSuchMethodException e) {
                        Constructor = NBTTag.getDeclaredConstructors()[0];
                    };

                    Constructor.setAccessible(true);

                    Base = Constructor.newInstance(getData(NBTBase));
                };
            };

            Field Field = Base.getClass().getDeclaredField(Value.V(NBTBase.getTypeID()).D());
            byte ID = ((byte) Base.getClass().getMethod(Version.B().D() ? "a" : "getTypeId").invoke(Base));
            Field.setAccessible(true);

            Object Data;

            if(NBTBase.getTypeID() == 9) {
                Field Type = Base.getClass().getDeclaredField(Version.B().D() ? "w" : "type");
                Type.setAccessible(true);

                Type.set(Base, getType((NBTList) NBTBase));

                ArrayList<Object> Array = new ArrayList<>();

                ((ArrayList<NBTBase<?>>) getData(NBTBase)).forEach(B -> {
                    Array.add(parseNBTBase(B));
                });

                Data = Array;

            }else if(NBTBase.getTypeID() == 10) {
                HashMap<String, Object> Map = new HashMap<>();

                ((Map<String, NBTBase<?>>) getData(NBTBase)).forEach((A, B) -> {
                    Map.put(A, parseNBTBase(B));
                });

                Data = Map;

            }else{
                Data = (NBTBase.getTypeID() != ID) ? serialize(getData(NBTBase)) : getData(NBTBase);
            };

            Field.set(Base, Data);

            return Base;

        }catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error reading NBTBase");
        }
    };

    /**
    * @param NBTBase a NMS {@link NBTBase}.
    *
    * @return a {@link NBTBase}.
    */

    public static @NotNull NBTBase<?> parseNMSNBTBase(@NotNull Object NBTBase) {
        try {
            byte ID = ((byte) NBTBase.getClass().getMethod(Version.B().D() ? "a" : "getTypeId").invoke(NBTBase));

            Field Field = NBTBase.getClass().getDeclaredField(Value.V(ID).D());
            Field.setAccessible(true);

            Object Data = Field.get(NBTBase);

            if(ID == 7) {
                Object Deserialized = deserialize((byte[]) Data);

                if(Deserialized instanceof long[]) {
                    ID = 12;
                    Data = Deserialized;
                };
            };

            NBTBase<?> NBT = Objects.requireNonNull(getNBTBase(ID, Data));

            if(NBT.getTypeID() == 9) {

                Field Type = NBTBase.getClass().getDeclaredField(Version.B().D() ? "w" : "type");
                Type.setAccessible(true);

                setType((NBTList) NBT, (byte) Type.get(NBTBase));
            };

            return NBT;
        }catch (NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error reading NMS NBTBase object");
        }
    };

    /**
    * @param ID a {@link NBTBase} ID.
    *
    * @return a {@link NBTBase}.
    */

    public static NBTBase<?> getNBTBase(byte ID, Object Value) {
        switch(ID) {
            case 1: {
                return new NBTByte((byte) Value);
            }
            case 2: {
                return new NBTShort((short) Value);
            }
            case 3: {
                return new NBTInt((int) Value);
            }
            case 4: {
                return new NBTLong((long) Value);
            }
            case 5: {
                return new NBTFloat((float) Value);
            }
            case 6: {
                return new NBTDouble((double) Value);
            }
            case 7: {
                return new NBTByteArray((byte[]) Value);
            }
            case 8: {
                return new NBTString((String) Value);
            }
            case 9: {
                ArrayList<NBTBase<?>> Array = new ArrayList<>();

                for(Object NBTBase : (ArrayList<Object>) Value) {
                    Array.add(parseNMSNBTBase(NBTBase));
                };

                return new NBTList(Array);
            }
            case 10: {

                Map<String, Object> Map = new HashMap<>();

                ((Map<String, Object>) Value).forEach((A, B) -> {
                    Map.put(A, parseNMSNBTBase(B));
                });

                return new NBTCompound(Map);
            }
            case 11: {
                return new NBTIntArray((int[]) Value);
            }
            case 12: {
                return new NBTLongArray((long[]) Value);
            }
            default: return null;
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

    /**
    * @param NBTBase a {@link NBTBase}.
    *
    * @return a object value.
    */

    private static @NotNull Object getData(@NotNull NBTBase<?> NBTBase) {
        try {
            Field Data = NBTBase.getClass().getSuperclass().getDeclaredField("Data");
            Data.setAccessible(true);

            return Data.get(NBTBase);
        }catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error reading NBTBase Data");
        }
    };

    /**
    * Sets the data of {@link NBTBase}.
    *
    * @param NBTBase a {@link NBTBase}.
    * @param Value a value.
    */

    private static void setData(@NotNull NBTBase<?> NBTBase, @NotNull Object Value) {
        try {
            Field Data = NBTBase.getClass().getSuperclass().getDeclaredField("Data");
            Data.setAccessible(true);

            Data.set(NBTBase, Value);
        }catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("Error saving data in NBTBase");
        }
    };

    /**
    * @param NBTList a {@link NBTList}.
    *
    * @return a {@link NBTList} type.
    */

    private static byte getType(@NotNull NBTList NBTList) {
        try {
            Field Type = NBTList.getClass().getDeclaredField("Type");
            Type.setAccessible(true);

            return (byte) Type.get(NBTList);
        }catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("Error reading NBTList Type");
        }
    };

    /**
    * Sets the type of {@link NBTList}.
    *
    * @param NBTList a {@link NBTList}.
    * @param Value a byte value.
    */

    private static void setType(@NotNull NBTList NBTList, byte Value) {
        try {
            Field Type = NBTList.getClass().getDeclaredField("Type");
            Type.setAccessible(true);

            Type.set(NBTList, Value);
        }catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("Error saving type in NBTList");
        }
    };
};