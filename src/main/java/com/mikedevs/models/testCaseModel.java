package com.mikedevs.models;

public class testCaseModel {
    public String idPrueba;
    public String funcionNegocio;
    public String testName;
    public String autor;
    public String sourcePath;
    public String description;
    public String application;
    public String criticality;

    public void setTestName(String testName) {
            String[] str = testName.split(":");
            try {
                String strTestName = str[1].trim();

                if (strTestName.trim().isEmpty())
                    this.testName = "No data";
                else
                    this.testName = str[1].replace("'","").trim();
            } catch (Exception ex){
               System.out.println(String.format("Error: %s, Script: %s", ex.getMessage(), this.idPrueba));
               this.testName = testName.replace("'","").trim();
            }
    }

    public void setAutor(String autor) {
            String[] str = autor.split(":");
            try {
                if (str[1] != null && !str[1].isEmpty())
                    this.autor = str[1].replace("'","").trim();
                else
                    this.autor = "No Data";
            }catch (Exception ex){
                System.out.println(String.format("Error: %s, Script: %s", ex.getMessage(), this.idPrueba));
                this.autor = autor.replace("'","").trim();
            }
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath.substring(29, sourcePath.length());
    }

    public void setDescription(String description) {
            String[] str = description.split(":");
            if (str[1] != null && !str[1].isEmpty())
                this.description = str[1].replace("'","").trim();
            else
                this.description = "No Data";

    }

    public void setApplication(String application) {
            String[] str = application.split(":");
            if (str[1] != null && !str[1].isEmpty())
                this.application = str[1].replace("'","").trim();
            else
                this.application = "No Data";
    }

    public void setCriticality(String criticality) {
        String[] str = criticality.split(":");

        if (str[1] != null && !str[1].isEmpty())
            this.criticality = str[1].replace("'", "").trim();
        else
            this.criticality = "No Data";
    }
}
