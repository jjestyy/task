package com.github.jjestyy.testwork.task.service;

import com.github.jjestyy.testwork.task.TestData;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GkshbezopsoxrValidationServiceTest {
    
    @Autowired
    private SomeValidationService service;

    @Test
    void getValidatedGkshbezopsoxrJSON() throws JSONException {
        List<String> marks = TestData.getMarks();
//       "0000 0000 0000 0000 0000 0000 0000 0000"
        JSONAssert.assertEquals(service.GetValidatedGkshbezopsoxrJSON(TestData.getSpec(), TestData.getOldJSON(), TestData.getNewJSON(), marks.get(0)),
                TestData.getOldJSON(), JSONCompareMode.LENIENT);
//       "1000 0000 0000 0000 0000 0000 0000 0000"
        JSONAssert.assertEquals(service.GetValidatedGkshbezopsoxrJSON(TestData.getSpec(), TestData.getOldJSON(), TestData.getNewJSON(), marks.get(1)),
                TestData.getRes1JSON(), JSONCompareMode.LENIENT);
//       "0100 0000 0000 0000 0000 0000 0000 0000"
        JSONAssert.assertEquals(service.GetValidatedGkshbezopsoxrJSON(TestData.getSpec(), TestData.getOldJSON(), TestData.getNewJSON(), marks.get(2)),
                TestData.getRes2JSON(), JSONCompareMode.LENIENT);
//       "1111 1111 1111 1111 1111 1111 1111 1111"
        JSONAssert.assertEquals(service.GetValidatedGkshbezopsoxrJSON(TestData.getSpec(), TestData.getOldJSON(), TestData.getNewJSON(), marks.get(3)),
                TestData.getNewJSON(), JSONCompareMode.LENIENT);
    }
}