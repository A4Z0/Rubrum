package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.enums.Version;
import com.a4z0.rubrum.reflection.CraftEntity;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class NBTEntity extends NBTCompound {

    private final Entity Entity;

    /**
    * Construct a {@link NBTItem} with the given params.
    *
    * @param Entity a {@link Entity}.
    */

    public NBTEntity(@NotNull Entity Entity) {
        this.Entity = Entity;

        if(this.getCompound() != null) {
            super.setCompound(NBTUtils.parseNBTCompound(this.getCompound()));
        };
    };

    /**
    * @return an NMS object from an NBT.
    */

    @Override
    public Object getCompound() {
        return CraftEntity.getNBTEntity(CraftEntity.asNMSCopy(this.Entity));
    };

    /**
    * Sets the {@link NBTCompound} and update the {@link Entity} NBT.
    *
    * @param NBTCompound a {@link NBTCompound}.
    */

    @Override
    public void setCompound(@NotNull NBTCompound NBTCompound) {
        super.setCompound(NBTCompound); CraftEntity.setNBT(CraftEntity.asNMSCopy(this.Entity), NBTUtils.parseNBT(NBTCompound));
    };

    /**
    * @return the {@link Entity}.
    */

    public @NotNull Entity getEntity() {
        return this.Entity;
    };

    @Version.A(V = Version.V1_14)
    public NBTCompound getPersistentDataContainer() {

        if(Version.U(this.getClass(), "getPersistentDataContainer")) {
            throw new IllegalArgumentException("This version doesn't support this method.");
        };

        return null;
    };
};