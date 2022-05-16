package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.reflection.CraftItemStack;
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
    * @param Item {@link ItemStack} to be read.
    */

    public NBTItem(@NotNull ItemStack Item) {

        if(Item.getType().equals(Material.AIR))
            throw new NullPointerException("Item Material can't be AIR");

        this.A = Item;
        this.B = Item.clone();

        if(this.hasNBTData()) {
            super.setTag((NBTCompound) NBTUtils.GET_NBTBASE(CraftItemStack.getNBT(CraftItemStack.getNMS(this.A))));
        }
    }

    /**
    * Defines the NBT of the item stored in this {@link NBTItem}.
    *
    * @param NBTCompound {@link NBTCompound} to be merged into the item.
    */

    @Override
    public void setTag(@NotNull NBTCompound NBTCompound) {
        this.B = this.merge(this.B);
    }

    /**
    * @param Item Item to be merged with this {@link NBTCompound}.
    *
    * @return the item merged with this {@link NBTCompound}.
    */

    public @NotNull ItemStack merge(@NotNull ItemStack Item) {
        return CraftItemStack.parseNMSItem(CraftItemStack.setNBT(CraftItemStack.getNMS(Item), this.getComponent()));
    }

    /**
    * @return the given {@link ItemStack}.
    */

    public @NotNull ItemStack getRawItem() {
        return this.A;
    }

    /**
    * @return the new {@link ItemStack}.
    */

    public @NotNull ItemStack getItem() {
        return this.B;
    }

    /**
    * @return true if the item contains NBT Data.
    */

    public final boolean hasNBTData() {
        return CraftItemStack.getNBT(CraftItemStack.getNMS(this.A)) != null;
    }
}