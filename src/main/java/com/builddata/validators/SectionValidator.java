package com.builddata.validators;

import com.builddata.BuildData;
import com.builddata.models.Section;

public class SectionValidator implements BuildData {

    private boolean valid;
    private String errorMessage;

    public SectionValidator(String name) {
        valid = true;
        if (name == "") {
            valid = false;
            errorMessage = "Section Name cannot be empty";
            return;
        }
        for (Section section : BuildData.SECTIONS) {
            if (name == section.getName()) {
                valid = false;
                errorMessage = "Section Name + " + name + " + is already being used";
                return;
            }
        }
    }

    public boolean passesValidation() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
