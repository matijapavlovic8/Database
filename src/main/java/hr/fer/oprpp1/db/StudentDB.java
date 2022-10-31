package hr.fer.oprpp1.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class StudentDB {
    public static void main(String[] args) throws IOException {
        File database = new File("database.txt");
        List<String> dbList;
        dbList = Files.readAllLines(database.toPath());

    }
}
