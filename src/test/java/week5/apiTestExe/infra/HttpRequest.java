package week5.apiTestExe.infra;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import week5.apiTestExe.entities.enums.HttpMethods;
import week5.apiTestExe.utils.ValidateJson;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequest {

    //request without param & header
    public static <T> ResponseWrapper<T> request(HttpMethods httpMethods, String url, Class<T> clz) {
        return request(httpMethods, url, null, null, clz);
    }

    // Overloaded method for request without header
    public static <T> ResponseWrapper<T> request(HttpMethods httpMethods, String url, Map<String, String> queryParams, Class<T> clz) {
        return request(httpMethods, url, queryParams, null, clz);
    }

    // Overloaded method for request without param
    public static <T> ResponseWrapper<T> requestWithoutParam(HttpMethods httpMethods, String url, Map<String, String> headers, Class<T> clz) {
        return request(httpMethods, url, null, headers, clz);
    }

    public static <T> ResponseWrapper<T> request(HttpMethods httpMethods, String url, Map<String, String> queryParams, Map<String, String> headers, Class<T> clz) {
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
            switch (httpMethods) {
                case POST -> {
                    // Create an instance of HttpPost with the URL
                    HttpPost httpPost = new HttpPost(url);
                    // Set the headers of the request
                    if (headers != null) {
                        for (String key : headers.keySet()) {
                            httpPost.setHeader(key, headers.get(key));
                        }
                    }
                    // Execute
                    execute(httpClient, httpPost, responseWrapper, clz);
                }
                case GET -> {
                    // Create an instance of HttpGet with the URL
                    HttpGet httpGet = new HttpGet(url);
                    // Set the headers of the request
                    if (headers != null) {
                        for (String key : headers.keySet()) {
                            httpGet.setHeader(key, headers.get(key));
                        }
                    }
                    // Execute
                    execute(httpClient, httpGet, responseWrapper, clz);
                }
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
            responseWrapper.setData(ValidateJson.validate(clz, responseBody));

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

}


