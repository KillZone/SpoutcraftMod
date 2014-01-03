/**
 * This file is part of SpoutcraftMod, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org/>
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
package org.spoutcraft.mod;

import java.nio.ByteBuffer;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.*;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.addon.AddonManager;
import org.spoutcraft.api.logger.SpoutcraftLogger;
import org.spoutcraft.api.protocol.MessageCodecLookupService;
import org.spoutcraft.api.resource.FileSystem;
import org.spoutcraft.api.util.TextureUtil;
import org.spoutcraft.mod.addon.ClientAddonManager;
import org.spoutcraft.mod.addon.CommonAddonManager;
import org.spoutcraft.mod.block.BlockPrefabRegistry;
import org.spoutcraft.mod.enchantment.EnchantmentPrefabRegistry;
import org.spoutcraft.mod.handler.ClientTickHandlers;
import org.spoutcraft.mod.item.ItemPrefabRegistry;
import org.spoutcraft.mod.protocol.CommonConnectionHandler;
import org.spoutcraft.mod.protocol.codec.AddFileCodec;
import org.spoutcraft.mod.protocol.codec.AddPrefabCodec;
import org.spoutcraft.mod.protocol.codec.AddonListCodec;
import org.spoutcraft.mod.protocol.codec.DownloadLinkCodec;
import org.spoutcraft.mod.protocol.message.AddFileMessage;
import org.spoutcraft.mod.protocol.message.AddPrefabMessage;
import org.spoutcraft.mod.protocol.message.AddonListMessage;
import org.spoutcraft.mod.protocol.message.DownloadLinkMessage;
import org.spoutcraft.mod.resource.ClientFileSystem;
import org.spoutcraft.mod.resource.CommonFileSystem;

// TODO: Reflect GameRegistry, LanguageRegistry, NetworkRegistry and remove addon content on server leave
// TODO: Fix generics?
@Mod (modid = "Spoutcraft")
@NetworkMod (clientSideRequired = true, serverSideRequired = true)
public class SpoutcraftMod {
    public static final String MOD_ID = "Spoutcraft";
    @Instance (value = "Spoutcraft")
    public static SpoutcraftMod instance;
    private static CustomTabs customTabs;

    @EventHandler
    public void onInitialize(FMLInitializationEvent event) {
        // Setup logger
        Spoutcraft.setLogger(new SpoutcraftLogger());

        // Setup protocol
        bindCodecMessages();

        final FileSystem fileSystem;
        final AddonManager manager;

        switch (event.getSide()) {
            case CLIENT:
                // Set the title
                Display.setTitle("Spoutcraft");

                // Set the icons
                final ByteBuffer windowIcon = TextureUtil.createImageBufferFrom(new ResourceLocation("spoutcraft", "textures/window_icon.png"), true);
                final ByteBuffer taskbarIcon = TextureUtil.createImageBufferFrom(new ResourceLocation("spoutcraft", "textures/taskbar_icon.png"), true);
                if (windowIcon != null && taskbarIcon != null) {
                    Display.setIcon(new ByteBuffer[] {windowIcon, taskbarIcon});
                }

                fileSystem = Spoutcraft.setFileSystem(new ClientFileSystem());

                // Setup file system
                try {
                    fileSystem.init();
                } catch (Exception e) {
                    throw new RuntimeException("Could not initialize FileSystem", e);
                }

                manager = Spoutcraft.setAddonManager(new ClientAddonManager());

                // Setup addon manager
                manager.loadAddons(CommonFileSystem.ADDONS_PATH);
                ClientTickHandlers.start();
                break;
            case SERVER:
                fileSystem = Spoutcraft.setFileSystem(new CommonFileSystem());

                // Setup file system
                try {
                    fileSystem.init();
                } catch (Exception e) {
                    throw new RuntimeException("Could not initialize FileSystem", e);
                }

                manager = Spoutcraft.setAddonManager(new CommonAddonManager());

                //Setup addon manager
                manager.loadAddons(CommonFileSystem.ADDONS_PATH);
                break;
            default:
                throw new RuntimeException("Spoutcraft is being ran on an invalid side!");
        }

        // Setup registries
        Spoutcraft.setBlockRegistry(new BlockPrefabRegistry());
        Spoutcraft.setEnchantmentPrefabRegistry(new EnchantmentPrefabRegistry());
        Spoutcraft.setItemPrefabRegistry(new ItemPrefabRegistry());

        // Setup creative tab
        customTabs = new CustomTabs();

        manager.enable();
    }

    private void bindCodecMessages() {
        NetworkRegistry.instance().registerConnectionHandler(new CommonConnectionHandler());
        MessageCodecLookupService.register(AddFileMessage.class, AddFileCodec.class);
        MessageCodecLookupService.register(AddPrefabMessage.class, AddPrefabCodec.class);
        MessageCodecLookupService.register(AddonListMessage.class, AddonListCodec.class);
        MessageCodecLookupService.register(DownloadLinkMessage.class, DownloadLinkCodec.class);
    }

    public static CustomTabs getCustomTabs() {
        return customTabs;
    }
}
