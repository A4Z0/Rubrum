package com.a4z0.rubrum.api.nbt;

import org.apache.commons.lang.SerializationUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;

/**
* Base of an NBT component.
*
* Stores data with class {@link Map}.
*/

public class NBTCompound extends NBTBase<Map<String, NBTBase<?>>> implements Cloneable {

    /**
    * Construct a {@link NBTCompound}.
    */

    public NBTCompound() {
        this.Data = new HashMap<>();
    };

    /**
    * Construct a {@link NBTCompound} with the given params.
    *
    * @param Map a serialized {@link NBTCompound}.
    */

    public NBTCompound(@NotNull Map<String, Object> Map) {

        Map<String, NBTBase<?>> Data = new HashMap<>();

        Map.forEach((A, B) -> {
            Data.put(A, (NBTBase<?>) B);
        });

        this.Data = Data;
    };

    /**
    * Construct a {@link NBTCompound} with the given params.
    *
    * @param NBTCompound an {@link NBTCompound} to be cloned.
    */

    public NBTCompound(@NotNull NBTCompound NBTCompound) {
        this.Data = new HashMap<>(NBTCompound.Data);
    };

    @Override
    protected Object getComponent() {

        Map<String, Object> Map = new HashMap<>();

        this.Data.forEach((A, B) -> {
           if(B != null) Map.put(A, B.getComponent());
        });

        return NBTUtils.GET_NBTBASE_INSTANCE(this.getTypeID(), Map);
    };

    /**
    * Overwrite this {@link NBTCompound}.
    *
    * @param NBTCompound {@link NBTCompound} to overwrite this.
    */

    public void setTag(@NotNull NBTCompound NBTCompound) {
        this.Data = NBTCompound.Data;
    };

    /**
    * @return keys that this {@link NBTCompound} contains.
    */

    public @NotNull Set<String> getKeys() {
        return this.Data.keySet();
    };

    /**
    * @param Key presence of key to be tested.
    *
    * @return true if it contains the Key.
    */

    public boolean hasKey(@NotNull String Key) {
        return this.Data.containsKey(Key);
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return a {@link NBTBase}.
    */

    public NBTBase<?> get(@NotNull String Key) {
        return this.Data.get(Key);
    };

    /**
    * @param Key key associated with the value to be set.
    *
    * @param NBTBase a {@link NBTBase}.
    */

    public void set(@NotNull String Key, NBTBase<?> NBTBase) {
        this.Data.put(Key, NBTBase);
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public byte getByte(@NotNull String Key) {

        if(!this.hasKey(Key)) throw new NullPointerException();

        return (byte) this.get(Key).Data;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param Byte the value associated with the key.
    */

    public void setByte(@NotNull String Key, byte Byte) {
        this.set(Key, new NBTByte(Byte));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public boolean getBoolean(@NotNull String Key) {

        if(!this.hasKey(Key)) throw new NullPointerException();

        return ((byte) this.get(Key).Data) > 0;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param Boolean the value associated with the key.
    */

    public void setBoolean(@NotNull String Key, boolean Boolean) {
        this.set(Key, new NBTByte((byte) (Boolean ? 1 : 0)));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public short getShort(@NotNull String Key) {

        if(!this.hasKey(Key)) throw new NullPointerException();

        return (short) this.get(Key).Data;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param Short the value associated with the key.
    */

    public void setShort(@NotNull String Key, short Short) {
        this.set(Key, new NBTShort(Short));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public int getInt(@NotNull String Key) {

        if(!this.hasKey(Key)) throw new NullPointerException();

        return (int) this.get(Key).Data;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param Int the value associated with the key.
    */

    public void setInt(@NotNull String Key, int Int) {
        this.set(Key, new NBTInt(Int));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public long getLong(@NotNull String Key) {

        if(!this.hasKey(Key)) throw new NullPointerException();

        return (long) this.get(Key).Data;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param Long the value associated with the key.
    */

    public void setLong(@NotNull String Key, long Long) {
        this.set(Key, new NBTLong(Long));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public float getFloat(@NotNull String Key) {

        if(!this.hasKey(Key)) throw new NullPointerException();

        return (float) this.get(Key).Data;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param Float the value associated with the key.
    */

    public void setFloat(@NotNull String Key, float Float) {
        this.set(Key, new NBTFloat(Float));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public double getDouble(@NotNull String Key) {

        if(!this.hasKey(Key)) throw new NullPointerException();

        return (double) this.get(Key).Data;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param Double the value associated with the key.
    */

    public void setDouble(@NotNull String Key, double Double) {
        this.set(Key, new NBTDouble(Double));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public byte[] getByteArray(@NotNull String Key) {
        return this.hasKey(Key) ? (byte[]) this.get(Key).Data : null;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param ByteArray the value associated with the key.
    */

    public void setByteArray(@NotNull String Key, byte[] ByteArray) {
        this.set(Key, new NBTByteArray(ByteArray));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public String getString(@NotNull String Key) {
        return this.hasKey(Key) ? (String) this.get(Key).Data : null;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param String the value associated with the key.
    */

    public void setString(@NotNull String Key, String String) {
        this.set(Key, new NBTString(String));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public List<NBTBase<?>> getList(@NotNull String Key) {
        return this.hasKey(Key) ? ((NBTList) this.get(Key)).Data : null;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param List the value associated with the key.
    */

    public void setList(@NotNull String Key, List<NBTBase<?>> List) {
        this.set(Key, new NBTList(List));
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param List the value associated with the key.
    * @param Type the type associated with the list.
    */

    public void setList(@NotNull String Key, List<NBTBase<?>> List, byte Type) {
        this.set(Key, new NBTList(List, Type));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public NBTCompound getCompound(@NotNull String Key) {
        return this.hasKey(Key) ? (NBTCompound) this.get(Key) : null;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param NBTCompound the value associated with the key.
    */

    public void setCompound(@NotNull String Key, NBTCompound NBTCompound) {
        this.set(Key, NBTCompound != null ? new NBTCompound(NBTCompound) : null);
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public int[] getIntArray(@NotNull String Key) {
        return this.hasKey(Key) ? (int[]) this.get(Key).Data : null;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param IntArray the value associated with the key.
    */

    public void setIntArray(@NotNull String Key, int[] IntArray) {
        this.set(Key, new NBTIntArray(IntArray));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public long[] getLongArray(@NotNull String Key) {
        return this.hasKey(Key) ? (long[]) this.get(Key).Data : null;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param LongArray the value associated with the key.
    */

    public void setLongArray(@NotNull String Key, long[] LongArray) {
        this.set(Key, new NBTLongArray(LongArray));
    };

    /**
    * @param Key key associated with the value to be returned.
    *
    * @return the value associated with the key.
    */

    public Object getObject(@NotNull String Key) {
        return this.hasKey(Key) ? this.get(Key).Data : null;
    };

    /**
    * @param Key key associated with the value to be returned.
    * @param Class class associated with the value to be returned
    *
    * @return the value associated with the key.
    */

    public <T> T getObject(@NotNull String Key, @NotNull Class<T> Class) {
        return this.hasKey(Key) ? Class.cast(this.get(Key).Data) : null;
    };

    /**
    * Defines a value associated with the key in this {@link NBTCompound}.
    *
    * @param Key key associated with the value to be set.
    * @param Object the value associated with the key.
    */

    public void setObject(@NotNull String Key, @NotNull Serializable Object) {
        this.set(Key, new NBTByteArray(SerializationUtils.serialize(Object)));
    };

    /**
    * @return a {@link Map} representing this {@link NBTCompound}.
    */

    public @NotNull Map<String, Object> serialize() {
        return new HashMap<>(this.Data);
    };

    /**
    * @return a clone of this {@link NBTCompound}.
    */

    @Override
    public @NotNull NBTCompound clone() {
        return new NBTCompound(this);
    };

    @Override
    protected final byte getTypeID() {
        return 10;
    };

    /**
    * @return this {@link NBTCompound} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {

        StringBuilder Builder = new StringBuilder();

        this.Data.forEach((K, V) -> {
            String[] Data = V.toString().split(V.getClass().getSimpleName() + ": ");

            Builder.append(K).append(": ").append(Data[1], 0, Data[1].length()).append(", ");
        });

        return this.getClass().getSimpleName() + ": {" + (Builder.length() > 0 ? Builder.substring(0, Builder.length() -2) : "") + "}";
    };
};