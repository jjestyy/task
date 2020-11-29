package com.github.jjestyy.testwork.task.service;

import com.github.jjestyy.testwork.task.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GkshbezopsoxrValidationServiceTest {
    @Autowired
    GkshbezopsoxrValidationService service;

    @Test
    void getValidatedGkshbezopsoxrJSON() {
        List<String> marks = TestData.getMarks();
        service.GetValidatedGkshbezopsoxrJSON(TestData.getSpec(), TestData.getOldJSON(), TestData.getNewJSON(), marks.get(0));
    }
}