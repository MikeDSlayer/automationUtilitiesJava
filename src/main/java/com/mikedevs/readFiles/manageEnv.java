package com.mikedevs.readFiles;

import java.io.File;
import java.io.IOException;

public class manageEnv {
    public static void main(String[] args) throws IOException {
        String strConfgFile = "T:\\XposAutomatizacionDocs\\config_files";
        File confgFiles = new File(strConfgFile);
        for (File envs : confgFiles.listFiles()){
            if (envs.isDirectory()) {

                for (File versions : envs.listFiles()) {
                    if (versions.isDirectory()) {
                        for (File file : versions.listFiles()) {
                            System.out.printf("Envs: %s, Version: %s, File: %s \n", envs.getName(), versions.getName(), file.getName());
                        }
                    }
                }
            }
        }
    }
}
