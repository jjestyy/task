package com.github.jjestyy.testwork.task.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JsonWorkService {
    
    public void replaceJsonObject(String index, JSONObject data, JSONObject objectToPut) throws JSONException {
        if(objectToPut != null) {
            Iterator<String> keys = data.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (key.equals(index)) {
                    keys.remove();
                    data.remove(key);
                    data.put(key, objectToPut);
                    return;
                } else {
                    replaceJsonObjectInInner(index, data.get(key), objectToPut);
                }
            }
        }
    }

    private void replaceJsonObjectInJsonArray(String index, JSONArray array, JSONObject objectToPut) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            replaceJsonObjectInInner(index, array.get(i), objectToPut);
        }
    }

    private void replaceJsonObjectInInner(String index, Object current, JSONObject objectToPut) throws JSONException {
        if (current instanceof JSONObject) {
            replaceJsonObject(index, (JSONObject) current, objectToPut);
        }
        if (current instanceof JSONArray) {
            replaceJsonObjectInJsonArray(index, (JSONArray) current, objectToPut);
        }
    }


    public List<Object> findJsonObjects(String index, JSONObject data) throws JSONException {
        Iterator<String> keys = data.keys();
        List<Object> result = new ArrayList<>();;
        while(keys.hasNext()) {
            String key = keys.next();
            if(key.equals(index)) {
                result.add( data.get(key));
            } else {
                List<Object> resultInner = getInnerJsonObject(index, data.get(key));
                if(resultInner.size() > 0) {
                    result.addAll(resultInner);
                }
            }
        }
        return result;
    }

    public List<Object> findJsonObjectInJsonArray(String index, JSONArray array) throws JSONException {
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            List<Object> resultInner = getInnerJsonObject(index, array.get(i));
            if(resultInner.size() > 0) {
                result.addAll(resultInner);
            }
        }
        return result;
    }

    private List<Object> getInnerJsonObject(String index, Object current) throws JSONException {
        if (current instanceof JSONObject) {
            return findJsonObjects(index, (JSONObject) current);
        }
        if (current instanceof JSONArray) {
            return findJsonObjectInJsonArray(index, (JSONArray) current);
        }
        return new ArrayList<>();
    }

}
