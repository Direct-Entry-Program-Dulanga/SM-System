package service;

import model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private static final List<Student> studentsDB = new ArrayList<>();

    public StudentService() {

        /* Let's add some dummy data */
        Student s1 = new Student("456789123V", "Chandima Herath", "Galle", LocalDate.of(1996,05,01), "077-1234567", "abc@ijse.lk");
        Student s2 = new Student("879456123V", "Pethum Jeewantha", "Matara", LocalDate.of(1989,10,01), "077-456789", "pethum@hotmail.lk");
        Student s3 = new Student("456132789V", "Dilan Chathuranga", "Panadura", LocalDate.now(), "077-1234567", "dilan@ijse.lk");
        Student s4 = new Student("879456123V", "Pethum Nuwan", "Matara", LocalDate.of(1989,10,01), "077-456789", "pethum@ijse.lk");
        saveStudent(s1);
        saveStudent(s2);
        saveStudent(s3);
        saveStudent(s4);
    }

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
