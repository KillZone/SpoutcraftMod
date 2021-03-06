/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013-2014 SpoutcraftDev <http://spoutcraft.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spoutcraft.api.addon;

import java.nio.file.Path;

import org.spoutcraft.api.Spoutcraft;

/**
 * An addon is like a Mod or a Plugin (from Bukkit). It is the bridge between external code and SpoutcraftAPI's framework. <p/> The power of addons lie in the ability to use Spoutcraft API and Forge
 * without needing to make a new Mod.
 */
public abstract class Addon {
    protected Spoutcraft game;
    protected AddonLoader loader;
    protected AddonDescription description;
    protected AddonClassLoader classLoader;
    protected AddonLogger logger;
    protected Path dataPath, root;
    protected boolean enabled = false;

    protected Addon() {
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    protected void enable() {
        this.enabled = true;
    }

    protected void disable() {
        this.enabled = false;
    }

    protected void initialize(Spoutcraft game, AddonLoader loader, AddonDescription description, AddonClassLoader classLoader, Path dataPath, Path root) {
        this.game = game;
        this.loader = loader;
        this.description = description;
        this.classLoader = classLoader;
        this.logger = new AddonLogger(game.getLogger(), this);
        this.dataPath = dataPath;
        this.root = root;
    }

    public boolean isEnabled() {
        return enabled;
    }
    
    public Spoutcraft getGame() {
        return game;
    }

    public AddonDescription getDescription() {
        return description;
    }

    public AddonLoader getLoader() {
        return loader;
    }

    public AddonClassLoader getClassLoader() {
        return classLoader;
    }

    public AddonLogger getLogger() {
        return logger;
    }

    public Path getDataPath() {
        return dataPath;
    }

    public Path getPath() {
        return root;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Addon)) {
            return false;
        }

        final Addon addon = (Addon) o;

        return !(description != null ? !description.equals(addon.description) : addon.description != null);
    }

    @Override
    public final int hashCode() {
        return description != null ? description.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Addon{" +
                "game=" + game +
                ", loader=" + loader +
                ", description=" + description +
                ", classLoader=" + classLoader +
                ", logger=" + logger +
                ", dataPath=" + dataPath +
                ", root=" + root +
                ", enabled=" + enabled +
                '}';
    }
}
