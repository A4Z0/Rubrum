package com.a4z0.rubrum.enums;

import org.jetbrains.annotations.NotNull;

public enum Value {
    INT((byte) 3, "data", "c"),
    BYTE((byte) 1, "data", "x"),
    LONG((byte) 4, "data", "c"),
    FLOAT((byte) 5, "data", "w"),
    SHORT((byte) 2, "data", "c"),
    STRING((byte) 8, "data", "A"),
    DOUBLE((byte) 6, "data", "w"),
    LIST((byte) 9, "list", "c"),
    COMPOUND((byte) 10, "map", "x"),
    INT_ARRAY((byte) 11, "data", "c"),
    LONG_ARRAY((byte) 12, "data", "c"),
    BYTE_ARRAY((byte) 7, "data", "c");

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