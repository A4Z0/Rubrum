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

        for(Task T : Task.values()) {
            boolean Okay = T.T((A, B) -> {
                if(B[0] != null) {
                    this.getLogger().info("TASK -> [" + A.N() + "] passed!");
                }else{
                    this.getLogger().warning("FAILURE -> [" + A.N() + "] did not pass.");
                };
            });

            if(!Okay) { this.getServer().getPluginManager().disablePlugin(this); return; }
        };

        this.getLogger().info("Successfully started!");
    };
};