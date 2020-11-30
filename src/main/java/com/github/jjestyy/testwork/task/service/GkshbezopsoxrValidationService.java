package com.github.jjestyy.testwork.task.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GkshbezopsoxrValidationService {

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
                if(resultData.has(set.getKey())) {
                    if (!checkRules(set.getValue(), mark)) {
                        resultData.remove(set.getKey());
                        resultData.put(set.getKey(), oldData.get(set.getKey()));
                    }
                }
            }
            resultJson.put(dataId, resultData);
            return resultJson.toString();
        } catch (JSONException e) {
            throw new RuntimeException("Bad forms format, JSON parsing error");
        }
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
