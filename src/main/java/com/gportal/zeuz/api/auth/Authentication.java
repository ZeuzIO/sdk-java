package com.gportal.zeuz.api.auth;

import com.gportal.zeuz.api.call.exception.ApiException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Authentication {
    private static final String hashingAlgorithm = "sha512";
    private static final String javaHashingKey = "HmacSHA512";
    private static final String iso8601Format = "yyyy-MM-dd'T'HH:mm'Z'";

    private static String getJavaHashingKey() {
        return javaHashingKey;
    }

    private static String getHashingAlgorithm() {
        return hashingAlgorithm;
    }

    private static String getIso8601Format() {
        return iso8601Format;
    }

    private String applicationToken;
    private String secretToken;
    private String requestDate;

    public String getApplicationToken() {
        return applicationToken;
    }

    public void setApplicationToken(String applicationToken) {
        this.applicationToken = applicationToken;
    }

    public String getSecretToken() {
        return secretToken;
    }

    public void setSecretToken(String secretToken) {
        this.secretToken = secretToken;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public Authentication(String applicationToken, String secretToken)
        throws ApiException  {
        if(applicationToken == null || secretToken == null)
            throw new ApiException("Provided tokens are null or invalid, please check your credentials");

        this.applicationToken = applicationToken;
        this.secretToken = secretToken;
        this.requestDate = getIso8601Date();
    }

    private String getIso8601Date() {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat(getIso8601Format());
        dateFormat.setTimeZone(timeZone);

        return dateFormat.format(new Date());
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }

    private String calculateHmac(String signature)
            throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(getSecretToken().getBytes(), getJavaHashingKey());
        Mac mac = Mac.getInstance(getJavaHashingKey());
        mac.init(signingKey);

        return toHexString(mac.doFinal(signature.getBytes()));
    }

    public String generateSignature(String rawJson, URL url)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String stringToSign = null;
        List<String> parameterList = new ArrayList<>();

        if(!rawJson.isEmpty()) {
            JSONObject jsonObject = new JSONObject(rawJson);
            parameterList = getRequestParameters(jsonObject);
        }

        stringToSign = getStringToSign(parameterList, url);

        String signature = String.format(
                "%s\n%s\n%s\n%s",
                this.getApplicationToken(),
                getHashingAlgorithm(),
                getRequestDate(),
                stringToSign
        );

        return this.calculateHmac(signature);
    }

    private String getStringToSign(List<String> parameters, URL webRequestUrl) {
        StringBuilder stringBuilder = new StringBuilder();

        if(!parameters.isEmpty()) {
            for(String singleParameter : parameters) {
                if(singleParameter != null) {
                    stringBuilder.append(singleParameter);
                }
            }
        }

        stringBuilder.append(webRequestUrl.toString());

        return stringBuilder.toString();
    }

    /**
     *
     * @param jsonObject
     * @return @description
     *
     */
    private List<String> getRequestParameters(JSONObject jsonObject) {
        List<String> parameters = new ArrayList<String>();

        if(jsonObject.length() > 0) {
            Iterator<?> keys = jsonObject.keys();

            while(keys.hasNext()) {
                String key = keys.next().toString();

                if(jsonObject.get(key) instanceof JSONObject || jsonObject.get(key) instanceof JSONArray) {
                    parameters.addAll(
                        this.getRequestParameters((JSONObject) jsonObject.get(key))
                    );
                }
                else {
                    parameters.add(jsonObject.get(key).toString());
                }
            }
        }

        return parameters;
    }
}
