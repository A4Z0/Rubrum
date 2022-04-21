package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* Base of an NBT component.
*
* Stores data with class {@link List}.
*/

public class NBTList extends NBTBase<List<NBTBase<?>>> {

    protected byte Type;

    /**
    * Construct a {@link NBTList}.
    */

    public NBTList() {
        this((byte) 0);
    };

    /**
    * Construct a {@link NBTList} with the given params.
    *
    * @param List list to be stored.
    */

    public NBTList(List<NBTBase<?>> List) {
        this(List, (byte) 0);
    };

    /**
    * Construct a {@link NBTList} with the given params.
    *
    * @param Type type to be stored.
    */

    public NBTList(byte Type) {
        this(new ArrayList<>(), Type);
    };

    /**
    * Construct a {@link NBTList} with the given params.
    *
    * @param List list to be stored.
    * @param Type type to be stored.
    */

    public NBTList(List<NBTBase<?>> List, byte Type) {
        this.Data = List;
        this.Type = Type;
    };

    @Override
    protected Object getComponent() {
        return NBTUtils.GET_NBTBASE_INSTANCE(this.getTypeID(), (this.Data.stream().filter(Objects::nonNull).map(NBTBase::getComponent).collect(Collectors.toList())), this.Type);
    };

    @Override
    protected final byte getTypeID() {
        return 9;
    };

    /**
    * @return this {@link NBTList} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {

        StringBuilder Builder = new StringBuilder();

        this.Data.forEach(V -> {
            String[] Data = V.toString().split(V.getClass().getSimpleName() + ": ");

            Builder.append(Data[1], 0, Data[1].length()).append(", ");
        });

        return this.getClass().getSimpleName() + ": {" + (Builder.length() > 0 ? Builder.substring(0, Builder.length() -2) : "") + "}";
    };
};