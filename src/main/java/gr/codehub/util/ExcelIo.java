package gr.codehub.util;

import gr.codehub.model.Cohort;

public class ExcelIo {

    public static void saveToExcel(String Excelfilename, String CsvFilename) {
        Cohort cohort = new Cohort();
        cohort.loadStudents(CsvFilename);
        cohort.saveToExcel(Excelfilename);
        System.out.println("The excel file has been saved");
    }

    public static void loadFromExcel(String filename) {
        Cohort cohort = new Cohort();
        cohort.loadFromExcel(filename);
        System.out.println(cohort);
        System.out.println("The excel file has been loaded");
    }

}
