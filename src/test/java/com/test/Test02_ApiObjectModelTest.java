package com.test;

import com.homework5.api.ApiObjectModel;
import com.homework5.steps.AssertModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test02_ApiObjectModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test02_ApiObjectModelTest.class);
    protected String CORPID = "ww5ef451bf006ec894";
    protected String CORPSECRET = "EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU";
    protected String TOKEN = "getToken";
    protected String PATH = "src/test/resources/api/tokenhelper.yaml";
    protected ArrayList<String> actualParameter = new ArrayList<>();
    protected ArrayList<AssertModel> asserts = new ArrayList<>();
    protected HashMap<String ,String> save = new HashMap<>();
    @BeforeAll
    static void loadTest () {
        logger.info("Debug!");
    }
    @Test
    void getACtionTest () throws IOException {
        actualParameter.add(CORPID);
        actualParameter.add(CORPSECRET);
        ApiObjectModel.load(PATH).getActions().get(TOKEN).run(actualParameter);
    }
}
