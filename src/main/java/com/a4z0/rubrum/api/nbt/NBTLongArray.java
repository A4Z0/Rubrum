package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.annotations.Since;
import com.a4z0.rubrum.enums.Minecraft;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
* Base of an NBT component.
*
* Stores data with class {@link Long} Array.
*/

@Since(Version = Minecraft.V1_12_R1) public class NBTLongArray extends NBTBase<long[]> {

    /**
    * Construct a {@link NBTLongArray}.
    */

    public NBTLongArray() {
        this(new long[]{});
    }

    /**
    * Construct a {@link NBTLongArray}.
    *
    * @param LongArray long array to be stored.
    */

    public NBTLongArray(long[] LongArray) {
        super(LongArray);
    }

    /**
    * @return the ID of this {@link NBTLongArray}.
    */

    @Override
    public final byte getTypeID() {
        return 12;
    }

    /**
    * @return a clone of this {@link NBTLongArray}.
    */

    @Override
    public @NotNull NBTLongArray clone() {
        return new NBTLongArray(this.Data);
    }

    /**
    * @return this {@link NBTLongArray} as a {@link String}.
    */

    @Override
    public @NotNull String toString() {
        return this.getClass().getSimpleName() + ": " + Arrays.toString(Data);
    }
}
