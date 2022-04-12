package com.a4z0.rubrum.api.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class NBTCompound extends Compound {

    /**
    * Construct a {@link NBTCompound}.
    */

    protected NBTCompound() {

    };

    /**
    * Construct a {@link NBTCompound} with the given params.
    *
    * @param Map a serialized {@link NBTCompound}.
    */

    protected NBTCompound(@NotNull Map<String, Object> Map) {
        this.Remap(Map);
    };

    /**
    * @return a {@link NBTCompound} object.
    */

    public Object getCompound() {
        return null;
    };

    /**
    * Sets the parent {@link NBTCompound}.
    *
    * @param Compound a {@link NBTCompound}.
    */

    public void setCompound(@NotNull Compound Compound) {
        this.Remap(Compound.serialize());
    };
};