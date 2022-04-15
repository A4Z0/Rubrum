package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NBTList extends NBTBase<List<NBTBase<?>>> {

    protected byte Type;

    public NBTList(ArrayList<NBTBase<?>> Value) {
        this(Value, (byte) 0);
    };

    public NBTList(ArrayList<NBTBase<?>> Value, byte Type) {
        this.Data = Value;
        this.Type = Type;
    };

    @Override
    public byte getTypeID() {
        return 9;
    };

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": {\"Data\": " + Data + ", \"Type\":" + Type + "}";
    };
};