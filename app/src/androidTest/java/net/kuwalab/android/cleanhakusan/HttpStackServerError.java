package net.kuwalab.android.cleanhakusan;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpStackServerError implements HttpStack {
    @Override
    public HttpResponse performRequest(final Request request, final Map additionalHeaders)
        throws IOException, AuthFailureError {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        HttpResponse response = new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 500,
            "Internal Server Error"));
        List<Header> responseHeaders = getHeaders();
        response.setHeaders(responseHeaders.toArray(new Header[responseHeaders.size()]));
        return response;
    }

    private List<Header> getHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Date", "2015/03/01"));
        headers.add(new BasicHeader("Server", "Dummy Server"));

        return headers;
    }
}
