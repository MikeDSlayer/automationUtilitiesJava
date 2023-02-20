package com.mikedevs.excelUtils;

import com.mikedevs.models.testCaseModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class writeExcel {

    public void writeExcelFile(List<testCaseModel> listModel, String excelFilePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Scripts");

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);


        Row row = sheet.createRow(0);
        Cell cellTitle = row.createCell(1);

        cellTitle.setCellStyle(cellStyle);
        cellTitle.setCellValue("Id Script");

        Cell cellTestName = row.createCell(2);
        cellTestName.setCellStyle(cellStyle);
        cellTestName.setCellValue("TestName");

        Cell cellAutor = row.createCell(3);
        cellAutor.setCellStyle(cellStyle);
        cellAutor.setCellValue("Funcion de Negocio");

        Cell cellBussines = row.createCell(4);
        cellBussines.setCellStyle(cellStyle);
        cellBussines.setCellValue("Aplicacion");

        Cell cellLocation = row.createCell(5);
        cellLocation.setCellStyle(cellStyle);
        cellLocation.setCellValue("Criticabilidad");

        int rowCount = 0;

        for (testCaseModel model : listModel) {
            if (model.testName != null) {
                Row row1 = sheet.createRow(++rowCount);
                writeBook(model, row1);
            }else {
                System.out.println("TestName Null!, TestCase: " + model.idPrueba);
            }
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath + "\\InventarioScritps_" + dateFormat.format(date) + ".xlsx")) {
            workbook.write(outputStream);
        }
    }

    private void writeBook(testCaseModel model, Row row) {

            Cell cell = row.createCell(1);
            cell.setCellValue(model.idPrueba);

            cell = row.createCell(2);
            cell.setCellValue(model.testName);

            cell = row.createCell(3);
            cell.setCellValue(model.funcionNegocio);

            cell = row.createCell(4);
            cell.setCellValue(model.application);

            cell = row.createCell(5);
            cell.setCellValue(model.criticality);

    }
}
