package week5.apiTestExe.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import week5.apiTestExe.infra.ResponseWrapper;

public class ValidateJson {
    public static <T> void validate(Class<T> clz, String jsonToCheck, ResponseWrapper<T> responseWrapper) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); // Allow unquoted field names

        try {
            responseWrapper.setData(objectMapper.readValue(jsonToCheck, clz));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing JSON: \n"+"We are trying to parse the json to class = "+clz+"\n*********************************\n"+e.getMessage());
        }
    }
}
