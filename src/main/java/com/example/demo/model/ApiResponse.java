package com.example.demo.model;

import java.util.List;

public class ApiResponse {
    private MainData mainData;
    private List<ActionData> actionData;

    public ApiResponse(MainData mainData, List<ActionData> actionData) {
        this.mainData = mainData;
        this.actionData = actionData;
    }

    public MainData getMainData() { return mainData; }
    public void setMainData(MainData mainData) { this.mainData = mainData; }
    public List<ActionData> getActionData() { return actionData; }
    public void setActionData(List<ActionData> actionData) { this.actionData = actionData; }
}