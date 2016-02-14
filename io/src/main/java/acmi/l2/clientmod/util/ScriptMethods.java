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
import javafx.util.Pair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScriptMethods {
    public static Named getAt(List<? extends Named> list, String name) {
        return list.parallelStream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(name + " not found"));
    }

    public static String constructorString(Object object) {
        if (object instanceof Color) {
            Color color = (Color) object;
            int r = (int) Math.round(color.getRed() * 255.0);
            int g = (int) Math.round(color.getGreen() * 255.0);
            int b = (int) Math.round(color.getBlue() * 255.0);
            int o = (int) Math.round(color.getOpacity() * 255.0);
            return String.format("Color.web(\"0x%02x%02x%02x%02x\")", r, g, b, o);
        } else if (object instanceof Enum) {
            Enum enumObject = (Enum) object;
            return String.format("%s.%s", getClassName(enumObject.getDeclaringClass()), enumObject.name());
        } else if (object instanceof CharSequence) {
            return String.format("\"%s\"", object);
        }

        return String.valueOf(object);
    }

    public static String getClassName(Class clazz) {
        String className = clazz.getName();
        return className.substring(className.indexOf('.') + 1).replaceAll("\\$", ".");
    }

    public static String toPropertyString(Object obj) {
        return toPropertyString(obj, false, true, false);
    }

    public static String toPropertyString(Object obj, boolean includeNames) {
        return toPropertyString(obj, includeNames, true, false);
    }

    public static String toPropertyString(Object obj, boolean includeNames, boolean includeParent) {
        return toPropertyString(obj, includeNames, includeParent, false);
    }

    @SuppressWarnings("unchecked")
    public static String toPropertyString(Object obj, boolean includeNames, boolean includeParent, boolean listContent) {
        List<Pair<String, String>> properties = new ArrayList<>();

        LinkedList<Class> list = new LinkedList<>();
        Class c = obj.getClass();

        if (includeParent)
            while (c != Object.class) {
                list.add(c);
                c = c.getSuperclass();
            }
        else
            list.add(c);

        while ((c = list.pollLast()) != null) {
            for (Field f : c.getDeclaredFields()) {
                if (f.isSynthetic())
                    continue;

                f.setAccessible(true);
                try {
                    Object o = f.get(obj);

                    String s;
                    if (o instanceof List)
                        if (listContent)
                            s = "[" + ((List<Object>) o).stream()
                                    .map(i -> i instanceof IOEntity ? toPropertyString(i, includeNames, includeParent, true) : Objects.toString(i))
                                    .collect(Collectors.joining("\t")) + "]";
                        else
                            s = "[..]";
                    else
                        s = Objects.toString(o);

                    properties.add(new Pair<>(f.getName(), s));
                } catch (IllegalAccessException ignore) {
                }
            }
        }

        return '[' + properties.stream()
                .map(p -> includeNames ? p.getKey() + "=" + p.getValue() : p.getValue())
                .collect(Collectors.joining("\t")) + ']';
    }
}
