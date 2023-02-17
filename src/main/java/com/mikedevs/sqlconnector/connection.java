package com.mikedevs.sqlconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class connection {
    public void executeQuery(String strQuery){
        String connectionUrl = "jdbc:sqlserver://10.200.10.218:1433;database=automation_xpos;user=gtim;password=12345678;" +
                "trustServerCertificate=true;encrypt=true;loginTimeout=30;";
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            PreparedStatement preparedStatement = connection.prepareStatement(strQuery);
            preparedStatement.execute();
        }
        catch (Exception ex) {
            System.out.printf("Error: %s, query: %s%n", ex.getMessage(), strQuery);
        }
    }
}
