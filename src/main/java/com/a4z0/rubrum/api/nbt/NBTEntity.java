package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.enums.Minecraft;
import com.a4z0.rubrum.reflection.CraftEntity;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
* NBT component of an {@link Entity}.
*/

public class NBTEntity extends NBTCompound {

    private final Entity A;
    private NBTPersistentDataContainer B;

    /**
    * Construct a {@link NBTEntity} with the given params.
    *
    * @param Entity {@link Entity} to be read.
    */

    public NBTEntity(@NotNull Entity Entity) {
        this.A = Entity;

        super.setTag((NBTCompound) NBTUtils.B(CraftEntity.getNBT(CraftEntity.getNMS(this.A))));
    }

    /**
    * Defines the NBT of the entity stored in this {@link NBTEntity}.
    *
    * @param NBTCompound {@link NBTCompound} to be merged into the entity.
    */

    @Override
    public void setTag(@NotNull NBTCompound NBTCompound) {
        CraftEntity.setNBT(CraftEntity.getNMS(this.A), NBTCompound.getComponent());
    }

    /**
    * @return the given {@link Entity}.
    */

    public @NotNull Entity getEntity() {
        return this.A;
    }

    /**
    * @return an entity {@link NBTPersistentDataContainer}.
    */

    public NBTPersistentDataContainer getPersistentDataContainer() {

        if(!Minecraft.V1_14_R1.isEqualOrOlder(Minecraft.getCurrentVersion()))
            throw new IllegalArgumentException("Feature available from version 1.14+");

        if(this.B == null)
            this.B = new NBTPersistentDataContainer(this.A.getPersistentDataContainer());

        return this.B;
    }
}