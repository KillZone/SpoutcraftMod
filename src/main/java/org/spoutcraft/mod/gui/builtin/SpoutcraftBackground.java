/**
 * This file is a part of Spoutcraft.
 *
 * Copyright (c) 2013 SpoutcraftDev <http://spoutcraft.org>
 * Spoutcraft is licensed under the MIT License.
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
package org.spoutcraft.mod.gui.builtin;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
//import org.lwjgl.opengl.GL11;
import org.spoutcraft.api.util.RandomUtil;
import org.spoutcraft.api.util.RenderUtil;
import org.spoutcraft.api.util.TimeUtil;
import org.spoutcraft.api.util.TextureUtil;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;

public class SpoutcraftBackground extends Gui {
    private static ResourceLocation location = selectBackground();
    private static final int BLUR_TEX = glGenTextures();

    static {
        TextureUtil.bind(BLUR_TEX);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, 420, 256, 0, GL_RGBA, GL_UNSIGNED_BYTE, (ByteBuffer)null);
        TextureUtil.setMinFilter(GL_LINEAR);
        TextureUtil.setMagFilter(GL_LINEAR);
        TextureUtil.setWrapS(GL_CLAMP_TO_EDGE);
        TextureUtil.setWrapT(GL_CLAMP_TO_EDGE);
    }

    private static ResourceLocation selectBackground() {
        switch (TimeUtil.getTime()) {
            case "day":
                return new ResourceLocation("spoutcraft", "textures/gui/title/background/day/background_" + RandomUtil.inclusive(1, 29) + ".jpg");
            case "evening":
                return new ResourceLocation("spoutcraft", "textures/gui/title/background/day/background_" + RandomUtil.inclusive(1, 29) + ".jpg");
            case "night":
                return new ResourceLocation("spoutcraft", "textures/gui/title/background/day/background_" + RandomUtil.inclusive(1, 29) + ".jpg");
            default:
                return new ResourceLocation("spoutcraft", "textures/gui/title/background/day/background_" + RandomUtil.inclusive(1, 29) + ".jpg");
        }
    }

    public void drawBackground(int x, int y, int width, int height, boolean overlay) {
        //RenderUtil.MINECRAFT.getTextureManager().bindTexture(location);
        //glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        //RenderUtil.create2DRectangleModal(x, y, width, height, 0);
        //RenderUtil.TESSELLATOR.draw();
        drawBackgroundBlur(x, y, width, height);

        if (overlay) {
            drawGradientRect(0, 0, width, height, Integer.MAX_VALUE, 0);
        }
    }

    //Applies blur effects and whatnot
    private void drawBackgroundBlur(int x, int y, int width, int height) {
        RenderUtil.MINECRAFT.getTextureManager().bindTexture(location);
        TextureUtil.setMinFilter(GL_LINEAR);
        glColor3f(1, 1, 1);

        //Setup projection matrix to custom
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();
        glOrtho(0, 420, 256, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();

        //Setup viewport for blurring
        glViewport(0, 0, 420, 256);
        RenderUtil.drawTexture(0, 0, 420, 256);
        TextureUtil.bind(BLUR_TEX);
        glEnable(GL_BLEND);
        glColorMask(true, true, true, false);
        for(int i = 0; i < 8; i++) {
            glCopyTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, 0, 0, 420, 256);
            for(int j = -1; j <= 1; j++) {
                glColor4f(1, 1, 1, (1 / (float)(j + 2)));
                float texOff = (j / 420F);
                RenderUtil.drawTexture(0, 0, 420, 256, texOff, 0, 1 + texOff, 1);
            }
        }
        glColorMask(true, true, true, true);
        glDisable(GL_BLEND);

        glMatrixMode(GL_PROJECTION);
        glPopMatrix();
        glMatrixMode(GL_MODELVIEW);
        glPopMatrix();
        glViewport(0, 0, RenderUtil.MINECRAFT.displayWidth, RenderUtil.MINECRAFT.displayHeight);
        RenderUtil.drawTexture(x, y, width, height);
    }
}
