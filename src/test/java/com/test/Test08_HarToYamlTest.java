package com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.homework5.actions.ApiActionModel;
import com.homework5.api.ApiObjectModel;
import com.homework5.steps.AssertModel;
import com.homework5.steps.StepModel;
import com.homework5.tesecase.ApiTestCaseModel;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test08_HarToYamlTest {
    public static final Logger logger = LoggerFactory.getLogger(Test08_HarToYamlTest.class);
    protected HarReader harReader = new HarReader();
    protected ApiObjectModel apiObjectModel =  new ApiObjectModel();
    protected ApiActionModel apiActionModel = new ApiActionModel();
    protected HashMap<String, ApiActionModel> actions = new HashMap<>();
    protected HashMap<String,String> queryMap = new HashMap<>();
    protected String HAR = "src/test/resources/har/qyapi.weixin.qq.com.har";
    protected String TOKENHELPER = "src/test/resources/har/tokenhelper.yaml";
    protected ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    protected static ObjectMapper objectMapperStatic = new ObjectMapper(new YAMLFactory());
    protected String CORPID = "ww5ef451bf006ec894";
    protected String CORPSECRET = "EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU";
    protected String TOKEN = "getToken";
    protected String PATH = "src/test/resources/api/tokenhelper.yaml";
    protected String CREATEPATH = "src/test/resources/testcase/creatdepartment.yaml";
    protected String INFO = "这是一个 成功的类！！";
    protected ArrayList<String> actualParameter = new ArrayList<>();
    protected static String PACKAGEAPI ="src/test/resources/api";
    protected static String PACKAGETESTCASE ="src/test/resources/testcase";
    protected ArrayList<AssertModel> asserts = new ArrayList<>();
    protected HashMap<String ,String> save = new HashMap<>();
    protected HashMap<String ,String> globalsave = new HashMap<>();
    protected StepModel stepModel = new StepModel();
    protected AssertModel assertModel = new AssertModel();
    protected ApiTestCaseModel apiTestCaseModel = new ApiTestCaseModel();
    public static Logger printLoLog (Class<?> tClass)  {
        return LoggerFactory.getLogger(tClass);
    }
    protected ArrayList<String> formalParameter = new ArrayList<>();
    @Test
    void harTest () throws HarReaderException, IOException {
        Har har = harReader.readFromFile(new File(HAR));
        logger.info("Debug!");
        har.getLog().getEntries().forEach(entrie -> {
            HarRequest harRequest = entrie.getRequest();
            harRequest.getQueryString().forEach(queryString -> {
                queryMap.put(queryString.getName(),queryString.getValue());
            });
            String method = harRequest.getMethod().toString();
            String url = harRequest.getUrl();
            if (method.equals("get")) {
                apiActionModel.setGet(url);
            } else {
                apiActionModel.setPost(url);
            }
            apiActionModel.setQuery(queryMap);
            actions.put(getRequestName(url),apiActionModel);
        });
        apiObjectModel.setName("tokenhelper");
        apiObjectModel.setActions(actions);
        objectMapper.writeValue(new File(TOKENHELPER),apiObjectModel);
    }
    String getRequestName (String url) {
        String[] suburl = url.split("\\u003F")[0].split("/");
        String name = " ";
        if (suburl.length >1) {
            name = suburl[suburl.length - 1];
        } else if (1 == suburl.length) {
            name = suburl[0];
        }
        return name;
    }

    @Test
    void runTest () throws IOException {
        ApiObjectModel apiObjectModel = ApiObjectModel.load(TOKENHELPER);
        apiObjectModel.getActions().get("gettoken").run(null);
    }
}
