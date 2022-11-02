package hr.fer.oprpp1.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class StudentDB {

    private static StudentDatabase database;
    private static List<StudentRecord> results;
    private static List<String> outputs;
    public static void main(String[] args) throws IOException {
        File databaseFile = new File("database.txt");
        List<String> dbList = Files.readAllLines(databaseFile.toPath());
        database = new StudentDatabase(dbList);

        Scanner sc = new Scanner(System.in);

        String line = "";

        while(true){
            System.out.println("> ");
            line = sc.nextLine();
            if(line.isBlank() || line.isEmpty()) throw new IllegalArgumentException("User input can't be empty!");
            if(line.equalsIgnoreCase("exit")) break;
            String[] splits = line.split("\\s+", 2);
            if(splits.length < 2) throw new IllegalArgumentException("Invalid user input!");
            if(!splits[0].equalsIgnoreCase("query"))
                throw new IllegalArgumentException("User queries have to begin with keyword 'query' ");

            results = runQuery(splits[1]);
            outputs = getOutputs(results);
            printOutput();
        }
        System.out.println("Goodbye!");
    }

    private static List<StudentRecord> runQuery(String query){
        List<StudentRecord> res = new ArrayList<>();
        if(query == null) throw new IllegalArgumentException("Can't run an empty query!");

        QueryParser qp = new QueryParser(query);
        qp.parse();
        if(qp.isDirectQuery()) {
            res.add(Objects.requireNonNull(database.forJmbag(qp.getQueriedJMBAG()), " " + database.isEmpty()));
            return res;
        }
        return database.filter(new QueryFilter(qp.getQuery()));
    }

    private static List<String> getOutputs(List<StudentRecord> records){
        List<String> outputs = new ArrayList<>();
        if(records.isEmpty())
            return outputs;

        StringBuilder sb = new StringBuilder();

        int maxJmbag = records.stream().map(StudentRecord::getJmbag).map(String::length).max(Integer::compareTo).get();
        int maxFirst = records.stream().map(StudentRecord::getFirstName).map(String::length).max(Integer::compareTo).get();
        int maxLast = records.stream().map(StudentRecord::getLastName).map(String::length).max(Integer::compareTo).get();

        sb.append("+").append("=".repeat(maxJmbag + 2)).append("+").append("=".repeat(maxLast + 2)).append("+")
            .append("=".repeat(maxFirst + 2)).append("+").append("=".repeat(3)).append("+");
        outputs.add(sb.toString());
        sb = new StringBuilder();
        for(StudentRecord sr: records){
            sb.append("| ").append(sr.getJmbag()).append(" ".repeat(maxJmbag - sr.getJmbag().length() + 1));
            sb.append("| ").append(sr.getLastName()).append(" ".repeat(maxLast - sr.getLastName().length() + 1));
            sb.append("| ").append(sr.getFirstName()).append(" ".repeat(maxFirst - sr.getFirstName().length() + 1));
            sb.append("| ").append(sr.getFinalGrade()).append(" |");
            outputs.add(sb.toString());
            sb = new StringBuilder();

        }
        sb.append("+").append("=".repeat(maxJmbag + 2)).append("+").append("=".repeat(maxLast + 2)).append("+")
            .append("=".repeat(maxFirst + 2)).append("+").append("=".repeat(3)).append("+");
        outputs.add(sb.toString());
        return outputs;
    }

    private static void printOutput(){
        int i = 0;
        if(outputs.isEmpty()) {
            System.out.println("Records selected: 0");
        } else {
            for (String s : outputs) {
                i++;
                System.out.println(s);
            }
            System.out.println("Records selected: " + (i - 2));
        }
    }
}
