package com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.homework5.actions.ApiActionModel;
import com.homework5.api.ApiObjectModel;
import com.homework5.global.ApiLoader;
import com.homework5.steps.AssertModel;
import com.homework5.steps.StepModel;
import com.homework5.tesecase.ApiTestCaseModel;
import com.test.basemethod.BaseMethod;
import de.sstoehr.harreader.HarReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test06_ApiParameterizedTest {
    public static final Logger logger = LoggerFactory.getLogger(Test06_ApiParameterizedTest.class);
    protected HashMap<String, ApiActionModel> actions = new HashMap<>();
    protected ArrayList<String> actualParameter = new ArrayList<>();
    protected static String PACKAGEAPI ="src/test/resources/api";
    protected static String PACKAGETESTCASE ="src/test/resources/testcase";
    protected ArrayList<AssertModel> asserts = new ArrayList<>();
    protected HashMap<String ,String> save = new HashMap<>();
    @ParameterizedTest(name = "{index}{1}")
    @MethodSource
    void apiTest(ApiTestCaseModel apiTestCaseModel,String name,String description){
        logger.info("【用例开始执行】");
        logger.info("用例名称： " + name);
        logger.info("用例描述： " + description);
        apiTestCaseModel.run();
    }
    static List<Arguments> apiTest () throws IOException {
        ApiLoader.load(PACKAGEAPI);
        List<Arguments> argumentsList = new ArrayList<>();
        Arrays.stream(new File(PACKAGETESTCASE).list()).forEach(name -> {
            String path = PACKAGETESTCASE + "/" + name;
            try {
                ApiTestCaseModel apiTestCase = ApiTestCaseModel.load(path);
                argumentsList.add(Arguments.arguments(apiTestCase,apiTestCase.getName(),apiTestCase.getDescription()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return argumentsList;
    }
}
