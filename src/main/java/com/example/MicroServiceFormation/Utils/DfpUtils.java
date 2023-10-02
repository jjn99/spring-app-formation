package com.example.MicroServiceFormation.Utils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class DfpUtils {

    public DfpUtils() {
    }
    
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}",httpStatus );
    }
    
    public static JSONArray getJsonArrayFromString(String data) throws JSONException{
        JSONArray jsonArray = new JSONArray(data);
        return jsonArray;
    }
    
    public static Map<String,Object> getMapFromJson(String data){
        if(!Strings.isNullOrEmpty(data))
            return new Gson().fromJson(data, new TypeToken<Map<String,Object>>(){}.getType());
        return new HashMap<>();
    }
    
}
