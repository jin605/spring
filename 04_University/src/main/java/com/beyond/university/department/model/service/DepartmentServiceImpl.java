package com.beyond.university.department.model.service;

import com.beyond.university.department.model.mapper.DepartmentMapper;
import com.beyond.university.department.model.vo.Department;
import com.beyond.university.student.model.vo.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartments() {

        return departmentMapper.selectAll();
    }

    @Override
    public Department getDepartmentByNo(String departmentNo) {

        return departmentMapper.selectDepartmentByNo(departmentNo);
    }
}
