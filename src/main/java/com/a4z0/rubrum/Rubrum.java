package com.a4z0.rubrum;

import com.a4z0.alumina.api.plugin.AlPlugin;
import com.a4z0.rubrum.enums.Task;

public final class Rubrum extends AlPlugin {

    @Override
    public void onEnable() {
        if(!this.isAvailable()) {
            this.getLogger().warning("Failed to start, running version isn't supported");
            this.getServer().getPluginManager().disablePlugin(this);

            return;
        }

        this.getLogger().info("Starting scan tasks...");
        this.getLogger().info("");

        if(!Task.Try((Objects) -> this.getLogger().info("Task -> (" + Objects[0] + ") Ok!"), (Objects) -> {
            this.getLogger().warning("Task -> (" + Objects[0] + ") Error.");
            this.getLogger().info("");
            this.getServer().getPluginManager().disablePlugin(this);
        })) {
            return;
        }

        this.getLogger().info("");
        this.getLogger().info("Successfully started!");
    }
}