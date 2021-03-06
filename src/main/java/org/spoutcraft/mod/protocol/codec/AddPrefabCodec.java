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
package org.spoutcraft.mod.protocol.codec;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.lang3.SerializationUtils;
import org.spoutcraft.api.Prefab;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.protocol.codec.Codec;
import org.spoutcraft.api.util.BufferUtil;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;

public class AddPrefabCodec implements Codec<AddPrefabMessage> {
    @Override
    public String getChannel() {
        return "SPC-AddPrefab";
    }

    @Override
    public AddPrefabMessage decode(Spoutcraft game, ByteBuf buffer) throws IOException {
        if (game.getSide().isServer()) {
            throw new IOException("The server is not allowed to receive prefabs");
        }
        final String addonIdentifier = BufferUtil.readUTF8(buffer);
        final byte[] data = new byte[buffer.readableBytes()];
        buffer.readBytes(data);
        return new AddPrefabMessage(addonIdentifier, (Prefab) SerializationUtils.deserialize(data));
    }

    @Override
    public ByteBuf encode(Spoutcraft game, AddPrefabMessage message) throws IOException {
        if (game.getSide().isClient()) {
            throw new IOException("The client is not allowed to send prefabs");
        }
        final ByteBuf buffer = Unpooled.buffer();
        BufferUtil.writeUTF8(buffer, message.getAddonIdentifier());
        final byte[] data = SerializationUtils.serialize(message.getPrefab());
        buffer.writeBytes(data);
        return buffer;
    }
}
