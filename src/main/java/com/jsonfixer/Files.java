package com.jsonfixer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Files {
    private Preferences preferences;

    public Files() {
        preferences = App.getPreferences();
    }

    private static JsonObject readFile(String filePath) {
        // Read the JSON File
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the JSON file contents into a string
            String jsonString = reader.lines().collect(Collectors.joining());

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            JsonObject emptyObject = new JsonObject();
            emptyObject.addProperty("message", "Something went wrong.");
            return emptyObject;
        }
    }

    private static JsonObject editFile(JsonObject jsonFile) {
        // Make Changes
        jsonFile.addProperty("description", "234 Servers Mining Crypto");
        jsonFile.remove("seller_fee_basis_points");
        jsonFile.remove("collection");

        JsonObject properties = jsonFile.getAsJsonObject("properties");
        properties.remove("creators");

        return jsonFile;
    }

    private String createFile(JsonObject jsonFile, String filePath) {
        String regex = ".*\\\\(\\w+).json";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(filePath);

        if (matcher.find()) {
            filePath = matcher.group(1);
            String fileLocation = preferences.get("fileLocation", "No file found.");
            try (FileWriter fileWriter = new FileWriter(fileLocation + "/" + filePath + ".json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonString = gson.toJson(jsonFile);
                fileWriter.write(jsonString);
                return jsonString;
            } catch (IOException e) {
                e.printStackTrace();
                ;
            }
        }
        return "Something went wrong.";
    }

    public String changeOne(String filePath) {
        JsonObject beforeFile = readFile(filePath);
        JsonObject afterFile = editFile(beforeFile);
        String createdFile = createFile(afterFile, filePath);
        return createdFile;
    }
}
