package com.ayberk.netflix.Entity;

public class Program {
    private int programID;
    private String programName, programCategory, programType;

    public Program(String programName, String programCategory, String programType) {
        this.programName = programName;
        this.programCategory = programCategory;
        this.programType = programType;
    }

    public String getProgramName() {
        return programName;
    }

    public String getProgramCategory() {
        return programCategory;
    }

    public String getProgramType() {
        return programType;
    }

}
