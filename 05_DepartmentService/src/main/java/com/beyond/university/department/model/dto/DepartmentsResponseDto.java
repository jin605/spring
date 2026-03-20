package com.beyond.university.department.model.dto;

import com.beyond.university.department.model.vo.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
public class DepartmentsResponseDto {

    @Schema(description = "응답 코드", example = "200")
    private final int code;

    @Schema(description = "응답 메시지", example = "OK")
    private final String message;

    @Schema(description = "응답 데이터")
    private final List<Department> items;

    @Schema(description = "페이지 번호", example = "1")
    private final int page;

    @Schema(description = "조회 결과 수", example = "10")
    private final int numOfRows;

    @Schema(description = "전체 결과 수", example = "100")
    private final int totalCount;

    public DepartmentsResponseDto(HttpStatus httpStatus, List<Department> items, int page, int totalCount) {


        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.items = items;
        this.page = page;
        this.numOfRows = items.size();
        this.totalCount = totalCount;

    }
}
