package com.beyond.university.department.model.service;

import com.beyond.university.department.model.vo.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<Department> getDepartments(int page, int numOfRows, String openYn);

    int getDepartmentCount(String openYn);

    Optional<Department> getDepartmentByNo(String departmentNo);
}

