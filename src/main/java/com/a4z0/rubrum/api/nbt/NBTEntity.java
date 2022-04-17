package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.enums.Version;
import com.a4z0.rubrum.reflection.CraftEntity;
import com.a4z0.rubrum.reflection.CraftPersistentDataContainer;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

/**
* NBT component of an {@link Entity}.
*/

public class NBTEntity extends NBTCompound {

    private final Entity A;

    /**
    * Construct a {@link NBTItem} with the given params.
    *
    * @param Entity a {@link Entity}.
    */

    public NBTEntity(@NotNull Entity Entity) {
        this.A = Entity;

        if(this.getCompound() != null) {
            super.setCompound(NBTUtils.parseNBTCompound(this.getCompound()));
        };
    };

    /**
    * @return an NMS object from an NBT.
    */

    @Override
    public Object getCompound() {
        return CraftEntity.getNBT(CraftEntity.getNMS(this.A));
    };

    /**
    * Sets the {@link NBTEntity} and update the {@link Entity} NBT.
    *
    * @param NBTCompound a {@link NBTCompound}.
    */

    @Override
    public void setCompound(NBTCompound NBTCompound) {
        super.setCompound(NBTCompound); CraftEntity.setNBT(CraftEntity.getNMS(this.A), NBTUtils.parseNBT(NBTCompound));
    };

    /**
    * @return the {@link Entity}.
    */

    public @NotNull Entity getEntity() {
        return this.A;
    };

    /**
    * @return a {@link NBTPersistentDataContainer}.
    */

    @Version.A(V = Version.V1_14_R1)
    public NBTPersistentDataContainer getPersistentDataContainer() {

        if(!Version.U(this.getClass(), "getPersistentDataContainer")) {
            throw new IllegalArgumentException("Feature available from version 1.14+");
        };

        PersistentDataContainer Persistent = CraftPersistentDataContainer.getPersistentData(this.A);
        return Persistent != null ? new NBTPersistentDataContainer(Persistent) : null;
    };
};