package week5.apiTestExe.logic.response;

import lombok.Data;

import java.util.Map;
@Data
public class ResponseWrapper<T> {
    private int status;
    private Map<String, String> responseHeaders;
    private T data;

    public ResponseWrapper() {
    }

    @Override
    public String toString() {
        return "HttpResponse:\n" +
                "status=" + status +
                "\nresponseHeaders=" + responseHeaders +
                "\ndata=" + data ;
    }
}
