package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.enums.Version;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public enum NBTUtils {
    NBTEND("NBTTagEnd", new Object[]{}, new String[]{}),
    NBTBYTE("NBTTagByte", new Object[]{(byte) 0}, new String[]{"data", "x"}),
    NBTSHORT("NBTTagShort", new Object[]{(short) 0}, new String[]{"data", "c"}),
    NBTINT("NBTTagInt", new Object[]{0}, new String[]{"data", "c"}),
    NBTLONG("NBTTagLong", new Object[]{0L}, new String[]{"data", "c"}),
    NBTFLOAT("NBTTagFloat", new Object[]{0f}, new String[]{"data", "w"}),
    NBTDOUBLE("NBTTagDouble", new Object[]{0d}, new String[]{"data", "w"}),
    NBTBYTEARRAY("NBTTagByteArray", new Object[]{new Byte[]{}}, new String[]{"data", "c"}),
    NBTSTRING("NBTTagString", new Object[]{""}, new String[]{"data", "A"}),
    NBTLIST("NBTTagList", new Object[]{(new ArrayList<>()), (byte) 0}, new String[]{"data", "c", "list", "w"}),
    NBTCOMPOUND("NBTTagCompound", new Object[]{(new HashMap<>())}, new String[]{"map", "x"}),
    NBTINTARRAY("NBTTagIntArray", new Object[]{(new int[]{})}, new String[]{"data", "c"}),
    NBTLONGARRAY("NBTTagLongArray", new Object[]{(new long[]{})}, new String[]{"data", "c"});

    private Class<?> B;
    private final Object[] C;
    private final List<String[]> D = new ArrayList<>();

    NBTUtils(@NotNull String B, @NotNull Object[] C, @NotNull String[]... D) {
        this.C = C;
        this.D.addAll(Arrays.asList(D));

        try {
            this.B = Class.forName((Version.B().D() ? "net.minecraft.nbt." : "net.minecraft.server.") + B);
        }catch (ClassNotFoundException e) {
            this.B = null;
        }
    }

    public static @NotNull NBTBase<?> B(@NotNull Object NBTBase) {
        NBTUtils NBT = NBTUtils.N(NBTBase.getClass().getSimpleName());

        List<Object> Params = new ArrayList<>();

        if(NBT.D.size() > 0) {
            for(String Fieldname : NBT.D.get(0)) {
                Field Field;

                try {
                    Field = NBTBase.getClass().getDeclaredField(Fieldname);
                    Field.setAccessible(true);

                    Params.add(Field.get(NBTBase));
                }catch (NoSuchFieldException | IllegalAccessException ignored) {}
            }
        }

        if(NBT.D.size() > 1) {
            for(String Fieldname : NBT.D.get(1)) {
                Field Field;

                try {
                    Field = NBTBase.getClass().getDeclaredField(Fieldname);
                    Field.setAccessible(true);

                    Params.add(Field.get(NBTBase));
                }catch (NoSuchFieldException | IllegalAccessException ignored) {}
            }
        }

        switch(NBT.ordinal()) {
            case 0: {
                return new NBTEnd();
            }
            case 1: {
                return new NBTByte((byte) Params.get(0));
            }
            case 2: {
                return new NBTShort((short) Params.get(0));
            }
            case 3: {
                return new NBTInt((int) Params.get(0));
            }
            case 4: {
                return new NBTLong((long) Params.get(0));
            }
            case 5: {
                return new NBTFloat((float) Params.get(0));
            }
            case 6: {
                return new NBTDouble((double) Params.get(0));
            }
            case 7: {
                return new NBTByteArray((byte[]) Params.get(0));
            }
            case 8: {
                return new NBTString((String) Params.get(0));
            }
            case 9: {
                ArrayList<NBTBase<?>> Array = new ArrayList<>();

                for(Object NMS : (ArrayList<Object>) Params.get(0)) {
                    Array.add(NBTUtils.B(NMS));
                }

                return new NBTList(Array, (byte) Params.get(1));
            }
            case 10: {
                Map<String, Object> Map = new HashMap<>();

                ((Map<String, Object>) Params.get(0)).forEach((A, B) -> Map.put(A, NBTUtils.B(B)));

                return new NBTCompound(Map);
            }
            case 11: {
                return new NBTIntArray((int[]) Params.get(0));
            }
            case 12: {
                return new NBTLongArray((long[]) Params.get(0));
            }
            default: throw new IllegalArgumentException("Unable to create NBTBase");
        }
    }

    public Object O(@NotNull Object... Parameters) {
        Constructor<?> Constructor = B.getDeclaredConstructors()[this.ordinal() == 12 ? 2 : 0];
        Constructor.setAccessible(true);

        try {
            if(Version.B().D()) {
                return Constructor.newInstance(Parameters.length > 0 ? Parameters : this.C);
            }

            Object Instance = Constructor.newInstance();

            if(this.D.size() > 0) {
                for(String Fieldname : this.D.get(0)) {
                    Field Field;

                    try {
                        Field = Instance.getClass().getDeclaredField(Fieldname);
                        Field.setAccessible(true);
                    }catch (NoSuchFieldException ignored) {
                        continue;
                    }

                    Field.set(Instance, Parameters[0]);
                }
            }

            if(this.D.size() > 1) {
                for(String Fieldname : this.D.get(1)) {
                    Field Field;

                    try {
                        Field = Instance.getClass().getDeclaredField(Fieldname);
                        Field.setAccessible(true);
                    }catch (NoSuchFieldException ignored) {
                        continue;
                    }

                    Field.set(Instance, Parameters[1]);
                }
            }

            return Instance;
        }catch (Error | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalArgumentException("Could not create this NMS object");
        }
    }

    public static @NotNull NBTUtils D(byte D) {
        for(NBTUtils NBT : NBTUtils.values()) {
            if(NBT.ordinal() == D) return NBT;
        }

        throw new NullPointerException();
    }

    public static @NotNull NBTUtils N(@NotNull String N) {
        for(NBTUtils NBT : NBTUtils.values()) {
            if(NBT.B.getSimpleName().equals(N)) return NBT;
        }

        throw new NullPointerException();
    }
}