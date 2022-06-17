/*
 *     Rubrum
 *     Copyright (C) 2022.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.a4z0.rubrum;

import com.a4z0.rubrum.enums.Minecraft;
import com.a4z0.rubrum.enums.Task;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rubrum extends JavaPlugin {

    @Override
    public void onEnable() {
        if(!Minecraft.isCurrentVersionSupported()) {
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