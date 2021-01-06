package com.homework5.global;

import com.homework5.actions.ApiActionModel;
import com.homework5.api.ApiObjectModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ApiLoader {
    public static final Logger logger = LoggerFactory.getLogger(ApiLoader.class);
    private static ArrayList<ApiObjectModel> apis = new ArrayList<>();

    public static void load (String string) {
        Arrays.stream(new File(string).list()).forEach(path ->
                {
                    try {
                        apis.add(ApiObjectModel.load(string + "/" + path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                );
    }
    public static ApiActionModel getAction (String apiName,String actionName) {
        final ApiActionModel[] apiActionModel = {new ApiActionModel()};
        apis.stream().filter(api -> api.getName().equals(apiName)).forEach(api -> {
            apiActionModel[0] = api.getActions().get(actionName);
                }
        );
        if (null != apiActionModel[0]) {
            return apiActionModel[0];
        } else {
            logger.info("没有找到接口对象： "+apiName+"中的方法： "+actionName);
        }
        return null;
    }

}
