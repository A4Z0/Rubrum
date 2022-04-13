package com.a4z0.rubrum.enums;

import com.a4z0.rubrum.api.nbt.Compound;
import com.a4z0.rubrum.api.nbt.NBTItem;
import com.a4z0.rubrum.interfaces.Request;
import com.a4z0.rubrum.interfaces.Response;
import com.a4z0.rubrum.reflection.NBTUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public enum Task {

    COMPOUND("Compound", () -> {

        Compound Compound = new Compound();

        Compound.setMap("Map", new HashMap<>());
        Compound.setInt("Int", 1);
        Compound.setByte("Byte", (byte) 1);
        Compound.setLong("Long", 10000);
        Compound.setFloat("Float", 0.01f);
        Compound.setUUID("UUID", UUID.randomUUID());
        Compound.setShort("Short", (short) 1.00);
        Compound.setString("String", "One");
        Compound.setDouble("Double", 100.100);
        Compound.setIntArray("IntArray", new int[]{1});
        Compound.setLongArray("LongArray", new long[1]);
        Compound.setByteArray("ByteArray", new byte[]{1});

        return NBTUtils.getCompound(NBTUtils.getNBT(Compound));
    }),

    NBTITEM("NBTItem", () -> {

        NBTItem NBT = new NBTItem(new ItemStack(Material.IRON_SWORD));
        NBT.setCompound(((Compound) Task.COMPOUND.B.Run()));

        return NBT.getItem();
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