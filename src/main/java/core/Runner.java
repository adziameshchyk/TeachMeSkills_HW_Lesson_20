package core;

import core.models.Student;
import core.services.CityService;
import core.services.StudentService;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        CityService cityService = new CityService();

        System.out.println("Information about all students in the group: ");
        List<Student> students = studentService.getAllStudents();
        for (Student student : students) {
            String cityName = cityService.getCityById(student.getCityId()).getName();
            System.out.println(student + "city: " + cityName + ".");
        }
    }

}
