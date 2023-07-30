package week4.tzahiGame.infra;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import week4.tzahiGame.response.ResponseWrapper;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class request {
    public static <T> ResponseWrapper<T> requestPost(String url, Class<T> clz) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();


        // Create an instance of CloseableHttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            {
                // Create an instance of HttpPost with the URL
                HttpPost httpPost = new HttpPost(url);

                // Execute
                execute(httpClient, httpPost, responseWrapper, clz);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error of creating an instance of CloseableHttpClient:\n" + e);
        }
        return responseWrapper;
    }

    public static <T> ResponseWrapper<T> requestGet(String url, Map<String, String> queryParams, Class<T> clz) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();

        // Set the param of the request if found!
        if (queryParams != null) {
            String queryString = queryParams.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            url += "?" + queryString;
        }

        // Create an instance of CloseableHttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            {
                // Create an instance of HttpPost with the URL
                HttpGet httpGet = new HttpGet(url);

                // Execute
                execute(httpClient, httpGet, responseWrapper, clz);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error of creating an instance of CloseableHttpClient:\n" + e);
        }
        return responseWrapper;
    }

    public static <T> void execute(CloseableHttpClient httpClient, ClassicHttpRequest httpMethod, ResponseWrapper<T> responseWrapper, Class<T> clz) {

        // Execute the request and get the response
        try (CloseableHttpResponse response = httpClient.execute(httpMethod)) {
            // Get the response status code
            responseWrapper.setStatus(response.getCode());

            // Get the response entity
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);

            // Validate Json
            validate(clz, responseBody, responseWrapper);

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void validate(Class<T> clz, String jsonToCheck, ResponseWrapper<T> responseWrapper) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); // Allow unquoted field names

        try {
            responseWrapper.setData(objectMapper.readValue(jsonToCheck, clz));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing JSON: \n" + "We are trying to parse the json to class = " + clz + "\n*********************************\n" + e.getMessage());
        }
    }
}
