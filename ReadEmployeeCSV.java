
package javaassignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class ReadEmployeeCSV {
    public static DefaultTableModel readCSV(String fileName) {
        DefaultTableModel model = new DefaultTableModel();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); //read the csv header
            if (line != null) {
                String[] columns = line.split(",");
                for (String column : columns) {
                    model.addColumn(column);
                }
            }
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }
    
    
}
