package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvDataReader {
    private static final String FILE_PATH = SerenityConfigReader.get("csv.userdata");

    public static Map<String, String> getUserData(String userId) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String[] headers = br.readLine().split(",");
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> record = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    record.put(headers[i].trim(), values[i].trim());
                }

                if (record.get("role").equalsIgnoreCase(userId)) {
                    return record;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + FILE_PATH, e);
        }

        throw new RuntimeException("User with id '" + userId + "' not found in CSV: " + FILE_PATH);
    }

//    @Given("^(?:the|a) (user\\d+) logs in using data from {string}$")
//    public void loginUserFromCsv(String userId, String fileName) {
//        String path = "src/test/resources/data/" + fileName;
//        Map<String, String> userData = CsvDataLoader.findByUserId(path, userId);
//
//        String username = userData.get("username");
//        String password = userData.get("password");
}
