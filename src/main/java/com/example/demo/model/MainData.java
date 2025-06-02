package com.example.demo.model;

import java.util.UUID;

public class MainData {
    private String id;
    private String name;
    private int value;
    private String description;
    private int[] action;

    public MainData(String name, int value, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.value = value;
        this.description = description;
        this.action = new int[]{10,-100,100};
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


    public void performAction(String actionType) {
        switch (actionType) {
            case "A":
                this.value += this.action[0];
                this.description = "Value increased by 10 after Action A.";
                break;
            case "B":
                this.value += this.action[1];
                this.description = "Value decreased by 5 after Action B.";
                break;
            case "C":
                this.value += this.action[2];
                this.description = "Value doubled after Action C.";
                break;
            default:
                this.description = "Unknown action performed.";
        }
    }
}