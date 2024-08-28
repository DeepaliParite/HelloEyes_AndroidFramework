package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;

public class TestDataUtil
{
    private static final String FILE_PATH = "src/test/resources/testData.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Faker faker = new Faker();

    //Method to generate and store credentials for different test cases
    public static void generateAndStoreCredentials(String userType)
    {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 12, true, true, true);
        try{
            ObjectNode rootNode = (ObjectNode) objectMapper.readTree(new File(FILE_PATH));
            ObjectNode userNode = (ObjectNode) rootNode.get(userType);
            userNode.put("email", email);
            userNode.put("password", password);
            objectMapper.writeValue(new File(FILE_PATH), rootNode);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Method to manually update email
    public static void updateEmail(String userType, String email)
    {
        try {
            ObjectNode rootNode = (ObjectNode) objectMapper.readTree(new File(FILE_PATH));
            ObjectNode userNode = (ObjectNode) rootNode.get(userType);
            userNode.put("email", email);
            objectMapper.writeValue(new File(FILE_PATH), rootNode);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Method to manually update password
    public static void updatePassword(String userType, String password)
    {
        try {
            ObjectNode rootNode = (ObjectNode) objectMapper.readTree(new File(FILE_PATH));
            ObjectNode userNode = (ObjectNode) rootNode.get(userType);
            userNode.put("password", password);
            objectMapper.writeValue(new File(FILE_PATH), rootNode);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Method to read user data
    public static JsonNode getUserData(String userType)
    {
        try {
            JsonNode rootNode = objectMapper.readTree(new File(FILE_PATH));
            return rootNode.get(userType);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}

