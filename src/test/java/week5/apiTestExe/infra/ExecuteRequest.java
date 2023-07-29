package week5.apiTestExe.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import week5.apiTestExe.logic.response.ResponseWrapper;
import week5.apiTestExe.utils.ValidateJson;

import java.io.IOException;

class ExecuteRequest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void execute(CloseableHttpClient httpClient, ClassicHttpRequest httpMethod, ResponseWrapper<T> responseWrapper, Class<T> clz) {

        // Execute the request and get the response
        try (CloseableHttpResponse response = httpClient.execute(httpMethod)) {
            // Get the response status code
            responseWrapper.setStatus(response.getCode());

            // Get the response entity
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);

            // Validate Json
            if (ValidateJson.validate(clz, responseBody))
                responseWrapper.setData(objectMapper.readValue(responseBody, clz));

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
