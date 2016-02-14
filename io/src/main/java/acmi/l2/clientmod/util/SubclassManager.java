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

import java.lang.reflect.Modifier;
import java.util.*;

public class SubclassManager {
    private static SubclassManager instance;

    public static SubclassManager getInstance() {
        if (instance == null)
            instance = new SubclassManager();
        return instance;
    }

    private SubclassManager() {
    }

    private Map<Class, Collection<Class>> subclassMap = new HashMap<>();

    public void registerClass(Class clazz) {
        while (clazz != Object.class) {
            Class parent = clazz.getSuperclass();
            if (!subclassMap.containsKey(parent))
                subclassMap.put(parent, new HashSet<>());

            subclassMap.get(parent).add(clazz);

            clazz = parent;
        }
    }

    public Collection<Class> getClassWithAllSubclasses(Class clazz) {
        Collection<Class> subclasses = new TreeSet<>((o1, o2) ->
                o1.getSimpleName().compareTo(o2.getSimpleName()));

        fillList(clazz, subclasses);

        return subclasses;
    }

    private void fillList(Class clazz, Collection<Class> subclasses) {
        if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()))
            subclasses.add(clazz);

        if (subclassMap.containsKey(clazz))
            subclassMap.get(clazz).forEach(s -> fillList(s, subclasses));
    }
}
