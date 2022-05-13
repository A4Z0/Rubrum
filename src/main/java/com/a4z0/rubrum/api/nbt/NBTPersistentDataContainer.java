package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.reflection.CraftPersistentDataContainer;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

/**
* NBT component of an {@link PersistentDataContainer}.
*/

public class NBTPersistentDataContainer extends NBTCompound {

    protected final PersistentDataContainer A;

    /**
    * Construct a {@link NBTPersistentDataContainer} with the given params.
    *
    * @param PersistentDataContainer {@link PersistentDataContainer} to be read.
    */

    protected NBTPersistentDataContainer(@NotNull PersistentDataContainer PersistentDataContainer) {
        this.A = PersistentDataContainer;

        super.setTag((NBTCompound) NBTUtils.GET_NBTBASE(CraftPersistentDataContainer.getNBT(this.A)));
    };

    /**
    * Defines the NBT of the PersistentDataContainer stored in this {@link NBTPersistentDataContainer}.
    *
    * @param NBTCompound {@link NBTCompound} to be merged into the PersistentDataContainer.
    */

    @Override
    public void setTag(@NotNull NBTCompound NBTCompound) {
        CraftPersistentDataContainer.setNBT(this.A, this.getComponent());
    };
};