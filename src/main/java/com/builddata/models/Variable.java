package com.builddata.models;

public class Variable {
    private String type;
    private String name;
    private String value;

    /**
     * Currently supported variable types:
     *  - string
     *  - integer
     *  - float
     *  - boolean
     *
     * @param name
     * @param type
     * @param value
     */
    public Variable(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String generateAssignCodeLine(String lang) {
        String res = "";
        value = value.replace("'", "\'");
        value = value.replace("\"", "\\\"");
        if (lang.equals("python")) {
            res = this.name + " = " + this.value;
        } else if (lang.equals("java")) {
            if (type.equals("string")) {
                res = "String ";
            } else if (type.equals("integer")) {
                res = "int ";
            } else if (type.equals("float")) {
                res = "double ";
            } else {
                res = type + " ";
            }
            res = res + this.name + " = " + this.value + ";";
        }
        return res;
    }

    public String generateAssignCodeLine(String lang, String newValue) {
        String res = "";
        newValue = newValue.replace("'", "\'");
        newValue = newValue.replace("\"", "\\\"");
        if (lang.equals("python")) {
            res = this.name + " = " + newValue;
        } else if (lang.equals("java")) {
            if (type.equals("string")) {
                res = "String ";
            } else if (type.equals("integer")) {
                res = "int ";
            } else if (type.equals("float")) {
                res = "double ";
            } else {
                res = type + " ";
            }
            res = res + this.name + " = " + newValue + ";";
        }
        return res;
    }
}
