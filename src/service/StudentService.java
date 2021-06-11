package service;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private static final List<Student> studentsDB = new ArrayList<>();

    public void saveStudent(Student student) {
        studentsDB.add(student);
    }

    public void updateStudent(Student student) {
        Student s = findStudent(student.getNic());
        int index = studentsDB.indexOf(s);
        studentsDB.set(index, student);
    }

    public void deleteStudent(String nic) {
        Student student = findStudent(nic);
        studentsDB.remove(student);
    }

    public List<Student> findAllStudents() {
        return studentsDB;
    }

    public Student findStudent(String nic) {
        for (Student student : studentsDB) {

            if (student.getNic().equals(nic)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findStudents(String query) {
        List<Student> result = new ArrayList<>();

        for (Student student : studentsDB) {

            if (student.getNic().contains(query) || student.getFullName().contains(query)
                    || student.getAddress().contains(query) || student.getEmail().contains(query) ||
                    student.getDateOfBirth().toString().contains(query) || student.getContact().contains(query)) {
                result.add(student);
            }
        }
        return result;
    }

}
