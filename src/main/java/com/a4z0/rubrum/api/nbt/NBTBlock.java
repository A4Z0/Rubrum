package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.enums.Version;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
* NBT component of a {@link Block}.
*/

public class NBTBlock extends NBTCompound {

    private final Block A;
    private final NBTChunk B;

    /**
    * Construct a {@link NBTBlock} with the given params.
    *
    * @param Block a {@link Block}.
    */

    public NBTBlock(@NotNull Block Block) {

        if(!Version.B().M(Version.V1_16_R3)) {
            throw new IllegalArgumentException("Feature available from version 1.16.4+");
        };

        this.A = Block;
        this.B = new NBTChunk(Block.getChunk());

        if(this.getCompound() != null) {
            super.setCompound(NBTUtils.parseNBTCompound(this.getCompound()));
        };
    };

    /**
    * @return an NMS object from an NBT.
    */

    @Override
    public Object getCompound() {

        NBTPersistentDataContainer C = this.B.getPersistentDataContainer();

        NBTCompound D = (NBTCompound) C.get("blocks");

        if(D == null) return null;

        if(D.get(this.A.getX() + "_" + this.A.getY() + "_" + this.A.getZ()) == null) return null;

        return NBTUtils.parseNBT((NBTCompound) D.get(this.A.getX() + "_" + this.A.getY() + "_" + this.A.getZ()));
    };

    /**
    * Sets the {@link NBTBlock} and update the {@link Block} NBT.
    *
    * @param NBTCompound a {@link NBTCompound}.
    */

    @Override
    public void setCompound(NBTCompound NBTCompound) {
        super.setCompound(NBTCompound);

        NBTPersistentDataContainer C = this.B.getPersistentDataContainer();

        if(C.get("blocks") == null) {
            C.set("blocks", new NBTCompound());
        };

        NBTCompound D = (NBTCompound) C.get("blocks");

        if(D.get(this.A.getX() + "_" + this.A.getY() + "_" + this.A.getZ()) == null) {
            D.set(this.A.getX() + "_" + this.A.getY() + "_" + this.A.getZ(), NBTUtils.parseNBTCompound(NBTUtils.parseNBT(NBTCompound)));
        };

        C.setCompound(C);
    };

    /**
    * @return the {@link Block}.
    */

    public @NotNull Block getBlock() {
        return this.A;
    };
};