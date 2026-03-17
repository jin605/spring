package com.beyond.university.student.model.service;

import com.beyond.university.student.model.dto.StudentAddRequestDto;
import com.beyond.university.student.model.vo.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentsByDepartmentNo(String dno);

    Student getStudentByNo(String dno);

    int save(Student student);

    int delete(String sno);
}
