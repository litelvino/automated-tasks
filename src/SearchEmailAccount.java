import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchEmailAccount {

    public static final String file = "file.txt";
    public static final String email = "";

    public static void main(String[] args) {
        List<String> linesEmail = getLines(file);
        linesEmail.forEach(System.out::println);
    }

    public static List<String> getLines(String fileName) {
        try {
            File file = new File("E:\\FilesTxt\\" + fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            List<String> lines = new ArrayList<>();

            String st;
            while ((st = br.readLine()) != null && st.contains(email)) lines.add(st);

            return lines;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
