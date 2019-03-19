package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonToObjectConverter {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Object readValue(String jsonString, Class valueType){
        try {
            return objectMapper.readValue(jsonString, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
