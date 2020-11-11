package gr.codehub.model;

import gr.codehub.exceptions.NotSuchStudentException;
import gr.codehub.exceptions.NullStudentException;
import gr.codehub.exceptions.ProhibittedAddressException;
import gr.codehub.exceptions.UnderAgeException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cohort {


    private List<Student> students;

    //4 basic actions in aList

    // Create a row in the list, (add, insert)
    //Read   (retreive, get, select)
    //Update  patch
    //Delete remove



    public Cohort() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) throws ProhibittedAddressException, NullStudentException {
        if (student == null)
            throw new NullStudentException("Null student exception");
        if (student.getAddress().equals("Athens"))
            throw new ProhibittedAddressException("This address is not permitted", 10);
        students.add(student);
    }


    public Student getStudent(int index) throws NotSuchStudentException {
        if (index<0 || index>= students.size())
                throw new NotSuchStudentException();
         return students.get(index);
    }


    public void removeStudent(int index) throws NotSuchStudentException {
        if (index<0 || index>= students.size())
            throw new NotSuchStudentException();
         students.remove(index);
    }



    public void saveStudents(String filename) {
        File file = new File(filename);
        try (PrintWriter pw = new PrintWriter(file)) {

            for (int i = 0; i < students.size(); i++)
                pw.println(students.get(i).asCsv());

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadStudents(String filename) {
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            return;
        }

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.split(",");
            try {
                int age = Integer.parseInt(words[2]);
                Student student = new Student(words[0], words[1], age);
                addStudent(student);
                System.out.println(student + " was loaded");

            } catch (UnderAgeException e) {
                System.out.println("UnderAgeException");
            } catch (NullStudentException e) {
                System.out.println("NullStudentException");
            } catch (ProhibittedAddressException e) {
                System.out.println("ProhibittedAddressException");
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ArrayIndexOutOfBoundsException");
            } catch (Exception e) {
                System.out.println("Exception");
            }
        }
        scanner.close();
    }


    public void saveToExcel(String filename) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Students");
        int rowCount = 0;
        Cell cell = null;
        Row row = null;

        row = sheet.createRow(rowCount);
        cell = row.createCell(0);
        cell.setCellValue("Name");
        cell = row.createCell(1);
        cell.setCellValue("Address");
        cell = row.createCell(2);
        cell.setCellValue("Age");

        for (Student student : students) {
            row = sheet.createRow(++rowCount);
            cell = row.createCell(0);
            cell.setCellValue(student.getName());

            cell = row.createCell(1);
            cell.setCellValue(student.getAddress());

            cell = row.createCell(2);
            cell.setCellValue(student.getAge());
        }
        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void loadFromExcel(String filename) {

        try {
            FileInputStream excelFile = new FileInputStream(new File(filename));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);

            boolean firstTime = true;
            for (Row row : datatypeSheet) {
                if (firstTime) {
                    firstTime = false;
                    continue;
                }
                try {
                    String name = row.getCell(0).getStringCellValue().toString();
                    String address = row.getCell(1).getStringCellValue().toString();
                    int age = (int) row.getCell(2).getNumericCellValue();
                    Student student = new Student(name, address, age);
                    addStudent(student);
                } catch (UnderAgeException e) {
                    System.out.println("UnderAgeException");
                } catch (NullStudentException e) {
                    System.out.println("NullStudentException");
                } catch (ProhibittedAddressException e) {
                    System.out.println("ProhibittedAddressException");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "Cohort{" +
                "students=" + students +
                '}';
    }

}


