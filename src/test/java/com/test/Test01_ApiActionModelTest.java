package com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.homework5.actions.ApiActionModel;
import com.homework5.api.ApiObjectModel;
import com.homework5.global.GlobalVariables;
import com.homework5.steps.AssertModel;
import com.homework5.steps.StepModel;
import com.homework5.tesecase.ApiTestCaseModel;
import com.test.basemethod.BaseMethod;
import de.sstoehr.harreader.HarReader;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class Test01_ApiActionModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test01_ApiActionModelTest.class);
    protected ApiActionModel apiActionModel = new ApiActionModel();
    protected HashMap<String, ApiActionModel> actions = new HashMap<>();
    protected HashMap<String,String> queryMap = new HashMap<>();
    protected static ObjectMapper objectMapperStatic = new ObjectMapper(new YAMLFactory());
    protected String CORPID = "ww5ef451bf006ec894";
    protected String CORPSECRET = "EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU";
    protected ArrayList<String> actualParameter = new ArrayList<>();
    protected ArrayList<AssertModel> asserts = new ArrayList<>();
    protected HashMap<String ,String> save = new HashMap<>();
    protected HashMap<String ,String> globalsave = new HashMap<>();
    protected ArrayList<String> formalParameter = new ArrayList<>();
    @Test
    void runTest () {
        actualParameter.add(CORPID);
        actualParameter.add(CORPSECRET);
        queryMap.put("corpid","${corpid}");
        queryMap.put("corpsecret","${corpsecret}");
        apiActionModel.setUrl("https://qyapi.weixin.qq.com/cgi-bin/${x}");
        globalsave.put("x","gettoken");
        GlobalVariables.setGlobalVariables(globalsave);
        formalParameter.add("corpid");
        formalParameter.add("corpsecret");
        apiActionModel.setFormalParam(formalParameter);
        apiActionModel.setQuery(queryMap);
        Response response = apiActionModel.run(actualParameter);
    }
}
