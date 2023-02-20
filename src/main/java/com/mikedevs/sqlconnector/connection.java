package com.mikedevs.sqlconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class connection {
    public void executeQuery(String strQuery){
        String connectionUrl = "jdbc:mysql://10.200.10.218:1433/automation_xpos";
        try (Connection connection = DriverManager.getConnection(connectionUrl, "ddr", "Oxxo.atm.00")) {
            PreparedStatement preparedStatement = connection.prepareStatement(strQuery);
            preparedStatement.execute();
        }
        catch (Exception ex) {
            System.out.printf("Error: %s, query: %s%n", ex.getMessage(), strQuery);
        }
    }
}
