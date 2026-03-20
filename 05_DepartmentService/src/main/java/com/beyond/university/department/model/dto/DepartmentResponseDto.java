package com.beyond.university.department.model.dto;

import com.beyond.university.department.model.vo.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class DepartmentResponseDto {

    @Schema(description = "응답 코드", example = "200")
    private final int code;

    @Schema(description = "응답 메시지", example = "OK")
    private final String message;

    @Schema(description = "응답 데이터")
    private final List<Department> item;

    public DepartmentResponseDto(HttpStatus httpStatus, Department item) {

        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.item = Collections.singletonList(item);

    }


}
