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
package acmi.l2.clientmod.util;

import javafx.scene.paint.Color;

import java.io.*;

public class IOUtil {
    public static String readString(InputStream buffer) throws IOException {
        return StreamsHelper.readString(buffer);
    }

    public static int readCompactInt(InputStream buffer) throws IOException {
        return StreamsHelper.readCompactInt(buffer);
    }

    public static int readInt(InputStream buffer) throws IOException {
        return Integer.reverseBytes(new DataInputStream(buffer).readInt());
    }

    public static Color readColor(InputStream buffer) throws IOException {
        DataInputStream dis = new DataInputStream(buffer);
        int b = dis.readUnsignedByte();
        int g = dis.readUnsignedByte();
        int r = dis.readUnsignedByte();
        int a = dis.readUnsignedByte();
        return Color.rgb(r, g, b, a / 255.0);
    }

    public static boolean readBoolean(InputStream buffer) throws IOException {
        return readInt(buffer) != 0;
    }

    public static float readFloat(InputStream buffer) throws IOException {
        return Float.intBitsToFloat(Integer.reverseBytes(new DataInputStream(buffer).readInt()));
    }

    public static IOEntity readObject(InputStream buffer, String pckg, ClassLoader classLoader) throws IOException, ReflectiveOperationException {
        String objClass = readString(buffer);

        Class<? extends IOEntity> clazz = Class.forName(pckg + "." + objClass, true, classLoader).asSubclass(IOEntity.class);
        IOEntity obj = clazz.newInstance();
        obj.read(buffer);
        return obj;
    }

    public static void writeString(OutputStream buffer, String val) throws IOException {
        StreamsHelper.writeString(buffer, val);
    }

    public static void writeCompactInt(OutputStream buffer, int val) throws IOException {
        StreamsHelper.writeCompactInt(buffer, val);
    }

    public static void writeInt(OutputStream buffer, int val) throws IOException {
        new DataOutputStream(buffer).writeInt(Integer.reverseBytes(val));
    }

    public static void writeColor(OutputStream buffer, Color color) throws IOException {
        DataOutputStream dos = new DataOutputStream(buffer);
        dos.writeByte((int) (color.getBlue() * 255));
        dos.writeByte((int) (color.getGreen() * 255));
        dos.writeByte((int) (color.getRed() * 255));
        dos.writeByte((int) (color.getOpacity() * 255));
    }

    public static void writeBoolean(OutputStream buffer, boolean val) throws IOException {
        new DataOutputStream(buffer).writeInt(Integer.reverseBytes(val ? 1 : 0));
    }

    public static void writeFloat(OutputStream buffer, float val) throws IOException {
        new DataOutputStream(buffer).writeInt(Integer.reverseBytes(Float.floatToIntBits(val)));
    }

    public static void writeObject(OutputStream buffer, IOEntity val) throws IOException {
        writeString(buffer, val.getClass().getSimpleName());
        val.write(buffer);
    }
}
