package com.github.jjestyy.testwork.task.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpecRulesService {

    @Autowired
    private JsonWorkService jsonWorkService;

    public Map<String, String> getRulesList(String spec) {
        JSONObject specJson;
        try {
            Map<String,String> rules = new HashMap<>();
            specJson = new JSONObject(spec);
            String listId = "list";
            String areaId = "area";
            String itemName = "item";
            String itemId = "item_id";

            List<Object> lists = jsonWorkService.findJsonObjects(listId, specJson);
            for(Object list: lists) {
                if(list instanceof JSONArray ) {
                    JSONArray listArray = (JSONArray) list;
                    for (int i = 0; i < listArray.length(); i++) {
                        String areaFromList = "";
                        if(listArray.get(i) instanceof JSONObject ) {
                            JSONObject listBody = (JSONObject) listArray.get(i);
                            if(listBody.has(areaId)) {
                                areaFromList = (String) listBody.get(areaId);
                            }
                            List<Object> items = jsonWorkService.findJsonObjects(itemName, listBody);
                            for (Object item : items) {
                                if (item instanceof JSONArray) {
                                    JSONArray itemsArray = (JSONArray) item;
                                    for (int k = 0; k < itemsArray.length(); k++) {
                                        if (itemsArray.get(k) instanceof JSONObject) {
                                            JSONObject itemObject = (JSONObject) itemsArray.get(k);
                                            if (itemObject.has(itemId)) {
                                                if (itemObject.has(areaId)) {
                                                    rules.put((String) itemObject.get(itemId), (String) itemObject.get(areaId));
                                                } else if (!areaFromList.equals("")) {
                                                    rules.put((String) itemObject.get(itemId), areaFromList);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return rules;
        } catch (JSONException e) {
            throw new RuntimeException("Bad spec format, JSON parsing error");
        }
    }
}
