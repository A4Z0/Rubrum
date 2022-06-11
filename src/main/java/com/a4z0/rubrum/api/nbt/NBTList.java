package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* Base of an NBT component.
*
* Stores data with class {@link List}.
*/

public class NBTList extends NBTBase<List<NBTBase<?>>> {

    protected final byte Type;

    /**
    * Construct a {@link NBTList}.
    */

    public NBTList() {
        this((byte) 0);
    }

    /**
    * Construct a {@link NBTList}.
    *
    * @param List list to be stored.
    */

    public NBTList(List<NBTBase<?>> List) {
        this(List, (byte) 0);
    }

    /**
    * Construct a {@link NBTList}.
    *
    * @param Type type to be stored.
    */

    public NBTList(byte Type) {
        this(new ArrayList<>(), Type);
    }

    /**
    * Construct a {@link NBTList}.
    *
    * @param List list to be stored.
    * @param Type type to be stored.
    */

    public NBTList(List<NBTBase<?>> List, byte Type) {
        super(List); this.Type = Type;
    }

    @Override
    protected @NotNull Object getComponent() {
        return NBTUtils.NBTLIST.O(this.Data.stream().map(NBTBase::getComponent).collect(Collectors.toList()), this.Type);
    }

    @Override
    public final byte getTypeID() {
        return 9;
    }

    @Override
    public @NotNull NBTList clone() {
        return new NBTList(this.Data, this.Type);
    }

    /**
    * @return this {@link NBTList} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {

        StringBuilder Builder = new StringBuilder();

        this.Data.forEach(NBTBase -> {
            String[] Data = NBTBase.toString().split(NBTBase.getClass().getSimpleName() + ": ");

            Builder.append(Data[1], 0, Data[1].length()).append(", ");
        });

        return this.getClass().getSimpleName() + ": {" + (Builder.length() > 0 ? Builder.substring(0, Builder.length() -2) : "") + "}";
    }
}