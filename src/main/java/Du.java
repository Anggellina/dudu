import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.round;

class Du {
    private final boolean human;
    private final boolean unite;
    private final double base;

    public Du(boolean human, boolean unite, boolean base) {
        this.human = human;
        this.unite = unite;
        this.base = base ? 1000 : 1024;
    }

    public long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();
        // Проверка существования файла
        for (File file : files) {
            if (file.isFile()) length += file.length();
            else length += getFolderSize(file);
        }
        return length;
    }

    public String getHumanFormat(Long length) {
        String result = "";
        if (length < base)
            result = length + " B";
        else if (length < pow(base, 2))
            result = round(length / base) + " KB";
        else if (length < pow(base, 3))
            result = round(length / pow(base, 2)) + " MB";
        else if (length < pow(base, 4))
            result = round(length / pow(base, 3)) + " GB";
        return result;
    }

    public List<String> size(List<String> fileNames) {
        List<String> result = new ArrayList<>();
        List<Long> lengths = new ArrayList<>();
        List<File> files = new ArrayList<>();
        for (String name : fileNames) {
            files.add(new File(name));
        }
        for (File name : files) {
            if (name.isDirectory()) {
                lengths.add(getFolderSize(name));
            } else {
                lengths.add(name.length());
            }
        }
        if (unite) {
            Long sum = 0L;
            for (Long length : lengths) {
                sum += length;
            }
            result.add("Summarise size: " + getHumanFormat(sum));
            return result;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < files.size(); i++) {
            sb.append(files.get(i)).append(": ").
                    append(human ? getHumanFormat(lengths.get(i)) : lengths.get(i) / base);
            result.add(sb.toString());
            sb.setLength(0);
        }
        return result;
    }
}