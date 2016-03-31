/*
 * Copyright (c) 2016 acmi
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
package acmi.l2.clientmod.l2resources.texture;

import acmi.l2.clientmod.io.UnrealPackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static acmi.l2.clientmod.io.BufferUtil.getCompactInt;

public class TextureProperties {
    private Img.Format format = Img.Format.values()[0];
    private int width, height;
    private int palette;

    private Split9 split9 = new Split9();

    public Img.Format getFormat() {
        return format;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPalette() {
        return palette;
    }

    public Split9 getSplit9() {
        return split9;
    }

    public TextureProperties read(UnrealPackage l2UnrealPackage, ByteBuffer buffer) {
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        String name;
        while (!(name = l2UnrealPackage.getNameTable().get(getCompactInt(buffer)).getName()).equals("None")) {
            int info = buffer.get() & 0xff;
            int propertyType = info & 0b1111;
            int sizeType = (info >> 4) & 0b111;
            boolean array = info >> 7 == 1;

            String structName = propertyType == 10 ?
                    l2UnrealPackage.getNameTable().get(getCompactInt(buffer)).getName() : null;
            int size = getPropertySize(sizeType, buffer);
            int arrayIndex = array && propertyType != 3 ? getCompactInt(buffer) : 0;

            byte[] objBytes = new byte[size];
            buffer.get(objBytes);
            ByteBuffer objBuffer = ByteBuffer.wrap(objBytes);
            objBuffer.order(ByteOrder.LITTLE_ENDIAN);

            switch (name) {
                case "Format":
                    format = Img.Format.values()[objBuffer.get() & 0xff];
                    break;
                case "USize":
                    width = objBuffer.getInt();
                    break;
                case "VSize":
                    height = objBuffer.getInt();
                    break;
                case "Palette":
                    palette = getCompactInt(objBuffer);
                    break;
                case "bSplit9Texture":
                    split9.setSplit9Texture(array);
                    break;
                case "Split9X1":
                    split9.setSplit9X1(objBuffer.getInt());
                    break;
                case "Split9X2":
                    split9.setSplit9X2(objBuffer.getInt());
                    ;
                    break;
                case "Split9X3":
                    split9.setSplit9X3(objBuffer.getInt());
                    break;
                case "Split9Y1":
                    split9.setSplit9Y1(objBuffer.getInt());
                    break;
                case "Split9Y2":
                    split9.setSplit9Y2(objBuffer.getInt());
                    break;
                case "Split9Y3":
                    split9.setSplit9Y3(objBuffer.getInt());
                    break;
            }
        }

        return this;
    }

    private static int getPropertySize(int sizeType, ByteBuffer buffer) {
        switch (sizeType) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 12;
            case 4:
                return 16;
            case 5:
                return buffer.get() & 0xff;
            case 6:
                return buffer.getShort() & 0xffff;
            case 7:
                return buffer.getInt();
            default:
                return 0;
        }
    }
}
