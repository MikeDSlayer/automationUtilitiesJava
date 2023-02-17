package com.mikedevs.readFiles;

import com.mikedevs.models.testCaseModel;
import com.mikedevs.sqlconnector.connection;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.List;


public class readFiles {
    public static void main(String[] args) throws IOException {

        String scriptFile = "\\Action1\\Script.mts";
        List<testCaseModel> list = new ArrayList<testCaseModel>();

        File mainDirectory = new File("C:\\Users\\63259\\repo\\UFT_Xpos\\Escenarios de XPOS\\CASOS_UNITARIOS");
        for (File mainFolder : mainDirectory.listFiles()) {
            if (mainFolder.isDirectory()) {
                for (File folder : mainFolder.listFiles()) {
                    testCaseModel model = new testCaseModel();

                    if (folder.isDirectory()) {
                        File file = new File(folder + scriptFile);
                        if (file.exists() && file.isFile()) {
                            model.idPrueba = folder.getName();
                            model.funcionNegocio = mainFolder.getName();
                            model.setSourcePath(folder.getPath());
                            String strFile = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

                            if (strFile.contains("TestName:")) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), StandardCharsets.UTF_8));
                                String st;
                                int count = 0;
                                while ((st = br.readLine()) != null) {

                                    if (st.contains("####"))
                                        count++;


                                    if (count == 1) {

                                        if (!st.contains("####")) {

                                            if (st.contains("Descripcion:"))
                                                model.setDescription(st);

                                            if (st.contains("Aplicacion"))
                                                model.setApplication(st);

                                            if (st.contains("Criticidad"))
                                                model.setCriticality(st);

                                            if (st.contains("TestName"))
                                                model.setTestName(st);

                                            if (st.contains("Autor"))
                                                model.setAutor(st);
                                        }
                                    }
                                    if (count == 2)
                                        break;
                                }
                                list.add(model);
                            } else {
                                model.autor = "No data";
                                model.description = "No data";
                                model.testName = "No data";
                                model.criticality = "No data";
                                model.application = "No data";
                            }
                        }
                    }
                }
            }
        }

        connection conn = new connection();
        //conn.executeQuery("truncate table [dbo].[testCases];");
        list.forEach(x -> {
            conn.executeQuery(String.format("INSERT INTO `automation_xpos`.`testCases` (`testCaseId`, `testName`, `author`, `sourceFolder`, `businessFunction`, `description`, `application`, `criticality`) " +
                    "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", x.idPrueba, x.testName, x.autor, x.sourcePath, x.funcionNegocio, x.description, x.application, x.criticality));
            //System.out.println(String.format("Script: %s, TestName: %s, SourcePath: %s", x.idPrueba, x.testName, x.sorucePath));
        });
//        writeExcel wE = new writeExcel();
//        wE.writeExcelFile(list, "C:\\Users\\63259\\Desktop\\inventario Excel");

    }
}
