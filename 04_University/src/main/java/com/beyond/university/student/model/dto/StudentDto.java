package com.beyond.university.student.model.dto;

import com.beyond.university.student.model.vo.Student;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StudentDto {

    private final String no;
    private final String name;
    private final String address;
    private final String absenceYn;

    public StudentDto(Student student) {
        this.no = student.getNo();
        this.name = student.getName();
        this.address = student.getAddress();
        this.absenceYn = student.getAbsenceYn();
    }
}
