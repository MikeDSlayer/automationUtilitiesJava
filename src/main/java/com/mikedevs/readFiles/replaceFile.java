package com.mikedevs.readFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class replaceFile {
    public static void main(String[] args) throws IOException {
        String tspFileOld = "\\Test.tsp";
        File tspFileNew = new File("C:\\Users\\63259\\Desktop\\resources\\tspFile\\Test.tsp");

        File mainDirectory = new File("C:\\Users\\63259\\repo\\UFT_Xpos\\Escenarios de XPOS\\CASOS_UNITARIOS");
        for (File mainFolder : mainDirectory.listFiles()) {
            if (mainFolder.isDirectory()) {
                for (File folder : mainFolder.listFiles()) {
                    if (folder.isDirectory()){
                        File file = new File(folder + tspFileOld);
                        Files.deleteIfExists(file.toPath());
                        Files.copy(tspFileNew.toPath(), file.toPath());
                    }
                }
            }
        }
    }
}
