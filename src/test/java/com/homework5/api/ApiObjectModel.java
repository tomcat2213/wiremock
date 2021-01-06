package com.homework5.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.homework5.actions.ApiActionModel;
import com.test.basemethod.BaseMethod;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ApiObjectModel {
    private String name;
    private HashMap<String, ApiActionModel> actions;
    private HashMap<String,String> obVariables = new HashMap<>();
    protected static ObjectMapper objectMapperStatic = new ObjectMapper(new YAMLFactory());
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ApiActionModel> getActions() {
        return actions;
    }

    public void setActions(HashMap<String, ApiActionModel> actions) {
        this.actions = actions;
    }

    public HashMap<String, String> getObVariables() {
        return obVariables;
    }

    public void setObVariables(HashMap<String, String> obVariables) {
        this.obVariables = obVariables;
    }
    public static ApiObjectModel load (String path) throws IOException {
        return objectMapperStatic.readValue(new File(path),ApiObjectModel.class);
    }
}
