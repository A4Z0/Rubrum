package com.a4z0.rubrum.enums;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

/**
* Contains the versions that are supported.
*
* Oldest version supported:
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
* {@link #V1_19_R1}.
*/

public enum Minecraft {

    NOT_SUPPORTED,

    V1_8_R3,

    V1_9_R1,
    V1_9_R2,
    V1_10_R1,
    V1_11_R1,
    V1_12_R1,
    V1_13_R1,
    V1_13_R2,
    V1_14_R1,
    V1_15_R1,

    V1_16_R1,
    V1_16_R2,
    V1_16_R3,
    V1_17_R1,
    V1_18_R1,
    V1_18_R2,
    V1_19_R1;

    /**
    * @return true if the version is "Two Handed".
    */

    public boolean isTwoHanded() {
        return Minecraft.V1_9_R1.isEqualOrOlder(this);
    }

    /**
    * @return true if the version has "Changed Drastically".
    */

    public boolean isDrasticallyChanged() {
        return Minecraft.V1_16_R1.isEqualOrOlder(this);
    }

    /**
    * @param Version a {@link Minecraft} version.
    *
    * @return true if the given version is equal or older than this one.
    */

    public boolean isEqualOrOlder(@NotNull Minecraft Version) {
        return Version.ordinal() >= this.ordinal();
    }

    /**
    * @return the package version.
    */

    private static @NotNull String getPackageVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    /** Store the running package version. */
    public final static String PACKAGE_VERSION = getPackageVersion();

    /**
    * @return true if the version used is supported.
    */

    public static boolean isCurrentVersionSupported() {
        for(Minecraft Version : Minecraft.values()) {
            if(PACKAGE_VERSION.contains(Version.name().substring(1))) return true;
        }

        return false;
    }

    /**
    * @return the running version.
    */

    public static @NotNull Minecraft getCurrentVersion() {
        for(Minecraft Version : Minecraft.values()) {
            if(PACKAGE_VERSION.contains(Version.name().substring(1))) return Version;
        }

        return NOT_SUPPORTED;
    }
}