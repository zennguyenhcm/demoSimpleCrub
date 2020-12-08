package com.vcc.adtech.demosimplecrub.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Util {
    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private static Object formatResult(Object result) throws Throwable {
        if (result == null) {
            return null;
        }
        if (result instanceof String) {
            return result;
        }
        if (result instanceof JSONObject) {
            return result;
        }
        if (result instanceof JSONArray) {
            return result;
        }
        if (ClassUtils.isPrimitiveOrWrapper(result.getClass())) {
            return result;
        }

        JsonNode jsonNode = OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(result), JsonNode.class);
        // normalize - remove null fields
        stripNulls(jsonNode);
        String jsonString = OBJECT_MAPPER.writeValueAsString(jsonNode);

//        String jsonString = OBJECT_MAPPER.writeValueAsString(result);

        if (result instanceof Collection || result.getClass().isArray()) {
            return new JSONArray(jsonString);
        }
        return new JSONObject(jsonString);
    }

    private static void stripNulls(JsonNode node) {
        Iterator<JsonNode> it = node.iterator();
        while (it.hasNext()) {
            JsonNode child = it.next();
            if (child.isNull())
                it.remove();
            else
                stripNulls(child);
        }
    }

    public static ResponseEntity<String> formatResponse(HttpStatus statusCode, Object result, int status, String message) {
        try {
            JSONObject response = new JSONObject();
            response.put("code", statusCode.value());
            response.put("result", formatResult(result));
            response.put("status", status);
            response.put("message", message);
            return ResponseEntity.status(statusCode).body(response.toString());
        } catch (Throwable e) {
            logger.error(String.valueOf(e));
            JSONObject response = new JSONObject();
            response.put("code", statusCode);
            response.put("status", status);
            response.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.toString());
        }
    }

    String url = "https://kenh14.vn/midu-tung-loat-anh-xinh-nhu-nang-tho-tiet-lo-thai-do-phu-tinh-cu-va-chuyen-hen-ho-giua-luc-duoc-day-thuyen-voi-chi-dan-20201208112101793.chn";
	    Pattern p = Pattern.compile("[0-9]+.chn");
	    Matcher m = p.matcher(url);
	    if(m.find()) {
	     // System.out.println(m.group());
	    }
	    String id = m.group().toString();
	    Pattern pid = Pattern.compile("[0-9]+");
	    Matcher mid = pid.matcher(id);
	    if(mid.find()) {
		      System.out.println(mid.group());
		    }    
}
