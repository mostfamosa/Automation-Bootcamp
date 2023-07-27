package week5.apiTestExe.infra;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import week5.apiTestExe.logic.HttpMethod;
import week5.apiTestExe.utils.ExecuteRequest;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequest {

    //request without param & header
    public static <T> ResponseWrapper<T> request(HttpMethod httpMethod, String url, Class<T> clz) {
        return request(httpMethod, url, null, null, clz);
    }

    // Overloaded method for request without header
    public static <T> ResponseWrapper<T> request(HttpMethod httpMethod, String url, Map<String, String> queryParams, Class<T> clz) {
        return request(httpMethod, url, queryParams, null,  clz);
    }

    // Overloaded method for request without param
    public static <T> ResponseWrapper<T> requestWithoutParam(HttpMethod httpMethod, String url, Map<String, String> headers, Class<T> clz) {
        return request(httpMethod, url, null, headers,  clz);
    }

    public static <T> ResponseWrapper<T> request(HttpMethod httpMethod, String url, Map<String, String> queryParams, Map<String, String> headers, Class<T> clz) {
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
            switch (httpMethod) {
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
                    ExecuteRequest.execute(httpClient, httpPost, responseWrapper, clz);
                }
                case GET -> {
                    // Create an instance of HttpPost with the URL
                    HttpGet httpGet = new HttpGet(url);
                    // Set the headers of the request
                    if (headers != null) {
                        for (String key : headers.keySet()) {
                            httpGet.setHeader(key, headers.get(key));
                        }
                    }
                    // Execute
                    ExecuteRequest.execute(httpClient, httpGet, responseWrapper, clz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseWrapper;
    }

}


