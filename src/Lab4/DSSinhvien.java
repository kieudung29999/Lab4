package Lab4;

import java.io.*;
import java.util.*;

class Sinhvien {
    String name;
    int age;
    double gpa;

    public Sinhvien(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
}

public class DSSinhvien {
    public static void saveStudentsToXML(List<Sinhvien> sinhviens, String filename) throws IOException {
        try (PrintWriter outputFile = new PrintWriter(new FileWriter(filename + ".xml"))) {
            outputFile.println("<Sinhviens>");
            for (Sinhvien sinhvien : sinhviens) {
                outputFile.println("<Sinhvien>");
                outputFile.println("<Ten>" + sinhvien.name + "</Ten>");
                outputFile.println("<Tuoi>" + sinhvien.age + "</Tuoi>");
                outputFile.println("<Gpa>" + sinhvien.gpa + "</Gpa>");
                outputFile.println("</Sinhvien>");
            }
            outputFile.println("</Sinhviens>");
        }
        System.out.println("Student data saved to '" + filename + ".xml'");
    }

    public static void main(String[] args) throws IOException {
        List<Sinhvien> sinhviens = Arrays.asList(
                new Sinhvien("Dung", 19, 8),
                new Sinhvien("Linh", 21, 9.5),
                new Sinhvien("Trang", 18, 8.8)
        );

        saveStudentsToXML(sinhviens, "Student");
    }
}
