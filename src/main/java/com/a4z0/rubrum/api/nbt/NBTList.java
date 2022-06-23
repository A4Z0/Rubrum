/*
 *     Rubrum
 *     Copyright (C) 2022.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
        return NBTUtils.NBTTagList.getNBTObject(this.Data.stream().map(NBTBase::getComponent).collect(Collectors.toList()), this.Type);
    }

    /**
    * Append the given {@link NBTBase} to the end of the list.
    *
    * @param NBTBase NTBBase to be attached.
    */

    public void add(@NotNull NBTBase<?> NBTBase) {
        this.Data.add(NBTBase);
    }

    /**
    * Adds the given {@link NBTBase} to the indicated index.
    *
    * @param Index Index that the {@link NBTBase} will be attached to.
    * @param NBTBase NTBBase to be attached.
    */

    public void set(int Index, @NotNull NBTBase<?> NBTBase) {
        this.Data.set(Index, NBTBase);
    }

    /**
    * @param Index {@link NBTBase} index to return.
    *
    * @return an {@link NBTBase} based on the given index.
    */

    public NBTBase<?> get(int Index) {

        if(this.Data.size() < Index)
            throw new ArrayIndexOutOfBoundsException("NBTList out of bounds [" + Index + "]");

        return this.Data.get(Index);
    }

    /**
    * @return the ID of this {@link NBTList}.
    */

    @Override
    public final byte getTypeID() {
        return 9;
    }

    /**
    * @return a clone of this {@link NBTList}.
    */

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