package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.reflection.NBTUtils;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
* NBT Component.
*/

public class NBTCompound extends NBTBase<Map<String, NBTBase<?>>> implements Cloneable {

    /**
    * Construct a {@link NBTCompound}.
    */

    public NBTCompound() {
        this.Data = new HashMap<>();
    };

    /**
    * Construct a {@link NBTCompound}.
    *
    * @param NBTCompound a {@link NBTCompound}.
    */

    public NBTCompound(@NotNull NBTCompound NBTCompound) {
        this.Data = NBTCompound.Data;
    };

    /**
    * Construct a {@link NBTCompound} with the given params.
    *
    * @param Map a serialized {@link NBTCompound}.
    */

    public NBTCompound(@NotNull Map<String, NBTBase<?>> Map) {
        this.Data = Map;
    };

    /**
    * @return a NBT object.
    */

    public Object getCompound() {
        return null;
    };

    /**
    * Sets the {@link NBTCompound}.
    *
    * @param NBTCompound a {@link NBTCompound}.
    */

    public void setCompound(@NotNull NBTCompound NBTCompound) {
        this.Data = NBTCompound.Data;
    };

    /**
    * @return keys of this {@link NBTCompound}.
    */

    public Set<String> getKeys() {
        return this.Data.keySet();
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link NBTBase}.
    */

    public NBTBase<?> get(@NotNull String ID) {
        return this.Data.get(ID);
    };

    /**
    * Sets a {@link NBTBase} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link NBTBase} Value.
    */

    public NBTBase<?> set(@NotNull String ID, NBTBase<?> Value) {
        return this.Data.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Integer}.
    */

    public int getInt(@NotNull String ID) {
        return (int) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link Integer} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Integer} Value.
    */

    public void setInt(@NotNull String ID, int Value) {
        this.Data.put(ID, new NBTInt(Value));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link ArrayList} of {@link NBTBase}.
    */

    public ArrayList<NBTBase<?>> getList(@NotNull String ID) {
        return (this.Data.get(ID) != null) ? (ArrayList<NBTBase<?>>) this.Data.get(ID).Data : null;
    };

    /**
    * Sets a {@link ArrayList} of {@link NBTBase} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link ArrayList} of {@link NBTBase} as a value.
    */

    public void setList(@NotNull String ID, ArrayList<NBTBase<?>> Value) {
        this.Data.put(ID, new NBTList(Value));
    };

    /**
    * Sets a {@link ArrayList} of {@link NBTBase} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Type a {@link NBTList} type.
    * @param Value {@link ArrayList} of {@link NBTBase} as a value.
    */

    public void setList(@NotNull String ID, byte Type, ArrayList<NBTBase<?>> Value) {
        this.Data.put(ID, new NBTList(Value, Type));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Byte}.
    */

    public byte getByte(@NotNull String ID) {
        return (byte) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link Byte} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Byte} Value.
    */

    public void setByte(@NotNull String ID, byte Value) {
        this.Data.put(ID, new NBTByte(Value));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Long}.
    */

    public long getLong(@NotNull String ID) {
        return (long) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link Long} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Long} Value.
    */

    public void setLong(@NotNull String ID, long Value) {
        this.Data.put(ID, new NBTLong(Value));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link UUID}.
    */

    public UUID getUUID(@NotNull String ID) {
        return (UUID) NBTUtils.deserialize((byte[]) this.Data.get(ID).Data);
    };

    /**
    * Sets a {@link UUID} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link UUID} Value.
    */

    public void setUUID(@NotNull String ID, @NotNull UUID Value) {
        this.Data.put(ID, new NBTByteArray(NBTUtils.serialize(Value)));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Float}.
    */

    public float getFloat(@NotNull String ID) {
        return (float) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link Float} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Float} Value.
    */

    public void setFloat(@NotNull String ID, float Value) {
        this.Data.put(ID, new NBTFloat(Value));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Short}.
    */

    public short getShort(@NotNull String ID) {
        return (short) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link Short} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Short} Value.
    */

    public void setShort(@NotNull String ID, short Value) {
        this.Data.put(ID, new NBTShort(Value));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link String}.
    */

    public String getString(@NotNull String ID) {
        return (String) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link String} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link String} Value.
    */

    public void setString(@NotNull String ID, @NotNull String Value) {
        this.Data.put(ID, new NBTString(Value));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Double}.
    */

    public double getDouble(@NotNull String ID) {
        return (double) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link Double} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Double} Value.
    */

    public void setDouble(@NotNull String ID, double Value) {
        this.Data.put(ID, new NBTDouble(Value));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Object}.
    */

    public Object getObject(@NotNull String ID) {
        return NBTUtils.deserialize((byte[]) this.Data.get(ID).Data);
    };

    /**
    * Sets a {@link Object} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Object} Value.
    */

    public void setObject(@NotNull String ID, Object Value) {
        this.Data.put(ID, new NBTByteArray(NBTUtils.serialize(Value)));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Boolean}.
    */

    public boolean getBoolean(@NotNull String ID) {
        return (boolean) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link Boolean} in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Boolean} Value.
    */

    public void setBoolean(@NotNull String ID, boolean Value) {
        this.Data.put(ID, new NBTByte((byte) (Value ? 1 : 0)));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Integer} Array.
    */

    public int[] getIntArray(@NotNull String ID) {
        return (int[]) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link Integer} Array in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Integer} Array Value.
    */

    public void setIntArray(@NotNull String ID, int[] Value) {
        this.Data.put(ID, new NBTIntArray(Value));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Long} Array.
    */

    public long[] getLongArray(@NotNull String ID) {
        return (long[]) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link Long} Array in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Long} Array Value.
    */

    public void setLongArray(@NotNull String ID, long[] Value) {
        this.Data.put(ID, new NBTLongArray(Value));
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Byte} Array.
    */

    public byte[] getByteArray(@NotNull String ID) {
        return (byte[]) this.Data.get(ID).Data;
    };

    /**
    * Sets a {@link Byte} Array in {@link NBTCompound}.
    *
    * @param ID Value ID.
    * @param Value {@link Byte} Array Value.
    */

    public void setByteArray(@NotNull String ID, byte[] Value) {
        this.Data.put(ID, new NBTByteArray(Value));
    };

    @Override
    public byte getTypeID() {
        return 10;
    };

    /**
    * @return a {@link Map} representing this {@link NBTCompound}.
    */

    public @NotNull Map<String, NBTBase<?>> serialize() {
        return new HashMap<>(this.Data);
    };

    /**
    * @return a clone of this {@link NBTCompound}.
    */

    @Override
    public @NotNull NBTCompound clone() {
        return new NBTCompound(this);
    };

    /**
    * @return a {@link NBTCompound} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {

        StringBuilder Structure = new StringBuilder();

        this.Data.forEach((A, B) -> {
            Structure.append("\"").append(A).append("\"").append(": ").append(B.Data).append(", ");
        });

        return this.getClass().getSimpleName() + ": {" + (Structure.length() > 0 ? Structure.substring(0, Structure.length() -2) : "") +"}";
    };
};