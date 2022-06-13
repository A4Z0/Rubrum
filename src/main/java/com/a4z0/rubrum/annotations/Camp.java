package com.a4z0.rubrum.annotations;

import com.a4z0.rubrum.enums.Minecraft;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* Guides to the fields that must be used.
*/

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Camp {

    /**
    * @return the names of the Data fields.
    */

    @NotNull String[] Data();

    /**
    * @return the names of the Type fields.
    */

    @NotNull String[] Type() default {};

    /**
    * @return the version that the names should be changed.
    */

    @NotNull Minecraft Version() default Minecraft.V1_18_R1;
}