package java.com.foxminded.task_6.racing;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Reader {


    public static List<String> readFile(String log)  {

        checkString(log);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL url = classLoader.getResource(log);
        checkUrl(url);

        File file = new File(url.getFile());
        checkFile(file);


        List<String> text = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            text = stream.collect(Collectors.toList());
          checkList(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    private static void checkUrl (URL url) {
        if (url == null) {
            throw new IllegalArgumentException("URL must not be null");
        }
    }
    private static void checkFile (File file) {
        if (!file.exists() || !file.canRead() || file.getAbsolutePath().isEmpty()) {
            throw new IllegalArgumentException("File must be exist and readable");
        }
    }

    private static void checkList (List<String>listText) {
        if (listText.isEmpty()) {
            throw new IllegalArgumentException("File must not be empty");
        }
    }

    private static void checkString (String log) {
        if (log == null) {
            throw new IllegalArgumentException("File must not be null");
        }
    }

}



