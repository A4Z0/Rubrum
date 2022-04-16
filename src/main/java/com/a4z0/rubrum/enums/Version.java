package com.a4z0.rubrum.enums;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
* What is the purpose of this?
* The purpose is to facilitate the creation of plugins for multiple versions.
*
* Ex:
*   A: Pick up item from player's hand.
*       1.8 (A == false): Player.getItemInHand();
*       1.9 - 1.15 (A == true): Player.getItemInMainHand();
*   B: Color System.
*       1.8 - 1.15 (B == false) Minecraft standard colors.
*       1.16 - 1.18 (B == true) Added hexadecimal colors.
*
* The A and B values help in checking to see if some methods have changed.
*/

/**
* Contains the versions that are supported.
*
* Older version supported:
* {@link #V1_8}.
*
* Versions that have been smoothly changed:
* {@link #V1_9},
* {@link #V1_10},
* {@link #V1_11},
* {@link #V1_12},
* {@link #V1_13},
* {@link #V1_14},
* {@link #V1_15}.
*
* Versions that have been drastically changed:
* {@link #V1_16},
* {@link #V1_17},
* {@link #V1_18}.
*/

public enum Version {

    NOT_SUPPORTED,

    /* Version that hasn't changed much. */

    V1_8_R3,

    /* Versions that is possible to have two items in both hands. */

    V1_9_R1(true),
    V1_9_R2(true),
    V1_10_R1(true),
    V1_11_R1(true),
    V1_12_R1(true),
    V1_13_R1(true),
    V1_13_R2(true),
    V1_14_R1(true),
    V1_15_R1(true),

    /* Versions that is possible to have two items in both hands and has been drastically changed */

    V1_16_R1(true, true),
    V1_16_R2(true, true),
    V1_16_R3(true, true),
    V1_17_R1(true, true),
    V1_18_R1(true, true),
    V1_18_R2(true, true);

    private final boolean A, B;

    /**
    * Construct a {@link Version}.
    */

    Version() {
        this(false);
    };

    /**
    * Construct a {@link Version} with the given params.
    *
    * @param A is Two Handed?
    */

    Version(boolean A) {
        this(A, false);
    };

    /**
    * Construct a {@link Version} with the given params.
    *
    * @param A is Two Handed?
    * @param B is Drastically Changed?
    */

    Version(boolean A, boolean B) {
        this.A = A;
        this.B = B;
    };

    /**
    * @return true if the version is Two Handed.
    */

    public boolean T() {
        return this.A;
    };

    /**
    * @return true if the version has Changed Drastically.
    */

    public boolean D() {
        return this.B;
    };

    /**
    * @param Version a {@link Version}.
    *
    * @return true if the given {@link Version} is equal to or newer than this one.
    */

    public boolean M(@NotNull Version Version) {
        return this.ordinal() >= Version.ordinal();
    };

      //============================================//
     //               Static Values                //
    //============================================//

    /**
    * @return the bukkit version.
    */

    private static @NotNull String V() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    };

    /** Store the running version. */
    public final static String BUKKIT_VERSION = V();

    /**
    * @return true if the version used is Supported.
    */

    public static boolean S() {
        for(Version Version : Version.values()) {
            if(BUKKIT_VERSION.contains(Version.name().substring(1))) return true;
        };

        return false;
    };

    /**
    * @return the bukkit version as a {@link Version}.
    */

    public static @NotNull Version B() {
        for(Version Version : Version.values()) {
            if(BUKKIT_VERSION.contains(Version.name().substring(1))) return Version;
        };

        return Version.NOT_SUPPORTED;
    };

    /**
    * Declares a method as available from a version.
    */

    @Retention(RUNTIME) @Target({ METHOD })
    public @interface A {

        /**
        * @return the available {@link Version}.
        */

        @NotNull Version V();

    };

    /**
    * @param C a {@link Class} with the given {@link Method} name.
    * @param N a {@link Method} name with {@link Version.A}.
    *
    * @return true if the {@link Method} can be used.
    */

    public static boolean U(@NotNull Class<?> C, @NotNull String N) {
        for(Method M : C.getMethods()) {
            if(M.getName().equals(N)) {
                if(M.getAnnotation(A.class) != null) {
                    return Version.B().M((M.getAnnotation(A.class)).V());
                };
            };
        };

        return true;
    };
};