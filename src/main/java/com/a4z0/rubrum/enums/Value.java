package com.a4z0.rubrum.enums;

import org.jetbrains.annotations.NotNull;

public enum Value {
    INT(int.class, 3, "setInt", "a", "getInt", "h", "c"),
    BYTE(byte.class, 1, "setByte", "a", "getByte", "d", "x"),
    LONG(long.class, 4, "setLong", "a", "getLong", "i", "c"),
    FLOAT(float.class, 5, "setFloat", "a", "getFloat", "j", "w"),
    SHORT(short.class, 2, "setShort", "a", "getShort", "g", "c"),
    STRING(String.class, 8, "setString", "a", "getString", "l", "A"),
    DOUBLE(double.class, 6, "setDouble", "a", "getDouble", "k", "w"),
    OBJECT(Object.class, 0, "setByteArray", "a", "getByteArray", "m", "c"),
    BOOLEAN(boolean.class, 0, "setBoolean", "a", "getBoolean", "e", "x"),
    INT_ARRAY(int[].class, 11, "setIntArray", "a", "getIntArray", "n", "c"),
    BYTE_ARRAY(byte[].class, 7, "setByteArray", "a", "getByteArray", "m", "c");

    private final byte B;
    private final Class<?> A;
    private final String C, D ,E, F, G;

    /**
    * Construct a {@link Value} with the given params.
    *
    * @param A {@link Value} class.
    * @param B {@link Value} class type.
    * @param C set method name.
    * @param D drastic set method name.
    * @param E get method name.
    * @param F drastic get method name.
    * @param G drastic get field name.
    */

    Value(@NotNull Class<?> A, int B, @NotNull String C, @NotNull String D, @NotNull String E, @NotNull String F, @NotNull String G) {
        this.A = A;
        this.B = (byte) B;
        this.C = C;
        this.D = D;
        this.E = E;
        this.F = F;
        this.G = G;
    };

    /**
    * @return the {@link Value} class.
    */

    public Class<?> A() {
        return this.A;
    };

    /**
    * @return the {@link Value} class type.
    */

    public byte B() {
        return this.B;
    };

    /**
    * @return a set method name.
    */

    public @NotNull String S() {
        return Version.B().D() ? this.D : this.C;
    };

    /**
    * @return a get method name.
    */

    public @NotNull String G() {
        return Version.B().D() ? this.F : this.E;
    };

    /**
    * @return drastic get field name.
    */

    public @NotNull String F() {
        return this.G;
    };

    /**
    * @param A a {@link Class}.
    *
    * @return a {@link Value} relative to the given {@link Class}.
    */

    public static @NotNull Value R(Class<?> A) {
        for(Value V : Value.values()) {
            if(V.A().equals(A)) return V;
        };

        return Value.OBJECT;
    };

    /**
    * @param A a {@link Class} type.
    *
    * @return a {@link Value} relative to the given {@link Class} type.
    */

    public static @NotNull Value R(byte A) {
        for(Value V : Value.values()) {
            if(V.B() == A) return V;
        };

        return Value.OBJECT;
    };
};