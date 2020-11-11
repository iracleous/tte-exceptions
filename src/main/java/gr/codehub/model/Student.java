package gr.codehub.model;

import gr.codehub.exceptions.UnderAgeException;

public class Student {
    private String name;
    private String address;
    private int age;

    public Student() {
    }

    public Student(String name, String address, int age)
    throws UnderAgeException{
        if (age<18)
            throw new UnderAgeException("Underage", 2);
       this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    public String asCsv(){
        return name + "," +address+ "," +age;
    }
}
