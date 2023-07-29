package week5.apiTestExe.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateJson {
    public static <T> boolean validate(Class<T> clz, String jsonToCheck) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); // Allow unquoted field names

        try {
            objectMapper.readValue(jsonToCheck, clz);
            return true;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing JSON: \n"+e.getMessage());
        }

    }
}
