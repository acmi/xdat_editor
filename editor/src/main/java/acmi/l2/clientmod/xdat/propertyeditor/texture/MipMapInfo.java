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
package acmi.l2.clientmod.xdat.propertyeditor.texture;

import acmi.l2.clientmod.io.UnrealPackage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

import static acmi.l2.clientmod.io.BufferUtil.getCompactInt;
import static acmi.l2.clientmod.io.BufferUtil.getString;

public class MipMapInfo {
    private static final Collection<Img.Format> SUPPORTED_FORMATS = Arrays.asList(
            Img.Format.RGBA8,
            Img.Format.DXT1,
            Img.Format.DXT3,
            Img.Format.DXT5,
            Img.Format.G16,
            Img.Format.P8
    );
    public String name;
    public int exportIndex;

    public Img.Format format = Img.Format.P8;
    public int width;
    public int height;
    public Palette palette;
    public int[] offsets;
    public int[] sizes;

    public static Optional<MipMapInfo> getInfo(UnrealPackage.ExportEntry ee) throws UncheckedIOException {
        if (ee.getObjectClass() == null)
            return Optional.empty();

        if (isTexture(ee.getObjectClass().getObjectFullName()))
            try {
                return Optional.ofNullable(texture(ee));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }

        return Optional.empty();
    }

    private static MipMapInfo texture(UnrealPackage.ExportEntry ee) throws IOException {
        UnrealPackage up = ee.getUnrealPackage();

        byte[] raw = ee.getObjectRawDataExternally();

        ByteBuffer buffer = ByteBuffer.wrap(raw).order(ByteOrder.LITTLE_ENDIAN);

        TextureProperties properties = new TextureProperties().read(up, buffer);

        Img.Format format = properties.getFormat();

        if (format == null || !SUPPORTED_FORMATS.contains(format))
            return null;

        final MipMapInfo info = new MipMapInfo();
        info.name = ee.toString();
        info.exportIndex = ee.getIndex();
        readUnk(buffer, up.getVersion(), up.getLicensee());
        info.format = properties.getFormat();
        info.width = properties.getWidth();
        info.height = properties.getHeight();
        info.offsets = new int[getCompactInt(buffer)];
        info.sizes = new int[info.offsets.length];

        UnrealPackage.Entry entry = up.objectReference(properties.getPalette());
        if (entry instanceof UnrealPackage.ExportEntry) {
            UnrealPackage.ExportEntry paletteEntry = (UnrealPackage.ExportEntry) entry;
            info.palette = Palette.getRGBA(paletteEntry);
        } else if (entry instanceof UnrealPackage.ImportEntry) {
            return null;
        }

        for (int j = 0; j < info.offsets.length; j++) {
            buffer.position(buffer.position() + 4);
            info.sizes[j] = getCompactInt(buffer);
            info.offsets[j] = buffer.position();
            buffer.position(buffer.position() + info.sizes[j] + 10);
        }

        return info.offsets.length > 0 ? info : null;
    }

    public static void readUnk(ByteBuffer obj, int version, int licensee) throws IOException {
        if (licensee <= 12) {
            //???
        } else if (licensee <= 28) {
            //c0-ct0
            obj.getInt();
        } else if (licensee <= 32) {
            //???
        } else if (licensee <= 35) {
            //ct1-ct22
            obj.position(obj.position() + 1067);
            for (int i = 0; i < 16; i++)
                getString(obj);
            getString(obj);
            obj.getInt();
        } else if (licensee == 36) {
            //ct22
            obj.position(obj.position() + 1058);
            for (int i = 0; i < 16; i++)
                getString(obj);
            getString(obj);
            obj.getInt();
        } else if (licensee <= 39) {
            //Epeisodion
            if (version == 129) {
                obj.position(obj.position() + 92);
                int stringCount = getCompactInt(obj);
                for (int i = 0; i < stringCount; i++) {
                    getString(obj);
                    int addStringCount = obj.get() & 0xFF;
                    for (int j = 0; j < addStringCount; j++)
                        getString(obj);
                }
                getString(obj);
                obj.getInt();
                return;
            }

            //ct23-Lindvior
            obj.position(obj.position() + 36);
            int stringCount = getCompactInt(obj);
            for (int i = 0; i < stringCount; i++) {
                getString(obj);
                int addStringCount = obj.get() & 0xFF;
                for (int j = 0; j < addStringCount; j++)
                    getString(obj);
            }
            getString(obj);
            obj.getInt();
        } else {
            //Ertheia+
            obj.position(obj.position() + 92);
            int stringCount = getCompactInt(obj);
            for (int i = 0; i < stringCount; i++) {
                getString(obj);
                int addStringCount = obj.get() & 0xFF;
                for (int j = 0; j < addStringCount; j++)
                    getString(obj);
            }
            getString(obj);
            obj.getInt();
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public static boolean isTexture(String objClass) {
        return objClass != null && TEXTURE.contains(objClass);
    }

    private static final Set<String> TEXTURE = new HashSet<>(Arrays.asList(
            "Engine.Texture",
            "Engine.ColorWheel",
            "Engine.Cubemap",
            "Engine.ColorMask",
            "Engine.MaskTexture",
            "Fire.FireTexture",
            "Fire.FluidTexture",
            "Fire.FractalTexture",
            "Fire.IceTexture",
            "Fire.WaterTexture",
            "Fire.WaveTexture",
            "Fire.WetTexture"));
}
