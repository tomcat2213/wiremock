package com.test;

import com.homework5.actions.ApiActionModel;
import com.homework5.global.ApiLoader;
import com.homework5.steps.AssertModel;
import com.homework5.steps.StepModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;

public class Test04_StepModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test04_StepModelTest.class);
    protected HashMap<String, ApiActionModel> actions = new HashMap<>();
    protected String CORPID = "ww5ef451bf006ec894";
    protected String CORPSECRET = "EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU";
    protected ArrayList<String> actualParameter = new ArrayList<>();
    protected static String PACKAGEAPI ="src/test/resources/api";
    protected ArrayList<AssertModel> asserts = new ArrayList<>();
    protected HashMap<String ,String> save = new HashMap<>();
    protected HashMap<String ,String> globalsave = new HashMap<>();
    protected StepModel stepModel = new StepModel();
    protected AssertModel assertModel = new AssertModel();
    @BeforeAll
    static void load () {
        ApiLoader.load(PACKAGEAPI);
        logger.info("debugger");
    }
    @Test
    void runTest () {
        actualParameter.add(CORPID);
        actualParameter.add(CORPSECRET);
        assertModel.setActual("errcode");
        assertModel.setExpect("2");
        assertModel.setMatcher("equalTo");
        assertModel.setReason("getToken错误码校验01");
        asserts.add(assertModel);
        save.put("accesstoken","access_token");
        globalsave.put("accesstoken","access_token");
        stepModel.setApi("tokenhelper");
        stepModel.setAction("getToken");
        stepModel.setActualParameter(actualParameter);
        stepModel.setAsserts(asserts);
        stepModel.setSave(save);
        stepModel.setSaveGlobal(globalsave);
        stepModel.run(null);
        logger.info("debugger");
    }
}
