package com.beyond.university.student.model.service;

import com.beyond.university.student.model.dto.StudentAddRequestDto;
import com.beyond.university.student.model.mapper.StudentMapper;
import com.beyond.university.student.model.vo.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    @Override
    public List<Student> getStudentsByDepartmentNo(String dno) {

        return studentMapper.selectAllByDepartmentNo(dno);
    }

    @Override
    public Student getStudentByNo(String sno) {

        return studentMapper.selectStudentByNo(sno);

    }

    @Override
    @Transactional
    public int save(Student student) {
        int result = 0;

        if (student.getNo() != null) {
            // update
            result = studentMapper.updateStudent(student);

        } else {
            // insert
            result = studentMapper.insertStudent(student);
        }

//        if (true) {
//            throw new RuntimeException();
//        }

        return result;
    }

    @Override
    public int delete(String sno) {

        return studentMapper.deleteStudent(sno);
    }


}
