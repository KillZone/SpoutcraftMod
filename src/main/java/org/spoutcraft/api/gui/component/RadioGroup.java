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
package org.spoutcraft.api.gui.component;

import java.util.ArrayList;
import java.util.List;

public class RadioGroup {
    private List<RadioButton> buttons = new ArrayList<>();
    private RadioButton checkedButton;

    public RadioButton getCheckedButton() {
        return checkedButton;
    }

    public void setCheckedButton(RadioButton btn) {
        if (btn != null) {
            this.checkedButton = btn;
        }
    }

    protected void addButton(RadioButton btn) {
        if (!buttons.contains(btn)) {
            buttons.add(btn);
            if (checkedButton == null) {
                checkedButton = btn;
            }
        }
    }

    protected void removeButton(RadioButton btn) {
        buttons.remove(btn);
        if (checkedButton == btn) {
            checkedButton = buttons.get(0);
        }
    }
}
