package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

public class NBTCompound extends Compound {

    private NBTCompound P;

    /**
    * Construct a {@link NBTCompound}.
    */

    protected NBTCompound() {};

    /**
    * @return a {@link NBTCompound} object.
    */

    public Object getCompound() {
        return this.P.getCompound();
    };

    /**
    * Sets the parent {@link NBTCompound}.
    *
    * @param Compound a {@link NBTCompound}.
    */

    protected void setCompound(@NotNull Compound Compound) {
        this.P.setCompound(Compound);
    };

    /**
    * @return the parent {@link NBTCompound} of this {@link NBTCompound}.
    */

    public NBTCompound getParent() {
        return this.P;
    };
};