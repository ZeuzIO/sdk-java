package com.gportal.zeuz.api.call;

import com.gportal.zeuz.api.call.exception.ResponseException;
import com.gportal.zeuz.api.model.request.*;
import com.gportal.zeuz.api.model.response.*;
import org.json.JSONObject;

public enum Endpoint {
    @EndpointDescription(Description = "fetch a list of services")
    GET_LIST("/service/listServices/${gameProfileId}/${serverGroupId}", "GET", ServiceListResponse.class),

    @EndpointDescription(Description = "fetch information about a service")
    GET_SERVICE_INFO("/service/service/${serviceId}", "GET", ServiceInfoResponse.class),

    @EndpointDescription(Description = "fetch the status of a service")
    GET_SERVICE_STATUS("/service/status/${serviceId}", "GET", ServiceStatusResponse.class),

    @EndpointDescription(Description = "provide a service (searches and reserves a services)")
    PROVIDE_SERVICE("/service/provide/${gameProfileId}/${serverGroupId}", "GET", ProvideResponse.class),

    @EndpointDescription(Description = "reserve a service")
    RESERVE_SERVICE("/service/reserve", "POST", null, ReserveRequest.class),

    @EndpointDescription(Description = "unreserve a service")
    UNRESERVE_SERVICE("/service/unreserve", "POST", null, UnreserveRequest.class),

    @EndpointDescription(Description = "allocate a service")
    ALLOCATE_SERVICE("/service/allocate", "POST", null, AllocateRequest.class),

    @EndpointDescription(Description = "unallocate a service")
    UNALLOCATE_SERVICE("/service/unallocate/${serviceId}", "GET"),

    @EndpointDescription(Description = "start a service")
    START_SERVICE("/service/start/${serviceId}", "GET"),

    @EndpointDescription(Description = "stop a service")
    STOP_SERVICE("/service/stop/${serviceId}", "GET"),

    @EndpointDescription(Description = "restart a service")
    RESTART_SERVICE("/service/restart/${serviceId}", "GET"),

    @EndpointDescription(Description = "reinstall a service")
    REINSTALL_SERVICE("/service/reinstall/${serviceId}", "GET"),

    @EndpointDescription(Description = "submit the ccu of a service")
    SUBMIT_STATISTICS("/statistics/ccu/${serviceId}", "POST", null, SubmitStatisticRequest.class),

    @EndpointDescription(Description = "request the ccu of a service")
    REQUEST_STATISTICS("/statistics/ccu/${serviceId}", "GET");

    private static final String apiUrl = "https://api.zeuz.io/%d";
    // private static final String apiUrl = "https://api.zeuz.io/staging";

    private String url;
    private static Version version;
    static {
        version = Version.V2_LIVE;
    }
    private String method;
    private Class<? extends RestResponseInterface> responseClass = null;
    private Class<? extends RestRequestInterface> requestClass = null;
    private RestResponseInterface restResponseInterface;
    private RestRequestInterface restRequestInterface;

    /**
     *
     * @param jsonObject json response of a specific call
     * @return
     */
    public RestResponseInterface parseResponse(JSONObject jsonObject) throws ResponseException {
        try {
            restResponseInterface = responseClass.newInstance();
            restResponseInterface._parseFromJSON(jsonObject);

            return restResponseInterface;
        }
        catch (InstantiationException | IllegalAccessException instanceException) {
            instanceException.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @return String
     */
    public String parseRequest() {
        if(requestClass == null)
            return "{}";

        try {
            restRequestInterface = requestClass.newInstance();
            return restRequestInterface.getJsonRequest();
        }
        catch (InstantiationException | IllegalAccessException instanceException) {
            instanceException.printStackTrace();
        }

        return null;
    }

    public String getUrl() {
        return url;
    }

    public static Version getVersion() {
        return version;
    }

    public String getMethod() {
        return method;
    }

    public Class<? extends RestRequestInterface> getRequestClass() {
        return requestClass;
    }

    public Class<? extends RestResponseInterface> getResponseClass() {
        return responseClass;
    }

    public static void setVersion(Version version) {
        if(version.getVersionNumber() > 0 || version.getUriPart() != null) {
            Endpoint.version = version;
        }
    }

    public void populateVersionToUri(String url) {
        if(version.getCustomPath() != null) {
            this.url = String.format("%s/%s/%s", apiUrl, getVersion().getCustomPath(), url);
        }
        else {
            this.url = String.format("%s/v%d/%s", apiUrl, getVersion().getVersionNumber(), url);
        }

    }

    Endpoint(String url, String method) {
        populateVersionToUri(url);

        this.method = method;
    }

    Endpoint(String url, String method, Class<? extends RestResponseInterface> responseClass) {
        populateVersionToUri(url);

        this.method = method;
        this.responseClass = responseClass;
    }

    Endpoint(String url, String method, Class<? extends RestResponseInterface> responseClass, Class<? extends RestRequestInterface> requestClass) {
        populateVersionToUri(url);

        this.method = method;
        this.responseClass = responseClass;
        this.requestClass = requestClass;
    }
}
