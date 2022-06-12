package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.annotations.Available;
import com.a4z0.rubrum.annotations.Camp;
import com.a4z0.rubrum.enums.Minecraft;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public enum NBTUtils {
    NBTTagEnd(NBTEnd.class),

    @Camp(Data = {"data", "x"}, Version = Minecraft.V1_16_R1)
    NBTTagByte(NBTByte.class, byte.class),

    @Camp(Data = {"data", "c"}, Version = Minecraft.V1_16_R1)
    NBTTagShort(NBTShort.class, short.class),

    @Camp(Data = {"data", "c"}, Version = Minecraft.V1_16_R1)
    NBTTagInt(NBTInt.class, int.class),

    @Camp(Data = {"data", "c"}, Version = Minecraft.V1_16_R1)
    NBTTagLong(NBTLong.class, long.class),

    @Camp(Data = {"data", "w"}, Version = Minecraft.V1_16_R1)
    NBTTagFloat(NBTFloat.class, float.class),

    @Camp(Data = {"data", "w"}, Version = Minecraft.V1_16_R1)
    NBTTagDouble(NBTDouble.class, double.class),

    @Camp(Data = {"data", "c"}, Version = Minecraft.V1_16_R1)
    NBTTagByteArray(NBTByteArray.class, byte[].class),

    @Camp(Data = {"data", "A"}, Version = Minecraft.V1_16_R1)
    NBTTagString(NBTString.class, String.class),

    @Camp(Data = {"list", "c"}, Type = {"type", "w"}, Version = Minecraft.V1_16_R1)
    NBTTagList(NBTList.class),

    @Camp(Data = {"map", "x"}, Version = Minecraft.V1_16_R1)
    NBTTagCompound(NBTCompound.class),

    @Camp(Data = {"data", "c"}, Version = Minecraft.V1_16_R1)
    NBTTagIntArray(NBTIntArray.class, int[].class),

    @Camp(Data = {"b", "c"}, Version = Minecraft.V1_16_R1)
    NBTTagLongArray(NBTLongArray.class, long[].class);

    private final Class<? extends NBTBase<?>> NBTClass;
    private final Class<?>[] Parameters;

    /**
    * Construct a {@link NBTUtils}.
    *
    * @param Class a {@link NBTBase} class.
    */

    NBTUtils(@NotNull Class<? extends NBTBase<?>> Class, @NotNull Class<?>... Parameters) {
        this.NBTClass = Class;
        this.Parameters = Parameters;
    }

    /**
    * @return the NMS class of this NBT.
    */

    private @NotNull Class<?> getNMSClass() {
        try {
            return Class.forName((Minecraft.getCurrentVersion().isDrasticallyChanged() ? "net.minecraft.nbt." : "net.minecraft.server." + Minecraft.PACKAGE_VERSION + ".") + this.name());
        }catch (ClassNotFoundException e) {
            throw new NullPointerException("Could not find this class");
        }
    }

    /**
    * @param NBTObject a NMS NBT Object.
    *
    * @return the fields of this NBTObject.
    */

    private @NotNull Field[] getFields(@NotNull Object NBTObject) {
        List<Field> Fields = new ArrayList<>();

        Camp Annotation;

        try {
            Annotation = this.getClass().getDeclaredField(this.name()).getAnnotation(Camp.class);
        }catch (NoSuchFieldException e) {
            throw new NullPointerException("Could not find this Field");
        }

        if(Annotation != null) {
            try {
                if(Annotation.Data().length > 1) {
                    Fields.add(NBTObject.getClass().getDeclaredField(Annotation.Version().isEqualOrOlder(Minecraft.getCurrentVersion()) ? Annotation.Data()[1] : Annotation.Data()[0]));
                }

                if(Annotation.Type().length > 1) {
                    Fields.add(NBTObject.getClass().getDeclaredField(Annotation.Version().isEqualOrOlder(Minecraft.getCurrentVersion()) ? Annotation.Type()[1] : Annotation.Type()[0]));
                }
            }catch (NoSuchFieldException ignored) {}
        }

        Fields.forEach(Field -> Field.setAccessible(true));

        return Fields.toArray(new Field[0]);
    }

    /**
    * @param Args Arguments needed to create the Object.
    *
    * @return a NMS NBT Object.
    */

    public @NotNull Object getNBTObject(@NotNull Object... Args) {
        Constructor<?> NBTConstructor;

        try {
            if (this.isAvailable()) {
                NBTConstructor = this.getNMSClass().getDeclaredConstructor(this.Parameters);
            } else {
                NBTConstructor = NBTTagByteArray.getNMSClass().getDeclaredConstructor(NBTTagByteArray.Parameters);
            }
        }catch (NoSuchMethodException e) {
            throw new NullPointerException("Could not find the desired constructor");
        }

        if(!this.isAvailable() && Args[0].getClass().equals(long[].class)) {
            Args[0] = SerializationUtils.serialize((long[]) Args[0]);
        }

        NBTConstructor.setAccessible(true);

        try {
            Object NBTObject;

            if(NBTConstructor.getParameterCount() > 0) {
                NBTObject = NBTConstructor.newInstance(Args);
            }else{
                NBTObject = NBTConstructor.newInstance();
            }

            Field[] Fields = this.getFields(NBTObject);

            if(Fields.length > 0) {
                Fields[0].set(NBTObject, Args[0]);
            }

            if(Fields.length > 1) {
                Fields[1].set(NBTObject, Args[1]);
            }

            return NBTObject;
        }catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Could not create desired class.");
        }
    }

    public static @NotNull NBTBase<?> getNBTBase(@NotNull Object NBTObject) {
        NBTUtils NBTTag = NBTUtils.valueOf(NBTObject.getClass().getSimpleName());

        Object[] Values = new Object[2];

        Field[] Fields = NBTTag.getFields(NBTObject);

        try {
            if(Fields.length > 0) {
                Values[0] = Fields[0].get(NBTObject);
            }

            if(Fields.length > 1){
                Values[1] = Fields[1].get(NBTObject);
            }
        }catch (IllegalAccessException e) {
            throw new NullPointerException("Unable to access these fields");
        }

        if(NBTTag.equals(NBTTagByteArray)) {
            Object Object;

            try {
                Object = SerializationUtils.deserialize((byte[]) Values[0]);

                if(Object.getClass().equals(long[].class)) {
                    Values[0] = Object;
                    NBTTag = NBTTagLongArray;
                }

            }catch (NullPointerException | SerializationException ignored) {}
        }else if(NBTTag.equals(NBTTagList)) {
            List<NBTBase<?>> List = new ArrayList<>();

            ((List<Object>) Values[0]).forEach(Object -> List.add(NBTUtils.getNBTBase(Object)));

            Values[0] = List;
        }else if(NBTTag.equals(NBTTagCompound)) {
            Map<String, NBTBase<?>> Map = new HashMap<>();

            ((Map<String, Object>) Values[0]).forEach((Key, Object) -> Map.put(Key, NBTUtils.getNBTBase(Object)));

            Values[0] = Map;
        }

        try {
            if(NBTTag.ordinal() > 0 && NBTTag.ordinal() != 9) {
                return (NBTBase<?>) NBTTag.NBTClass.getConstructors()[1].newInstance(Values[0]);
            } else if (NBTTag.ordinal() == 9) {
                return (NBTBase<?>) NBTTag.NBTClass.getConstructors()[0].newInstance(Values[0], Values[1]);
            } else {
                return (NBTBase<?>) NBTTag.NBTClass.getConstructors()[0].newInstance();
            }
        }catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new NullPointerException("Could not create an instance of this object");
        }
    }

    /**
    * @param ID a {@link NBTBase} ID.
    *
    * @return a NBTTag.
    */

    public static @NotNull NBTUtils getNBTTag(byte ID) {
        for(NBTUtils NBTTag : NBTUtils.values()) {
            if(NBTTag.ordinal() == ID) return NBTTag;
        }

        throw new NullPointerException("This shouldn't be null");
    }

    /**
    * @return true if this NBT is available in the current version.
    */

    private boolean isAvailable() {
        if(this.NBTClass.isAnnotationPresent(Available.class)) {
            return this.NBTClass.getAnnotation(Available.class).Version().isEqualOrOlder(Minecraft.getCurrentVersion());
        }

        return true;
    }
}