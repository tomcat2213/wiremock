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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class Test03_ApiLoderlTest{
    public static final Logger logger = LoggerFactory.getLogger(Test03_ApiLoderlTest.class);
    protected HashMap<String, ApiActionModel> actions = new HashMap<>();
    protected String CORPID = "ww5ef451bf006ec894";
    protected String CORPSECRET = "EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU";
    protected ArrayList<String> actualParameter = new ArrayList<>();
    protected static String PACKAGEAPI ="src/test/resources/api";
    protected ArrayList<AssertModel> asserts = new ArrayList<>();
    protected HashMap<String ,String> save = new HashMap<>();
    @BeforeAll
    static void loadTest () {
        ApiLoader.load(PACKAGEAPI);
        logger.info("Debug!");
    }
    @Test
    void getActionTest () {
        actualParameter.add(CORPID);
        actualParameter.add(CORPSECRET);
        ApiLoader.getAction("tokenhelper","getToken").run(actualParameter);
    }
}
