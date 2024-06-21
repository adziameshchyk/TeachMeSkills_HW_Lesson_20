package core.services;

import core.models.Student;
import core.services.crud.StudentCRUDService;

import java.util.List;

public class StudentService {

    private StudentCRUDService studentCRUDService;

    public StudentService() {
        this.studentCRUDService = new StudentCRUDService();
    }

    public Student getStudentById(int id) {
        return studentCRUDService.getStudentById(id);
    }

    public List<Student> getAllStudents() {
        return studentCRUDService.getAllStudents();
    }

    public void addStudent(Student student) {
        studentCRUDService.addStudent(student);
    }

    public void deleteStudentById(int id) {
        studentCRUDService.deleteStudentById(id);
    }

}
