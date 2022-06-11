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

        for(Task Task : Task.values()) {
            switch(Task.T()) {
                case 0: {
                    this.getLogger().warning("Task -> (" + Task.N() + ") Error.");
                    this.getServer().getPluginManager().disablePlugin(this);

                    return;
                }
                case 1: {
                    this.getLogger().info("Task -> (" + Task.N() + ") Ok!");
                    break;
                }
                case 2: {
                    break;
                }
            }
        }

        this.getLogger().info("");
        this.getLogger().info("Successfully started!");
    }
}