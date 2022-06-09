package com.a4z0.rubrum.enums;

import com.a4z0.rubrum.api.nbt.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public enum Task {
    NBTCOMPOUND("NBTCompound", () -> {

        NBTCompound NBT = new NBTCompound();

        NBT.set("End", new NBTEnd());
        NBT.setByte("Byte", (byte) 1);
        NBT.setShort("Short", (short) 1.00);
        NBT.setInt("Int", 1);
        NBT.setLong("Long", 1L);
        NBT.setFloat("Float", 1f);
        NBT.setDouble("Double", 1d);
        NBT.setByteArray("ByteArray", new byte[]{1});
        NBT.setString("String", "One");
        NBT.setList("List", new ArrayList<>());
        NBT.setCompound("Compound", new NBTCompound());
        NBT.setIntArray("IntArray", new int[]{1});
        NBT.setLongArray("LongArray", new long[1]);

        NBTItem A = new NBTItem(new ItemStack(Material.IRON_SWORD));
        A.setTag(NBT);

        System.out.println(new NBTItem(A.getItem()));

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

        if(NBT.hasKey("Equipment")) {
            List = NBT.getList("Equipment");
        }

        if(NBT.hasKey("ArmorItems")) {
            List = NBT.getList("ArmorItems");
        }

        if(List == null) return null;

        NBTCompound IH = new NBTCompound();
        NBTCompound IC = new NBTCompound();
        NBTCompound IL = new NBTCompound();
        NBTCompound IB = new NBTCompound();

        NBTCompound D = new NBTCompound();
        D.setInt("Damage", 0);

        IH.setString("id", "minecraft:iron_helmet");
        IC.setString("id", "minecraft:iron_chestplate");
        IL.setString("id", "minecraft:iron_leggings");
        IB.setString("id", "minecraft:iron_boots");

        List.set(3, IH);
        List.set(2, IC);
        List.set(1, IL);
        List.set(0, IB);

        if(Version.B().M(Version.V1_14_R1)) {
            IH.setCompound("tag", D.clone());
            IH.setByte("Count", (byte) 1);
            IC.setCompound("tag", D.clone());
            IC.setByte("Count", (byte) 1);
            IL.setCompound("tag", D.clone());
            IL.setByte("Count", (byte) 1);
            IB.setCompound("tag", D.clone());
            IB.setByte("Count", (byte) 1);

            NBTCompound Data = NBT.getPersistentDataContainer();
            Data.setString("Data", "Hello, World!");
            Data.setTag(Data);
        }

        NBT.setBoolean("Invisible", true);
        NBT.setTag(NBT);

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
            e.printStackTrace();
            return 0;
        }
    }
}