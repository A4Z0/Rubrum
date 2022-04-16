package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.reflection.CraftPersistentDataContainer;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

public class NBTPersistentDataContainer extends NBTCompound {

    private final PersistentDataContainer A;

    /**
    * Construct a {@link NBTPersistentDataContainer} with the given params.
    *
    * @param Container a {@link PersistentDataContainer}.
    */

    public NBTPersistentDataContainer(@NotNull PersistentDataContainer Container) {
        this.A = Container;

        if(this.getCompound() != null) {
            super.setCompound(NBTUtils.parseNBTCompound(this.getCompound()));
        };
    };

    /**
    * @return an NMS object from an NBT.
    */

    @Override
    public Object getCompound() {
        return CraftPersistentDataContainer.getNBTContainer(this.A);
    };

    /**
    * Sets the {@link NBTCompound} and update the {@link PersistentDataContainer} NBT.
    *
    * @param NBTCompound a {@link NBTCompound}.
    */

    @Override
    public void setCompound(@NotNull NBTCompound NBTCompound) {
        super.setCompound(NBTCompound);
        CraftPersistentDataContainer.setPersistentData(this.A, this);
    };
};