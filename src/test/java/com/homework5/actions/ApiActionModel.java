package com.homework5.actions;

import com.homework5.global.GlobalVariables;
import com.test.basemethod.BaseMethod;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.PlaceholderUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiActionModel{
    private String method = "get";
    private String url;
    private String body;
    private String contentType;
    private HashMap<String,String> query;
    private HashMap<String,String> headers;
    private String post;
    private String get;
    private Response response;
    private ArrayList<String> formalParam;
    private HashMap<String,String> actionVariables = new HashMap<>();
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public HashMap<String, String> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, String> query) {
        this.query = query;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public ArrayList<String> getFormalParam() {
        return formalParam;
    }

    public void setFormalParam(ArrayList<String> formalParam) {
        this.formalParam = formalParam;
    }
    public Response run (ArrayList<String> actualParameter ) {
        HashMap<String,String> queryMap = new HashMap<>();
        String runbody = this.body;
        String runurl = this.url;
        if (post != null) {
            runurl = post;
            method = "post";
        }
        if (get != null) {
            runurl = get;
            method = "get";
        }
        if (query != null) {
            queryMap.putAll(PlaceholderUtils.resolveMap(query,GlobalVariables.getGlobalVariables()));
        }
        runbody = PlaceholderUtils.resolveString(runbody,GlobalVariables.getGlobalVariables());
        runurl = PlaceholderUtils.resolveString(runurl,GlobalVariables.getGlobalVariables());
        if (formalParam != null && formalParam.size() > 0 && actualParameter != null && actualParameter.size() > 0) {
            for (int index =0;index < actualParameter.size();index++) {
                actionVariables.put(formalParam.get(index),actualParameter.get(index));
            }
            if (query != null) {
                queryMap.putAll(PlaceholderUtils.resolveMap(query,actionVariables));
            }
            runbody = PlaceholderUtils.resolveString(body,actionVariables);
            runurl = PlaceholderUtils.resolveString(runurl,actionVariables);
        }
        RequestSpecification requestSpecification = given().log().all();
        if (contentType !=null) {
            requestSpecification.contentType(contentType);
        }
        if (headers != null) {
            requestSpecification.headers(headers);
        }
        if (queryMap != null && queryMap.size()> 0) {
            requestSpecification.queryParams(queryMap);
        } else if (runbody != null) {
            requestSpecification.body(runbody);
        }
        Response response = requestSpecification.request(method,runurl).then().log().all().extract().response();
        this.response = response;
        return response;
    }
}
