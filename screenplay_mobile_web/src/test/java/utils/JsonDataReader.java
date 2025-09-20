package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonDataReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, String> getData(String filePath) {
        try {
            File file = new File(filePath);
            return mapper.readValue(file, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to read JSON file: " + filePath, e);
        }
    }

    public static Map<String, String> getData(String filePath, String caseKey) {
        try {
            File file = new File(filePath);
            JsonNode rootNode = mapper.readTree(file);
            JsonNode caseNode = rootNode.get(caseKey);

            if (caseNode == null) {
                throw new RuntimeException("❌ Case key not found in JSON: " + caseKey);
            }
            // Use LinkedHashMap to preserve order
//            Map<String, String> data = new HashMap<>();
            Map<String, String> data = new LinkedHashMap<>();
            Iterator<String> fieldNames = caseNode.fieldNames();
            while (fieldNames.hasNext()) {
                String field = fieldNames.next();
                data.put(field, caseNode.get(field).asText());
            }
            return data;

        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to read JSON file: " + filePath, e);
        }
    }
}
