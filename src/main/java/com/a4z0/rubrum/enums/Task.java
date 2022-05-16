package com.a4z0.rubrum.enums;

import com.a4z0.rubrum.api.nbt.*;
import com.a4z0.rubrum.api.version.Version;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public enum Task {
    NBTCOMPOUND("NBTCompound", () -> {

        NBTCompound NBT = new NBTCompound();

        NBT.setInt("Int", 1);
        NBT.setByte("Byte", (byte) 1);
        NBT.setLong("Long", 10000);
        NBT.setFloat("Float", 0.01f);
        NBT.setShort("Short", (short) 1.00);
        NBT.setString("String", "One");
        NBT.setDouble("Double", 100.100);
        NBT.setIntArray("IntArray", new int[]{1});
        NBT.setLongArray("LongArray", new long[1]);
        NBT.setByteArray("ByteArray", new byte[]{1});

        return 1;
    }),
    NBTITEM("NBTItem", () -> {

        NBTItem NBT = new NBTItem(new ItemStack(Material.IRON_SWORD));
        NBT.setString("Data", "Hello, World!");

        NBT.setTag(NBT);

        return 1;
    }),
    NBTENTITY("NBTEntity", () -> {

        World A = Bukkit.getWorlds().get(0);
        ArmorStand B = A.spawn(A.getSpawnLocation(), ArmorStand.class);

        NBTEntity NBT = new NBTEntity(B);

        List<NBTBase<?>> List = null;

        if(NBT.getList("Equipment") != null) {
            List = NBT.getList("Equipment");
        }

        if(NBT.getList("ArmorItems") != null) {
            List = NBT.getList("ArmorItems");
        }

        if(List == null) return null;

        NBTCompound Compound = (NBTCompound) List.get(2);

        Compound.setString("id", "minecraft:iron_chestplate");
        List.set(3, Compound);

        if(NBT.getList("Equipment") != null) {
            NBT.setList("Equipment", List, (byte) 10);
        }

        if(NBT.getList("ArmorItems") != null) {
            NBT.setList("ArmorItems", List, (byte) 10);
        }

        NBT.setBoolean("Invisible", true);
        NBT.setTag(NBT);

        if(Version.B().M(Version.V1_14_R1)) {

            NBTCompound Data = NBT.getPersistentDataContainer();
            Data.setString("Data", "Hello, World!");
            Data.setTag(Data);
        }

        B.remove();

        return 1;
    }),
    NBTCHUNK("NBTChunk", () -> {

        if(!Version.B().M(Version.V1_16_R3)) return 2;

        World A = Bukkit.getWorlds().get(0);
        NBTChunk NBT = new NBTChunk(A.getChunkAt(A.getSpawnLocation()));
        NBT.setString("Data", "Hello, World!");
        NBT.setTag(NBT);

        return 1;
    }),
    NBTBLOCK("NBTBlock", () -> {

        if(!Version.B().M(Version.V1_16_R3)) return 2;

        World A = Bukkit.getWorlds().get(0);
        NBTBlock NBT = new NBTBlock(A.getBlockAt(A.getSpawnLocation()));
        NBT.setString("Data", "Hello, World!");
        NBT.setTag(NBT);

        NBTChunk Chunk = new NBTChunk(NBT.getBlock().getChunk());
        Chunk.setCompound("blocks", null);
        Chunk.setTag(Chunk);

        return 1;
    }),
    NBTTILEENTITY("NBTTileEntity", () -> {

        World A = Bukkit.getWorlds().get(0);
        Block O = A.getHighestBlockAt(A.getSpawnLocation());
        Material M = O.getType();

        try {
            O.setType(Objects.requireNonNull(Material.getMaterial("SIGN")));
        }catch (NullPointerException e) {
            O.setType(Objects.requireNonNull(Material.getMaterial("OAK_SIGN")));
        }

        NBTTileEntity NBT = new NBTTileEntity(O.getState());

        NBT.setString("Text1", "Hello, World!");
        NBT.setTag(NBT);

        if(Version.B().M(Version.V1_14_R1)) {
            NBTCompound Data = NBT.getPersistentDataContainer();
            Data.setString("Data", "Hello, World!");
            Data.setTag(Data);
        }

        O.setType(M);

        return 1;
    });

    private final String A;
    private final Callable<Integer> B;

    /**
    * Construct a {@link Task} with the given params.
    *
    * @param A Task's name.
    * @param B Task's callabe.
    */

    Task(@NotNull String A, @NotNull Callable<Integer> B) {
        this.A = A;
        this.B = B;
    }

    /**
    * @return the {@link Task} name.
    */

    public @NotNull String N() {
        return this.A;
    }

    /**
    * @return a value based on {@link Task} execution.
    */

    public int T() {
        try {
            return this.B.call();
        } catch (Exception e) {
            return 0;
        }
    }
}