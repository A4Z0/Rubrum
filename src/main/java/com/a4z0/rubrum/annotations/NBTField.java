package com.a4z0.rubrum.annotations;

import com.a4z0.alumina.api.version.Minecraft;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* Representation of reflected fields from an NBTObject.
*/

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NBTField {

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

    @NotNull Minecraft Version() default Minecraft.V1_16_R1;
}