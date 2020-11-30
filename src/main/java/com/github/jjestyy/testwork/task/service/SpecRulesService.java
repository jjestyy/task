package com.github.jjestyy.testwork.task.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class SpecRulesService {
    private Map<String,String> rules;
    private String lastFoundedSearchId;
    private String lastFoundedArea;

    public Map<String, String> getRulesList(String spec) {
        JSONObject specJson;
        try {
            rules = new HashMap<>();
            specJson = new JSONObject(spec);
            String searchId = "item_id";
            String searchArea = "area";
            String areaDeepTagSearch = "list_id";
            findInJSONObject(specJson, searchId, searchArea, areaDeepTagSearch);
            return rules;
        } catch (JSONException e) {
            throw new RuntimeException("Bad spec format, JSON parsing error");
        }
    }

    public void findInJSONObject(JSONObject json, String searchId, String searchArea, String areaDeepTagSearch) throws JSONException{
        Iterator<String> keys = json.keys();
        boolean inList = false;
        while(keys.hasNext()){
            String key = keys.next();
            if(key.equals(areaDeepTagSearch)) {
                inList = true;
            }
            getValueFromJSON(searchId, key, json.get(key), searchArea, areaDeepTagSearch);
            if(!keys.hasNext()) {
                lastFoundedSearchId = null;
            }
        }
        if(inList) {
            lastFoundedArea = null;
        }
    }

    private void findInJSONArray(JSONArray array, String searchId, String searchArea, String areaDeepTagSearch) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            getValueFromJSON(searchId, String.valueOf(i), array.get(i), searchArea, areaDeepTagSearch);
            if(i == array.length()-1) {
                lastFoundedSearchId = null;
            }
        }
    }

    private void getValueFromJSON(String searchId, String key, Object nextValue, String searchArea, String areaDeepTagSearch) throws JSONException {
        if (nextValue instanceof JSONObject) {
            findInJSONObject((JSONObject) nextValue, searchId, searchArea, areaDeepTagSearch);
        }
        if (nextValue instanceof JSONArray) {
            findInJSONArray((JSONArray) nextValue, searchId, searchArea, areaDeepTagSearch);
        }
        if(nextValue instanceof String) {
            String val = (String) nextValue;
            if (key.equals(searchId)) {
                lastFoundedSearchId = val;
                checkSearchState();
            }
            if(key.equals(searchArea)) {
                lastFoundedArea = val;
                checkSearchState();
            }
        }
    }

    private void checkSearchState() {
        if(lastFoundedArea != null && lastFoundedSearchId != null) {
            rules.put(lastFoundedSearchId, lastFoundedArea);
        }
    }

}
