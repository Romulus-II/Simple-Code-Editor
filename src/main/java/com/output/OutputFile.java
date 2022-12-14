package com.output;

import com.builddata.BuildData;

public class OutputFile implements BuildData {

    //BuildData buildData;
    String fileType;

    public OutputFile(String fileType) {
        //this.buildData = buildData;
        this.fileType = fileType;
    }

    private void writeGlobalVariables() {

    }

}
