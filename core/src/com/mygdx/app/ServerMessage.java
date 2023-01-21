package com.mygdx.app;

import java.io.Serializable;

public class ServerMessage implements Serializable {

    private String request = "Empty Request";
    private String response = "Empty Response";

    public ServerMessage() { }

    public ServerMessage(String request) {
        this.request = request;
    }

    public ServerMessage(String request, String response) {
        this.request = request;
        this.response = response;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
