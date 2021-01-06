package com.homework5;

import io.restassured.response.Response;

public class BaseResult {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
