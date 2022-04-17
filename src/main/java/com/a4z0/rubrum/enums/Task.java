package com.a4z0.rubrum.enums;

import com.a4z0.rubrum.api.nbt.*;
import com.a4z0.rubrum.interfaces.Response;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
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

        NBTUtils.parseNBT(NBT);

        return Conclusion.PASSED;
    }),
    NBTITEM("NBTItem", () -> {

        NBTItem NBT = new NBTItem(new ItemStack(Material.IRON_SWORD));
        NBT.setString("Data", "Hello, World!");

        NBT.setCompound(NBT);

        return Conclusion.PASSED;
    }),
    NBTENTITY("NBTEntity", () -> {

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

        if(Version.B().M(Version.V1_14_R1)) {

            NBTCompound Container = NBT.getPersistentDataContainer();
            Container.setString("Data", "Hello, World!");
            Container.setCompound(Container);
        };

        B.remove();

        return Conclusion.PASSED;
    }),
    NBTCHUNK("NBTChunk", () -> {

        if(!Version.B().M(Version.V1_16_R3)) return Conclusion.NOT_SUPPORTED;

        World A = Bukkit.getWorlds().get(0);
        NBTChunk NBT = new NBTChunk(A.getChunkAt(A.getSpawnLocation()));

        NBTCompound Container = NBT.getPersistentDataContainer();
        Container.setString("Data", "Hello, World!");
        Container.setCompound(Container);

        return Conclusion.PASSED;
    }),
    NBTBLOCK("NBTBlock", () -> {

        if(!Version.B().M(Version.V1_16_R3)) return Conclusion.NOT_SUPPORTED;

        World A = Bukkit.getWorlds().get(0);
        NBTBlock NBT = new NBTBlock(A.getBlockAt(A.getSpawnLocation()));
        NBT.setString("Data", "Hello, World!");
        NBT.setCompound(NBT);

        NBTChunk Chunk = new NBTChunk(NBT.getBlock().getChunk());
        Chunk.getPersistentDataContainer().setCompound(null);

        return Conclusion.PASSED;
    }),
    NBTTILEENTITY("NBTTileEntity", () -> {

        World A = Bukkit.getWorlds().get(0);
        Block O = A.getHighestBlockAt(A.getSpawnLocation());
        Material M = O.getType();

        O.setType(Material.getMaterial("SIGN"));
        NBTTileEntity NBT = new NBTTileEntity(O.getState());

        NBT.setString("Text1", "Hello, World!");
        NBT.setCompound(NBT);

        if(Version.B().M(Version.V1_14_R1)) {
            NBTCompound Container = NBT.getPersistentDataContainer();
            Container.setString("Data", "Hello, World!");
            Container.setCompound(Container);
        };

        O.setType(M);

        return Conclusion.PASSED;
    });

    private final String A;
    private final Response<Conclusion> B;

    /**
    * Construct a {@link Task} with the given params.
    *
    * @param B a {@link Response}.
    */

    Task(@NotNull String A, @NotNull Response<Conclusion> B) {
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
    * @return a {@link Conclusion}.
    */

    public @NotNull Conclusion T() {
        try {
            return this.B.Run();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return Conclusion.FAILED;
        }
    };

    /**
    * {@link Task} conclusion types.
    */

    public enum Conclusion {
        PASSED,
        FAILED,
        NOT_SUPPORTED;
    }
};