import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.List;

public class DuLauncher {

    @Option(name = "-h", metaVar = "Human", usage = "Use human readable format")
    private boolean human;

    @Option(name = "-c", metaVar = "combine size", usage = "Print total size")
    private boolean unite;

    @Option(name = "--si", metaVar = "base 1000", usage = "Use base 1000 instead of 1024")
    private boolean base;

    @Argument(required = true, metaVar = "File names", usage = "File names")
    private List<String> fileNames;

    public static void main(String[] args) {
        new DuLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        Du du = new Du(human, unite, base);
        try {
            for (String name : fileNames) {
                // Проверка существования файла
            }
            for (String line : du.size(fileNames)) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
