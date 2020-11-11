package gr.codehub.util;

import gr.codehub.exceptions.NullStudentException;
import gr.codehub.exceptions.ProhibittedAddressException;
import gr.codehub.exceptions.UnderAgeException;
import gr.codehub.model.Cohort;
import gr.codehub.model.Student;

public class CohortUtil {
    private static void fileLoadDemo() {
        Cohort cohort = new Cohort();
        cohort.loadStudents("students.txt");
        System.out.println(cohort);

    }


    private static void fileSaveDemo(){
        try {
            Cohort cohort = new Cohort();
            Student student = new Student("Dimitris", "Lamia", 18);
            cohort.addStudent(student);
            cohort.saveStudents("students.txt");
        } catch (UnderAgeException e) {
            e.printStackTrace();
        } catch (NullStudentException e) {
            e.printStackTrace();
        } catch (ProhibittedAddressException e) {
            e.printStackTrace();
        }
    }



    private static void checkedExceptionsDemo() {
        Cohort cohort = new Cohort();
        Student student = null;
        try {
            student = new Student("Dimitris", "Lamia", 17);
            cohort.addStudent(student);
        } catch (ProhibittedAddressException e) {
            System.out.println(e);
        } catch (NullStudentException e) {
            e.printStackTrace();
        }
        catch (UnderAgeException e) {
            e.printStackTrace();
        }
        System.out.println(cohort);
///////////////////////////////////////////////////////

        Student studentFromAthens = null;
        try {
            studentFromAthens = new Student("John", "Athens", 20);
            cohort.addStudent(studentFromAthens);
        } catch (ProhibittedAddressException e) {
            e.printStackTrace();
        } catch (NullStudentException e) {
            e.printStackTrace();
        } catch (UnderAgeException e) {
            e.printStackTrace();
        }
        System.out.println(cohort);
    }


    private static void uncheckedExceptionDemo() {

        int a = 5;
        int b = 0;
        try {
            System.out.println(" a/b= " + a / b);
        } catch (Exception exception) {
            System.out.println("The arithmetic operation is invalid");
        }
        Student anotherStudent = null;

        try {
            anotherStudent.setName("Dimosthenis");
            System.out.println(anotherStudent);
        } catch (Exception e) {
            System.out.println((e));
        }
    }
}
