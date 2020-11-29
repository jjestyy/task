package com.github.jjestyy.testwork.task.service;

import com.github.jjestyy.testwork.task.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class GkshbezopsoxrValidationServiceTest {
    
    @Autowired
    private GkshbezopsoxrValidationService service;

    @Test
    void getValidatedGkshbezopsoxrJSON() {
        List<String> marks = TestData.getMarks();
        service.GetValidatedGkshbezopsoxrJSON(TestData.getSpec(), TestData.getOldJSON(), TestData.getNewJSON(), marks.get(0));
    }
}