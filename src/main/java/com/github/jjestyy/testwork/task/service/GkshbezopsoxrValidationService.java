package com.github.jjestyy.testwork.task.service;

import lombok.RequiredArgsConstructor;
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

        System.out.println(rules);
        return null;
    }



}
