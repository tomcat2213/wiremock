package com.homework5.tesecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.homework5.steps.StepModel;
import com.homework5.steps.StepResult;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FakerUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

public class ApiTestCaseModel {
    public static final Logger logger = LoggerFactory.getLogger(ApiTestCaseModel.class);
    private String name;
    private String description;
    private ArrayList<StepModel> steps;
    private ArrayList<Executable> assertList = new ArrayList<>();
    private HashMap<String,String> testCaseVariables = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepModel> steps) {
        this.steps = steps;
    }

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getTestCaseVariables() {
        return testCaseVariables;
    }

    public void setTestCaseVariables(HashMap<String, String> testCaseVariables) {
        this.testCaseVariables = testCaseVariables;
    }

    public static ApiTestCaseModel load (String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),ApiTestCaseModel.class);
    }
    public void run () {
        this.testCaseVariables.put("getTimeStamp" ,FakerUtils.getTimeStamp());
        logger.info("用例变量更新： "+testCaseVariables);
        steps.forEach(stepModel -> {
                    StepResult stepResult = stepModel.run(testCaseVariables);
                    if (stepResult.getStepVariables().size() >0 ) {
                        testCaseVariables.putAll(stepResult.getStepVariables());
                        logger.info("testcase变量更新： " + testCaseVariables);
                    }
                    if (stepResult.getAssertList().size() >0) {
                        assertList.addAll(stepResult.getAssertList());
                    }
                }
        );
        assertAll("",assertList.stream());
    }
}
