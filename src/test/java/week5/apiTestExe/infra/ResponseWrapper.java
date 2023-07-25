package week5.apiTestExe.infra;

import lombok.Data;

import java.util.Map;
@Data
public class ResponseWrapper<T> {
    private int status;
    private Map<String, String> responseHeaders;
    private T data;

    public ResponseWrapper(int status, Map<String, String> responseHeaders, T data) {
        this.status = status;
        this.responseHeaders = responseHeaders;
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResponse:\n" +
                "status=" + status +
                "\nresponseHeaders=" + responseHeaders +
                "\ndata=" + data ;
    }
}
