package com.a4z0.rubrum.enums;

import com.a4z0.rubrum.api.nbt.*;
import com.a4z0.rubrum.interfaces.Request;
import com.a4z0.rubrum.interfaces.Response;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public enum Task {
    NBTCOMPOUND("NBTCompound", () -> {

        NBTCompound NBT = new NBTCompound();

        NBT.setInt("Int", 1);
        NBT.setByte("Byte", (byte) 1);
        NBT.setLong("Long", 10000);
        NBT.setFloat("Float", 0.01f);
        NBT.setUUID("UUID", UUID.randomUUID());
        NBT.setShort("Short", (short) 1.00);
        NBT.setString("String", "One");
        NBT.setDouble("Double", 100.100);
        NBT.setIntArray("IntArray", new int[]{1});
        NBT.setLongArray("LongArray", new long[1]);
        NBT.setByteArray("ByteArray", new byte[]{1});

        return NBTUtils.parseNBT(NBT);
    }),
    NBTITEM("NBTItem", () -> {

        NBTItem NBT = new NBTItem(new ItemStack(Material.IRON_SWORD));
        NBT.setCompound(NBTUtils.parseNBTCompound(NBTCOMPOUND.B.Run()));

        return NBT.getItem();
    }),
    NBTENTITY("NBTEntity", () -> {

        if(Bukkit.getWorlds().size() <= 0) return null;

        World A = Bukkit.getWorlds().get(0);
        ArmorStand B = A.spawn(A.getSpawnLocation(), ArmorStand.class);
        B.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));

        NBTEntity NBT = new NBTEntity(B);

        ArrayList<NBTBase<?>> List = null;

        if(NBT.getList("Equipment") != null) {
            List = NBT.getList("Equipment");
        };

        if(NBT.getList("ArmorItems") != null) {
            List = NBT.getList("ArmorItems");
        };

        if(List == null) return null;

        NBTCompound Compound = (NBTCompound) List.get(2);

        Compound.setString("id", "minecraft:iron_chestplate");
        List.set(3, Compound);

        if(NBT.getList("Equipment") != null) {
            NBT.setList("Equipment", (byte) 10, List);
        };

        if(NBT.getList("ArmorItems") != null) {
            NBT.setList("ArmorItems", (byte) 10, List);
        };

        NBT.setBoolean("Invisible", true);
        NBT.setCompound(NBT);

        B.remove();

        return NBT;
    });

    private final String A;
    private final Response<Object> B;

    /**
    * Construct a {@link Task} with the given params.
    *
    * @param B a {@link Request}.
    */

    Task(@NotNull String A, @NotNull Response<Object> B) {
        this.A = A;
        this.B = B;
    };

    /**
    * @return the task name.
    */

    public @NotNull String N() {
        return this.A;
    };

    /**
    * @param C a {@link Request}.
    *
    * @return true if the test runs successfully.
    */

    public boolean T(@NotNull Request C) {

        Object O;

        try {
            O = this.B.Run();
        } catch (IllegalArgumentException e) {
            O = null;
        };

        C.Run(this, O);

        return O != null;
    };
};