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

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class StreamsHelper {
    private static Charset defaultCharset = Charset.forName("ascii");
    private static CharsetEncoder defaultEncoder = defaultCharset.newEncoder();
    private static Charset utf16leCharset = Charset.forName("utf-16le");

    public static int readCompactInt(InputStream input) throws IOException {
        int output = 0;
        boolean signed = false;
        for (int i = 0; i < 5; i++) {
            int x = input.read();
            if (x < 0)
                throw new EOFException();
            if (i == 0) {
                if ((x & 0x80) > 0)
                    signed = true;
                output |= (x & 0x3F);
                if ((x & 0x40) == 0)
                    break;
            } else if (i == 4) {
                output |= (x & 0x1F) << (6 + (3 * 7));
            } else {
                output |= (x & 0x7F) << (6 + ((i - 1) * 7));
                if ((x & 0x80) == 0)
                    break;
            }
        }
        if (signed)
            output *= -1;
        return output;
    }

    public static String readString(InputStream input) throws IOException {
        int len = readCompactInt(input);
        if (len == 0)
            return "";

        byte[] bytes = new byte[len > 0 ? len : -2 * len];
        new DataInputStream(input).readFully(bytes);
        return new String(bytes, 0, bytes.length - (len > 0 ? 1 : 2), len > 0 ? defaultCharset : utf16leCharset).intern();
    }

    public static void writeCompactInt(OutputStream output, int value) throws IOException {
        output.write(compactIntToByteArray(value));
    }

    public static void writeString(OutputStream buffer, String s) throws IOException {
        if (s == null || s.isEmpty()) {
            writeCompactInt(buffer, 0);
            return;
        }

        s += '\0';
        boolean def = defaultEncoder.canEncode(s);
        byte[] bytes = s.getBytes(def ? defaultCharset : utf16leCharset);
        writeCompactInt(buffer, def ? bytes.length : -bytes.length / 2);
        buffer.write(bytes);
    }

    public static byte[] compactIntToByteArray(int v) {
        boolean negative = v < 0;
        v = Math.abs(v);
        int[] bytes = new int[]{
                (v) & 0b00111111,
                (v >> 6) & 0b01111111,
                (v >> 6 + 7) & 0b01111111,
                (v >> 6 + 7 + 7) & 0b01111111,
                (v >> 6 + 7 + 7 + 7) & 0b01111111
        };

        if (negative) bytes[0] |= 0b10000000;

        int size = 5;
        for (int i = 4; i > 0; i--) {
            if (bytes[i] != 0)
                break;
            size--;
        }
        byte[] res = new byte[size];

        for (int i = 0; i < size; i++) {
            if (i != size - 1)
                bytes[i] |= i == 0 ? 0b01000000 : 0b10000000;
            res[i] = (byte) bytes[i];
        }
        return res;
    }
}
