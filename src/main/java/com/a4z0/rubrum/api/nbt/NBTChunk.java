package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.enums.Version;
import org.bukkit.Chunk;
import org.jetbrains.annotations.NotNull;

/**
* NBT component of a {@link Chunk}.
*/

public class NBTChunk extends NBTCompound {

    protected final Chunk A;
    protected final NBTPersistentDataContainer B;

    /**
    * Construct a {@link NBTChunk} with the given params.
    *
    * @param Chunk {@link Chunk} to be read.
    */

    public NBTChunk(@NotNull Chunk Chunk) {

        if(!Version.B().M(Version.V1_16_R3)) {
            throw new IllegalArgumentException("Feature available from version 1.16.4+");
        }

        this.A = Chunk;
        this.B = new NBTPersistentDataContainer(Chunk.getPersistentDataContainer());

        super.setTag(this.B);
    }

    /**
    * Defines the NBT of the chunk stored in this {@link NBTChunk}.
    *
    * @param NBTCompound {@link NBTCompound} to be merged into the chunk.
    */

    @Override
    public void setTag(@NotNull NBTCompound NBTCompound) {
        this.B.setTag(NBTCompound);
    }

    /**
    * @return the given {@link Chunk}.
    */

    public @NotNull Chunk getChunk() {
        return this.A;
    }
}