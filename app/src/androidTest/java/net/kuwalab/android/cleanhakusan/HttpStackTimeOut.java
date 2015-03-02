package net.kuwalab.android.cleanhakusan;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpStackTimeOut implements HttpStack {
    @Override
    public HttpResponse performRequest(final Request request, final Map additionalHeaders)
        throws IOException, AuthFailureError {
        try {
            // タイムアウト
            Thread.sleep(25000);
        } catch (InterruptedException e) {
        }
        HttpResponse response = new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 200, "OK"));
        List<Header> responseHeaders = getHeaders();
        response.setHeaders(responseHeaders.toArray(new Header[responseHeaders.size()]));
        response.setEntity(createEntity(request));
        return response;
    }

    private List<Header> getHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Date", "2015/03/01"));
        headers.add(new BasicHeader("Server", "Dummy Server"));

        return headers;
    }

    private HttpEntity createEntity(Request request) throws UnsupportedEncodingException {
        if (request instanceof JsonObjectRequest) {
            return new StringEntity("{\"foo\": \"bar\"}");
        }
        return new StringEntity("");
    }

}
