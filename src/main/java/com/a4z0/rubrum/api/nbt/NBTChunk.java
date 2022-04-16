package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.enums.Version;
import com.a4z0.rubrum.reflection.CraftPersistentDataContainer;
import org.bukkit.Chunk;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

/**
* NBT component of a {@link Chunk}.
*/

public class NBTChunk {

    private final Chunk A;
    private NBTPersistentDataContainer B;

    /**
    * Construct a {@link NBTChunk} with the given params.
    *
    * @param Chunk a {@link Chunk}.
    */

    public NBTChunk(@NotNull Chunk Chunk) {

        if(!Version.B().M(Version.V1_16_R3)) {
            throw new IllegalArgumentException("Feature available from version 1.16.4+");
        };

        this.A = Chunk;
    };

    /**
    * @return the {@link Chunk}.
    */

    public @NotNull Chunk getChunk() {
        return this.A;
    };

    /**
    * @return a {@link NBTPersistentDataContainer}.
    */

    public NBTPersistentDataContainer getPersistentDataContainer() {
        PersistentDataContainer Persistent = CraftPersistentDataContainer.getPersistentData(this.A);

        if(this.B == null) {
            this.B = Persistent != null ? new NBTPersistentDataContainer(Persistent) : null;
        };

        return this.B;
    };
};