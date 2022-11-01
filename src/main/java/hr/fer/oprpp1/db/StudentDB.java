package hr.fer.oprpp1.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class StudentDB {
    public static void main(String[] args) throws IOException {
        File database = new File("database.txt");
        List<String> dbList;
        dbList = Files.readAllLines(database.toPath());

        Scanner sc = new Scanner(System.in);

        String line = "";

        while(!line.equalsIgnoreCase("exit")){
            System.out.println("> ");
            line = sc.nextLine().trim();
            if(line.isBlank() || line.isEmpty()) throw new IllegalArgumentException("User input can't be empty!");

            String[] splits = line.split("\\s+", 2);
            if(splits.length < 2) throw new IllegalArgumentException("Invalid user input!");
            if(!splits[0].equalsIgnoreCase("query"))
                throw new IllegalArgumentException("User queries have to begin with keyword 'query' ");







        }
        System.out.println("Goodbye!");
    }
}
