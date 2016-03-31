package acmi.l2.clientmod.l2resources;

import acmi.l2.clientmod.crypt.L2Crypt;
import acmi.l2.clientmod.io.DataInputStream;
import acmi.l2.clientmod.l2resources.texture.Img;
import acmi.l2.clientmod.l2resources.texture.MipMapInfo;
import acmi.l2.clientmod.l2resources.texture.Split9;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderWidths;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class L2Resources {
    private final Environment environment;
    private final Map<Integer, String> strings = new HashMap<>();
    private final Map<String, BufferedImage> images = new HashMap<>();
    private final Map<String, Split9> imageSplit9Info = new HashMap<>();

    public L2Resources(Environment environment) {
        this.environment = environment;

        loadStrings();
    }

    public Environment getEnvironment() {
        return environment;
    }

    private void loadStrings() {
        Collection<File> files = FileUtils.listFiles(getEnvironment().getStartDir(), new WildcardFileFilter("SysString-*.dat"), null);
        if (!files.isEmpty()) {
            File file = files.iterator().next();
            try (DataInputStream is = new DataInputStream(L2Crypt.decrypt(new FileInputStream(file), file.getName()), Charset.forName("EUC-KR"))) {
                strings.clear();
                int count = is.readInt();
                for (int i = 0; i < count; i++) {
                    strings.put(is.readInt(), is.readLine());
                }
            } catch (Exception ignore) {
            }
        }
    }

    public String getSysString(int i) {
        if (environment == null)
            return null;

        return strings.get(i);
    }

    private void loadImage(String name) {
        if (environment == null)
            return;

        if (images.containsKey(name))
            return;

        getEnvironment().getExportEntry(name, entry -> MipMapInfo.isTexture(entry.getFullClassName()))
                .ifPresent(texture -> MipMapInfo.getInfo(texture)
                        .ifPresent(info -> {
                            imageSplit9Info.put(name, info.properties.getSplit9());
                            try {
                                byte[] raw = texture.getObjectRawDataExternally();
                                switch (info.properties.getFormat()) {
                                    case DXT1:
                                    case DXT3:
                                    case DXT5:
                                        images.put(name, Img.DDS.createFromData(raw, info).getMipMaps()[0]);
                                        break;
                                    case RGBA8:
                                        images.put(name, Img.TGA.createFromData(raw, info).getMipMaps()[0]);
                                        break;
                                    case G16:
                                        images.put(name, Img.G16.createFromData(raw, info).getMipMaps()[0]);
                                        break;
                                    case P8:
                                        images.put(name, Img.P8.createFromData(raw, info).getMipMaps()[0]);
                                }
                            } catch (IOException e) {
                                throw new UncheckedIOException(e);
                            }
                        }));
    }

    public Image getImage(String name) {
        loadImage(name);

        if (images.get(name) == null)
            return null;

        return SwingFXUtils.toFXImage(images.get(name), null);
    }

    public Border getBorder(String name) {
        loadImage(name);

        BufferedImage img = images.get(name);
        Split9 split9 = imageSplit9Info.get(name);

        if (img == null)
            return null;

        Image image;
        BorderWidths borderWidths;
        if (split9.isSplit9Texture()) {
            image = SwingFXUtils.toFXImage(img.getSubimage(0, 0, split9.getSplit9X3(), split9.getSplit9Y3()), null);
            if (split9.getSplit9X1() == split9.getSplit9X2() && split9.getSplit9X2() == split9.getSplit9X3()) {
                borderWidths = new BorderWidths(
                        split9.getSplit9Y1(),
                        split9.getSplit9X3() - split9.getSplit9X2(),
                        split9.getSplit9Y3() - split9.getSplit9Y2(),
                        0);
            } else if (split9.getSplit9Y1() == split9.getSplit9Y2() && split9.getSplit9Y2() == split9.getSplit9Y3()) {
                borderWidths = new BorderWidths(
                        0,
                        split9.getSplit9X3() - split9.getSplit9X2(),
                        split9.getSplit9Y3() - split9.getSplit9Y2(),
                        split9.getSplit9X1());
            } else {
                borderWidths = new BorderWidths(
                        split9.getSplit9Y1(),
                        split9.getSplit9X3() - split9.getSplit9X2(),
                        split9.getSplit9Y3() - split9.getSplit9Y2(),
                        split9.getSplit9X1());
            }
        } else {
            image = SwingFXUtils.toFXImage(img, null);
            borderWidths = new BorderWidths(0, 0, 0, 0);
        }
        return new Border(new BorderImage(image, borderWidths, null, borderWidths, true, null, null));
    }
}
