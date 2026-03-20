package com.beyond.university.department.comtroller;

import com.beyond.university.common.excpetion.UniversityException;
import com.beyond.university.common.excpetion.message.ExceptionMessage;
import com.beyond.university.department.model.dto.DepartmentResponseDto;
import com.beyond.university.department.model.dto.DepartmentsResponseDto;
import com.beyond.university.department.model.service.DepartmentService;
import com.beyond.university.department.model.vo.Department;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    학과 관련 API

    1. 학과 목록 조회
      - GET /api/v1/department-service/departments

    2. 학과 상세 조회
      - GET /api/v1/department-service/departments/{department-no}

    3. 학과 등록
      - POST /api/v1/department-service/departments

    4. 학과 수정
      - PUT /api/v1/department-service/departments/{department-no}

    5. 학과 삭제
      - DELETE /api/v1/department-service/departments/{department-no}
 */

@RestController
@RequestMapping("/api/v1/department-service")
@RequiredArgsConstructor
@Tag(name = "Departments APIs", description = "학과 관련 목록")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/departments")
    @Operation(summary = "학과 목록 조회", description = "학과의 목록을 조회한다.")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호", example = "1"),
            @Parameter(name = "numOfRows", description = "한 페이지 결과 수", example = "10"),
            @Parameter(name = "openYn", description = "개설여부", example = "Y")
    })
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    // content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    // content = @Content(
                    //         mediaType = MediaType.APPLICATION_JSON_VALUE,
                    //         schema = @Schema(implementation = DepartmentsResponseDto.class))),

            @ApiResponse(
                    responseCode = "404",
                    description = "DEPARTMENT_NOT_FOUND",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(
                    responseCode = "500",
                    description = "INTENAL_SERVER_ERROR",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))

    })
    public ResponseEntity<DepartmentsResponseDto> getDepartments(@RequestParam int page,
                                                                 @RequestParam int numOfRows,
                                                                 @RequestParam(required = false) String openYn) {

        int totalCount = departmentService.getDepartmentCount(openYn);

        List<Department> departments =
                departmentService.getDepartments(page,numOfRows,openYn);

        if (departments.isEmpty()) {
            throw new UniversityException(ExceptionMessage.DEPARTMENT_NOT_FOUND);
        }



        return ResponseEntity.ok(
                new DepartmentsResponseDto(HttpStatus.OK, departments, page, totalCount)
        );

    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(
                    responseCode = "404",
                    description = "DEPARTMENT_NOT_FOUND",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(
                    responseCode = "500",
                    description = "INTENAL_SERVER_ERROR",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))

    })
    @GetMapping("/departments/{department-no}")
    @Operation(summary = "학과 상세 조회", description = "학과 번호로 학과의 상세 정보를 조회한다.")
    public ResponseEntity<DepartmentResponseDto> getDepartment(
            @Parameter(description = "학과번호", example = "001")
            @PathVariable("department-no") String departmentNo) {

        Department department = departmentService.getDepartmentByNo(departmentNo)
                .orElseThrow(() -> new UniversityException(ExceptionMessage.DEPARTMENT_NOT_FOUND));

        return ResponseEntity.ok(new DepartmentResponseDto(HttpStatus.OK, department));

    }

}
