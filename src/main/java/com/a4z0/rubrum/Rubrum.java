package com.a4z0.rubrum;

import com.a4z0.rubrum.enums.Task;
import com.a4z0.rubrum.enums.Version;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rubrum extends JavaPlugin {

    @Override
    public void onEnable() {
        if(!Version.S()) {
            this.getLogger().info("Failed to start, running version isn't supported");
            this.getServer().getPluginManager().disablePlugin(this);

            return;
        };

        this.getLogger().info("Starting scan tasks...");
        this.getLogger().info("");

        for(Task T : Task.values()) {
            switch(T.T()) {
                case PASSED: {
                    this.getLogger().info("Task -> (" + T.N() + ") Ok!");
                    break;
                }
                case FAILED: {
                    this.getLogger().warning("Task -> (" + T.N() + ") Error.");
                    this.getServer().getPluginManager().disablePlugin(this);
                    return;
                }
                case NOT_SUPPORTED: {
                    break;
                }
            }
        };

        this.getLogger().info("");
        this.getLogger().info("Successfully started!");
    };
};