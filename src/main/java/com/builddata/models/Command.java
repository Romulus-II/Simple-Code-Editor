package com.builddata.models;

import java.util.ArrayList;

public class Command {

    private String id;
    private String label;
    private int rowNumber;
    private String action;
    private ArrayList<Variable> targetVariables;

    public Command(String id, int rowNumber, String action, ArrayList<Variable> targetVariables) {
        this.id = id;
        label = assignLabel(id);
        this.rowNumber = rowNumber;
        this.action = action;
        this.targetVariables = targetVariables;
    }

    private String assignLabel(String id) {
        if (id.equals("STR001")) {
            return "";
        }
        return "unknown.png";
    }

    public String getID() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public ArrayList<Variable> getTargetVariables() {
        return targetVariables;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setTargetVariables(ArrayList<Variable> targetVariables) {
        this.targetVariables = targetVariables;
    }
}
