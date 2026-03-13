package org.example.week02.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.week02.constant.GenderEnum;
import org.example.week02.dto.StudentAddDTO;
import org.example.week02.dto.StudentUpdateDTO;
import org.example.week02.entity.Student;
import org.example.week02.vo.StudentVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class StudentServiceTest {
    @Resource
    private StudentService studentService;

    @Test
    void getAllStudents() {
        List<StudentVO> allStudents = studentService.getAllStudents();
        allStudents.forEach(studentVO -> log.info("{}", studentVO));
    }

    @Test
    void addStudent() {
        studentService.addStudent(StudentAddDTO.builder()
                .name("mqxu")
                .mobile("12345678901")
                .gender(GenderEnum.MALE)
                .avatar("https://mqxu.top/avatar.jpg")
                .birthday(LocalDate.of(1999, 1, 1))
                .build());
        log.info("添加成功");
        List<StudentVO> allStudents = studentService.getAllStudents();
        allStudents.forEach(studentVO -> log.info("{}", studentVO));
    }

    @Test
    void getStudent() {
        StudentVO studentVO = studentService.getStudent(1001L);
        log.info("{}", studentVO);
    }

    @Test
    void getStudentByName() {
        List<StudentVO> studentVO = studentService.getStudentByName("张");
        studentVO.forEach(studentVO1 -> log.info("{}", studentVO1));
    }

    @Test
    void updateStudent() {
        studentService.updateStudent(1001L, StudentUpdateDTO.builder()
                .name("张三111")
                .mobile("12345678901")

                .build());
        log.info("修改成功");
        StudentVO studentVO = studentService.getStudent(1001L);
        log.info("{}", studentVO);
    }

    @Test
    void deleteStudent() {
        studentService.deleteStudent(1001L);
        log.info("删除成功");
        List<StudentVO> allStudents = studentService.getAllStudents();
        allStudents.forEach(studentVO -> log.info("{}", studentVO));
    }
}