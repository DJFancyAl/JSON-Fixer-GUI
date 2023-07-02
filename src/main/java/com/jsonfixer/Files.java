package com.jsonfixer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;

public class Files {

    public static void readFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            // Read the JSON file contents into a string
            StringBuilder stringBuilder = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                stringBuilder.append((char) character);
            }

            Gson gson = new Gson();
            String jsonString = stringBuilder.toString();
            JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

            System.out.print(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println("Reading...");
        readFile("C:\\Users\\ablue\\Desktop\\Scripts\\For_Aaron\\JSON Converter\\Files\\151.json");
    }

}
