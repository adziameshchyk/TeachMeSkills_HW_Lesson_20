package core;

import core.models.Student;
import core.services.CityService;
import core.services.StudentService;

import java.util.List;

/**
 * Task:
 *
 * Mandatory task:
 *  Create a table with student data groups.
 *  Create a Java application to obtain a list of all students in a group.
 * Extra credit task:
 *  Improve the main task. Create a table with cities where they live
 *  students. Display information about each student in the group and his hometown.
 *  Provide for operations of adding new cities, new students,
 *  deleting students and deleting cities.
 */

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
