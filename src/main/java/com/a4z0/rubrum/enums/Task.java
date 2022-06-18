/*
 *     Rubrum
 *     Copyright (C) 2022.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.a4z0.rubrum.enums;

import com.a4z0.rubrum.annotations.Since;
import com.a4z0.rubrum.api.nbt.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
* Contains all of Rubrum tasks.
*/

public enum Task {
    NBTUTILS("NBTUtils", () -> {
        for(Object NBTObject : Task.getNBTObjects()) {
            NBTUtils.getNBTBase(NBTObject);
        }

        return null;
    }),
    NBTCOMPOUND("NBTCompound", () -> {
        NBTCompound NBT = new NBTCompound();

        for(int i = 0; i < Task.getNBTs().length; i++) {
            NBT.set(String.valueOf(i), Task.getNBTs()[i]);
        }

        return null;
    }),
    NBTITEM("NBTItem", () -> {
        NBTItem NBT = new NBTItem(new ItemStack(Material.IRON_SWORD));

        for(int i = 0; i < Task.getNBTs().length; i++) {
            NBT.set(String.valueOf(i), Task.getNBTs()[i]);
        }

        NBT.setTag(NBT);

        return null;
    }),
    NBTENTITY("NBTEntity", () -> {
        World World = Bukkit.getWorlds().get(0);
        ArmorStand ArmorStand = World.spawn(World.getSpawnLocation(), ArmorStand.class);

        NBTEntity NBT = new NBTEntity(ArmorStand);

        List<NBTBase<?>> List = null;

        if(NBT.hasKey("Equipment")) {
            List = NBT.getList("Equipment");
        }

        if(NBT.hasKey("ArmorItems")) {
            List = NBT.getList("ArmorItems");
        }

        if(List == null) throw new IllegalArgumentException("This shouldn't have happened");

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

        if(Minecraft.V1_14_R1.isEqualOrNewer(Minecraft.getCurrentVersion())) {
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

        ArmorStand.remove();

        return null;
    }),

    @Since(Version = Minecraft.V1_16_R3)
    NBTCHUNK("NBTChunk", () -> {
        World World = Bukkit.getWorlds().get(0);
        NBTChunk NBT = new NBTChunk(World.getChunkAt(World.getSpawnLocation()));
        NBT.setString("Data", "Hello, World!");
        NBT.setTag(NBT);

        return null;
    }),

    @Since(Version = Minecraft.V1_16_R3)
    NBTBLOCK("NBTBlock", () -> {
        World World = Bukkit.getWorlds().get(0);
        NBTBlock NBT = new NBTBlock(World.getBlockAt(World.getSpawnLocation()));
        NBT.setString("Data", "Hello, World!");
        NBT.setTag(NBT);

        NBTChunk Chunk = new NBTChunk(NBT.getBlock().getChunk());
        Chunk.remove("blocks");
        Chunk.setTag(Chunk);

        return null;
    }),

    NBTTILEENTITY("NBTTileEntity", () -> {
        World World = Bukkit.getWorlds().get(0);
        Block Block = World.getHighestBlockAt(World.getSpawnLocation());
        Material Blocktype = Block.getType();

        try {
            Block.setType(Objects.requireNonNull(Material.getMaterial("SIGN")));
        }catch (NullPointerException e) {
            Block.setType(Objects.requireNonNull(Material.getMaterial("OAK_SIGN")));
        }

        NBTTileEntity NBT = new NBTTileEntity(Block.getState());

        NBT.setString("Text1", "Hello, World!");
        NBT.setTag(NBT);

        if(Minecraft.V1_14_R1.isEqualOrNewer(Minecraft.getCurrentVersion())) {
            NBTCompound Data = NBT.getPersistentDataContainer();
            Data.setString("Data", "Hello, World!");
            Data.setTag(Data);
        }

        Block.setType(Blocktype);

        return null;
    });

    private final String Taskname;
    private final Callable<Void> Work;

    /**
    * Construct a {@link Task}.
    *
    * @param Taskname Task name.
    * @param Work Task work.
    */

    Task(@NotNull String Taskname, @NotNull Callable<Void> Work) {
        this.Taskname = Taskname;
        this.Work = Work;
    }

    /**
    * @return the task name.
    */

    public @NotNull String getTaskname() {
        return this.Taskname;
    }

    /**
    * @return true if this {@link Task} is available in the current version.
    */

    private boolean isAvailable() {
        try {
            if(Task.class.getDeclaredField(this.name()).isAnnotationPresent(Since.class)) {
                return Task.class.getDeclaredField(this.name()).getAnnotation(Since.class).Version().isEqualOrNewer(Minecraft.getCurrentVersion());
            }
        }catch (NoSuchFieldException e) {
            throw new NullPointerException("Could not find this field");
        }

        return true;
    }

    /**
    * @return true if the test succeeds.
    */

    public boolean test() {
        if(!this.isAvailable()) return true;

        try {
            this.Work.call();
        }catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
    * @return a list of already defined NBTs.
    */

    private static @NotNull NBTBase<?>[] getNBTs() {
        return new NBTBase[]{
            new NBTEnd(),
            new NBTByte(),
            new NBTShort(),
            new NBTInt(),
            new NBTLong(),
            new NBTFloat(),
            new NBTDouble(),
            new NBTByteArray(),
            new NBTString(),
            new NBTList(),
            new NBTCompound(),
            new NBTIntArray(),
            new NBTLongArray()
        };
    }

    /**
    * @return a list of already defined NBTObjects.
    */

    private static @NotNull Object[] getNBTObjects() {
        return new Object[]{
                NBTUtils.NBTTagEnd.getNBTObject(),
                NBTUtils.NBTTagByte.getNBTObject((byte) 1),
                NBTUtils.NBTTagShort.getNBTObject((short) 1),
                NBTUtils.NBTTagInt.getNBTObject(1),
                NBTUtils.NBTTagLong.getNBTObject(1L),
                NBTUtils.NBTTagFloat.getNBTObject(1f),
                NBTUtils.NBTTagDouble.getNBTObject(1d),
                NBTUtils.NBTTagByteArray.getNBTObject(new byte[]{}),
                NBTUtils.NBTTagString.getNBTObject(""),
                NBTUtils.NBTTagList.getNBTObject(new ArrayList<>(), (byte) 10),
                NBTUtils.NBTTagCompound.getNBTObject(new HashMap<>()),
                NBTUtils.NBTTagIntArray.getNBTObject(new int[]{}),
                NBTUtils.NBTTagLongArray.getNBTObject(new long[]{}),
        };
    }
}