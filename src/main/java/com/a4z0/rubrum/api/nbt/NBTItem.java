package com.a4z0.rubrum.api.nbt;

import com.a4z0.rubrum.reflection.CraftItemStack;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NBTItem extends NBTCompound {

    private final ItemStack A;
    
    /**
    * Construct a {@link NBTItem} with the givem params.
    *
    * @param Item a bukkit {@link ItemStack}.
    */

    public NBTItem(@NotNull ItemStack Item) {

        if(Item.getType().equals(Material.AIR)) {
            throw new IllegalArgumentException("Item type can't be AIR.");
        };

        this.A = Item;

        if(this.hasNBTData())  {
            this.setCompound(NBTUtils.getCompound(this.getCompound()));
        };
    };

    /**
    * @return the bukkit {@link ItemStack}.
    */

    public @NotNull ItemStack getRawItem() {
        return this.A;
    };

    /**
    * @param Item Item to merge.
    *
    * @return the given {@link ItemStack} merged with this {@link NBTItem}.
    */

    public @NotNull ItemStack merge(@NotNull ItemStack Item) {

        if(Item.getType().equals(Material.AIR)) {
            throw new IllegalArgumentException("Item type can't be AIR.");
        };

        return CraftItemStack.asBukkitCopy(CraftItemStack.setNBT(CraftItemStack.asNMSCopy(Item), NBTUtils.getNBT(this)));
    };

    /**
    * @return the {@link ItemStack} merged with this {@link NBTItem}.
    */

    public @NotNull ItemStack getItem() {
        return this.merge(this.A);
    };

    @Override
    public Object getCompound() {
        return CraftItemStack.getNBTItem(Objects.requireNonNull(CraftItemStack.asNMSCopy(this.A)));
    };

    @Override
    public void setCompound(@NotNull Compound Compound) {
        super.Remap(Compound.serialize());
    };

    /**
    * @return true if it has an {@link Compound}.
    */

    public boolean hasNBTData() {
        return this.getCompound() != null;
    };
};