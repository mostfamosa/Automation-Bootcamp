package week5.apiTestExe.infra;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequest {
    private String method;
    private String url;
    private Map<String, String> headers;
    private String body;

    private HttpRequest(String method, String url,
                        Map<String, String> headers, String body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
    }


    public static ResponseWrapper get(String url, Map<String, String> headers,
                                      Map<String, String> queryParams) throws IOException {

        // set query parameters
        if (queryParams != null) {
            String queryString = queryParams.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            url += "?" + queryString;
        }
        // create connection
        HttpURLConnection connection =
                (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        // set headers
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        // send request and handle response
        int status = connection.getResponseCode();
        String body = new String(connection.getInputStream().readAllBytes());
        Map<String, String> responseHeaders =
                connection.getHeaderFields().entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != null)
                        .collect(Collectors.toMap(Map.Entry::getKey, entry ->
                                entry.getValue().get(0)));
        return new ResponseWrapper<>(status, responseHeaders, body);
    }

    public static ResponseWrapper getWithoutHeader(String url,
                                                   Map<String, String> queryParams) throws IOException {
        // set query parameters
        if (queryParams != null) {
            String queryString = queryParams.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            url += "?" + queryString;
        }
        // create connection
        HttpURLConnection connection =
                (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        // send request and handle response
        int status = connection.getResponseCode();
        String body = new String(connection.getInputStream().readAllBytes());
        Map<String, String> responseHeaders =
                connection.getHeaderFields().entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != null)
                        .collect(Collectors.toMap(Map.Entry::getKey, entry ->
                                entry.getValue().get(0)));
        return new ResponseWrapper<>(status, responseHeaders, body);
    }

    public static ResponseWrapper getOnlyWithUrl(String url) throws IOException {
        // create connection
        HttpURLConnection connection =
                (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        // send request and handle response
        int status = connection.getResponseCode();
        String body = new String(connection.getInputStream().readAllBytes());
        Map<String, String> responseHeaders =
                connection.getHeaderFields().entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != null)
                        .collect(Collectors.toMap(Map.Entry::getKey, entry ->
                                entry.getValue().get(0)));
        return new ResponseWrapper<>(status, responseHeaders, body);
    }

    public static ResponseWrapper post(String url, Map<String, String> headers,
                                       String body) throws IOException {
        // create connection
        HttpURLConnection connection =
                (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        // set headers
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        // send request body
        OutputStream os = connection.getOutputStream();
        os.write(body.getBytes());
        os.flush();
        os.close();
        // handle response
        int status = connection.getResponseCode();
        String responseBody = "";
        if (status >= 200 && status < 300) {
            responseBody =
                    new String(connection.getInputStream().readAllBytes());
        } else {
            responseBody =
                    new String(connection.getErrorStream().readAllBytes());
        }

        Map<String, String> responseHeaders = new HashMap<>();
        for (Map.Entry<String, List<String>> entry :
                connection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                responseHeaders.put(entry.getKey(), entry.getValue().get(0));
            }
        }
        return new ResponseWrapper(status, responseHeaders, responseBody);
    }

    public static ResponseWrapper postOnlyWithUrl(String url) throws IOException {
        // create connection
        HttpURLConnection connection =
                (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // handle response
        int status = connection.getResponseCode();
        String responseBody = "";
        if (status >= 200 && status < 300) {
            responseBody =
                    new String(connection.getInputStream().readAllBytes());
        } else {
            responseBody =
                    new String(connection.getErrorStream().readAllBytes());
        }

        Map<String, String> responseHeaders = new HashMap<>();
        for (Map.Entry<String, List<String>> entry :
                connection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                responseHeaders.put(entry.getKey(), entry.getValue().get(0));
            }
        }
        return new ResponseWrapper(status, responseHeaders, responseBody);
    }
}


