package com.a4z0.rubrum.api.nbt;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
* Component container.
*/

public class Compound implements ConfigurationSerializable, Cloneable {

    private Map<String, Object> M;

    /**
    * Construct a {@link Compound}.
    */

    public Compound() {
        this.M = new HashMap<>();
    };

    /**
    * Construct a {@link Compound} with the given params.
    *
    * @param Map a {@link Map} representing a {@link Compound}.
    */

    public Compound(Map<String, Object> Map) {
        this.M = Map;
    };

    /**
    * Re-map this {@link Compound}.
    *
    * @param Map a serialized {@link Compound}.
    */

    protected void Remap(@NotNull Map<String, Object> Map) {
        this.M = Map;
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Integer}.
    */

    public int getInt(@NotNull String ID) {
        return (int) this.M.get(ID);
    };

    /**
    * Sets a {@link Integer} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Integer} Value.
    */

    public void setInt(@NotNull String ID, int Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Map}.
    */

    public Map<?, ?> getMap(@NotNull String ID) {
        return (Map<?, ?>) this.M.get(ID);
    };

    /**
    * Sets a {@link Map} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Map} Value.
    */

    public void setMap(@NotNull String ID, @NotNull Map<?, ?> Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Byte}.
    */

    public byte getByte(@NotNull String ID) {
        return (byte) this.M.get(ID);
    };

    /**
    * Sets a {@link Byte} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Byte} Value.
    */

    public void setByte(@NotNull String ID, byte Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Long}.
    */

    public long getLong(@NotNull String ID) {
        return (long) this.M.get(ID);
    };

    /**
    * Sets a {@link Long} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Long} Value.
    */

    public void setLong(@NotNull String ID, long Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link UUID}.
    */

    public UUID getUUID(@NotNull String ID) {
        return (UUID) this.M.get(ID);
    };

    /**
    * Sets a {@link UUID} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link UUID} Value.
    */

    public void setUUID(@NotNull String ID, @NotNull UUID Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Float}.
    */

    public float getFloat(@NotNull String ID) {
        return (float) this.M.get(ID);
    };

    /**
    * Sets a {@link Float} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Float} Value.
    */

    public void setFloat(@NotNull String ID, float Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Short}.
    */

    public short getShort(@NotNull String ID) {
        return (short) this.M.get(ID);
    };

    /**
    * Sets a {@link Short} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Short} Value.
    */

    public void setShort(@NotNull String ID, short Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link String}.
    */

    public String getString(@NotNull String ID) {
        return (String) this.M.get(ID);
    };

    /**
    * Sets a {@link String} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link String} Value.
    */

    public void setString(@NotNull String ID, @NotNull String Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Double}.
    */

    public double getDouble(@NotNull String ID) {
        return (double) this.M.get(ID);
    };

    /**
    * Sets a {@link Double} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Double} Value.
    */

    public void setDouble(@NotNull String ID, double Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Object}.
    */
    
    public Object getObject(@NotNull String ID) {
        return this.M.get(ID);
    };

    /**
    * Sets a {@link Object} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Object} Value.
    */

    public void setObject(@NotNull String ID, Object Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Boolean}.
    */

    public boolean getBoolean(@NotNull String ID) {
        return (boolean) this.M.get(ID);
    };

    /**
    * Sets a {@link Boolean} in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Boolean} Value.
    */

    public void setBoolean(@NotNull String ID, boolean Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Integer} Array.
    */

    public int[] getIntArray(@NotNull String ID) {
        return (int[]) this.M.get(ID);
    };

    /**
    * Sets a {@link Integer} Array in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Integer} Array Value.
    */

    public void setIntArray(@NotNull String ID, int[] Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Long} Array.
    */

    public long[] getLongArray(@NotNull String ID) {
        return (long[]) this.M.get(ID);
    };

    /**
    * Sets a {@link Long} Array in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Long} Array Value.
    */

    public void setLongArray(@NotNull String ID, long[] Value) {
        this.M.put(ID, Value);
    };

    /**
    * @param ID Value ID.
    *
    * @return a {@link Byte} Array.
    */

    public byte[] getByteArray(@NotNull String ID) {
        return (byte[]) this.M.get(ID);
    };

    /**
    * Sets a {@link Byte} Array in {@link Compound}.
    *
    * @param ID Value ID.
    * @param Value {@link Byte} Array Value.
    */

    public void setByteArray(@NotNull String ID, byte[] Value) {
        this.M.put(ID, Value);
    };

    /**
    * @return a {@link Map} representing this {@link Compound}.
    */

    @Override
    public @NotNull Map<String, Object> serialize() {
        return this.M;
    };

    /**
    * @return a clone of this {@link Compound}.
    */

    @Override
    public @NotNull Compound clone() {
        return new Compound(this.serialize());
    };

    /**
    * @return a {@link Compound} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {

        StringBuilder Structure = new StringBuilder();

        this.M.forEach((A, B) -> {
            Structure.append("\"").append(A).append("\"").append(": ").append(B.getClass().equals(String.class) ? "\"" : "").append(B).append(B.getClass().equals(String.class) ? "\"" : "").append(", ");
        });

        return this.getClass().getSimpleName() + ": {" + (Structure.length() > 0 ? Structure.substring(0, Structure.length() -2) : "") +"}";
    };
};