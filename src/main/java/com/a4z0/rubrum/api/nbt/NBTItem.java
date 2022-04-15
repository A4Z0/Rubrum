package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.reflection.CraftItemStack;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

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

    @Override
    public Object getCompound() {
        return CraftItemStack.getNBTItem(CraftItemStack.asNMSCopy(this.A));
    };

    @Override
    public void setCompound(@NotNull NBTCompound NBTCompound) {
        super.setCompound(NBTCompound); this.B = CraftItemStack.asBukkitCopy(CraftItemStack.setNBT(CraftItemStack.asNMSCopy(this.B), NBTUtils.parseNBT(this)));
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
    * @return true if it has NBT data.
    */

    public boolean hasNBTData() {
        return this.getCompound() != null;
    };
};