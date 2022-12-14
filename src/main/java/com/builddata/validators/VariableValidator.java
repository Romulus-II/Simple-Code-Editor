package com.builddata.validators;

import com.builddata.BuildData;
import com.builddata.models.Variable;

import java.util.ArrayList;

public class VariableValidator implements BuildData {

    private boolean valid;
    private String errorMessage;

    public VariableValidator(Variable variable) {
        valid = true;
        if (variable.getName().equals("")) {
            valid = false;
            errorMessage = "Variable Name cannot be empty";
            return;
        }
        if (variable.getName().contains(" ")) {
            valid = false;
            errorMessage = "Variable Name cannot contain spaces";
            return;
        }
        for (int i = 0; i < variable.getName().length(); i++) {
            if (BuildData.ILLEGAL_CHARACTERS.contains(variable.getName().charAt(i))) {
                valid = false;
                errorMessage = "Variable Name cannot contain illegal character: '" + variable.getName().charAt(i) + "'";
                return;
            }
        }
        for (Variable var : BuildData.VARIABLES) {
            if (variable.getName().equals(var.getName())) {
                valid = false;
                errorMessage = "Variable Name " + variable.getName() + " is already being used";
            }
        }

        switch(variable.getType()) {
            case "string":
                validateString(variable);
                break;
            case "integer":
                validateInteger(variable);
                break;
            case "float":
                validateFloat(variable);
                break;
        }
    }

    public boolean passesValidation() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private void validateString(Variable variable) {
        if (variable.getValue().contains("$(")) {
            valid = false;
            errorMessage = "Variable Value cannot contain '$('\n";
        }
        if (variable.getValue().contains(")$")) {
            valid = false;
            errorMessage = "Variable Value cannot contain ')$'";
        }
    }

    private void validateInteger(Variable variable) {
        try {
            Integer.parseInt(variable.getValue());
        } catch(NumberFormatException e) {
            valid = false;
            errorMessage = "Please enter a valid integer";
        }
    }

    private void validateFloat(Variable variable) {
        try {
            Double.parseDouble(variable.getValue());
        } catch(NumberFormatException e) {
            valid = false;
            errorMessage = "Please enter a valid float";
        }
    }

}
