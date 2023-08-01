package week5.apiTestExe.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateJson {
    public static <T> T validate(Class<T> clz, String jsonToCheck) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); // Allow unquoted field names

        try {
            return objectMapper.readValue(jsonToCheck, clz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing JSON: \n"
                    + "We are trying to parse the json to class = " + clz +
                    "\n*********************************\n" + e.getMessage());
        }
    }
}
