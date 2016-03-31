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

import gr.zdimensions.jsquish.Squish;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public abstract class Img {
    private Format format;
    private byte[][] data;
    private BufferedImage[] mipMaps;

    public Format getFormat() {
        return format;
    }

    protected void setFormat(Format format) {
        this.format = format;
    }

    public byte[][] getData() {
        return data;
    }

    protected void setData(byte[][] data) {
        this.data = data;
    }

    public BufferedImage[] getMipMaps() {
        return mipMaps;
    }

    protected void setMipMaps(BufferedImage[] mipMaps) {
        this.mipMaps = mipMaps;
    }

    public int getWidth() {
        return getMipMaps()[0].getWidth();
    }

    public int getHeight() {
        return getMipMaps()[0].getHeight();
    }

    public enum Format {
        P8,
        RGBA7,
        RGB16,
        DXT1,
        RGB8,
        RGBA8,
        NODATA,
        DXT3,
        DXT5,
        L8,
        G16,
        RRRGGGBBB
    }

    public static int log2(int n) {
        return log2(n, 0);
    }

    private static int log2(int n, int c) {
        return n == 1 ? c : log2(n >> 1, c + 1);
    }

    public static class DDS extends Img {
        private DDS() {
        }

        public static DDS createFromData(byte[] data, MipMapInfo info) throws IOException {
            DDS dds = new DDS();
            dds.setFormat(info.properties.getFormat());
            dds.setMipMaps(new BufferedImage[info.offsets.length]);
            dds.setData(new byte[info.offsets.length][]);
            for (int i = 0; i < info.offsets.length; i++) {
                int width = Math.max(info.properties.getWidth() / (1 << i), 1);
                int height = Math.max(info.properties.getHeight() / (1 << i), 1);

                byte[] compressed = Arrays.copyOfRange(data, info.offsets[i], info.offsets[i] + info.sizes[i]);
                dds.getData()[i] = compressed;
                byte[] decompressed = decompress(compressed, width, height, dds.getFormat());
                BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                bi.getRaster().setDataElements(0, 0, width, height, decompressed);
                dds.getMipMaps()[i] = bi;
            }
            return dds;
        }

        private static byte[] decompress(byte[] compressed, int width, int height, Format format) throws IOException {
            Squish.CompressionType compressionType;
            try {
                compressionType = Squish.CompressionType.valueOf(format.toString());
            } catch (IllegalArgumentException iae) {
                throw new IOException("Format " + format + " is not supported");
            }
            return Squish.decompressImage(null, width, height, compressed, compressionType);
        }
    }

    public static class G16 extends Img {
        private G16() {
            setFormat(Format.G16);
        }

        public static G16 createFromData(byte[] data, MipMapInfo info) {
            G16 G16 = new G16();

            byte[] imageData = new byte[info.sizes[0]];

            for (int i = 0; i < data.length - info.properties.getWidth() * 2; i += info.properties.getWidth() * 2)
                System.arraycopy(data, info.offsets[0] + i, imageData, imageData.length - i - info.properties.getWidth() * 2, info.properties.getWidth() * 2);

            ByteBuffer buffer = ByteBuffer.wrap(imageData).order(ByteOrder.LITTLE_ENDIAN);

            BufferedImage image = new BufferedImage(info.properties.getWidth(), info.properties.getHeight(), BufferedImage.TYPE_USHORT_GRAY);
            for (int y = info.properties.getHeight() - 1; y >= 0; y--)
                for (int x = 0; x < info.properties.getWidth(); x++) {
                    int b = (buffer.getShort() & 0xffff) >> 8;
                    image.setRGB(x, y, b | (b << 8) | (b << 16));
                }

            G16.setMipMaps(new BufferedImage[]{image});
            G16.setData(new byte[][]{imageData});
            return G16;
        }
    }

    public static class P8 extends Img {
        private static final int BITS_PER_PIZEL = 8;

        public final Palette palette;

        public P8(Palette palette) {
            this.palette = palette;
        }

        public static P8 createFromData(byte[] data, MipMapInfo info) {
            P8 p8 = new P8(info.palette);

            byte[] imageData = new byte[info.sizes[0]];

            for (int i = 0; i < info.sizes[0]; i += info.properties.getWidth())
                System.arraycopy(data, info.offsets[0] + i, imageData, imageData.length - i - info.properties.getWidth(), info.properties.getWidth());

            BufferedImage image = p8.fromData(imageData, info.properties.getWidth(), info.properties.getHeight(), true);

            p8.setMipMaps(new BufferedImage[]{image});
            p8.setData(new byte[][]{imageData});
            return p8;
        }

        private BufferedImage fromData(byte[] data, int width, int height, boolean reverseLines) {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED, getColorModel());
            if (reverseLines) {
                for (int i = 0; i < height; i++)
                    System.arraycopy(data, i * width, ((DataBufferByte) image.getRaster().getDataBuffer()).getData(), data.length - width - i * width, width);
            } else {
                System.arraycopy(data, 0, ((DataBufferByte) image.getRaster().getDataBuffer()).getData(), 0, data.length);
            }
            return image;
        }

        private IndexColorModel getColorModel() {
            int size = palette.colors.length;
            byte[] r = new byte[size];
            byte[] g = new byte[size];
            byte[] b = new byte[size];
            for (int i = 0; i < size; i++) {
                r[i] = (byte) palette.colors[i].getRed();
                g[i] = (byte) palette.colors[i].getGreen();
                b[i] = (byte) palette.colors[i].getBlue();
            }
            return new IndexColorModel(BITS_PER_PIZEL, size, r, g, b);
        }
    }

    public static class TGA extends Img {
        private TGA() {
            setFormat(Format.RGBA8);
        }

        public static TGA createFromData(byte[] data, MipMapInfo info) throws IOException {
            TGA tga = new TGA();
            BufferedImage[] mipMaps = new BufferedImage[info.offsets.length];
            byte[][] ds = new byte[info.offsets.length][];
            for (int i = 0; i < info.offsets.length; i++) {
                int width = Math.max(info.properties.getWidth() / (1 << i), 1);
                int height = Math.max(info.properties.getHeight() / (1 << i), 1);

                ds[i] = Arrays.copyOfRange(data, info.offsets[i], info.offsets[i] + info.sizes[i]);
                ByteBuffer imageBuffer = ByteBuffer.wrap(ds[i]);
                imageBuffer.order(ByteOrder.LITTLE_ENDIAN);
                BufferedImage orig = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                for (int y = 0; y < height; y++)
                    for (int x = 0; x < width; x++) {
                        orig.setRGB(x, y, imageBuffer.getInt());
                    }
                mipMaps[i] = orig;
            }
            tga.setMipMaps(mipMaps);
            tga.setData(ds);
            return tga;
        }
    }
}
