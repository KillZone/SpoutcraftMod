/**
 * This file is a part of Spoutcraft
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * Spoutcraft is licensed under the MIT License
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
package org.spoutcraft.api;

import java.io.Serializable;

/**
 * A Prefab is a descriptor, by extending this class and providing characteristics you can use it to build:
 *
 * - {@link net.minecraft.block.Block} - {@link net.minecraft.item.Item} - {@link net.minecraft.block.material.Material}
 *
 * Prefabs are sent over the wire and constructed into real Minecraft classes.
 */
public abstract class Prefab implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String identifier;

	public Prefab(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Prefab)) {
			return false;
		}

		final Prefab prefab = (Prefab) o;

		if (!identifier.equals(prefab.identifier)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}

	@Override
	public String toString() {
		final String NEW_LINE = System.getProperty("line.separator");
		final StringBuilder builder = new StringBuilder();
		builder
				.append(getClass().getName() + " {" + NEW_LINE)
				.append(" Identifier: " + getIdentifier() + NEW_LINE)
				.append("}");
		return builder.toString();
	}
}
