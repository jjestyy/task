package com.github.jjestyy.testwork.task.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SomeValidationService {

    @Autowired
    private SpecRulesService specRulesService;

    public String GetValidatedGkshbezopsoxrJSON(String spec, String oldForm, String newForm, String mark)  {
        Map<String,String> rules = specRulesService.getRulesList(spec);
        String dataId = "data";
        try {
            JSONObject oldJson = new JSONObject(oldForm);
            JSONObject newJson = new JSONObject(newForm);
            JSONObject resultJson = new JSONObject(newForm);
            JSONObject oldData = (JSONObject) oldJson.get(dataId);
            JSONObject resultData = (JSONObject) newJson.get(dataId);
            resultJson.remove(dataId);

            for (Map.Entry<String, String> set : rules.entrySet()) {
                if (!checkRules(set.getValue(), mark)) {
                    replaceJsonObject(set.getKey(), resultData, findJsonObject(set.getKey(), oldData));
                }
            }
            resultJson.put(dataId, resultData);
            return resultJson.toString();
        } catch (JSONException e) {
            throw new RuntimeException("Bad forms format, JSON parsing error");
        }
    }

    private void replaceJsonObject(String index, JSONObject data, JSONObject objectToPut) throws JSONException {
        if(objectToPut != null) {
            Iterator<String> keys = data.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (key.equals(index)) {
                    keys.remove();
                    data.remove(key);
                    data.put(key, objectToPut);
                    return;
                }
                if (data.get(key) instanceof JSONObject) {
                    replaceJsonObject(index, (JSONObject) data.get(key), objectToPut);
                }

                if (data.get(key) instanceof JSONArray) {
                    JSONArray array = (JSONArray) data.get(key);
                    for(int i = 0; i < array.length(); i++) {
                        replaceJsonObject(index, (JSONObject) array.get(i), objectToPut);
                    }
                }
            }
        }
    }

    private JSONObject findJsonObject(String index, JSONObject data) throws JSONException {
        Iterator<String> keys = data.keys();
        JSONObject finded = null;
        while(keys.hasNext() && finded == null) {
            String key = keys.next();
            if(key.equals(index)) {
                finded = (JSONObject) data.get(key);
                break;
            } else {
                if (data.get(key) instanceof JSONObject) {
                    finded = findJsonObject(index, (JSONObject) data.get(key));
                }
                if (data.get(key) instanceof JSONArray) {
                    JSONArray array = (JSONArray) data.get(key);
                    for (int i = 0; i < array.length(); i++) {
                        finded = findJsonObject(index, (JSONObject) array.get(i));
                    }
                }
            }
        }
        return finded;
    }

    private boolean checkRules(String value, String mark) {
        for(int i = 0; i< value.length(); i++) {
            if(value.charAt(i) == '1' && mark.charAt(i) == '0') {
                return false;
            }
        }
        return true;
    }


}
