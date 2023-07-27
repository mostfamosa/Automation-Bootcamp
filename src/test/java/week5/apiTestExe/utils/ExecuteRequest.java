package week5.apiTestExe.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import week5.apiTestExe.infra.ResponseWrapper;

import java.io.IOException;

public class ExecuteRequest {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> ResponseWrapper<T> execute(CloseableHttpClient httpClient, ClassicHttpRequest httpMethod, ResponseWrapper<T> responseWrapper, Class<T> clz) {

        // Execute the request and get the response
        try (CloseableHttpResponse response = httpClient.execute(httpMethod)) {
            // Get the response status code
            responseWrapper.setStatus(response.getCode());

            // Get the response entity
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);

            responseWrapper.setData(objectMapper.readValue(responseBody, clz));
            return responseWrapper;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
