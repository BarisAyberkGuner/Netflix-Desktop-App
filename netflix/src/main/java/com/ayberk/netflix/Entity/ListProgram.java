package com.ayberk.netflix.Entity;

public class ListProgram {
    private int programID;
    private String programName, programType, programCategory;

    public ListProgram(int id, String name, String type, String category) {
        this.programID = id;
        this.programName = name;
        this.programType = type;
        this.programCategory = category;
    }

    public int getId() {
        return programID;
    }

    public String getName() {
        return programName;
    }

    public String getType() {
        return programType;
    }

    public String getCategory() {
        return programCategory;
    }
}
