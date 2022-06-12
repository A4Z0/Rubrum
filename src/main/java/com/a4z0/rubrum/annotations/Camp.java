package com.a4z0.rubrum.annotations;

import com.a4z0.rubrum.enums.Minecraft;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Camp {

    @NotNull String[] Data();

    @NotNull String[] Type() default {};

    @NotNull Minecraft Version() default Minecraft.V1_18_R1;
}