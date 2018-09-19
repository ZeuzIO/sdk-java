import com.gportal.zeuz.api.call.Api;
import com.gportal.zeuz.api.call.Endpoint;
import com.gportal.zeuz.api.call.Version;
import com.gportal.zeuz.api.call.entity.ApiResponse;
import com.gportal.zeuz.api.call.exception.ApiException;
import com.gportal.zeuz.api.entity.Service;
import com.gportal.zeuz.api.model.request.AllocateRequest;
import com.gportal.zeuz.api.model.response.*;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiTest {
    private String _testAppToken = "12354567890";
    private String _testSecretToken = "12354567890";

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.Test
    void configTest() throws Exception {
        Api api = Api.fromConfig();

        assertNotNull(api);
    }

    @org.junit.jupiter.api.Test
    void requestList() throws Exception {
        Api api = Api.fromConfig();

        ApiResponse apiResponse =
                api.call(Endpoint.GET_LIST, null, new HashMap<String, Object>() {{
                    put("gameProfileId", "12345");
                    put("serverGroupId", "12345");
                }});

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                List<Service> serviceList = ((ServiceListResponse)restResponseInterface).getServiceList();

                if(!serviceList.isEmpty()) {
                    for(Service service : serviceList) {
                        System.out.println("####################################");
                        System.out.println("Service-Id: " + service.getServiceId());
                        System.out.println("External IP: " + service.getExternalIp());
                        System.out.println("Primary IP:" + service.getPrimaryIp());
                        System.out.println("####################################");
                    }
                }
                else {
                    System.out.println("####################################");
                    System.out.println("No services were found");
                    System.out.println("####################################");
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    void provideService() throws Exception {
        Api api = Api.fromConfig();

        ApiResponse apiResponse =
                api.call(Endpoint.PROVIDE_SERVICE, null, new HashMap<String, Object>() {{
                    put("gameProfileId", "12345");
                    put("serverGroupId", "12345");
                }});

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                ProvideResponse provideResponse = ((ProvideResponse)restResponseInterface);

                if(provideResponse.isSuccess()) {
                    System.out.println("####################################");
                    System.out.println("Service-Id: " + provideResponse.getService().getServiceId());
                    System.out.println("External IP: " + provideResponse.getService().getExternalIp());
                    System.out.println("Primary IP:" + provideResponse.getService().getPrimaryIp());
                    System.out.println("####################################");
                }
                else {
                    System.out.println("####################################");
                    System.out.println("Service is propably not ready");
                    System.out.println("Error: " + provideResponse.getSimpleRestResponse().getError());
                    System.out.println("####################################");
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    void reserveService() throws Exception {
        Api api = Api.fromConfig();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serviceId", "123");

        ApiResponse apiResponse =
                api.call(Endpoint.RESERVE_SERVICE, jsonObject, null);

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                ProvideResponse provideResponse = ((ProvideResponse)restResponseInterface);

                if(provideResponse.isSuccess()) {
                    System.out.println("####################################");
                    System.out.println("Service-Id: " + provideResponse.getService().getServiceId());
                    System.out.println("External IP: " + provideResponse.getService().getExternalIp());
                    System.out.println("Primary IP:" + provideResponse.getService().getPrimaryIp());
                    System.out.println("####################################");
                }
                else {
                    System.out.println("####################################");
                    System.out.println("Error: " + provideResponse.getSimpleRestResponse().getError());
                    System.out.println("####################################");
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    void allocateService() throws Exception {
        Api api = Api.fromConfig();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gameProfileId", "123");
        jsonObject.put("serverGroupId", "123");

        ApiResponse apiResponse =
                api.call(Endpoint.ALLOCATE_SERVICE, jsonObject, null);

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                AllocateResponse allocateResponse = ((AllocateResponse)restResponseInterface);

                if(allocateResponse.isSuccess()) {
                    System.out.println("####################################");
                    System.out.println("Service-Id: " + allocateResponse.getService().getServiceId());
                    System.out.println("External IP: " + allocateResponse.getService().getExternalIp());
                    System.out.println("Primary IP:" + allocateResponse.getService().getPrimaryIp());
                    System.out.println("####################################");
                }
                else {
                    System.out.println("####################################");
                    System.out.println("Error: " + allocateResponse.getSimpleRestResponse().getError());
                    System.out.println("####################################");
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    void getServiceInfo() throws Exception {
        Api api = Api.fromConfig();

        ApiResponse apiResponse =
                api.call(Endpoint.GET_SERVICE_INFO, null, new HashMap<String, Object>() {{
                    put("serviceId", "123");
                }});

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                ServiceInfoResponse serviceInfoResponse = ((ServiceInfoResponse)restResponseInterface);

                if(serviceInfoResponse.isSuccess()) {
                    System.out.println("####################################");
                    System.out.println("Service-Id: " + serviceInfoResponse.getService().getServiceId());
                    System.out.println("External IP: " + serviceInfoResponse.getService().getExternalIp());
                    System.out.println("Primary IP:" + serviceInfoResponse.getService().getPrimaryIp());
                    System.out.println("####################################");
                }
                else {
                    System.out.println("####################################");
                    System.out.println("Error: " + serviceInfoResponse.getSimpleRestResponse().getError());
                    System.out.println("####################################");
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    void getServiceStatus() throws Exception {
        Api api = Api.fromConfig();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serviceId", "123");

        ApiResponse apiResponse =
                api.call(Endpoint.GET_SERVICE_STATUS, null, null);

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                ServiceStatusResponse serviceStatusResponse = ((ServiceStatusResponse)restResponseInterface);

                if(serviceStatusResponse.isSuccess()) {
                    System.out.println("####################################");
                    System.out.println("Status: " + serviceStatusResponse.getStatus());
                    System.out.println("####################################");
                }
                else {
                    System.out.println("####################################");
                    System.out.println("Error: " + serviceStatusResponse.getSimpleRestResponse().getError());
                    System.out.println("####################################");
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    void unallocateService() throws Exception {
        Api api = Api.fromConfig();

        ApiResponse apiResponse =
                api.call(Endpoint.GET_SERVICE_STATUS, null, new HashMap<String, Object>() {{
                    put("serviceId", "123");
                }});

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                UnallocateResponse unallocateResponse = ((UnallocateResponse)restResponseInterface);

                if(unallocateResponse.isSuccess()) {
                    System.out.println("####################################");
                    System.out.println("Status: " + unallocateResponse.getStatus());
                    System.out.println("####################################");
                }
                else {
                    System.out.println("####################################");
                    System.out.println("Error: " + unallocateResponse.getSimpleRestResponse().getError());
                    System.out.println("####################################");
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    void startService() throws Exception {
        Api api = Api.fromConfig();

        ApiResponse apiResponse =
                api.call(Endpoint.START_SERVICE, null, new HashMap<String, Object>() {{
                    put("serviceId", "123");
                }});

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                SimpleRestResponse simpleRestResponse = ((SimpleRestResponse)restResponseInterface);

                if(simpleRestResponse.isSuccess()) {
                    System.out.println("####################################");
                    System.out.println("Starting the service successfully");
                    System.out.println("####################################");
                }
                else {
                    System.out.println("####################################");
                    System.out.println("Could not start the service");
                    System.out.println("####################################");
                }
            }
        }
    }


    @org.junit.jupiter.api.Test
    void restartService() throws Exception {
        Api api = Api.fromConfig();

        ApiResponse apiResponse =
                api.call(Endpoint.RESTART_SERVICE, null, new HashMap<String, Object>() {{
                    put("serviceId", "123");
                }});

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                SimpleRestResponse simpleRestResponse = ((SimpleRestResponse)restResponseInterface);

                if(simpleRestResponse.isSuccess()) {
                    System.out.println("####################################");
                    System.out.println("Restart the service successfully");
                    System.out.println("####################################");
                }
                else {
                    System.out.println("####################################");
                    System.out.println("Could not restart the service");
                    System.out.println("####################################");
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    void stopService() throws Exception {
        Api api = Api.fromConfig();

        ApiResponse apiResponse =
                api.call(Endpoint.STOP_SERVICE, null, new HashMap<String, Object>() {{
                    put("serviceId", "123");
                }});

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                SimpleRestResponse simpleRestResponse = ((SimpleRestResponse)restResponseInterface);

                if(simpleRestResponse.isSuccess()) {
                    System.out.println("####################################");
                    System.out.println("Stopping the service successfully");
                    System.out.println("####################################");
                }
                else {
                    System.out.println("####################################");
                    System.out.println("Could not stop the service");
                    System.out.println("####################################");
                }
            }
        }
    }

    @org.junit.jupiter.api.Test
    void reinstallService() throws Exception {
        Api api = Api.fromConfig();

        ApiResponse apiResponse =
                api.call(Endpoint.REINSTALL_SERVICE, null, new HashMap<String, Object>() {{
                    put("serviceId", "123");
                }});

        if(apiResponse.isValidJSON() && apiResponse.isOK()) {
            if(apiResponse.isModel()) {
                RestResponseInterface restResponseInterface = apiResponse.getRestResponseInterface();
                SimpleRestResponse simpleRestResponse = ((SimpleRestResponse)restResponseInterface);

                if(simpleRestResponse.isSuccess()) {
                    System.out.println("####################################");
                    System.out.println("Reinstalling the service successfully");
                    System.out.println("####################################");
                }
                else {
                    System.out.println("####################################");
                    System.out.println("Could not reinstall the service");
                    System.out.println("####################################");
                }
            }
        }
    }
}