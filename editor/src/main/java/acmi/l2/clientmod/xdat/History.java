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
package acmi.l2.clientmod.xdat;

import java.util.Objects;

import static acmi.l2.clientmod.util.ScriptMethods.constructorString;
import static acmi.l2.clientmod.util.ScriptMethods.getClassName;
import static java.lang.System.lineSeparator;

public class History implements CharSequence {
    private StringBuilder sb = new StringBuilder();

    private StringBuilder tmp = new StringBuilder();

    private String prevObj;
    private String prevProp;
    private int prevObjHash;

    public void valueChanged(String obj, String property, Object value, int objHash) {
        if (!Objects.equals(property, prevProp) || prevObjHash != objHash) {
            flush();

            prevObj = obj;
            prevProp = property;
            prevObjHash = objHash;
        } else {
            clearTmp();
            obj = prevObj;
        }
        valueChanged(tmp, obj, property, value);
    }

    private static void valueChanged(StringBuilder sb, String obj, String property, Object value) {
        sb.append(obj)
                .append(".")
                .append(property)
                .append(" = ")
                .append(constructorString(value))
                .append(lineSeparator());
    }

    public void valueCreated(String list, Class clazz) {
        flush();
        sb.append(list)
                .append(".add(new ")
                .append(getClassName(clazz))
                .append("())")
                .append(lineSeparator());
    }

    public void valueRemoved(String list, int index) {
        flush();
        sb.append(list)
                .append(".remove(")
                .append(index)
                .append(")")
                .append(lineSeparator());
    }

    public void valueRemoved(String list, String name) {
        flush();
        sb.append(list)
                .append(".removeByName(\"")
                .append(name)
                .append("\")")
                .append(lineSeparator());
    }

    @Override
    public int length() {
        flush();
        return sb.length();
    }

    @Override
    public char charAt(int index) {
        flush();
        return sb.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        flush();
        return sb.subSequence(start, end);
    }

    @Override
    public String toString() {
        flush();
        return sb.toString();
    }

    private void flush() {
        if (tmp.length() > 0) {
            sb.append(tmp);
            clearTmp();
        }
    }

    private void clearTmp() {
        tmp = new StringBuilder();
    }
}
