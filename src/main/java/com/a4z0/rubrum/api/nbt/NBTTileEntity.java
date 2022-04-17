package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.enums.Version;
import com.a4z0.rubrum.reflection.CraftTileEntity;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.block.BlockState;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

/**
* NBT component of a TileEntity.
*/

public class NBTTileEntity extends NBTCompound {

    private final BlockState A;

    /**
    * Construct a {@link NBTTileEntity} with the givem params.
    *
    * @param Tile a {@link BlockState}.
    */

    public NBTTileEntity(@NotNull BlockState Tile) {
        this.A = Tile;

        if(this.getCompound() != null) {
            super.setCompound(NBTUtils.parseNBTCompound(this.getCompound()));
        };
    };

    /**
    * @return the {@link BlockState} Tile.
    */

    public @NotNull BlockState getTileEntity() {
        return this.A;
    };

    /**
    * @return an NMS object from an NBT.
    */

    @Override
    public Object getCompound() {
        return CraftTileEntity.getNBT(CraftTileEntity.getNMS(this.A));
    };

    /**
    * Sets the {@link NBTTileEntity} and update the TileEntity NBT.
    *
    * @param NBTCompound a {@link NBTCompound}.
    */

    @Override
    public void setCompound(NBTCompound NBTCompound) {
        super.setCompound(NBTCompound);
        CraftTileEntity.setNBT(CraftTileEntity.getNMS(this.A), NBTUtils.parseNBT(this));
    };

    /**
    * @return a {@link NBTPersistentDataContainer}.
    */

    @Version.A(V = Version.V1_14_R1)
    public NBTPersistentDataContainer getPersistentDataContainer() {

        if(!Version.U(this.getClass(), "getPersistentDataContainer")) {
            throw new IllegalArgumentException("Feature available from version 1.14+");
        };

        try {
            Object T = CraftTileEntity.getNMS(this.A);
            Field D = T.getClass().getField("persistentDataContainer");

            PersistentDataContainer Persistent = (PersistentDataContainer) D.get(T);

            return Persistent != null ? new NBTPersistentDataContainer(Persistent) : null;
        }catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    };
};