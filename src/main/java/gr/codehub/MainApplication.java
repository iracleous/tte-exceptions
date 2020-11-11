package gr.codehub;
import gr.codehub.util.ExcelIo;

public class MainApplication {
    public static void main(String[] args) {
        ExcelIo.saveToExcel("students.xlsx", "students.txt");
        ExcelIo.loadFromExcel("students.xlsx");
    }
}
