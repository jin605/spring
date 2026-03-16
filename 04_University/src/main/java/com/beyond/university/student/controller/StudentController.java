package com.beyond.university.student.controller;

import com.beyond.university.department.model.dto.DepartmentsDto;
import com.beyond.university.department.model.service.DepartmentService;
import com.beyond.university.student.model.dto.StudentDto;
import com.beyond.university.student.model.service.StudentService;
import com.beyond.university.student.model.vo.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudentController {
    private final DepartmentService departmentService;

    private final StudentService studentService;

    @GetMapping("/student/search")
    public ModelAndView search(ModelAndView modelAndView, String dno) {
        // List<Department> departments = departmentService.getDepartments();
        List<DepartmentsDto> departments =
                departmentService.getDepartments()
                        .stream()
                        .map(DepartmentsDto::new)
                        .toList();


        log.info("Departments Size:{}", departments.size());

        if (dno != null) {
            List<StudentDto> students =
                    studentService.getStudentsByDepartmentNo(dno)
                            .stream()
                            .map(StudentDto::new)
                            .toList();

            log.info("Student Size : {}", students.size());

            modelAndView.addObject("students",students);
        }


        log.info("search() 메소드 호출");
        modelAndView.addObject("departments", departments);
        modelAndView.setViewName("student/search");

        return modelAndView;
    }

    @GetMapping("/student/info")
    public ModelAndView info(ModelAndView modelAndView, String sno) {

        Student student = studentService.getStudentByNo(sno);
        List<DepartmentsDto> departments =
                departmentService.getDepartments()
                        .stream()
                        .map(DepartmentsDto::new)
                        .toList();

        log.info("Student No : {}", student.getNo());

        modelAndView.addObject("student",student);
        modelAndView.addObject("departments", departments);

        modelAndView.setViewName("student/info");

        return modelAndView;

    }







}
