package com.a4z0.rubrum;

import com.a4z0.rubrum.enums.Minecraft;
import com.a4z0.rubrum.enums.Task;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rubrum extends JavaPlugin {

    @Override
    public void onEnable() {
        if(!Minecraft.isCurrentVersionSupported()) {
            this.getLogger().info("Failed to start, running version isn't supported");
            this.getServer().getPluginManager().disablePlugin(this);

            return;
        }

        this.getLogger().info("Starting scan tasks...");
        this.getLogger().info("");

        Task.Try((Objects) -> this.getLogger().info("Task -> (" + Objects[0] + ") Ok!"), (Objects) -> {
            this.getLogger().warning("Task -> (" + Objects[0] + ") Error."); this.getServer().getPluginManager().disablePlugin(this);
        });

        this.getLogger().info("");
        this.getLogger().info("Successfully started!");
    }
}