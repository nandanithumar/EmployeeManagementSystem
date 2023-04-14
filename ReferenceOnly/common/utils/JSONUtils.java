package com.egovja.tatransform.licencingmanagement.common.utils;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.licenceapplicationmanagement.licence.dto.info.LicenceRouteDetailsInfo;
import com.egovja.tatransform.licenceapplicationmanagement.licence.dto.info.LicenceRouteDirectionDetailsInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.OperationFailedException;
import io.astefanutti.metrics.aspectj.Metrics;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Metrics(registry = "JSONUtils")
public final class JSONUtils {

    private JSONUtils() {
    }

    @Timed(name = "convertStringToHashMap")
    public static HashMap<String, String> convertStringToHashMap(String jsonString) throws OperationFailedException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
        };
        try {
            return mapper.readValue(jsonString, typeRef);
        } catch (IOException e) {
            throw new OperationFailedException("Error while convertStringToHashMap", e);
        }
    }

    @Timed(name = "convertHashMapToString")
    public static String convertHashMapToString(Map<String, String> hashMap) throws OperationFailedException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(hashMap);
        } catch (IOException e) {
            throw new OperationFailedException("Error while convertHashMapToString", e);
        }
    }

    @Timed(name = "convertLicenceRouteDirectionDetailsInfoToString")
    public static String convertLicenceRouteDirectionDetailsInfoToString(LicenceRouteDirectionDetailsInfo ob) throws OperationFailedException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(ob);
        } catch (IOException e) {
            throw new OperationFailedException("Error while convertLicenceRouteDirectionDetailsInfoToString", e);
        }

    }

    @Timed(name = "convertStringToLicenceRouteDirectionDetailsInfo")
    public static LicenceRouteDirectionDetailsInfo convertStringToLicenceRouteDirectionDetailsInfo(String jsonString) throws OperationFailedException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<LicenceRouteDirectionDetailsInfo> typeRef = new TypeReference<LicenceRouteDirectionDetailsInfo>() {
        };
        try {
            return mapper.readValue(jsonString, typeRef);
        } catch (IOException e) {
            throw new OperationFailedException("Error while convertStringToLicenceRouteDirectionDetailsInfo", e);
        }
    }

    @Timed(name = "convertLicenceRouteDetailsInfoToString")
    public static String convertLicenceRouteDetailsInfoToString(LicenceRouteDetailsInfo ob) throws OperationFailedException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(ob);
        } catch (IOException e) {
            throw new OperationFailedException("Error while convertLicenceRouteDetailsInfoToString", e);
        }
    }

    @Timed(name = "convertStringToLicenceRouteDetailsInfo")
    public static LicenceRouteDetailsInfo convertStringToLicenceRouteDetailsInfo(String jsonString) throws OperationFailedException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<LicenceRouteDetailsInfo> typeRef = new TypeReference<LicenceRouteDetailsInfo>() {
        };
        try {
            return mapper.readValue(jsonString, typeRef);
        } catch (IOException e) {
            throw new OperationFailedException("Error while convertStringToLicenceRouteDetailsInfo", e);
        }
    }
}
