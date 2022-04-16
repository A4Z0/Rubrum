package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.reflection.CraftItemStack;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
* NBT component of an {@link ItemStack}.
*/

public class NBTItem extends NBTCompound {

    private final ItemStack A;
    private ItemStack B;

    /**
    * Construct a {@link NBTItem} with the given params.
    *
    * @param Item a {@link ItemStack}.
    */

    public NBTItem(@NotNull ItemStack Item) {

        if(Item.getType().equals(Material.AIR)) {
            throw new IllegalArgumentException("Item type can't be AIR.");
        };

        this.A = Item;
        this.B = Item.clone();

        if(this.getCompound() != null) {
            super.setCompound(NBTUtils.parseNBTCompound(this.getCompound()));
        };
    };

    /**
    * @return an NMS object from an NBT.
    */

    @Override
    public Object getCompound() {
        return CraftItemStack.getNBTItem(CraftItemStack.asNMSCopy(this.A));
    };

    /**
    * Sets the {@link NBTItem} and update the {@link ItemStack} NBT.
    *
    * @param NBTCompound a {@link NBTCompound}.
    */

    @Override
    public void setCompound(NBTCompound NBTCompound) {
        super.setCompound(NBTCompound); this.B = this.merge(this.B);
    };

    /**
    * @return the original {@link ItemStack}.
    */

    public @NotNull ItemStack getRawItem() {
        return this.A;
    };

    /**
    * @return the new {@link ItemStack}.
    */

    public @NotNull ItemStack getItem() {
        return this.B;
    };

    /**
    * @param Item a {@link ItemStack}.
    *
    * @return given {@link ItemStack} merged with this {@link NBTCompound}.
    */

    public @NotNull ItemStack merge(@NotNull ItemStack Item) {
        return CraftItemStack.asBukkitCopy(CraftItemStack.setNBT(CraftItemStack.asNMSCopy(Item), NBTUtils.parseNBT(this)));
    };

    /**
    * @return true if it has NBT data.
    */

    public boolean hasNBTData() {
        return this.getCompound() != null;
    };
};