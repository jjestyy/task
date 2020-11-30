package com.github.jjestyy.testwork.task.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class GkshbezopsoxrValidationService {
    public String GetValidatedGkshbezopsoxrJSON(String spec, String oldForm, String newForm, String mark)  {
        JSONObject specJson;
        try {
            specJson = new JSONObject(spec);
            Map<String,String> out = new HashMap<>();
            System.out.println(specJson);
            out = findInJSONObject(specJson, out, "item_id");
            System.out.println(out);

        } catch (JSONException e) {
            throw new RuntimeException("Bad spec format, JSON parsing error");
        }
        return null;
    }

    public Map<String,String> findInJSONObject(JSONObject json , Map<String,String> out, String searchId) throws JSONException{
        Iterator<String> keys = json.keys();
        while(keys.hasNext()){
            String key = keys.next();
            out = getValueFromJSON(out, searchId, key, json.get(key));
        }
        return out;
    }

    private Map<String, String> findInJSONArray(JSONArray array, Map<String, String> out, String searchId) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            out = getValueFromJSON(out, searchId, String.valueOf(i), array.get(i));
        }
        return out;
    }

    private Map<String, String> getValueFromJSON(Map<String, String> out, String searchId, String key, Object nextValue) throws JSONException {
        String val = null;
        if (nextValue instanceof JSONObject) {
            out = findInJSONObject((JSONObject) nextValue,out, searchId);
        }
        if (nextValue instanceof JSONArray) {
            out = findInJSONArray((JSONArray) nextValue,out, searchId);
        }
        if(nextValue instanceof String) {
            val = (String) nextValue;
        }
        if(val != null && key.equals(searchId)){
            out.put(val, key);
        }
        return out;
    }

}
