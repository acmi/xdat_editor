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
package acmi.l2.clientmod.xdat.propertyeditor;

import acmi.l2.clientmod.l2resources.texture.Img;
import acmi.l2.clientmod.l2resources.texture.MipMapInfo;
import acmi.l2.clientmod.unreal.Environment;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.AbstractPropertyEditor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;

public class TexturePropertyEditor extends AbstractPropertyEditor<String, TextField> {
    public static Environment environment;

    public TexturePropertyEditor(PropertySheet.Item property) {
        super(property, createTextField());

        Tooltip tooltip = new Tooltip();
        tooltip.graphicProperty().bind(Bindings.createObjectBinding(() -> {
            if (environment == null)
                return null;

            if (getValue() == null || getValue().isEmpty())
                return null;

            BufferedImage[] image = new BufferedImage[1];
            environment.getExportEntry(getValue(), MipMapInfo::isTexture)
                    .ifPresent(texture -> MipMapInfo.getInfo(texture)
                            .ifPresent(info -> {
                                try {
                                    byte[] raw = texture.getObjectRawDataExternally();
                                    switch (info.properties.getFormat()) {
                                        case DXT1:
                                        case DXT3:
                                        case DXT5:
                                            image[0] = Img.DDS.createFromData(raw, info).getMipMaps()[0];
                                            break;
                                        case RGBA8:
                                            image[0] = Img.TGA.createFromData(raw, info).getMipMaps()[0];
                                            break;
                                        case G16:
                                            image[0] = Img.G16.createFromData(raw, info).getMipMaps()[0];
                                            break;
                                        case P8:
                                            image[0] = Img.P8.createFromData(raw, info).getMipMaps()[0];
                                    }
                                } catch (IOException e) {
                                    throw new UncheckedIOException(e);
                                }
                            }));
            if (image[0] == null)
                return null;

            BufferedImage img = image[0];
            WritableImage fxImage = new WritableImage(img.getWidth(), img.getHeight());
            SwingFXUtils.toFXImage(img, fxImage);
            return new ImageView(fxImage);
        }, getObservableValue()));
        getEditor().setTooltip(tooltip);
    }

    private static TextField createTextField() {
        return new TextField();
    }

    @Override
    protected ObservableValue<String> getObservableValue() {
        return getEditor().textProperty();
    }

    @Override
    public void setValue(String value) {
        getEditor().setText(value);
    }
}
