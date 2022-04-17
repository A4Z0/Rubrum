package com.a4z0.rubrum.enums;

import org.jetbrains.annotations.NotNull;

public enum Value {
    Int((byte) 3, "data", "c"),
    Byte((byte) 1, "data", "x"),
    Long((byte) 4, "data", "c"),
    Float((byte) 5, "data", "w"),
    Short((byte) 2, "data", "c"),
    String((byte) 8, "data", "A"),
    Double((byte) 6, "data", "w"),
    List((byte) 9, "list", "c"),
    Compound((byte) 10, "map", "x"),
    IntArray((byte) 11, "data", "c"),
    LongArray((byte) 12, "data", "c"),
    ByteArray((byte) 7, "data", "c");

    private final byte A;
    private final String B, C;

    /**
    * Construct a {@link Value} with the given params.
    *
    * @param A a {@link com.a4z0.rubrum.api.nbt.NBTBase} ID.
    * @param B a data field name;
    * @param C a drastric data field name.
    */

    Value(byte A, @NotNull String B, @NotNull String C) {
        this.A = A;
        this.B = B;
        this.C = C;
    };

    /**
    * @return a data field name.
    */

    public @NotNull String D() {
        return Version.B().D() ? this.C : this.B;
    };

    /**
    * @param A a {@link Value} ID.
    *
    * @return a {@link Value}.
    */

    public static @NotNull Value V(byte A) {
        for(Value V : Value.values()) {
            if(V.A == A) return V;
        };

        throw new IllegalArgumentException("Error returning a Value.");
    };
};