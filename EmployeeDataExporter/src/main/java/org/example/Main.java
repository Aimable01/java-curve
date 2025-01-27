package org.example;

import java.sql.*;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.poi.xssf.usermodel.*;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/employeedb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres"; // Replace with your PostgreSQL password

    private final ExecutorService executorService;

    public Main() {
        this.executorService = Executors.newFixedThreadPool(3);
    }

    public void exportEmployeeData(String fileName) {
        executorService.submit(() -> performExport(fileName));
    }

    private void performExport(String fileName) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
             XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet sheet = workbook.createSheet("Employees");

            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Department");
            headerRow.createCell(3).setCellValue("Salary");

            int rowNum = 1;
            while (rs.next()) {
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getString("name"));
                row.createCell(2).setCellValue(rs.getString("department"));
                row.createCell(3).setCellValue(rs.getDouble("salary"));
            }

            // Save to file
            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
            }

        } catch (Exception e) {
            System.err.println("Export failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public static void main(String[] args) {
        Main exporter = new Main();

        exporter.exportEmployeeData("employees1.xlsx");

        exporter.shutdown();
    }
}