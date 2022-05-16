package com.a4z0.rubrum.api.nbt;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
* NBT Component of a {@link Block}.
*/

public class NBTBlock extends NBTCompound {

    protected final Block A;
    protected final NBTChunk B;

    /**
    * Construct a {@link NBTBlock} with the given params.
    *
    * @param Block {@link Block} to be read.
    */

    public NBTBlock(@NotNull Block Block) {

        this.A = Block;
        this.B = new NBTChunk(Block.getChunk());

        if(!this.B.hasKey("blocks")) return;

        if(!this.B.getCompound("blocks").hasKey(this.A.getX() + "_" + this.A.getY() + "_" + this.A.getZ())) return;

        super.setTag(this.B.getCompound("blocks").getCompound(this.A.getX() + "_" + this.A.getY() + "_" + this.A.getZ()));
    }

    /**
    * Defines the NBT of the block stored in this {@link NBTBlock}.
    *
    * @param NBTCompound {@link NBTCompound} to be merged into the block.
    */

    @Override
    public void setTag(@NotNull NBTCompound NBTCompound) {
        if(!this.B.hasKey("blocks")) this.B.setCompound("blocks", new NBTCompound());

        this.B.getCompound("blocks").setCompound(this.A.getX() + "_" + this.A.getY() + "_" + this.A.getZ(), NBTCompound);
        this.B.setTag(this.B);
    }

    /**
    * @return the given {@link Block}.
    */

    public @NotNull Block getBlock() {
        return this.A;
    }
}