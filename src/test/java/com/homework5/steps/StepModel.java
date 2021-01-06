package com.homework5.steps;

import com.homework5.global.ApiLoader;
import com.homework5.global.GlobalVariables;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.PlaceholderUtils;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StepModel {
    public static final Logger logger = LoggerFactory.getLogger(StepModel.class);

    private String api;
    private String action;
    private ArrayList<String> actualParameter;
    private ArrayList<AssertModel> asserts;
    private HashMap<String,String> save;
    private HashMap<String,String> saveGlobal;

    private ArrayList<String> finalActualParameter = new ArrayList<>();
    private HashMap<String,String> stepVariables = new HashMap<>();
    private StepResult stepResult = new StepResult();
    private ArrayList<Executable> assertList = new ArrayList<>();

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getActualParameter() {
        return actualParameter;
    }

    public void setActualParameter(ArrayList<String> actualParameter) {
        this.actualParameter = actualParameter;
    }

    public ArrayList<AssertModel> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<AssertModel> asserts) {
        this.asserts = asserts;
    }

    public HashMap<String, String> getSave() {
        return save;
    }

    public void setSave(HashMap<String, String> save) {
        this.save = save;
    }

    public HashMap<String, String> getSaveGlobal() {
        return saveGlobal;
    }

    public void setSaveGlobal(HashMap<String, String> saveGlobal) {
        this.saveGlobal = saveGlobal;
    }

    public StepResult run (HashMap<String,String> testCaseVariables) {
        if (null != actualParameter) {
            finalActualParameter.addAll(PlaceholderUtils.resolveList(actualParameter,testCaseVariables));
        }
        Response response = ApiLoader.getAction(api,action).run(finalActualParameter);

        if (null !=save) {
            save.forEach((variablesName,path) ->{
                    String value = response.path(path).toString();
                    stepVariables.put(variablesName,value);
                    logger.info("step变量更新： "+variablesName);}
                    );
        }
        if (null !=saveGlobal) {
            saveGlobal.forEach((variablesName,path) ->{
                    String value = response.path(path).toString();
                    GlobalVariables.getGlobalVariables().put(variablesName,value);
                logger.info("全局变量更新： "+GlobalVariables.getGlobalVariables());}
            );
        }
        if (null !=asserts) {
            asserts.stream().forEach(assertModel -> {
                assertList.add(() -> {
                        assertThat(assertModel.getReason(), response.path(assertModel.getActual()).toString(),equalTo(assertModel.getExpect()));
                });
            });
        }
        stepResult.setAssertList(assertList);
        stepResult.setStepVariables(stepVariables);
        stepResult.setResponse(response);
        return stepResult;
    }
}
