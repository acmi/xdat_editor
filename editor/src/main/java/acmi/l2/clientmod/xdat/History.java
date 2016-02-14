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

import static acmi.l2.clientmod.util.ScriptMethods.constructorString;
import static acmi.l2.clientmod.util.ScriptMethods.getClassName;
import static java.lang.System.lineSeparator;

public class History implements CharSequence {
    private StringBuilder sb = new StringBuilder();

    public void valueChanged(String obj, String property, Object value) {
        sb.append(obj)
                .append(".")
                .append(property)
                .append(" = ")
                .append(constructorString(value))
                .append(lineSeparator());
    }

    public void valueCreated(String list, Class clazz) {
        sb.append(list)
                .append(".add(new ")
                .append(getClassName(clazz))
                .append("())")
                .append(lineSeparator());
    }

    public void valueRemoved(String list, int index) {
        sb.append(list)
                .append(".remove(")
                .append(index)
                .append(")")
                .append(lineSeparator());
    }

    @Override
    public int length() {
        return sb.length();
    }

    @Override
    public char charAt(int index) {
        return sb.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return sb.subSequence(start, end);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
