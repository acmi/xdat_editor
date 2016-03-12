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

import acmi.l2.clientmod.crypt.CryptoException;
import acmi.l2.clientmod.crypt.L2Crypt;
import acmi.l2.clientmod.io.UnrealPackage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Environment {
    private static final Pattern PATHS_PATTERN = Pattern.compile("\\s*Paths=(.*)");

    private File startDir;
    private List<String> paths;

    private Map<File, UnrealPackage> pckgCache = new HashMap<>();

    public Environment(File ini) throws IOException {
        try (InputStream fis = new FileInputStream(ini)) {
            InputStream is = fis;
            try {
                is = L2Crypt.decrypt(is, ini.getName());
            } catch (CryptoException e) {
                System.err.println(e.getMessage());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            paths = br.lines()
                    .filter(s -> PATHS_PATTERN.matcher(s).matches())
                    .map(s -> s.substring(s.indexOf('=') + 1))
                    .collect(Collectors.toList());
            if (paths.isEmpty())
                throw new IllegalStateException("Couldn't find any Path in file");
            startDir = ini.getParentFile();
        }
    }

    public Environment(File startDir, List<String> paths) {
        this.startDir = startDir;
        this.paths = paths;
    }

    public File getStartDir() {
        return startDir;
    }

    public List<String> getPaths() {
        return paths;
    }

    public Stream<File> listFiles() {
        return paths.stream()
                .flatMap(s -> {
                    File file = new File(startDir, s);
                    File parent = file.getParentFile();
                    if (!parent.exists())
                        return Stream.empty();
                    return FileUtils.listFiles(file.getParentFile(), new WildcardFileFilter(file.getName()), null).stream();
                });
    }

    public Collection<File> getPackage(String name) {
        return listFiles()
                .filter(file -> FilenameUtils.removeExtension(file.getName()).equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public Optional<UnrealPackage.ExportEntry> getExportEntry(String fullName, String fullClassName) throws UncheckedIOException {
        return getExportEntry(fullName, e -> fullClassName == null ||
                e.getObjectClass() == null && fullClassName.equalsIgnoreCase("Core.Class") ||
                e.getObjectClass().getObjectFullName().equalsIgnoreCase(fullClassName));
    }

    public Optional<UnrealPackage.ExportEntry> getExportEntry(String fullName, Predicate<UnrealPackage.ExportEntry> filter) throws UncheckedIOException {
        if (!fullName.contains("."))
            return Optional.empty();

        String[] name = fullName.split("\\.", 2);
        return getPackage(name[0]).stream()
                .map(this::getPackage)
                .filter(Objects::nonNull)
                .map(UnrealPackage::getExportTable)
                .flatMap(Collection::stream)
                .filter(e -> e.getObjectInnerFullName().equalsIgnoreCase(name[1]) ||
                        e.getObjectName().getName().equalsIgnoreCase(name[1]))
                .filter(filter)
                .findAny();
    }

    private UnrealPackage getPackage(File f) throws UncheckedIOException {
        if (!pckgCache.containsKey(f)) {
            try (UnrealPackage up = new UnrealPackage(f, true, null, null)) {
                pckgCache.put(f, up);
            } catch (IOException e) {
                throw new UncheckedIOException("Couldn't load package " + f.getName(), e);
            }
        }

        return pckgCache.get(f);
    }
}
