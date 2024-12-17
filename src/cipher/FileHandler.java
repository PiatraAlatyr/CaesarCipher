package cipher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {

    private FileHandler() {
        throw new IllegalStateException("Utility class");
    }

    public static String readFile(File file) {
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String text){
        String path = GUI.fileInput.getSelectedFile().getAbsolutePath();
        StringBuilder builder = new StringBuilder(path);
        builder.insert(path.lastIndexOf('.'), "_result");
        Path result = Path.of(builder.toString());
        try {
            Files.writeString(result, text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}