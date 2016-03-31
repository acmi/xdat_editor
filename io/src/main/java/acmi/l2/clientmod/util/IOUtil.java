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

import javafx.collections.FXCollections;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static Color intToARGB(int val) {
        return Color.rgb((val >> 16) & 0xff,
                (val >> 8) & 0xff,
                val & 0xff,
                ((val >> 24) & 0xff) / 255.);
    }

    public static Color readColor(InputStream buffer) throws IOException {
        return intToARGB(readInt(buffer));
    }

    public static Color intToRGBA(int val) {
        return Color.rgb(val & 0xff,
                (val >> 8) & 0xff,
                (val >> 16) & 0xff,
                ((val >> 24) & 0xff) / 255.);
    }

    public static Color readRGBA(InputStream buffer) throws IOException {
        return intToRGBA(readInt(buffer));
    }

    public static Boolean intToBool(int val) {
        switch (val) {
            case 0:
                return false;
            case 1:
                return true;
            default:
                return null;
//                throw new IllegalStateException(val + " is not valid boolean value");
        }
    }

    public static Boolean readBoolean(InputStream buffer) throws IOException {
        return intToBool(readInt(buffer));
    }

    public static float readFloat(InputStream buffer) throws IOException {
        return Float.intBitsToFloat(Integer.reverseBytes(new DataInputStream(buffer).readInt()));
    }

    public static UIEntity readUIEntity(InputStream buffer, String pckg, ClassLoader classLoader) throws IOException {
        String objClass = readString(buffer);

        try {
            Class<? extends UIEntity> clazz = Class.forName(pckg + "." + objClass, true, classLoader).asSubclass(UIEntity.class);
            return readIOEntity(buffer, clazz);
        } catch (ReflectiveOperationException e) {
            throw new IOException(e);
        }
    }

    public static <T extends IOEntity> T readIOEntity(InputStream buffer, Class<T> clazz) throws IOException {
        T object;
        try {
            object = clazz.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IOException("Couldn't instantiate " + clazz, e);
        }
        object.read(buffer);
        return object;
    }

    public static <T extends Enum<T>> T readEnum(InputStream buffer, Class<T> enumType) throws IOException {
        if (StringValue.class.isAssignableFrom(enumType))
            return Enum.valueOf(enumType, readString(buffer));

        int val = readInt(buffer);
        T[] constants = enumType.getEnumConstants();
        if (IntValue.class.isAssignableFrom(enumType)) {
            return Arrays.stream(constants)
                    .filter(c -> ((IntValue) c).intValue() == val)
                    .findAny()
                    .orElseThrow(() -> new IOException(enumType.getName() + ": invalid enum value: " + val));
        } else {
            if (val < 0 || val >= constants.length)
                throw new IOException(enumType.getName() + ": invalid enum index: " + val + "(max " + (constants.length - 1) + ")");
            return constants[val];
        }
    }

    public static <T extends IOEntity> List<T> readList(InputStream buffer, Class<T> type, ArrayLength lengthType) throws IOException {
        int len;
        switch (lengthType) {
            case COMPACT_INT:
                len = readCompactInt(buffer);
                break;
            case INT:
            default:
                len = readInt(buffer);
        }
        List<T> list = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            T obj;
            if (UIEntity.class.isAssignableFrom(type)) {
                obj = (T) readUIEntity(buffer, type.getPackage().getName(), type.getClassLoader());
            } else {
                obj = readIOEntity(buffer, type);
            }
            list.add(obj);
        }
        return FXCollections.observableList(list);
    }

    public static <T extends IOEntity> List<T> readList(InputStream buffer, Class<T> type) throws IOException {
        return readList(buffer, type, ArrayLength.INT);
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

    public static int colorToARGB(Color color) {
        if (color == null)
            return 0;

        return (int) Math.round(color.getBlue() * 255.0) |
                ((int) Math.round(color.getGreen() * 255.0) << 8) |
                ((int) Math.round(color.getRed() * 255.0) << 16) |
                ((int) Math.round(color.getOpacity() * 255.0) << 24);
    }

    public static void writeColor(OutputStream buffer, Color color) throws IOException {
        writeInt(buffer, colorToARGB(color));
    }

    public static int colorToRGBA(Color color) {
        if (color == null)
            return 0;

        return (int) Math.round(color.getRed() * 255.0) |
                ((int) Math.round(color.getGreen() * 255.0) << 8) |
                ((int) Math.round(color.getBlue() * 255.0) << 16) |
                ((int) Math.round(color.getOpacity() * 255.0) << 24);
    }

    public static void writeRGBA(OutputStream buffer, Color color) throws IOException {
        writeInt(buffer, colorToRGBA(color));
    }

    public static int boolToInt(Boolean val) {
        return val == null ? -1 : val ? 1 : 0;
    }

    public static void writeBoolean(OutputStream buffer, Boolean val) throws IOException {
        new DataOutputStream(buffer).writeInt(Integer.reverseBytes(boolToInt(val)));
    }

    public static void writeFloat(OutputStream buffer, float val) throws IOException {
        new DataOutputStream(buffer).writeInt(Integer.reverseBytes(Float.floatToIntBits(val)));
    }

    public static void writeUIEntity(OutputStream buffer, UIEntity val) throws IOException {
        writeString(buffer, val.getClass().getSimpleName());
        writeIOEntity(buffer, val);
    }

    public static void writeIOEntity(OutputStream buffer, IOEntity val) throws IOException {
        val.write(buffer);
    }

    public static <T extends Enum<T>> void writeEnum(OutputStream buffer, T val) throws IOException {
        if (val instanceof IntValue)
            writeInt(buffer, ((IntValue) val).intValue());
        else if (val instanceof StringValue)
            writeString(buffer, val.toString());
        else
            writeInt(buffer, val.ordinal());
    }

    public static void writeList(OutputStream buffer, List list) throws IOException {
        writeList(buffer, list, ArrayLength.INT);
    }

    public static <T extends IOEntity> void writeList(OutputStream buffer, List<T> list, ArrayLength lengthType) throws IOException {
        switch (lengthType) {
            case COMPACT_INT:
                writeCompactInt(buffer, list.size());
                break;
            case INT:
            default:
                writeInt(buffer, list.size());
        }
        for (T obj : list) {
            if (obj instanceof UIEntity)
                writeUIEntity(buffer, (UIEntity) obj);
            else
                writeIOEntity(buffer, obj);
        }
    }
}
