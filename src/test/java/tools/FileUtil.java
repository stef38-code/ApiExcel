package tools;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static String getAbsolutePath(String fileName) {
        Path resourceDirectory = Paths.get("src", "test", "resources", fileName);

        return resourceDirectory.toFile().getAbsolutePath();
    }
}
