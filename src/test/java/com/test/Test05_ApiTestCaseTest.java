package com.test;

import com.homework5.actions.ApiActionModel;
import com.homework5.global.ApiLoader;
import com.homework5.steps.AssertModel;
import com.homework5.tesecase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test05_ApiTestCaseTest {
    public static final Logger logger = LoggerFactory.getLogger(Test05_ApiTestCaseTest.class);
    protected HashMap<String, ApiActionModel> actions = new HashMap<>();
    protected String CREATEPATH = "src/test/resources/testcase/creatdepartment.yaml";
    protected ArrayList<String> actualParameter = new ArrayList<>();
    protected static String PACKAGEAPI ="src/test/resources/api";
    protected ArrayList<AssertModel> asserts = new ArrayList<>();
    protected HashMap<String ,String> save = new HashMap<>();
    protected ApiTestCaseModel apiTestCaseModel = new ApiTestCaseModel();
    @BeforeAll
    static void loadTest () {
        ApiLoader.load(PACKAGEAPI);
        logger.info("debugger");
    }
    @Test
    void loadApiTest () throws IOException {
        apiTestCaseModel = ApiTestCaseModel.load(CREATEPATH);
        logger.info("debugger");
    }
    @Test
    void runTest () throws IOException {
        ApiTestCaseModel.load(CREATEPATH).run();
        logger.info("debugger");
    }
}
