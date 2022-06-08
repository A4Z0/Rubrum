package com.a4z0.rubrum.enums;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

/**
* Contains the versions that are supported.
*
* Older version supported:
* {@link #V1_8_R3}.
*
* Versions that have been smoothly changed:
* {@link #V1_9_R1},
* {@link #V1_9_R2},
* {@link #V1_10_R1},
* {@link #V1_11_R1},
* {@link #V1_12_R1},
* {@link #V1_13_R1},
* {@link #V1_13_R2},
* {@link #V1_14_R1},
* {@link #V1_15_R1}.
*
* Versions that have been drastically changed:
* {@link #V1_16_R1},
* {@link #V1_16_R2},
* {@link #V1_16_R3},
* {@link #V1_17_R1},
* {@link #V1_18_R1}.
* {@link #V1_18_R2}.
*/

public enum Version {

    NOT_SUPPORTED,

    /* Version that hasn't changed much. */

    V1_8_R3,

    /* Versions that is possible to have two items in both hands. */

    V1_9_R1(1),
    V1_9_R2(1),
    V1_10_R1(1),
    V1_11_R1(1),
    V1_12_R1(1),
    V1_13_R1(1),
    V1_13_R2(1),
    V1_14_R1(1),
    V1_15_R1(1),

    /* Versions that is possible to have two items in both hands and has been drastically changed */

    V1_16_R1(2),
    V1_16_R2(2),
    V1_16_R3(2),
    V1_17_R1(2),
    V1_18_R1(2),
    V1_18_R2(2);

    private final byte T;

    /**
    * Construct a {@link Version}.
    */

    Version() {
        this((byte) 0);
    }

    /**
    * Construct a {@link Version} with the given params.
    *
    * @param V_TYPE {@link Version} type.
    */

    Version(int V_TYPE) {
        this.T = (byte) V_TYPE;
    }

    /**
    * @return true if the version is "Two Handed".
    */

    public boolean T() {
        return T > 0;
    }

    /**
    * @return true if the version has "Changed Drastically".
    */

    public boolean D() {
        return T > 1;
    }

    /**
    * @param Version a {@link Version}.
    *
    * @return true if the given {@link Version} is equal to or newer than this one.
    */

    public boolean M(@NotNull Version Version) {
        return this.ordinal() >= Version.ordinal();
    }

    /**
    * @return the bukkit version.
    */

    private static @NotNull String V() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    /** Store the running version. */
    public final static String BUKKIT_VERSION = V();

    /**
    * @return true if the version used is Supported.
    */

    public static boolean S() {
        for(Version Version : Version.values()) {
            if(BUKKIT_VERSION.contains(Version.name().substring(1))) return true;
        }

        return false;
    }

    /**
    * @return the bukkit version as a {@link Version}.
    */

    public static @NotNull Version B() {
        for(Version Version : Version.values()) {
            if(BUKKIT_VERSION.contains(Version.name().substring(1))) return Version;
        }

        return NOT_SUPPORTED;
    }
}