package HR;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;


import java.util.Scanner;

public class CSVUtil {

    private static final String FILE_PATH = "C:\\Users\\acer\\Documents\\NetBeansProjects\\JavaAsg\\src\\csv\\Employees_DetailsCSV.csv"; 
    


    public static void saveTableDataToCSV(DefaultTableModel model) {
        // Check if model or its data is null
        if (model == null || model.getRowCount() == 0 || model.getColumnCount() == 0) {
            System.err.println("Table model is empty or not initialized.");
            return;
        }

        FileWriter fileWriter = null;
        try {
            // Create a FileWriter to write the CSV file
            fileWriter = new FileWriter("C:\\Users\\acer\\Documents\\NetBeansProjects\\JavaAsg\\src\\csv\\Employees_DetailsCSV.csv");

            // Write column headers
            for (int i = 0; i < model.getColumnCount(); i++) {
                fileWriter.append(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) {
                    fileWriter.append(',');
                }
            }
            fileWriter.append('\n');

            // Write rows
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object cellData = model.getValueAt(i, j);
                    // Handle null values by using an empty string
                    fileWriter.append(cellData != null ? cellData.toString() : "");
                    if (j < model.getColumnCount() - 1) {
                        fileWriter.append(',');
                    }
                }
                fileWriter.append('\n');
            }

            // Flush and close the writer
            fileWriter.flush();
        } catch (IOException e) {
            // Handle IOExceptions
            System.err.println("Error writing to CSV file: " + e.getMessage());
        } finally {
            // Ensure the FileWriter is closed
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.err.println("Error closing FileWriter: " + e.getMessage());
                }
            }
        }
    }






    public static void loadTableDataFromCSV(DefaultTableModel model) {
    try {
        File file = new File(FILE_PATH);
        System.out.println("Attempting to load CSV file from: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("CSV file not found, starting with an empty table.");
            return;
        }

        Scanner scanner = new Scanner(file);

        // Skip the first line (header)
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            
            String[] rowData = scanner.nextLine().split(",");
            // Ensure all data is treated as String
            for (int i = 0; i < rowData.length; i++) {
                rowData[i] = rowData[i].trim(); // Remove any extra spaces
            }
            model.addRow(rowData);
        }
//            String[] rowData = scanner.nextLine().split(",");
//            model.addRow(rowData);
//        }
        scanner.close();

        System.out.println("Data successfully loaded from CSV.");

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
