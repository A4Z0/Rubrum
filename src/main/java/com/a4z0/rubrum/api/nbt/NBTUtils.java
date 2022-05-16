package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.api.version.Version;
import org.apache.commons.lang.SerializationUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;

public class NBTUtils {

    public static final Class<?> A;

    static {
        try {
            A = Class.forName(Version.B().D() ? "net.minecraft.nbt.NBTBase" : "net.minecraft.server." + Version.BUKKIT_VERSION + ".NBTBase");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Could not find NBTBase class");
        }
    }

    /**
    * @param ID type ID of an NBTBase
    *
    * @return the class name of an NBTBase based on the given type ID.
    */

    protected static @NotNull String GET_NBTBASE_CLASS_NAME(byte ID) {
        switch (ID) {
            case 0: {
                return "NBTTagEnd";
            }
            case 1: {
                return "NBTTagByte";
            }
            case 2: {
                return "NBTTagShort";
            }
            case 3: {
                return "NBTTagInt";
            }
            case 4: {
                return "NBTTagLong";
            }
            case 5: {
                return "NBTTagFloat";
            }
            case 6: {
                return "NBTTagDouble";
            }
            case 7: {
                return "NBTTagByteArray";
            }
            case 8: {
                return "NBTTagString";
            }
            case 9: {
                return "NBTTagList";
            }
            case 10: {
                return "NBTTagCompound";
            }
            case 11: {
                return "NBTTagIntArray";
            }
            case 12: {
                return "NBTTagLongArray";
            }
            default:
                throw new IllegalArgumentException("Could not find a NBTBase class name");
        }
    }

    /**
    * @param ID type ID of an NBTBase.
    * @param Values values to be stored in NBTBase.
    *
    * @return an instance of an NBTBase based on the given type ID.
    */

    public static @NotNull Object GET_NBTBASE_INSTANCE(byte ID, Object... Values) {
        try {
            if(!NBTUtils.A.isInterface()) {
                Method Method = NBTUtils.A.getDeclaredMethod("createTag", byte.class);
                Method.setAccessible(true);

                Object Object = Method.invoke(NBTUtils.A, ID);

                if(Object == null) {
                    Object = Method.invoke(NBTUtils.A, (byte) 7);
                    Values[0] = SerializationUtils.serialize((Serializable) Values[0]);
                }

                Field[] Fields = GET_NBTBASE_FIELDS(Object);

                if(Fields[0] != null && Values.length > 0) {
                    Fields[0].set(Object, Values[0]);
                }

                if(Fields[1] != null && Values.length > 1) {
                    Fields[1].set(Object, Values[1]);
                }

                return Object;
            }

            Class<?> NBTClass = Class.forName("net.minecraft.nbt." + GET_NBTBASE_CLASS_NAME(ID));
            Constructor<?> Constructor = NBTClass.getDeclaredConstructors()[0];
            Constructor.setAccessible(true);

            switch (ID) {
                case 0: {
                    return Constructor.newInstance();
                }
                case 1: {
                    return Constructor.newInstance(Values.length > 0 ? Values[0] : (byte) 0);
                }
                case 2: {
                    return Constructor.newInstance(Values.length > 0 ? Values[0] : (short) 0);
                }
                case 3: {
                    return Constructor.newInstance(Values.length > 0 ? Values[0] : 0);
                }
                case 4: {
                    return Constructor.newInstance(Values.length > 0 ? Values[0] : 0L);
                }
                case 5: {
                    return Constructor.newInstance(Values.length > 0 ? Values[0] : 0f);
                }
                case 6: {
                    return Constructor.newInstance(Values.length > 0 ? Values[0] : 0d);
                }
                case 7: {
                    return Constructor.newInstance((Object) (Values.length > 0 ? (byte[]) Values[0] : new byte[]{}));
                }
                case 8: {
                    return Constructor.newInstance(Values.length > 0 ? Values[0] : "");
                }
                case 9: {
                    return Constructor.newInstance((Values.length > 0 ? Values[0] : new ArrayList<>()), (Values.length > 1 ? Values[1] : (byte) 0));
                }
                case 10: {
                    return Constructor.newInstance(Values.length > 0 ? Values[0] : new HashMap<>());
                }
                case 11: {
                    return Constructor.newInstance((Object) (Values.length > 0 ? (int[]) Values[0] : new int[]{}));
                }
                case 12: {
                    return Constructor.newInstance((Object) (Values.length > 0 ? (long[]) Values[0] : new long[]{}));
                }
                default: {
                    throw new IllegalArgumentException("Could not create instance of an NMS NBTBase");
                }
            }

        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException | InstantiationException e) {
            throw new IllegalArgumentException("Something went wrong when creating an instance of an NMS NBTBase");
        }
    }

    /**
    * @param NBTBase NMS object of an NBTBase.
    *
    * @return the NMS object of an NBTBase converted to {@link NBTBase}.
    */

    protected static @NotNull NBTBase<?> GET_NBTBASE(@NotNull Object NBTBase) {
        Object[] Values = new Object[3];

        try {
            Values[0] = NBTBase.getClass().getMethod(Version.B().D() ? "a" : "getTypeId").invoke(NBTBase);

            Field[] Fields = GET_NBTBASE_FIELDS(NBTBase);

            if(Fields[0] != null) {
                Values[1] = Fields[0].get(NBTBase);

                if(((byte) Values[0]) == 7) {
                    Object Deserialized = SerializationUtils.deserialize((byte[]) Values[1]);

                    if(Deserialized instanceof long[]) {
                        Values[0] = (byte) 12;
                        Values[1] = Deserialized;
                    }
                }
            }

            if(Fields[1] != null) {
                Values[2] = Fields[1].get(NBTBase);
            }

        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Error reading values from an NMS NBTBase");
        }

        switch((byte) Values[0]) {
            case 0: {
                return new NBTEnd();
            }
            case 1: {
                return new NBTByte((byte) Values[1]);
            }
            case 2: {
                return new NBTShort((short) Values[1]);
            }
            case 3: {
                return new NBTInt((int) Values[1]);
            }
            case 4: {
                return new NBTLong((long) Values[1]);
            }
            case 5: {
                return new NBTFloat((float) Values[1]);
            }
            case 6: {
                return new NBTDouble((double) Values[1]);
            }
            case 7: {
                return new NBTByteArray((byte[]) Values[1]);
            }
            case 8: {
                return new NBTString((String) Values[1]);
            }
            case 9: {
                ArrayList<NBTBase<?>> Array = new ArrayList<>();

                for(Object NBT : (ArrayList<Object>) Values[1]) {
                    Array.add(GET_NBTBASE(NBT));
                }

                return new NBTList(Array, (byte) Values[2]);
            }
            case 10: {
                Map<String, Object> Map = new HashMap<>();

                ((Map<String, Object>) Values[1]).forEach((A, B) -> Map.put(A, GET_NBTBASE(B)));

                return new NBTCompound(Map);
            }
            case 11: {
                return new NBTIntArray((int[]) Values[1]);
            }
            case 12: {
                return new NBTLongArray((long[]) Values[1]);
            }
            default: throw new IllegalArgumentException("Unable to create NBTBase");
        }
    }

    /**
    * @param NBTBase NMS object of an NBTBase.
    *
    * @return the fields of the NMS object of a given NBTBase.
    */

    protected static @NotNull Field[] GET_NBTBASE_FIELDS(@NotNull Object NBTBase) {

        byte ID;

        try {
            ID = ((byte) NBTBase.getClass().getMethod(Version.B().D() ? "a" : "getTypeId").invoke(NBTBase));
        } catch (Error | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new IllegalArgumentException("Error getting type ID from an NMS NBTBase");
        }

        final Field[] Fields = new Field[2];
        final List<String[]> Names = GET_NBTBASE_FIELDS_NAME(ID);

        if(Names.size() > 0) {
            for(String Fieldname : Names.get(0)) {
                try {
                    Field Field = NBTBase.getClass().getDeclaredField(Fieldname);
                    Field.setAccessible(true);

                    Fields[0] = Field;
                } catch (NoSuchFieldException ignored) {
                    continue;
                }

                break;
            }
        }

        if(Names.size() > 1) {
            for (String Fieldname : Names.get(1)) {
                try {
                    Field Field = NBTBase.getClass().getDeclaredField(Fieldname);
                    Field.setAccessible(true);

                    Fields[1] = Field;
                } catch (NoSuchFieldException ignored) {
                    continue;
                }

                break;
            }
        }

        return Fields;
    }

    /**
    * @param ID type ID of an NBTBase
    *
    * @return the field name of an NBTBase based on the given type ID.
    */

    protected static @NotNull List<String[]> GET_NBTBASE_FIELDS_NAME(byte ID) {

        List<String[]> List = new ArrayList<>();

        switch (ID) {
            case 1: {
                List.add(new String[]{"data", "x"});
            }
            case 2: case 3: case 4: case 7: case 11: case 12: {
                List.add(new String[]{"data", "c"});
            }
            case 5: case 6: {
                List.add(new String[]{"data", "w"});
            }
            case 8: {
                List.add(new String[]{"data", "A"});
            }
            case 9: {
                List.add(new String[]{"list", "c"});
                List.add(new String[]{"type", "w"});
            }
            case 10: {
                List.add(new String[]{"map", "x"});
            }
        }

        if(List.size() < 2) List.add(new String[0]);

        return List;
    }
}