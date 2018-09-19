package com.gportal.zeuz.api.call;

import com.gportal.zeuz.api.auth.Authentication;
import com.gportal.zeuz.api.call.entity.ApiResponse;
import com.gportal.zeuz.api.call.exception.ApiException;
import com.gportal.zeuz.api.model.request.RestRequestInterface;
import com.gportal.zeuz.api.auth.Config;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Api {
    private static final String authenticationType = "ZEUZ-HMAC-SHA512";
    private static final String headerDate = "X-ZEUZ-DATE";
    private Authentication authentication;

    public static Api fromConfig()
            throws ApiException, IOException {
        Config config = Config.getInstance();

        String appToken = config.fetch("api", "appToken");
        String secretToken = config.fetch("api", "secretToken");

        return new Api(appToken, secretToken);
    }

    public Api(String applicationToken, String secretToken)
        throws ApiException {
        this.authentication = new Authentication(applicationToken, secretToken);
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    private String getAuthenticationHeader(byte[] rawJson, URL url) throws Exception {
        String signature = this.getAuthentication().generateSignature(
                new String(rawJson, StandardCharsets.UTF_8), url
        );

        return
            String.format("%s %s:%s", authenticationType, getAuthentication().getApplicationToken(), signature);
    }

    public ApiResponse requestServiceList(Integer gameprofileId, Integer servergroupId) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>() {{
           put("gameProfileId", gameprofileId);
           put("serverGroupId", servergroupId);
        }};

        return
            this.call(Endpoint.GET_LIST, null, parameters);
    }

    public ApiResponse callWithModel(Endpoint endpoint, RestRequestInterface object, Map<String, Object> parameter)
    throws Exception {

        if(endpoint.getRequestClass() != null) {
            String response = object.getJsonRequest();

            return call(endpoint, new JSONObject(response), parameter);
        }
        else
            throw new ApiException("Model not implemented or not supported!");

    }

    public ApiResponse call(Endpoint endpoint, JSONObject jsonObject, Map<String, Object> parameter)
            throws Exception {
        URL url = new URL(StringSubstitutor.replace(endpoint.getUrl(), parameter));

        URLConnection urlConnection = url.openConnection();
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
        httpsURLConnection.setRequestMethod(endpoint.getMethod());
        httpsURLConnection.setDoOutput(true);

        byte[] bytes = new byte[0];
        httpsURLConnection.setFixedLengthStreamingMode(0);

        if (endpoint.getMethod().equals("POST") && jsonObject != null) {
            httpsURLConnection.setDoInput(true);

            bytes = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;

            httpsURLConnection.setFixedLengthStreamingMode(length);
        }

        System.out.println(this.getAuthenticationHeader(bytes, url));
        httpsURLConnection.setRequestProperty(headerDate, this.getAuthentication().getRequestDate());
        httpsURLConnection.setRequestProperty("Authorization", this.getAuthenticationHeader(bytes, url));
        httpsURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpsURLConnection.setRequestProperty("Content-Length", "" + bytes.length + "");
        httpsURLConnection.setRequestProperty("Connection", "close");
        httpsURLConnection.setInstanceFollowRedirects(false);

        try (OutputStream outputStream = httpsURLConnection.getOutputStream()) {
            outputStream.write(bytes);
        }

        httpsURLConnection.connect();

        switch(httpsURLConnection.getResponseCode()) {
            case 200:
                InputStream inputStream = httpsURLConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                String result = scanner.hasNext() ? scanner.next() : "";

                switch (httpsURLConnection.getResponseCode()) {
                    case HttpsURLConnection.HTTP_OK:
                        break;
                }
                inputStream.close();

                ApiResponse apiResponse = new ApiResponse(httpsURLConnection.getResponseCode(), result, endpoint);
                httpsURLConnection.disconnect();

                return apiResponse;


            case 401:
                httpsURLConnection.disconnect();

                throw
                    new ApiException("Authentication failed, Responsecode: " + httpsURLConnection.getResponseCode() + "!");

            default:
                httpsURLConnection.disconnect();

                throw
                    new ApiException("Request failed, please check your request, Repoonsecode: " + httpsURLConnection.getResponseCode() + "!");
        }
    }
}
