package com.mikedevs.readFiles;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class readScripts {
    public static void main(String[] args) throws IOException {
        readJson rJ = new readJson("scriptsData.json");
        JSONObject scriptsInfo = rJ.readJsonFile();

        JSONArray scripts = scriptsInfo.getJSONArray("scripts");

        String scriptFolder = scriptsInfo.get("carpetaScripts").toString().endsWith("\\") ? scriptsInfo.get("carpetaScripts").toString() : scriptsInfo.get("carpetaScripts") + "\\";
        String folderFile = "./Resultado//";
        if (Files.notExists(Paths.get(folderFile)))
            Files.createDirectory(Paths.get(folderFile));

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();

        File outputFile = new File(folderFile + scriptsInfo.get("nombreArchivo") + "_" + dateFormat.format(date) + ".txt");
        String scriptFile = "\\Action1\\Script.mts";

        if (!scripts.isEmpty()) {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile.getAbsoluteFile()), StandardCharsets.UTF_8));
            for (Object script : scripts) {
                File file = new File(scriptFolder + script + scriptFile);
                if (file.exists() && file.isFile()) {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), StandardCharsets.UTF_8));
                        String st;
                        wr.write("Public Function " + script + "(sParameter)\n");
                        while ((st = br.readLine()) != null) {
                            wr.write("\t" + st + "\n");
                        }
                        wr.write("End Function\n\n");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            try {
                wr.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }
}
