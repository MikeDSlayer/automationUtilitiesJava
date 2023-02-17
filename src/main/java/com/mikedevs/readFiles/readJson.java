package com.mikedevs.readFiles;


import org.json.JSONObject;

public class readJson {
    String file;
    public readJson(String jsonFile) {
        this.file = jsonFile;
    }

    public JSONObject readJsonFile() {
            JSONObject scriptsInfo = new JSONObject(file);
            return scriptsInfo;

    }
}
