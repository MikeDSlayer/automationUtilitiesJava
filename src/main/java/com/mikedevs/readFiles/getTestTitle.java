package com.mikedevs.readFiles;

import com.mikedevs.models.testCaseModel;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class getTestTitle {
    public static void main(String[] args) throws IOException {

        String scriptFile = "\\Action1\\Script.mts";
        List<testCaseModel> list = new ArrayList<testCaseModel>();

        File mainDirectory = new File("C:\\Users\\63259\\repo\\UFT_Xpos\\Escenarios de XPOS\\CASOS_UNITARIOS\\VENTA");
        for (File folder : mainDirectory.listFiles()) {
            //if (mainFolder.isDirectory()) {
                //for (File folder : mainFolder.listFiles()) {
                    testCaseModel model = new testCaseModel();

                    if (folder.isDirectory()) {
                        File file = new File(folder + scriptFile);
                        if (file.exists() && file.isFile()) {
                            model.idPrueba = folder.getName();
                            BufferedReader br = new BufferedReader(new FileReader(file));

                            String st;
                            int count = 0;

                            while ((st = br.readLine()) != null) {

                                if (st.contains("####"))
                                    count++;

                                if (count > 0 && count < 2) {

                                    if (!st.contains("####")) {
                                        String[] str = st.split(":");

//                                        if (st.contains("Prueba"))


//                                      if (st.contains("Negocio"))
//                                          model.funcionNegocio = str[1].trim();

                                        if (st.contains("TestName"))
                                            //model.testName = str[1].trim();
                                            model.setTestName(st);

                                        if (st.contains("Autor"))
                                            model.autor = str[1].trim();

                                        model.sourcePath = folder.getPath();
                                    }
                                } else {
                                    break;
                                }
                            }
                            list.add(model);
                        }
                    }
                //}
            //}
            //System.out.println("Carpeta: " + folder.getName() + ", revisada!");
        }

        for (testCaseModel item : list){

            if (item.testName != null){
                File file = new File(mainDirectory + "\\" + item.idPrueba + scriptFile);
                String st = FileUtils.readFileToString(file);
                System.out.println(st);
            }
            System.out.println("Script: " + item.idPrueba + ", TestName: " + item.testName);
        }

        //for (testCaseModel str : list)
        //System.out.println("Caso de prueba: " + str.idPrueba + ", TestName: "+ str.testName +", Autor: " + str.autor);
       //writeExcel wE = new writeExcel();
        //wE.writeExcelFile(list, "C:\\Users\\63259\\Desktop\\inventario Excel");

    }
}
